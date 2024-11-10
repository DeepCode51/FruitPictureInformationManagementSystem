import container from "./container";

function install(Vue) {
    Vue.component(container.name , container)
}

export default {
    install,
    container
}
