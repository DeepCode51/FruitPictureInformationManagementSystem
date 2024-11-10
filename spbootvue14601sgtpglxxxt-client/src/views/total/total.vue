<template>
    <iframe class="iframe" :src="setting.proxyUrl+'/'+src"></iframe>
</template>

<script>
    var setting = require("@/setting");
    export default {
        name: "total",
        data() {
            return {
                setting,
            };
        },
        props: {
            src: {
                type: String,
                required: true,
            },
        },
        created() {
            console.log(this.$route.query);
            console.log(this.src);
        },
    };
</script>
<style scoped type="text/scss" lang="scss">
    .iframe {
        border: none;
        width: 100%;
        height: 650px;
        overflow: hidden;
        overflow-y: auto;
    }
</style>
