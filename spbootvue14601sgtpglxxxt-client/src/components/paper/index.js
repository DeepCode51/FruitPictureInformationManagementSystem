import paper from "./paper";

import paperView from './paper-view'
import paperForm from './paper-form'
function install(Vue) {
    Vue.component(paper.name , paper)
    Vue.component(paperView.name , paperView)
    Vue.component(paperForm.name , paperForm)
}

export default {
    install,
    paper,
    paperView
}


