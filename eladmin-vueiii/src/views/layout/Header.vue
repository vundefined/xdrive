<template>
  <div class="header">
    <div class="header-lf">
      <el-icon class="collapse-icon" @click="globalStore.setCollapse()">
        <component :is="globalStore.isCollapse ? Expand : Fold"></component>
      </el-icon>
      <el-breadcrumb :separator-icon="ArrowRight">
        <transition-group name="breadcrumb">
          <el-breadcrumb-item :to="{ path: HOME_URL }" key="/home">首页</el-breadcrumb-item>
          <el-breadcrumb-item v-for="item in matched" :key="item.path" :to="{ path: item.path }">
            {{ item.meta.title }}
          </el-breadcrumb-item>
        </transition-group>
      </el-breadcrumb>
    </div>
    <div class="header-ri">
      <img src="@/assets/images/largescreen.svg" alt="largescreen" class="largescreen" @click="toLargeScreen"/>
      <el-icon style="margin-right: 14px;" @click="onChangeTheme"><Switch /></el-icon>
      <el-select v-model="globalStore.tcI18n" style="width: 100px;margin: 0px 20px 0px 0px">
        <el-option value="zh-cn" label="中文"></el-option>
        <el-option value="en" label="English">English</el-option>
      </el-select>
      <Avatar></Avatar>
    </div>
  </div>
</template>

<script setup>
import Avatar from "./Avatar.vue";
import {computed} from "vue";
import {useRoute} from "vue-router";
import {useGlobalStore} from "@/stores/global.js";
import {ArrowRight, Expand, Fold, Switch} from "@element-plus/icons-vue";
import {HOME_URL} from "@/router/index";
import {useRouter} from "vue-router";

const route = useRoute();
const router = useRouter();
const globalStore = useGlobalStore();
const matched = computed(() => route.matched.filter(item => item.meta && item.meta.title && item.meta.title !== "首页"));

function toLargeScreen() {
  router.push({path: '/largescreen/board'})
}

function onChangeTheme() {
  if (themeConfigStore.theme !== 'dark') {
    themeConfigStore.setTheme('dark');
    document.querySelector("html").classList.add("dark");
  } else {
    themeConfigStore.setTheme('');
    document.querySelector("html").classList.remove("dark");
  }
}
</script>

<style scoped>
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 55px;
  padding: 0 15px;
  background-color: #ffffff;
  border-bottom: 1px solid #f6f6f6;
}

.header-lf {
  display: flex;
  flex-direction: row;
  align-items: center;
}

.header-ri {
  display: flex;
  flex-direction: row;
  align-items: center;
}

.collapse-icon {
  margin-right: 20px;
  font-size: 22px;
  cursor: pointer;
}

.largescreen {
  background-color: #1d1e26;
  width: 24px;
  height: 24px;
  margin:0px 10px 0px 0px;
  cursor: pointer;
}
</style>
