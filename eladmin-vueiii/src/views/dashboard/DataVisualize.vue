<template>
    <div>
        <Edit style="width: 1em; height: 1em; margin-right: 8px" />

        <el-icon><Share /></el-icon>

       <el-icon>
            <component :is="Delete"></component>
        </el-icon>
    </div>
</template>

<script setup>
    import { Delete, Share, Edit } from "@element-plus/icons-vue";
</script>

<style scoped></style>
