<template>
    <div class="e-paper">

        <div id="TypeFieldabc" v-if="isSelect">
            <div style="border: 1px solid #ededed; border-radius: 5px; padding: 10px; background: #F2F2F2;">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th width="60">&nbsp;</th>
                        <th style="text-align: left">答案</th>
                        <!--<th width="80">跳转序号</th>-->
                        <th style="text-align: left" width="100">得分</th>
                        <th width="60">
                            <el-button size="mini" type="primary" @click="addItem">增加答案</el-button>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for="(v,k) in paperList" :key="v.zimu">
                        <td style="text-align: right" v-text="v.zimu"></td>
                        <td>
                            <el-input size="mini" :value="v.title" @input="updatePaper($event,v,k,'title')"></el-input>
                        </td>
                        <td>
                            <el-input size="mini" :value="v.point" @input="updatePaper($event,v,k,'point')" type="number"></el-input>
                        </td>
                        <td>
                            <el-popconfirm  icon="el-icon-info" iconColor="red" title="确定删除？" @confirm="deleteItem(k,v)" @onConfirm="deleteItem(k,v)">
                            <el-button size="mini" type="danger" slot="reference" icon="el-icon-delete-solid"></el-button>
                            </el-popconfirm>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>

        </div>
    </div>
</template>
<style type="text/scss" lang="scss">

</style>
<script>
    import {extend, isArray} from "@/utils/extend";

    export default {
        name: "e-paper",
        data() {
            return {
                isSelect:true
            }
        },
        props:{
            value:[String],
            vo:Object,
            type:String,
            danxuanti:{
                type:String,
                default:''
            },
            duoxuanti:{
                type:String,
                default:''
            },
            panduanti:{
                type:String,
                default:''
            },
            correctText:{
                type:String,
                default:'正确'
            },
            errorText:{
                type:String,
                default:'错误'
            },
        },
        watch:{
            type( val ){
                if(this.value.length == 0){
                    if(this.panduanti.indexOf(val) != -1)
                    {
                        // 这个是判断类型
                        // 直接帮忙设定为两个
                        if(this.paperList.length != 2 || this.paperList[0].title != this.correctText)
                        {
                            var obj = [
                                {
                                    zimu:'A',
                                    title:this.correctText,
                                    point:0
                                },
                                {
                                    zimu:'B',
                                    title:this.errorText,
                                    point:0
                                }
                            ];
                            this.emitInput(obj);
                        }
                        this.isSelect = true;
                    }else if(this.danxuanti.indexOf(val) == -1 && this.duoxuanti.indexOf(val) == -1)
                    {
                        this.isSelect = false;
                    }else{
                        if(this.paperList.length == 0)
                        {
                            var obj = [
                                {
                                    zimu:'A',
                                    title:'',
                                    point:0
                                },
                                {
                                    zimu:'B',
                                    title:'',
                                    point:0
                                },
                                {
                                    zimu:'C',
                                    title:'',
                                    point:0
                                },
                                {
                                    zimu:'D',
                                    title:'',
                                    point:0
                                }
                            ];
                            this.emitInput(obj);
                        }
                        this.isSelect = true;
                    }
                }
            }
        },
        computed: {
            paperList(){
                var value = this.value;
                try{
                    value = JSON.parse(value);
                }catch (e) {
                    value = [];
                }
                if(!isArray(value)){
                    value = [];
                }
                return value;
            }
        },
        methods: {
            updateZimu( paperList )
            {
                var zimu = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
                var result = paperList ? paperList : extend(true,[],this.paperList);
                for (var i=0;i<result.length;i++)
                {
                    result[i].zimu = zimu.substr(i,1)
                }
                this.emitInput(result);
            },
            updatePaper($event , v , k , key ){
                var result = extend(true,[],this.paperList);
                var obj = {};
                obj[key] = $event;
                var nowObj = extend(true,{} , v , obj);
                result[k] = nowObj;
                this.emitInput(result);
            },
            emitInput(val){
                this.$emit('input' , JSON.stringify(val))
            },
            addItem(){
                var zimu = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';

                var result = extend(true,[],this.paperList);
                var index = result.length;
                var obj = {
                    zimu:zimu.substr(index,1),
                    title:'',
                    point:0
                }
                result.push(obj);
                this.emitInput(result);
            },
            deleteItem(k){
                var result = extend(true,[],this.paperList);
                result.splice(k,1);
                this.updateZimu(result);
            }
        },
        created() {

        },
        mounted() {
        },
        destroyed() {
        }
    }
</script>
