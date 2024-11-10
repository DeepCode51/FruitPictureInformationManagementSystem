<template>
    <div class="e-video" style="width: 100%;height: 100%">
        <video-player :playsinline="true" style="width: 100%;height: 100%" :options="playerOption"></video-player>
    </div>
</template>
<style type="text/scss" lang="scss">

</style>
<script>

import {formatImageSrc} from "@/utils/utils";

export default {
    name: "e-video",
    data() {
        return {
            playerOption: {
                muted: false,
                autoplay: true,
                loop: false,
                preload: 'auto',
                aspectRatio: '16:9',
                fluid: true,
                width: '100%',
                height: '100%',
                language: 'zh-CN',
                playbackRate: 1.0,
                playbackRates: [0.7, 1.0, 1.5, 2.0],
                sources: '',
                poster: require('./poster.jpg'),
                controlBar: {
                    timeDivider: true,
                    durationDisplay: true,
                    remainingTimeDisplay: false,
                    fullscreenToggle: true  //全屏按钮
                }
            }
        }
    },
    props:{
        src:String,
        poster:String,
        autoplay:{
            type:Boolean,
            default:true
        },
    },
    watch: {
        autoplay:{
            handler(val){
                this.playerOption.autoplay = val;
            },
            immediate:true
        },
        src:{
            handler(val){
                this.playerOption.sources = formatImageSrc(val)
            },
            immediate:true
        },
        poster:{
            handler(val){
                this.playerOption.poster = val;
            },
            immediate:true
        }
    },
    computed: {},
    methods: {},
    created() {

    },
    mounted() {
    },
    destroyed() {
    }
}
</script>
