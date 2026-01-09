<template>
  <div class="login-container">
    <div></div>
    <div class="login-form">
      <div class="login-title"><img src="@/assets/images/login/dlTitle.svg" alt=""><span>能源集中管理系统</span></div>
      <div class="contentb">
        <div class="content">
          <img class="topsvg" src="@/assets/images/login/top.svg" alt="">
          <div class="logo-text">
            <span class="aa"></span>
            <span class="bb">用户登陆 USER LOGIN</span>
          </div>
          <el-form ref="loginFormRef" :model="loginForm" :rules="loginRules" size="large">
            <el-form-item prop="username">
              <el-input v-model="loginForm.username" clearable placeholder="用户名">
                <template #prefix>
                  <el-icon class="el-input__icon">
                    <User class="icon-cla"/>
                  </el-icon>
                </template>
              </el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input type="password" v-model="loginForm.password" placeholder="密码" show-password
                        autocomplete="new-password" clearable @keyup.enter="handleLogin(loginFormRef)">
                <template #prefix>
                  <el-icon class="el-input__icon">
                    <Lock class="icon-cla"/>
                  </el-icon>
                </template>
              </el-input>
            </el-form-item>
            <el-form-item prop="captcha">
              <div class="captcha-box">
                <el-input class="input3" v-model="loginForm.captcha" placeholder="验证码" clearable>
                  <template #prefix>
                    <el-icon class="el-input__icon">
                      <Lock class="icon-cla"/>
                    </el-icon>
                  </template>
                </el-input>
                <!--<img src="@/assets/images/login/captcha.jpeg" alt="">-->
                <img :src="captchaImg" alt="">
              </div>
            </el-form-item>
          </el-form>
          <div class="login-btn">
            <el-button :icon="CircleClose" @click="resetForm(loginFormRef)" size="large">重置</el-button>
            <el-button :icon="UserFilled" @click.prevent="handleLogin(loginFormRef)" size="large" type="primary"
                       :loading="loading">登录
            </el-button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {ref, reactive, onMounted} from "vue";
import {CircleClose, UserFilled, User, Lock} from "@element-plus/icons-vue";
import {useRouter} from "vue-router";
import {ElMessage} from "element-plus";
import MXhr from "@/utils/MXhr";
import useAssets from "@/hooks/useAssets";

const {getImageUrl} = useAssets();

const router = useRouter();
const loading = ref(false);
const loginFormRef = ref();
let captchaImg = ref(null); // @/assets/images/login/captcha.jpeg
const loginRules = reactive({
  username: [{required: true, message: "请输入用户名", trigger: "blur"}],
  password: [{required: true, message: "请输入密码", trigger: "blur"}]
});
const loginForm = reactive({
  username: "admin",
  password: "1234",
  captcha: "",
});

onMounted(() => {
  captchaImg.value = getImageUrl('login/captcha.jpeg');
  // getCaptchaImg();
});

function handleLogin(formEl) {
  if (!formEl) return;
  formEl.validate(async valid => {
    if (valid) {
      loading.value = true;
      try {
        MXhr.post("/admin/auth/login", loginForm).then((response) => {
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

function getCaptchaImg() {
  fetch("api/captcha", {
    method: 'GET',
    headers: {
      "Content-Type": "application/json;charset=UTF-8",
    }
  }).then((response) => {
    if (response.ok) {
      return response.blob()
    }
    throw new Error('出现错误');
  }).then((data) => {
    let _captchaImg = window.URL.createObjectURL(data);
    captchaImg.value = _captchaImg;
    loginForm.username = "admin";
    loginForm.password = "test123456";
    getCaptchaCode();
  });
}

function getCaptchaCode() {
  fetch("api/devel/captcha", {
    method: 'GET',
    headers: {
      "Content-Type": "application/json;charset=UTF-8",
    }
  }).then((response) => {
    if (response.ok) {
      return response.text()
    }
    throw new Error('出现错误');
  }).then((data) => {
    loginForm.captcha = data;
  });
}
</script>

<style scoped lang="scss">
.login-container {
  height: 100vh;
  min-width: 550px;
  min-height: 500px;
  background-image: url("../assets/images/login/dl.jpg");
  background-repeat: no-repeat;
  background-size: cover;

  display: grid;
  grid-template-columns: 50% 50%;
}

.login-form {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  .content {
    width: 450px;
    position: relative;
    background: url("../assets/images/login/dl_kuang.svg") center center / 100% 100% no-repeat;
    padding: 50px 36px;
    border-radius: 4px;

    .topsvg {
      position: absolute;
      top: 0px;
      left: 0px;
    }
  }

  .contentb {
    width: 450px;
    background-image: url("../assets/images/login/qipao.svg");
  }
}

.login-title {
  width: 450px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  margin: 0px 0px 20px 0px;

  img {
    width: 50px;
    height: 50px;
    margin: 0px 20px 0px 0px;
  }

  span {
    font-size: 38px;
    color: rgb(3, 180, 245);
  }
}

.logo-text {
  margin: 0px 0px 40px 0px;

  .aa {
    display: inline-block;
    width: 4px;
    height: 20px;
    background-color: rgb(3, 180, 245);
    margin: 0px 12px 0px 0px;
  }

  .bb {
    font-size: 26px;
    color: rgb(3, 180, 245);
  }
}

.login-btn {
  display: flex;
  justify-content: space-between;
  margin-top: 40px;
}

.icon-cla {
  color: rgb(3, 180, 245);
}

.captcha-box {
  display: flex;
  flex-direction: row;

  img {
    width: 90px;
    height: 40px;
  }
}

.input3 {
  width: 288px;
}

.el-button {
  width: 185px;
}

:deep(.el-input__wrapper) {
  background-color: rgba(3, 180, 245, 0.2);
  box-shadow: 0 0 0 0px var(--el-input-border-color, var(--el-border-color)) inset;
}

:deep(.el-input__inner) {
  color: #ffffff;
}
</style>
