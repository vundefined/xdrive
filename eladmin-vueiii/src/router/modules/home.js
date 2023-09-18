const Layout = () => import("@/views/layout/Layout.vue");

export default {
    path: "/home",
    name: "home",
    component: Layout,
    meta: {
        title: "首页",
        icon: "mnt",
        hidden: false,
        keepAlive: false,
        permission: "home"
    },
    children: [{
        path: "index",
        name: "index",
        component: () => import("@/views/home/index.vue"),
        meta: {
            title: "首页",
            icon: "mnt",
            hidden: false,
            keepAlive: false,
            permission: "index"
        },
        children: []
    }]
}
