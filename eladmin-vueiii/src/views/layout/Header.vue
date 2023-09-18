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
            <Avatar></Avatar>
        </div>
    </div>
</template>

<script setup>
import Avatar from "./Avatar.vue";
import { computed } from "vue";
import { useRoute } from "vue-router";
import { useGlobalStore } from "@/stores/global.js";
import { ArrowRight, Expand, Fold } from "@element-plus/icons-vue";
import {HOME_URL} from "@/router/index";

const route = useRoute();
const globalStore = useGlobalStore();
const matched = computed(() => route.matched.filter(item => item.meta && item.meta.title && item.meta.title !== "首页"));
</script>

<style scoped>
.header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 55px;
    padding: 0 15px;
    border-bottom: 1px solid #f6f6f6;
}

.header-lf {
    display: flex;
    flex-direction: row;
    align-items: center;
}

.header-ri {
    margin: 0 30px;
    display: flex;
    flex-direction: row;
    align-items: center;
}
.collapse-icon {
    margin-right: 20px;
    font-size: 22px;
    cursor: pointer;
}
</style>
