<template>
    <div class="MKPlayer" id="MKPlayer" @click.stop.prevent>
        <div class="notification" v-show="message!=''" v-text="message">

        </div>
        <div class="MKPlayer-play-message" :class="{active:openMusicList}">
            <!-- 播放列表 -->
            <div class="MKPlayer-play-disc">
                <div class="mkdisc">
                    <a class="mkloop" style="display: inline-block">
                        <i class="mkicon mkicon-disc"></i>
                    </a>
                </div>
                <div class="mklyric" :style="{transform:scrollTopText}">
                    <ul>
                        <li v-for="(v,k,index) in lyricList" :data-on="index" class="lrc-item" :class="{mkactive:k==lastLyric}" v-text="v"></li>
                    </ul>
                </div>
            </div>
            <div class="MKPlayer-play-list" :class="{mkempty:musicList.length==0}">
                <div class="MKPlayer-play-header">
                    <div class="mkheader">播放列表 <span v-html="musicList.length"></span></div>
                </div>
                <div class="mkempty-text">
                    暂无音乐
                </div>
                <div class="MKPlayer-play-music">
                    <div class="mkmusic-row clearfix" v-for="(v,k) in musicList" :class="{active:v.id == music.id}">
                        <div class="mkmusic-icon">
                            <i class="mkicon mkicon-play-o"></i>
                        </div>
                        <div class="mkmusic-title" @click="playIndex(k)">
                            <div class="mkmusic-hover-icon">
                                <i class="mkicon mkicon-collect" title="收藏" @click.prevent.stop="collect(v)"></i>
                                <i class="mkicon mkicon-download" title="下载" @click.prevent.stop="download(v)"></i>
                                <i class="mkicon mkicon-like" title="喜欢" @click.prevent.stop="like(v)"></i>
                            </div>
                            <div class="mkgeming" v-html="v.name"></div>
                        </div>
                        <div class="mkmusic-name" v-text="v.artist"></div>
                        <div class="mkmusic-album" v-text="v.album"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="MKPlayer-bottom">
            <div class="MKPlayer-bottom-btn">
                <a href="javascript:;">
                    <i class="mkicon mkicon-prev" @click.stop.prevent="prevMusic"></i>
                </a>
                <a href="javascript:;" class="MKPlayer-play" :class="{active:playState}">
                    <i class="mkicon mkicon-play text-active" @click.stop.prevent="playMusic"></i>
                    <i class="mkicon mkicon-pause" @click.stop.prevent="pauseMusic"></i>
                </a>
                <a href="javascript:;">
                    <i class="mkicon mkicon-next" data-func="next" @click.stop.prevent="nextMusic"></i>
                </a>
            </div>
            <div class="MKPlayer-info">
                <a href="javascript:;" :class="'MKPlayer-play-type '+ orderby[playOrderby]" @click="updateOrderby">
                    <i class="mkicon mkicon-list-cycle" data-type="mklist"></i>
                    <i class="mkicon mkicon-random" data-type="mkrandom"></i>
                    <i class="mkicon mkicon-single-cycle" data-type="mksingle"></i>
                </a>
                <a href="javascript:;">
                    <i class="mkicon mkicon-gedan" @click="gedanClick"></i><span style="font-size: 14px">{{musicList.length}}</span>
                </a>
            </div>
            <div class="MKPlayer-volume">
                <div class="MKPlayer-volume-left">
                    <i class="mkicon" :class="{'mkicon-volume':!mkmute ,'mkicon-mute':mkmute }" @click="setMute"></i>
                </div>
                <mk-progress v-model="volume" @changed="onVolumeChange" :lock="false"></mk-progress>
            </div>

            <!--进度条 -->
            <div class="MKPlayer-progress">
                <div class="MKPlayer-music-info clearfix">
                    <span v-text="progressTime" style="float: right"></span>
                    <span v-text="music.name"></span>
                    <span v-text="music.artist"></span>
                </div>
                <mk-progress v-model="musicProsess" @changed="onMusicProsess" :lock="!playState"></mk-progress>
            </div>
        </div>
    </div>
</template>
<style type="text/scss" lang="scss">
@import "./asset/icon/MKPlayer";
</style>
<script>
    export default {
        name: "MKPlayer",
        data() {
            return {}
        },
        watch: {},
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
