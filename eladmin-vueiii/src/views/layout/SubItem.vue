<template>
  <template v-for="menuItem in menuList" :key="menuItem.meta.url">
    <el-menu-item v-if="menuItem.children.length === 1" :index="menuItem.meta.url + '/' + menuItem.children[0].meta.url">
      <template #title>
        <el-icon>
          <component :is="ElementPlusIconsVue[menuItem.meta.icon]"></component>
        </el-icon>
        <span>{{ menuItem.children[0].meta.title }}</span>
      </template>
    </el-menu-item>
    <el-sub-menu v-if="menuItem.children.length > 1" :index="menuItem.meta.url">
      <template #title>
        <el-icon>
          <component :is="ElementPlusIconsVue[menuItem.meta.icon]"></component>
        </el-icon>
        <span>{{ menuItem.meta.title }}</span>
      </template>
      <template v-for="subItem in menuItem.children" :key="subItem.meta.url">
        <el-menu-item v-if="!subItem.meta.hidden" :index="menuItem.meta.url + '/' + subItem.meta.url">
          <template #title>
            <el-icon>
              <component :is="ElementPlusIconsVue[subItem.meta.icon]"></component>
            </el-icon>
            <span>{{ subItem.meta.title }}</span>
          </template>
        </el-menu-item>
        <SubItem :menu-list="subItem.children"/>
      </template>
    </el-sub-menu>
  </template>
</template>

<script setup>
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

defineProps({
  menuList: {
    type: Array,
    default: []
  }
});
</script>

<style scoped>
</style>
