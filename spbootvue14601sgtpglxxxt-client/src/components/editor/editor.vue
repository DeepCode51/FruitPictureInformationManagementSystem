<template>
    <jt-tinymce @input="$emit('input' , $event)" :upload-image="uploadFile" :value="value"></jt-tinymce>
</template>
<style type="text/scss" lang="scss">

</style>
<script>
    import JtTinymce from "@/components/Tinymce/index";
    import api from "@/api";
    import {formatImageSrc} from "@/utils/utils";
    export default {
        name: "e-editor",
        components: {JtTinymce},
        data() {
            return {}
        },
        props:{
            value:String
        },
        watch: {},
        computed: {},
        methods: {
            uploadFile(file , success , error)
            {
                api.attachment.upload(file).then(file=>{
                    success(formatImageSrc(file))
                }).catch(error);
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
