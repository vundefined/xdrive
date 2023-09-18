import { defineStore } from "pinia";
import { ref } from "vue";
import HttpConnection from "@/utils/HttpConnection";
import { ElMessage } from "element-plus";
import home from "@/router/modules/home";
import dashboard from "@/router/modules/dashboard";

const modules = import.meta.glob("@/views/*/*.vue");
function handleRouter(routerList) {
    const _accessedRouters = routerList.filter((item) => {
        item.component = modules[`/src/views/${item.component}.vue`];
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

    async function setMenuList() {
        let _asyncRoutes = [];
        await HttpConnection.get("auth/userInfo").then(function(response) {
            userInfo.value = response.data.data.userInfo;
            let _menuInfo = response.data.data.menuInfo;
            if (_menuInfo.length > 0) {
                _asyncRoutes = handleRouter(_menuInfo);
                asyncRoutes.value = [home, dashboard].concat(_asyncRoutes);
            }
        });
        return _asyncRoutes;
    }

    function logout() {
        HttpConnection.post("auth/logout").then(() => {
            localStorage.clear();
            window.location.reload();
            ElMessage({
                type: "success",
                message: "退出登录成功！"
            });
        });
    }

    return {userInfo, asyncRoutes, setMenuList, logout};
});
