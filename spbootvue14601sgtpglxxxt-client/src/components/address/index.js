
import address from "@/components/address/address";
import detail from "@/components/address/detail";
import addressName from './address-name'
import BaiduMap from "vue-baidu-map";

function install(Vue)
{
    if(install.installed)return;
    Vue.use(BaiduMap, {
        // ak 是在百度地图开发者平台申请的密钥 详见 http://lbsyun.baidu.com/apiconsole/key */
        ak: 'AoBYL0LlRkcYBMCeay4fgpq7qaIYlKjs'
    })
    Vue.component(address.name , address);
    Vue.component(detail.name , detail);
    Vue.component(addressName.name , addressName);

}

export default {
    install,
    address,
    detail
}
