


const modulesFiles = require.context('./', true, /index\.js$/)  // 批量导入组件

const modules = modulesFiles.keys().reduce((modules, modulePath) => {
    // set './app.js' => 'app'
    const moduleName = modulePath.replace(/^\.\/(.*)\.\w+$/, '$1')
    if(moduleName == 'index')return modules;
    const value = modulesFiles(modulePath)
    modules[moduleName] = value.default
    return modules
}, {})


export default function install(Vue) {
    for (var i in modules){
        var ins = modules[i];
        Vue.use(ins);
    }
}


