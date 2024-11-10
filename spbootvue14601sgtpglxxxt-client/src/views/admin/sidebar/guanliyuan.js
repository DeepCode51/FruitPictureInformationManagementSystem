export default [
    {
        label: "账号管理",
        to: "",
        children: [
            {
                label: "管理员账号管理",
                to: "/admin/admins",
            },
            {
                label: "管理员账号添加",
                to: "/admin/adminsadd",
            },
            {
                label: "注册用户管理",
                to: "/admin/yonghu",
            },
            {
                label: "添加新用户",
                to: "/admin/yonghuadd",
            },
            {
                label: "密码修改",
                to: "/admin/mod",
            },
        ],
    },
    {
        label: "水果图片管理",
        to: "",
        children: [
            {
                label: "水果图片添加",
                to: "/admin/shuiguotupianadd",
            },
            {
                label: "水果图片查询",
                to: "/admin/shuiguotupian",
            },
            {
                label: "评论管理",
                to: "/admin/pinglun",
            },
        ],
    },
    {
        label: "系统管理",
        to: "",
        children: [
            {
                label: "轮播图添加",
                to: "/admin/lunbotuadd",
            },
            {
                label: "轮播图查询",
                to: "/admin/lunbotu",
            },
            {
                label: "留言管理",
                to: "/admin/liuyanban",
            },
        ],
    },
];
