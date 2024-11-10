import comment from "./comment";


function install(Vue) {
    Vue.component(comment.name , comment);
}

export default {
    install,
    comment
}
