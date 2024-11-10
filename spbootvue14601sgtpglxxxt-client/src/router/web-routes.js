export default [
    {
        path: "yonghuadd",
        name: "IndexyonghuAdd",
        component: () => import("@/views/yonghu/webadd"),
        meta: { title: "用户添加" },
    },
    {
        path: "liuyanbanadd",
        name: "IndexliuyanbanAdd",
        component: () => import("@/views/liuyanban/webadd"),
        meta: { title: "留言板添加", authLogin: true, msg: true },
    },
    {
        path: "pinglunadd",
        name: "IndexpinglunAdd",
        component: () => import("@/views/pinglun/webadd"),
        meta: { title: "评论添加", authLogin: true, msg: true },
    },
    {
        path: "shuiguotupian",
        name: "IndexshuiguotupianList",
        component: () => import("@/views/shuiguotupian/index"),
        meta: { title: "水果图片列表" },
    },
    {
        path: "shuiguotupiandetail",
        name: "IndexshuiguotupianDetail",
        props: (route) => ({ id: route.query.id }),
        component: () => import("@/views/shuiguotupian/webdetail"),
        meta: { title: "水果图片详情" },
    },
];
