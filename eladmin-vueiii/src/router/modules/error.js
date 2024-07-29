export default [
  {
    path: "/403",
    name: "403",
    component: () => import("@/views/error/403.vue")
  },
  {
    path: "/404",
    name: "404",
    component: () => import("@/views/error/404.vue")
  },
  {
    path: "/500",
    name: "500",
    component: () => import("@/views/error/500.vue")
  }
]
