export default [
    {
        label: "水果图片",
        to: "",
        children: [
            {
                label: "水果图片添加",
                to: "/admin/shuiguotupianadd",
            },
            {
                label: "水果图片查询",
                to: "/admin/shuiguotupian_faburen",
            },
        ],
    },
    {
        label: "个人中心",
        to: "",
        children: [
            {
                label: "修改个人资料",
                to: "/admin/yonghuupdtself",
            },
            {
                label: "修改密码",
                to: "/admin/mod",
            },
            {
                label: "我的评论",
                to: "/admin/pinglun_pinglunren",
            },
            {
                label: "我的留言",
                to: "/admin/liuyanban_liuyanren",
            },
        ],
    },
];
