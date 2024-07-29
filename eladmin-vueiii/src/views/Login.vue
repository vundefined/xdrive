<template>
  <div class="login-container">
    <div class="login-form">
      <p class="logo-text">Admin</p>
      <el-form ref="loginFormRef" :model="loginForm" :rules="loginRules" size="large">
        <el-form-item prop="username">
          <el-input v-model="loginForm.username" placeholder="用户名：admin / user">
            <template #prefix>
              <el-icon class="el-input__icon">
                <User/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input type="password" v-model="loginForm.password" placeholder="密码：123456" show-password
                    autocomplete="new-password" @keyup.enter="handleLogin(loginFormRef)">
            <template #prefix>
              <el-icon class="el-input__icon">
                <Lock/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
      </el-form>
      <div class="login-btn">
        <el-button :icon="CircleClose" round @click="resetForm(loginFormRef)" size="large">重置</el-button>
        <el-button :icon="UserFilled" round @click.prevent="handleLogin(loginFormRef)" size="large"
                   type="primary" :loading="loading">登录
        </el-button>
      </div>
    </div>
  </div>
</template>

<script setup>
import {ref, reactive} from "vue";
import {CircleClose, UserFilled, User, Lock} from "@element-plus/icons-vue";
import {useRouter} from "vue-router";
import {ElMessage} from "element-plus";
import MXhr from "@/utils/MXhr";

const router = useRouter();
const loading = ref(false);
const loginFormRef = ref();
const loginRules = reactive({
  username: [{required: true, message: "请输入用户名", trigger: "blur"}],
  password: [{required: true, message: "请输入密码", trigger: "blur"}]
});
const loginForm = reactive({
  username: "dahai",
  password: "123456"
});

function handleLogin(formEl) {
  if (!formEl) return;
  formEl.validate(async valid => {
    if (valid) {
      loading.value = true;
      try {
        MXhr.post("auth/login", loginForm).then((response) => {
          localStorage.setItem("admin-token", response.data.data);
          ElMessage.success("登录成功！");
          router.push({path: "/home/index"});
        });
      } finally {
        loading.value = false;
      }
    }
  });
}

function resetForm(formEl) {
  if (!formEl) return;
  formEl.resetFields();
}
</script>

<style scoped>
.login-container {
  height: 100vh;
  min-width: 550px;
  min-height: 500px;
  background-image: url("../assets/images/welcome02.jpg");
  background-repeat: no-repeat;
  background-size: cover;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}

.login-form {
  width: 420px;
  height: 400px;
  padding: 50px 40px 45px;
  background-color: rgba(255 255 255 / 98%);
  border-radius: 10px;
  box-shadow: 2px 3px 7px rgb(0 0 0 / 20%);
}

.logo-text {
  font-size: 42px;
  font-weight: bold;
  color: #34495e;
  white-space: nowrap;
  text-align: center;
  margin-bottom: 30px;
}

.login-btn {
  display: flex;
  justify-content: space-between;
  margin-top: 40px;
}

.el-button {
  width: 185px;
}
</style>
