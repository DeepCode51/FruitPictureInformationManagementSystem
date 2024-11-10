<template>
    <el-radio-group v-model="select" @change="changeSelect" :disabled="disabled">
        <el-radio v-for="v in list" :label="v.zimu"> {{ v.zimu }} 、 {{ v.title }} </el-radio>
    </el-radio-group>
</template>

<script>

    import {findObject} from "@/utils/utils";

    export default {
        name: "t-radio",
        data () {
            return {
                select:'',
            }
        },
        props:{
            obj:Object,
            paper:String,
            daan:String,
            disabled:{
                type:Boolean,
                default:false
            }
        },
        computed:{
            list(){
                if(!this.paper){
                    return [];
                }
                return eval("("+this.paper+")")
            }
        },
        methods:{
            changeSelect(val){
                if(val){
                    var obj = findObject(this.list , r=> r.zimu == val );
                    this.obj.zimu = obj.zimu;
                    this.obj[this.daan] = obj.title;
                    this.obj.defen = obj.point === undefined ? obj.defen : obj.point;
                }else{
                    this.obj.zimu = '';
                    this.obj[this.daan] = '';
                    this.obj.defen = 0;
                }

            }
        }
    }
</script>

<style scoped>

</style>
