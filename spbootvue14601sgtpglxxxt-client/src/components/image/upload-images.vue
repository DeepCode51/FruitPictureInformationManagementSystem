<template>
    <div class="e-upload-images" v-loading="loading">
        <el-upload list-type="picture-card" :show-file-list="false" action="#" multiple :before-upload="beforeUpload">
            <i slot="trigger" class="el-icon-plus"></i>
            <draggable class="el-upload-list el-upload-list--picture-card" element="ul" v-model="fileList" :options="dragOptions" @start="isDragging=true" @end="isDragging=false">
                <li tabindex="0" class="el-upload-list__item is-ready" v-for="img in imagesUrl()" :key="img">
                    <div>
                        <e-img :src="img" alt="" class="el-upload-list__item-thumbnail" />
                        <div class="el-upload-list__item-actions">
                            <span class="el-upload-list__item-preview" @click="Preview(img)"><i class="el-icon-zoom-in"></i></span>
                            <span class="el-upload-list__item-delete" @click="download(img)"><i class="el-icon-download"></i></span>
                            <el-popconfirm title="确定删除该图片？" @confirm="deleteImage(img)" @onConfirm="deleteImage(img)">
                            <span class="el-upload-list__item-delete" slot="reference"><i class="el-icon-delete"></i></span>
                            </el-popconfirm>
                        </div>
                    </div>
                </li>
            </draggable>
        </el-upload>
        <el-dialog :visible.sync="dialogVisible">
            <e-img width="100%" :src="dialogImageUrl" alt=""/>
        </el-dialog>
    </div>
</template>
<style type="text/scss" scoped lang="scss">
    .e-upload-images {
        .el-upload-list{
            margin-left: 10px;
        }
        .el-upload {
            margin-right: 20px;
            margin-bottom: 20px;
            text-align: center;

            img {
                max-width: 100%;
                max-height: 100%;
            }
        }
    }
</style>
<script>
    import {isArray} from "@/utils/extend";
    import api from "@/api";
    import EImg from "@/components/image/img";
    import {formatImageSrc} from "@/utils/utils";
    import draggable from 'vuedraggable'
    const path = require('path')

    export default {
        name: "e-upload-images",
        components: {EImg,draggable},
        data() {
            return {
                dragOptions:{

                },
                isDragging:false,
                dialogVisible:false,
                dialogImageUrl:'',
                loading: false
            }
        },
        props: {
            value: [String, Array]
        },
        computed:{
            fileList:{
                get(){
                    return this.imagesUrl();
                },
                set(val){
                    this.$emit('input' , val.join(','))
                }
            }
        },
        watch: {},
        methods: {
            Preview(file){
                this.dialogImageUrl = file;
                this.dialogVisible = true;
            },
            download(file){
                var a = document.createElement('a');
                a.href = formatImageSrc(file);
                a.setAttribute("download" , path.basename(file));
                a.target = '_blank'
                a.click();
            },
            deleteImage( file ){
                var images = this.imagesUrl().filter( f=>f!=file )
                this.$emit('input' , images.join(','))
            },
            imagesUrl() {
                var images = this.value;
                if (!isArray(images)) {
                    images = images.split(",");
                }
                return images.filter(s => s != '');
            },
            beforeUpload(file) {

                this.loading = true;
                api.attachment.upload(file).then(f => {
                    this.loading = false;
                    var images = this.imagesUrl();
                    images.push(f);
                    this.$emit('input', images.join(','));
                }).catch(err => {
                    this.loading = false;
                    this.$message.error(err);
                })
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
