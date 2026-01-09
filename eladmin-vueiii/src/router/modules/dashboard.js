const Layout = () => import("@/views/layout/Layout.vue");

export default {
  path: "/dashboard",
  name: "dashboard",
  component: Layout,
  meta: {
    title: "Dashboard",
    icon: "Coin",
    url: "/dashboard",
    hidden: false,
    keepAlive: false,
    permission: "dashboard"
  },
  children: [
    {
      path: "datavisualize",
      name: "DataVisualize",
      component: () => import("@/views/dashboard/DataVisualize.vue"),
      meta: {
        title: "数据可视化",
        icon: "Coin",
        url: "datavisualize",
        hidden: false,
        keepAlive: false,
        permission: "datavisualize"
      },
      children: []
    }
  ]
}
