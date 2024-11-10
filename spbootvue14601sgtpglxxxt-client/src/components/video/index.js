import video from "./video";
import VueVideoPlayer from 'vue-video-player'

import 'video.js/dist/video-js.css'

function install(Vue) {
    if(install.installed)return;

    Vue.component(video.name , video)

    Vue.use(VueVideoPlayer)
}


export default {
    install,
    video,
    videoPlayer:VueVideoPlayer.videoPlayer,

}
