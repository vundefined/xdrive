<template>
  <div class="llayout" :style="{'grid-template-columns' : globalStore.isCollapse ? '64px calc(100vw - 64px)' : '220px calc(100vw - 220px)'}">
    <div class="llayout-side">
      <el-menu
        :default-active="route.path"
        :router="true"
        :collapse-transition="false"
        :collapse="globalStore.isCollapse"
        :unique-opened="true"
        @select="menuSelect">
        <div class="mlogo">
          <img src="@/assets/logo.svg" alt="logo"/>
          <span v-show="!globalStore.isCollapse">Admin</span>
        </div>
        <SubItem :menu-list="authStore.asyncRoutes"></SubItem>
      </el-menu>
    </div>
    <div class="llayout-main">
      <Header></Header>
      <Tabs></Tabs>
      <section class="route-container">
        <router-view v-slot="{ Component, route }">
          <transition appear name="fade-transform" mode="out-in">
            <keep-alive :include="cacheRouter">
              <component :is="Component" :key="route.path"></component>
            </keep-alive>
          </transition>
        </router-view>
      </section>
      <footer class="footer">
        <a href="" target="_blank"> 2022 © Geeker-Admin By Geeker Technology. </a>
      </footer>
    </div>
  </div>
</template>

<script setup>
import {useRoute} from "vue-router";
import {useAuthStore} from "@/stores/auth.js";
import {useGlobalStore} from "@/stores/global.js";
import Header from "./Header.vue";
import Tabs from "./Tabs.vue";
import SubItem from "./SubItem.vue";

const route = useRoute();
const authStore = useAuthStore();
const globalStore = useGlobalStore();
const cacheRouter = ["Index"];

function menuSelect(index, indexPath, item, routeResult) {
  // console.log(index, indexPath, item, routeResult);
}
</script>

<style scoped lang="scss">
.llayout {
  width: 100vw;
  height: 100vh;
  overflow: hidden;
  display: inline-grid;
  transition: grid-template-columns 0.3s ease;
}

.llayout-side {
  height: 100vh;
  //background-color: #191a20;
}

.llayout-main {
  height: 100vh;
  background-color: #f0f2f5;
}

.route-container {
  height: calc(100vh - 154px);
  margin: 10px 14px;
  padding: 16px;
  background-color: #ffffff;
  border-radius: 4px;
  box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
  overflow-y: scroll;
  overflow-x: hidden;
}

.route-container::-webkit-scrollbar {display:none}

.footer {
  text-align: center;
  padding: 6px 0px;
  background-color: #ffffff;
  border-top: 1px solid #e4e7ed;
}

.mlogo {
  height: 55px;
  border-bottom: 1px solid #1d1e26;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;

  span {
    font-size: 22px;
    font-weight: bold;
    //color: #dadada;
    white-space: nowrap;
    margin-left: 8px;
  }

  img {
    width: 30px;
    object-fit: contain;
  }
}
</style>
