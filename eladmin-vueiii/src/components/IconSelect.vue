<template>
    <div>
        <el-input v-model="iconName" clearable placeholder="请输入图标名称" @clear="filterIcons" @input="filterIcons" :suffix-icon="Search"></el-input>
        <ul class="icon-select-list">
            <li v-for="(item, index) in iconList" :key="index" @click="selectedIcon(item)">
                <!--<svg-icon :name="item" size="1.2em" :color="iconColor" />-->
                <span>{{ item }}</span>
            </li>
        </ul>
    </div>
</template>

<script setup lang="ts">
    import { ref } from "vue";
    import {Search} from "@element-plus/icons-vue";

    const icons = [];

    const iconList = ref(icons);
    const iconName = ref("");
    const emit = defineEmits(["selected"]);
    const iconColor = ref("#000000");

    function filterIcons() { // 监听 input value
        iconList.value = icons;
        if (iconName.value) {
            iconList.value = icons.filter(item => item.indexOf(iconName.value) !== -1);
        }
    }
    function selectedIcon(name) {
        emit("selected", name);
        document.body.click();
    }
    function reset() {
        iconName.value = "";
        iconList.value = icons;
    }
    defineExpose({
        reset
    });
</script>

<style scoped>
    .icon-select-list {
        width: 544px;
        height: 200px;
        padding: 16px 0px 0px 0px;
        overflow-y: scroll;
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        flex-wrap: wrap;
    }
    li {
        width: 160px;
        cursor: pointer;
        margin: 0px 0px 14px 0px;
    }
    span {
        margin: 0px 0px 0px 10px;
    }
</style>
