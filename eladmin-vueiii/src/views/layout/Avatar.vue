<template>
  <el-dropdown trigger="click">
    <div class="avatar">
      <!--<img class="aimg" :src="fileFilter(authStore.userInfo.avatar)" alt="avatar"/>-->
      <img class="aimg" src="http://192.168.1.104/avator.jpg" alt="avatar"/>
      <span class="username">{{ authStore.userInfo.username }}</span>
    </div>
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item @click="openDialog('infoRef')">个人信息</el-dropdown-item>
        <el-dropdown-item @click="openDialog('passwordRef')">更改密码</el-dropdown-item>
        <el-dropdown-item @click="logout" divided>退出</el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
  <InfoDialog ref="infoRef" :user-info="authStore.userInfo"></InfoDialog>
  <PasswordDialog ref="passwordRef"></PasswordDialog>
</template>

<script setup>
import {ref} from "vue";
import InfoDialog from "@/components/InfoDialog.vue";
import PasswordDialog from "@/components/PasswordDialog.vue";
import {ElMessageBox} from "element-plus";
import {useAuthStore} from "@/stores/auth";
import useUpload from "@/hooks/useUpload";

const authStore = useAuthStore();
const {fileFilter} = useUpload();
const infoRef = ref(null);
const passwordRef = ref(null);

function logout() {
  ElMessageBox.confirm("您是否确认退出登录?", "温馨提示", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning"
  }).then(() => {
    authStore.logout();
  });
}

function openDialog(refName) {
  if (refName === "infoRef") return infoRef.value?.openDialog();
  passwordRef.value.openDialog();
}
</script>

<style scoped>
.username {
  margin: 0 10px 0 0;
  font-size: 15px;
  color: rgb(0 0 0 / 75%);
}

.avatar {
  cursor: pointer;
  display: flex;
  flex-direction: row;
  align-items: center;
}

.aimg {
  width: 40px;
  height: 40px;
  margin: 0px 10px 0px 0px;
}
</style>
