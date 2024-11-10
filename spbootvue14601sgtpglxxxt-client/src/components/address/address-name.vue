<template>
    <span>
        {{ weizhi.address }}
    </span>
</template>

<script>
    import {empty, isObject} from "@/utils/extend";

    export default {
        name: "e-address-name",
        props:{
            address:[String,Object],
        },
        computed:{
            weizhi(){
                if(empty(this.address))
                {
                    return {
                        address:''
                    };
                }
                if(isObject(this.address))
                {
                    return this.address;
                }
                try{
                    var result = eval("("+this.address+")");
                    return result
                }catch (e) {
                    return {
                        address:''
                    };
                }
            }
        }
    }
</script>

<style scoped>

</style>