import router from "./index";
import store from "../store";

import { MessageBox } from "element-ui";
import NProgress from "nprogress"; // progress bar
import "nprogress/nprogress.css"; // progress bar style
//import { getToken } from './utils/auth' // get token from cookie
import jdk from "@/utils/extend";
const setting = require("@/setting");
NProgress.configure({ showSpinner: false }); // NProgress Configuration

const whiteList = ["/login"]; // no redirect whitelist
var index = 0;

var title = setting.title;
function getPageTitle(pageTitle) {
    if (pageTitle) {
        return `${pageTitle} - ${title}`;
    }
    return `${title}`;
}

router.beforeEach(async (to, from, next) => {
    // start progress bar
    NProgress.start();
    //console.log(jdk.extend( true,{},to ));
    //console.log(location.href);
    // set page title
    var title = to.meta.title;

    document.title = getPageTitle(jdk.isFunction(title) ? title(store.state) : title);

    const hasToken = store.state.user.token;
    const user = store.state.user;

    if (hasToken && !user.session.username) {
        // token session 信息不存在，则更新
        await store.dispatch("user/tokenLogin").catch((err) => {
            MessageBox.alert(err.message);
        });
    }

    // 判断是否需要登录才可访问该链接
    if (to.meta.authLogin) {
        // 需要验证登录，则判断是否有登录

        if (!hasToken || !user.session.username) {
            if (to.meta.msg) {
                await MessageBox.alert("尚未登录，请登录后操作");
            }

            next(`/login?redirect=${to.fullPath}`);
            NProgress.done();
        } else {
            next();
            NProgress.done();
        }
    } else {
        next();
        NProgress.done();
    }
});

router.afterEach(() => {
    // finish progress bar
    NProgress.done();
});
