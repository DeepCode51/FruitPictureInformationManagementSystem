<template>
    <span>
        <template v-if="value">
            <a :href="setting.proxyUrl + '/'+(value)" :download="filename ? filename : basename(value)">
                下载
            </a>
        </template>
        <template v-else>
            -
        </template>
    </span>
</template>
<style type="text/scss" lang="scss">

</style>
<script>
import setting from '@/setting'
const path = require('path');

export default {
    name: "e-file-list",
    data() {
        return {
            setting,
            path
        }
    },
    props:{
        value:String,
        filename:String
    },
    watch: {},
    computed: {},
    methods: {
        basename:path.basename
    },
    created() {

    },
    mounted() {
    },
    destroyed() {
    }
}
</script>
