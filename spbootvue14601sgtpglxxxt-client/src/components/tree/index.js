import tree from './tree'

function install(Vue) {
    if(install.installed)return;
    Vue.component(tree.name , tree)
}

export default {
    install,
    tree
}
