<template>
    <div class="e-spec">
        <el-select v-model="values" multiple
                   filterable
                   allow-create
                   default-first-option
                   placeholder="请设置"></el-select>
    </div>
</template>
<style type="text/scss" lang="scss">

</style>
<script>
    import {empty} from "@/utils/extend";

    export default {
        name: "e-spec",
        data() {
            return {}
        },
        props:{
            value:String
        },
        computed: {
            values:{
                get(){
                    return this.value.split(',').filter(v=>!empty(v))
                },
                set(val){
                    this.$emit('input' , val.join(','))
                }
            }
        }
    }
</script>
