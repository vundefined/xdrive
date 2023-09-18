<template>
    <div class="llayout">
        <div class="llayout-aside" :style="{ 'min-width': globalStore.isCollapse ? '65px' : '220px' }">
            <div class="mlogo">
                <img src="@/assets/logo.svg" alt="logo" />
                <span v-show="!globalStore.isCollapse">Admin</span>
            </div>
            <el-scrollbar>
                <el-menu
                    :default-active="route.path"
                    :router="true"
                    :collapse="globalStore.isCollapse"
                    :unique-opened="true"
                    background-color="#191a20"
                    text-color="#bdbdc0"
                    active-text-color="#fff"
                    @select="menuSelect">
                    <SubItem :menu-list="authStore.asyncRoutes"></SubItem>
                </el-menu>
            </el-scrollbar>
        </div>
        <div class="llayout-main">
            <Header></Header>
            <Tabs></Tabs>
            <section class="container-a">
                <div class="container-b">
                    <router-view v-slot="{ Component, route }">
                        <transition appear name="fade-transform" mode="out-in">
                            <keep-alive :include="cacheRouter">
                                <component :is="Component" :key="route.path"></component>
                            </keep-alive>
                        </transition>
                    </router-view>
                </div>
            </section>
            <div class="footer">
                <a href="" target="_blank"> 2022 © Geeker-Admin By Geeker Technology. </a>
            </div>
        </div>
    </div>
</template>

<script setup>
    import { useRoute } from "vue-router";
    import { useAuthStore } from "@/stores/auth.js";
    import { useGlobalStore } from "@/stores/global.js";
    import Header from "./Header.vue";
    import Tabs from "./Tabs.vue";
    import SubItem from "./SubItem.vue";

    const route = useRoute();
    const authStore = useAuthStore();
    const globalStore = useGlobalStore();
    const cacheRouter = ["mmarkdown"];

    function menuSelect(index, indexPath, item, routeResult) {
        // console.log(index, indexPath, item, routeResult);
    }
</script>

<style scoped>
    .el-scrollbar {
        height: calc(100% - 55px);
    }
    .el-menu {
        border-right: none;
    }

    .llayout {
        width: 100vw;
        height: 100vh;
        min-width: 970px;
        display: flex;
        flex-direction: row;
        overflow: hidden;
    }
    .llayout-aside {
        background-color: #191a20;
        transition: all 0.2s ease;
    }

    .llayout-main{
        flex-grow: 1;
        transition: all 0.2s ease;
        display: flex;
        flex-direction: column;
        overflow: auto;
    }

    .container-a {
        padding: 10px 13px;
        background-color: #f0f2f5;
        flex-grow: 1;
    }

    .container-b {
        height: 100%;
        padding: 20px;
        background-color: #ffffff;
        border-radius: 4px;
        box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
    }

    .footer {
        text-align: center;
        padding: 6px 0px;
        border-top: 1px solid #e4e7ed;
    }
    .mlogo {
        height: 55px;
        border-bottom: 2px solid #1d1e26;
        box-shadow: 2px 0 6px rgb(0 21 41 / 35%);
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
    }
    .mlogo span {
        font-size: 22px;
        font-weight: bold;
        color: #dadada;
        white-space: nowrap;
    }
    .mlogo img {
        width: 30px;
        object-fit: contain;
        margin-right: 8px;
    }
</style>
