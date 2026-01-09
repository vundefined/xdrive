import {defineStore} from "pinia";
import {ref} from "vue";
import MXhr from "@/utils/MXhr";
import {ElMessage} from "element-plus";

const modules = import.meta.glob("@/views/*/*.vue");
const a404 = () => import("@/views/error/404.vue");

function handleRouter(routerList) {
  const _accessedRouters = routerList.filter((item) => {
    let _component = modules[`/src/views/${item.component}.vue`];
    if (_component == undefined) {
      item.component = a404;
    } else {
      item.component = _component;
    }
    if (item.children && item.children.length > 0) {
      item.children = handleRouter(item.children);
      return (item.children && item.children.length > 0);
    }
    return true;
  });
  return _accessedRouters;
}

export const useAuthStore = defineStore("auth", () => {
  let userInfo = ref({
    avatar: "wx_avatar/1684307798041_325.jpeg",
    deleted: false,
    email: "",
    id: 0,
    mobile: "",
    password: "",
    roleIds: [],
    sort: 0,
    username: "geeker"
  });
  let asyncRoutes = ref([]);

  async function getMenuList() {
    let response = await MXhr.get("/admin/auth/userInfo");
    userInfo.value = response.userInfo;
    if (response.menuInfo.length > 0) {
      asyncRoutes.value = handleRouter(response.menuInfo);
    }
  }

  function logout() {
    MXhr.post("/admin/auth/logout").then(() => {
      localStorage.clear();
      window.location.reload();
      ElMessage({
        type: "success",
        message: "退出登录成功！"
      });
    });
  }

  return {userInfo, asyncRoutes, getMenuList, logout};
});
