<template>
    <el-upload
            v-loading="loading"
            class="upload-file"
            element-loading-text="上传中"
            drag
            action="https://127.0.0.1"
            :before-upload="beforeUpload"
            :multiple="multiple" :show-file-list="false">
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__text success" v-if="value!=''"><i class="el-icon-success"></i>已上传</div>
    </el-upload>
</template>
<style type="text/scss" lang="scss">
.upload-file{

    .success{
        color: #108210!important;
        font-size: 18px!important;
    }
}
</style>
<script>
    import api from "@/api";

    export default {
        name: "e-upload-file",
        data() {
            return {
                loading:false
            }
        },
        props:{
            value:String,
            multiple:{
                type:Boolean,
                default:false
            }
        },
        watch: {},
        computed: {},
        methods: {
            beforeUpload(file){
                this.loading = true;
                api.attachment.upload(file).then(file=>{
                    this.loading = false;
                    this.$emit('input' , file)
                }).catch(err=>{
                    this.loading = false;
                    this.$message.error(err);
                });
                return false;
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
