<template>
    <div class="e-video-list">
        <el-button-group v-if="src">
            <el-button size="mini" @click="previewVideo">预览</el-button>
            <el-button size="mini" @click="downloadVideo">下载</el-button>
        </el-button-group>
        <span v-else>
            -
        </span>
        <el-dialog title="预览视频" :visible.sync="visible" destroy-on-close width="30%">
            <e-video :src="src"></e-video>
        </el-dialog>
    </div>
</template>
<style type="text/scss" lang="scss">

</style>
<script>
    import {formatImageSrc} from "@/utils/utils";

    const path = require('path')
    export default {
        name: "e-video-list",
        data() {
            return {
                visible:false
            }
        },
        props:{
            src:{
                type:String,
                required:true
            },
            download:{
                type:Boolean,
                default:true
            }
        },
        watch: {},
        computed: {},
        methods: {
            previewVideo(){
                this.visible = true;
            },
            downloadVideo(){
                var a = document.createElement('a');
                a.href = formatImageSrc(this.src);
                console.log(path.basename(this.src));
                a.download =  path.basename(this.src);
                a.target = '_blank';
                a.click();
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
