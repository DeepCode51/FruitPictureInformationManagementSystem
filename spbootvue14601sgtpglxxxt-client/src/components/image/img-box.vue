<template>
    <div class="img-box" :class="['pb'+pb,isScale?'img-scale':'']">
        <div class="img" :style="{'background-image':'url('+image+')'}"></div>
    </div>
</template>
<style type="text/scss" lang="scss">

</style>
<script>
import {formatImageSrc} from "@/utils/utils";

export default {
    name: "e-img-box",
    data() {
        return {}
    },
    props:{
        pb:{
            type:[String,Number],
            default:100
        },
        src:[String],
        isScale:{
            type:Boolean,
            default:false
        }
    },
    watch: {},
    computed: {
        image(){
            var src = this.src;
            if(src == ''){
                return '';
            }
            var a = src.split(',');
            return formatImageSrc(a[0]);
        }
    },
    methods: {},
    created() {

    },
    mounted() {
    },
    destroyed() {
    }
}
</script>