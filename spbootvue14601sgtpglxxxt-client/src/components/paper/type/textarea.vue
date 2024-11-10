<template>
    <el-input v-model="obj.daan" type="textarea" />
</template>

<script>
    export default {
        name: "t-textarea",
        props:{
            obj:Object
        },
        created(){
            var obj = this.obj;
            this.$set(obj , 'defen' , -1);
            this.$set(obj , 'zimu' , '');
            this.$set(obj , 'daan' , '');
        }

    }
</script>

<style scoped>

</style>