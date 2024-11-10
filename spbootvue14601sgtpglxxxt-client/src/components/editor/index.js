import editor from "@/components/editor/editor";


function install(Vue) {
    Vue.component(editor.name , editor)
}


export default {
    install,
    editor
}



