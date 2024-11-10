<!--<template>
    <div class="e-dataset-table">
        <slot :dataSets="lists"></slot>
    </div>
</template>
<style type="type/scss" lang="scss">

</style>-->
<script>

import api from "@/api";
export default {
    name: "e-dataset-table",
    data() {
        return {
            lists:[]
        }
    },
    props:{
        table:{
            type:String,
            required:true
        },
        where:{
            type:Object,
        },
        limit:{
            type:[String,Number]
        },
        order:{
            type:[String]
        },
        field:{
            type:[String]
        },
        var:{
            type:String,
            required: true
        }
    },
    render(createElement) {
        var result = {};
        result[this.var] = this.lists;
        return createElement('div' , [
            this.$scopedSlots.default(result)
        ])
    },
    created() {
        this.$post(api.search.all , {
            where:JSON.stringify(this.where),
            table:this.table,
            limit:this.limit,
            order:this.order,
            field:this.field
        }).then(res=>{
            if(res.code == api.code.OK){
                this.lists = res.data;
            }
        });
    },
    mounted() {
    },
    destroyed() {
    }
}
</script>
