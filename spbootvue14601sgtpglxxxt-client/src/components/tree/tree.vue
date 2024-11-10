<template>
    <div class="e-tree">
        <tree-select
                :options="list"
                placeholder="请选择分类..."
                :value="value"
                @input="$emit('input' , $event)"
        />
    </div>
</template>
<style type="text/scss" lang="scss">

</style>
<script>
    import api from "@/api";
    import {each, empty} from "@/utils/extend";
    import TreeSelect from '@riophae/vue-treeselect'
    import '@riophae/vue-treeselect/dist/vue-treeselect.css'

    export default {
        name: "e-tree",
        data() {
            return {
                list:[]
            }
        },
        components:{
            TreeSelect
        },
        props:{
            value:[String,Number],
            module:{
                type:String,
                required:true
            },
            isParent:{
                type:Boolean,
                default:false
            },
            parentName:{
                type:String,
                default:'顶级分类'
            },
            label:{
                type:String,
                required: true
            },
            pid:{
                type:String,
                required: true
            }
        },
        watch: {},
        computed: {},
        methods: {
            loadSelectList(){
                this.$post(api.search.all,{
                    table:this.module,
                    order:'id asc'
                }).then(res=>{
                    if(res.code == api.code.OK)
                    {
                        var result = this.loadChildren(res.data , 0);
                        if(this.isParent){
                            result.unshift({
                                id:'0',
                                label:this.parentName
                            });
                        }
                        this.list = result
                    }
                });
            },
            loadChildren( list , parentid ){
                var result = this.getChild(list , parentid);
                //var length = list.length;
                each(result , (i,obj)=>{
                    var child = this.loadChildren(list , obj.id);
                    if(!empty(child)){
                        obj.isDefaultExpanded = true;
                        obj.children = child;
                    }
                })
                return result;
            },
            getChild(list , parentid){
                var result = [];
                //var length = list.length;
                each(list , (i,obj)=>{
                    if(obj[this.pid] == parentid)
                    {
                        obj.label = obj[this.label];
                        result.push(obj);
                    }
                })
                return result;
            }
        },
        created() {
            this.loadSelectList();
        },
        mounted() {
        },
        destroyed() {
        }
    }
</script>
