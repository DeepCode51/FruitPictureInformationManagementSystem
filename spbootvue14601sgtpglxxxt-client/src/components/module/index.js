import { each} from "@/utils/extend";
const modulesFiles = require.context('./', true, /\.vue$/)  // 批量导入组件

const modules = modulesFiles.keys().reduce((modules, modulePath) => {
    // set './app.js' => 'app'
    const moduleName = modulePath.replace(/^\.\/(.*)\.\w+$/, '$1')
    if(moduleName == 'index')return modules;
    const value = modulesFiles(modulePath)
    modules[moduleName] = value.default
    return modules
}, {})


function install(Vue) {
    if(install.installed)return;
    each(modules , (i,o)=>{
        Vue.component(o.name , o);
    })
}

export default install;
