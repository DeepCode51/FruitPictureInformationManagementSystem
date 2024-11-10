<template>
    <fieldset class="images_box">
        <div class="imagesList" v-viewer>
            <div class="uploadImage" v-for="(img,k) in images" :key="k">
                <el-image :src="$formatImageSrc(img)" fit="cover" style="width: 100px;height: 100px">
                </el-image>
            </div>
        </div>
    </fieldset>
</template>
<style type="text/scss" lang="scss">
    .images_box{
        border: 1px solid #DEDEDE;
    }
    .imagesList{
        .uploadImage{
            display: inline-block;
            width: 120px;
            height: 120px;
            padding: 10px;
            border: 1px solid #DEDEDE;
            text-align: center;
            margin-right: 10px;
            margin-bottom: 10px;
        }
    }
</style>
<script>
    import {isString} from "@/utils/extend";

    export default {
        name: "e-images",
        data() {
            return {}
        },
        props:{
            src:{
                type:[String,Array]
            },
            type:String
        },
        watch: {},
        computed: {
            images(){
                var src = this.src;
                if(isString(src)){
                    src = src.split(",").filter(s=>s!='');
                }
                if(!src)return[];
                src = src.map(s=>this.$formatImageSrc(s));

                return src;
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
