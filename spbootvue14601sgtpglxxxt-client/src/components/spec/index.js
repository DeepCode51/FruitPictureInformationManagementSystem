import spec from "./spec";

function install(Vue) {
    if(install.installed)return;

    Vue.component(spec.name , spec)

}

export default {
    install,
    spec
}
