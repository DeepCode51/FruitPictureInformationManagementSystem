<template>
    <div class="e-module-info">
        <slot :data="data"></slot>
    </div>
</template>
<style type="type/scss" lang="scss">

</style>
<script>
import api from '@/api'

export default {
    name: "e-module-info",
    data() {
        return {
            data:{}
        }
    },
    props:{
        module:String,
        order:String,
        where:Object,
        field:String
    },
    watch: {
        module(){
            this.loadData();
        },
        order(){
            this.loadData();
        },
        where(){
            this.loadData();
        },
        field(){
            this.loadData();
        }
    },
    computed: {},
    methods: {
        loadData(){
            var param = {};
            if(this.where){
                param.where = JSON.stringify(this.where);
            }
            if(this.order){
                param.order = this.order;
            }
            if(this.field){
                param.field = this.field;
            }
            param.limit = 1;
            param.table = this.module;

            this.$post(api.search.all , param).then(res=>{
                if(res.code == 0 && res.data && res.data[0]){
                    this.data = res.data[0]
                }
            });
        }
    },
    created() {
        if(this.module){
            this.loadData();
        }
    },
    mounted() {
    },
    destroyed() {
    }
}
</script>