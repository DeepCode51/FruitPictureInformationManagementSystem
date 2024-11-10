import audio from "./audio";



function install( Vue ) {
    if(install.installed)return;

    Vue.component(audio.name , audio);
}


export default {
    install,
    audio
}
