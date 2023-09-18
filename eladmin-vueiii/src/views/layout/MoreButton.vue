<template>
    <el-dropdown trigger="click">
        <el-button size="small" type="primary">
            <span>更多</span>
            <el-icon class="el-icon--right"><ArrowDown /></el-icon>
        </el-button>
        <template #dropdown>
            <el-dropdown-menu>
                <el-dropdown-item @click="closeCurrentTab">关闭当前</el-dropdown-item>
                <el-dropdown-item @click="closeOtherTab">关闭其它</el-dropdown-item>
                <el-dropdown-item @click="closeAllTab">关闭所有</el-dropdown-item>
            </el-dropdown-menu>
        </template>
    </el-dropdown>
</template>

<script setup>
    import { useTabsStore } from "@/stores/tabs";
    import {HOME_URL} from "@/router/index";
    import { ArrowDown } from "@element-plus/icons-vue";
    const tabStore = useTabsStore();

    function closeCurrentTab() {
        if (tabStore.curTabs === HOME_URL) return;
        tabStore.removeTabs(tabStore.curTabs);
    }

    function closeOtherTab() {
        tabStore.closeMultipleTab(tabStore.curTabs);
    }

    function closeAllTab() {
        tabStore.closeMultipleTab();
        tabStore.goHome();
    }
</script>

<style scoped>
    .el-dropdown {
        position: absolute;
        top: 8px;
        right: 13px;
    }
</style>
