/**
 * Created by JnToo on 2017/8/1.
 */

import http from "./http";

function plugin(Vue) {
    if (plugin.installed) {
        return;
    }
    plugin.installed = true;
    Vue.https = http;
    Vue.prototype.$get = http.get;
    Vue.prototype.$post = http.post;
    Vue.prototype.$ajax = http.http;
}

if (typeof window !== "undefined" && window.Vue) {
    window.Vue.use(plugin);
}

export default plugin;
