<template>
  <div>
    <Edit style="width: 2em; height: 2em; margin-right: 8px"/>

    <el-icon>
      <Share/>
    </el-icon>

    <el-icon size="40">
      <component :is="Delete"></component>
    </el-icon>

    <i class="iconfont icon-zhongyingwen"></i>
    <i class="iconfont icon-suoxiao"></i>
    <i class="iconfont icon-fangda"></i>
    <i class="iconfont icon-contentright"></i>
  </div>
</template>

<script setup>
import {Delete, Share, Edit} from "@element-plus/icons-vue";
</script>

<style scoped>
@import "@/assets/iconfont/iconfont.scss";
</style>
