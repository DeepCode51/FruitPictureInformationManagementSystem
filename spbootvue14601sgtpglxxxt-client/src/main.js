import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import ElementUI from "element-ui";
import UtilsAjax from "@/utils/ajax";
import Components from "./components";
import "./styles.scss";
import "./assets/style.scss";
import "./router/permission";
import "font-awesome/css/font-awesome.css";

Vue.use(UtilsAjax);
Vue.use(ElementUI);
Vue.use(Components);

Vue.config.productionTip = false;
Vue.config.devtools = process.env.NODE_ENV !== "production";
new Vue({
    router,
    store,
    render: (h) => h(App),
}).$mount("#app");
