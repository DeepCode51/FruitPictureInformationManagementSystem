import http from "@/utils/ajax/http"
import api from '@/api'
import {MessageBox} from "element-ui"
import {formatImageSrc} from "@/utils/utils"
import Viewer from 'v-viewer'
import 'viewerjs/dist/viewer.css'
import router from "@/router"
import store from '@/store'
import {isString} from "@/utils/extend"

function install(Vue) {
    Vue.prototype.$collect = function (module , ziduan , id) {
        return new Promise((resolve, reject) => {
            http.post(api.collect , {
                biao:module,
                ziduan:ziduan,
                id
            }).then(res=>{
                if(res.code == api.code.OK)
                {
                    MessageBox.alert('收藏成功');
                    resolve();
                }else{
                    MessageBox.alert(res.msg);
                    reject(res.msg);
                }
            }).catch(err=>{
                MessageBox.alert(err.message);
                reject(err.message);
            });
        });
    }

    Vue.mixin({
        methods:{
            $print(){
                var print = this.$refs.print;

                var app = document.querySelector("#app");
                // 根节点
                var html = print.cloneNode(true);
                var div = document.createElement('div');
                div.appendChild(html);
                document.body.append(div);
                app.style.display = 'none';
                window.print();
                app.style.display = 'block';
                document.body.removeChild(div);
            }
        }
    });

    Vue.prototype.$substr = function (str , len , append='...'){
        var content = str.replace(/<[^>]+>/g, "").replace(/&nbsp;/i,' ');
        if(content.length < len){
            return content;
        }
        return content.substr(0, len)+append;
    }

    Vue.prototype.$formatImageSrc = formatImageSrc;

    Vue.prototype.$goto = function (url , replace = false) {

        if(isString(url) && (url == '#' || url.indexOf('javascript:')!=-1))return;

        if(isString(url) && url.match(/^https?:\/\//ig) ){
            if(this.replace){
                location.replace( url );
            }else{
                location.href = url;
            }
            return;
        }

        if(replace){
            router.replace(url);
        }else{
            router.push(url);
        }
    }

    Vue.mixin({
        computed:{
            $session(){
                return store.state.user.session
            }
        }
    });

    Vue.use(Viewer)
    Viewer.setDefaults({
        Options: { 'inline': true, 'button': true, 'navbar': true, 'title': true, 'toolbar': true, 'tooltip': true, 'movable': true, 'zoomable': true, 'rotatable': true, 'scalable': true, 'transition': true, 'fullscreen': true, 'keyboard': true, 'url': 'data-source' }
    })


}

export default install


