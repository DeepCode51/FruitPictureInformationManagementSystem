import store from "@/store";
import WebRoutes from "./web-routes";

import AdminRoutes from "./admin-routes";

const routes = [
    {
        path: "/",
        name: "Home",
        component: () => import("@/views/home"),
        redirect: "/index",
        meta: { title: "" },
        children: [
            {
                path: "index",
                name: "Index",
                component: () => import("@/views/index"),
                meta: { title: "首页" },
            },
            ...WebRoutes,
        ],
    },
    {
        path: "/login",
        name: "Login",
        component: () => import("@/views/login"),
        meta: { title: "系统登录" },
    },
    {
        path: "/admin",
        name: "Admin",
        component: () => import("@/views/admin"),
        redirect: "/admin/sy",
        meta: { title: "后台管理", authLogin: true },
        children: [
            {
                path: "sy",
                name: "AdminSy",
                component: () => import("@/views/admin/sy"),
                meta: { authLogin: true, title: "欢迎页", affix: true },
            },
            {
                path: "mod",
                name: "AdminMod",
                component: () => import("@/views/admin/mod"),
                meta: { authLogin: true, title: "修改密码" },
            },
            {
                path: "total",
                name: "AdminTotal",
                component: () => import("@/views/total/total"),
                meta: { authLogin: true, title: "统计" },
                props: (route) => ({ src: route.query.src }),
            },
            ...AdminRoutes,
        ],
    },
];

export default routes;
