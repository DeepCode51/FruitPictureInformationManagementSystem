import uploadFile from './upload-file'
import FileList from './file-list'
function install(Vue) {
    if(Vue.installed)return;
    Vue.component(uploadFile.name , uploadFile)
    Vue.component(FileList.name , FileList)
}


export default {
    install,
    uploadFile,
    FileList
}
