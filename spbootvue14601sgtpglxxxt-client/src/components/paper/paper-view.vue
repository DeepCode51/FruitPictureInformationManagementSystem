<template>
    <div class="e-paper-view">
        <p v-for="v in paperList" :key="v.zimu">
            {{v.zimu}}：{{v.title}} 得分 {{v.point}}
        </p>
    </div>
</template>
<style type="text/scss" lang="scss">

</style>
<script>
    import {isArray} from "@/utils/extend";

    export default {
        name: "e-paper-view",
        data() {
            return {

            }
        },
        props:{
            map:String
        },
        watch:{
        },
        computed: {
            paperList(){
                var value = this.map;
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
        },
        created() {

        },
        mounted() {
        },
        destroyed() {
        }
    }
</script>
