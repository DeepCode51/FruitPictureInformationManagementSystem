<template>
    <span class="e-audio">
        <a :href="formatImageSrc(src)" download target="_blank">下载</a>
        <span class="mkicon" @click="playAudio" :class="{'mkicon-play':!playState,'mkicon-pause':playState}"></span>
        <span>{{currTime}}</span>
        /
        <span>{{endTime}}</span>
        <audio :src="formatImageSrc(src)" @canplay="canplay" @timeupdate="updateTime" preload="auto" ref="audio" @play="play" @pause="pause" @ended="pause" @error="pause"></audio>
    </span>
</template>
<style type="text/scss" lang="scss">
.e-audio{
    .mkicon{
        font-size: 16px;
        color: #888888;
    }
}
</style>

<script>
    import './asset/icon/MKPlayer.scss'
    import {formatImageSrc} from "@/utils/utils";

    function formatTime(time) {
        var hour, minute, second;
        hour = (parseInt(time / 3600, 10));
        if (hour <10) hour = '0' + hour;

        minute = (parseInt((time % 3600) / 60, 10));
        if (minute < 10) minute = '0' + minute;

        second = (parseInt(time % 60, 10));
        if (second < 10) second = '0' + second;

        if (hour > 0) {
            return hour + ":" + minute + ":" + second;
        } else {
            return minute + ":" + second;
        }
    }

    export default {
        name: "e-audio",
        data() {
            return {
                playState:false,
                currentTime:0,
                duration:0,
            }
        },
        props:{
            src:String
        },
        watch: {},
        computed: {
            currTime(){
                return formatTime(this.currentTime)
            },
            endTime(){
                return formatTime(this.duration)
            }
        },
        methods: {
            formatImageSrc,
            canplay(){
                this.updateTime()
            },
            updateTime(){
                this.duration = this.$refs.audio.duration
                this.currentTime = this.$refs.audio.currentTime
            },
            playAudio(){
                if(this.playState){
                    this.$refs.audio.pause();
                    this.playState = false;
                }else{
                    this.$refs.audio.play();
                    this.playState = true;
                }
            },
            play(){
                this.playState = true;
            },
            pause(){
                this.playState = false;
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
