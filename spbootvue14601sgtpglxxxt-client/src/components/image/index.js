import img from "@/components/image/img";
import uploadImage from '@/components/image/upload-image'
import uploadImages from '@/components/image/upload-images'
import images from "@/components/image/images";
import imgBox from './img-box'
function install( Vue ) {
    if(install.installed)return;
    Vue.component(img.name , img)
    Vue.component(uploadImage.name , uploadImage)
    Vue.component(uploadImages.name , uploadImages)
    Vue.component(images.name , images)
    Vue.component(imgBox.name , imgBox)
}

export default {
    install,
    img,
    uploadImage,
    uploadImages
}
