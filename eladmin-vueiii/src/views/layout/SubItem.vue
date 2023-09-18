<template>
    <template v-for="menuItem in menuList" :key="menuItem.path">
        <el-menu-item v-if="menuItem.children.length === 1" :index="menuItem.path + '/' + menuItem.children[0].path">
            <el-icon><User/></el-icon>
            <template #title>
                <span>{{ menuItem.children[0].meta.title }}</span>
            </template>
        </el-menu-item>
        <el-sub-menu v-if="menuItem.children.length > 1" :index="menuItem.path">
            <template #title>
                <el-icon><User/></el-icon>
                <span>{{ menuItem.meta.title }}</span>
            </template>
            <template v-for="subItem in menuItem.children" :key="subItem.path">
                <el-menu-item :index="menuItem.path + '/' + subItem.path">
                    <template #title>
                        <el-icon><User/></el-icon>
                        <span>{{ subItem.meta.title }}</span>
                    </template>
                </el-menu-item>
                <SubItem :menu-list="subItem.children" />
            </template>
        </el-sub-menu>
    </template>
</template>

<script setup>
    import { User } from "@element-plus/icons-vue";
    defineProps({
        menuList: {
            type: Array,
            default: []
        }
    });
</script>

<style scoped>
    .el-menu-item.is-active {
        color: var(--el-menu-active-color);
        background-color: #060708;
    }
    .el-menu-item.is-active:before {
        position: absolute;
        top: 0;
        bottom: 0;
        left: 0;
        width: 4px;
        background: var(--el-color-primary);
        content: "";
    }
</style>
