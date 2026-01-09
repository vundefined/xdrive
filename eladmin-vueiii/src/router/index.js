import {createRouter, createWebHashHistory} from 'vue-router'
import error from "@/router/modules/error";
import home from "@/router/modules/home";
import dashboard from "@/router/modules/dashboard";
import largescreen from "@/router/modules/largescreen";
import NProgress from "nprogress";
import "nprogress/nprogress.css";
import {useAuthStore} from "@/stores/auth";
import { cloneDeep } from 'lodash-es';

NProgress.configure({
  easing: "ease", // 动画方式
  speed: 500, // 递增进度条的速度
  showSpinner: true, // 是否显示加载ico
  trickleSpeed: 200, // 自动递增间隔
  minimum: 0.3 // 初始化时的最小百分比
});

export const HOME_URL = "/home/index"
export const whiteList = ["/403", "/404", "/500", "/layout", "/login", "/loginb", "/largescreen/board", "/largescreen/energy"]

const constantRoutes = [
  {
    path: "/",
    name: "rootdirectory",
    redirect: "/home/index"
  },
  {
    path: "/login",
    name: "login",
    component: () => import("@/views/Login.vue")
  },
  {
    path: "/loginb",
    name: "loginb",
    component: () => import("@/views/LoginB.vue")
  },
  largescreen,
  home,
  dashboard,
  ...error
];

const router = createRouter({
  history: createWebHashHistory(), // history 模式则使用 createWebHistory()
  routes: constantRoutes,
  strict: false,
  // 切换页面，滚动到最顶部
  scrollBehavior: () => ({left: 0, top: 0})
})

router.beforeEach(async (to, from) => {
  NProgress.start();
  let isAuthenticated = localStorage.getItem("admin-token");
  const authStore = useAuthStore();
  console.log('from->', from.path, 'to->', to.path, to.name);
  if (whiteList.includes(to.path)) {
    return true;
  }
  if (isAuthenticated) {
    if (authStore.asyncRoutes.length == 0) {
      await authStore.getMenuList();
      cloneDeep(authStore.asyncRoutes).forEach((item) => {
        router.addRoute(item);
      });
      return { ...to, replace: true };  // return {path: to.path}
    }
    return true;
  } else {
    return {path: "/login"}
  }
  if (to.name === undefined) {
    return {path: "/404"}
  }
  NProgress.done();
});

router.afterEach(() => {
  NProgress.done();
});

router.onError((error) => {
  console.log(error)
})

export default router
