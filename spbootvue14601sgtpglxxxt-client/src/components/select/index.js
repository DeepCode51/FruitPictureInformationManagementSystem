import selectList from './select-list'
import selectView from './select-view'
import selectOption from './select-option'

function install(Vue) {
    if(install.installed)return;
    Vue.component(selectList.name , selectList)
    Vue.component(selectView.name , selectView)
    Vue.component(selectOption.name , selectOption)
}

export default {
    install,
    selectView,
    selectList,
    selectOption
}
