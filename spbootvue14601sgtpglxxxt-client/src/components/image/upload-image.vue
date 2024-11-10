<template>
    <div v-loading="loading" element-loading-text="上传中" class="avatar-uploader">
        <el-upload
                action="https://127.0.0.1"
                drag
                :show-file-list="false"
                :before-upload="beforeAvatarUpload">
            <e-img v-if="value" :src="value" class="avatar"/>
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
        <div contentEditable="true" ref="divimg" @keydown="hookKey" @keyup="hookKey" @paste.prevent.stop="Paste">点我按Ctrl+V粘贴</div>
    </div>
</template>
<style type="text/scss" lang="scss">
    $width:150px;
    .avatar-uploader{
        width: $width;
    }
    .avatar-uploader .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
        .el-upload-dragger{
            width: auto;
            height: auto;
        }
    }
    .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
    }
    .avatar-uploader-icon {
        font-size: 38px;
        color: #8c939d;
        width: $width;
        height: $width;
        line-height: $width;
        text-align: center;
    }
    .avatar {
        width: $width;
        height: $width;
        display: block;
    }
</style>
<script>
    import api from "@/api";
    import EImg from "@/components/image/img";

    export default {
        name: "e-upload-image",
        components: {EImg},
        data() {
            return {
                loading:false,
                //imageUrl:''
            }
        },
        props: {
            value:String
        },
        computed: {},
        methods: {

            beforeAvatarUpload(file) {
                this.loading = true;
                api.attachment.upload(file).then(file=>{
                    this.loading = false;
                    this.$emit('input' , file);
                }).catch(err=>{
                    this.loading = false;
                    this.$message.error(err);
                })
                return false;
            },
            hookKey(e){
                if(!e.ctrlKey){
                    e.preventDefault();
                }
            },
            Paste(e){

                var items = this.getPasteImage(e);
                if(items){
                    for (var i=0;i<items.length;i++)
                    {
                        var file = items[i];
                        if (file.getAsFile) file = file.getAsFile();
                        if (file && file.type && /^image\//.test(file.type) && file.size > 0) {
                            this.beforeAvatarUpload(file);
                        }
                    }
                }
            },
            getPasteImage(e) {
                var ret = false;
                if (e.clipboardData && e.clipboardData.items) {
                    for (var i = 0; i < e.clipboardData.items.length; i++) {
                        if (/^image\//.test(e.clipboardData.items[i].type)) {
                            ret = true;
                            break;
                        }
                    }
                }
                return ret ? e.clipboardData.items : null;
            }
        },
        created() {

        },
        mounted() {

        },
        beforeDestroy() {

        }

    }
</script>
