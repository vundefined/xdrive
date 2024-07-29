<template>
  <div class="not-container">
    <img src="@/assets/images/error/500.png" class="not-img" alt="500"/>
    <div class="not-detail">
      <h2>500</h2>
      <h4>抱歉，您的网络不见了~🤦‍♂️🤦‍♀️</h4>
      <el-button type="primary" @click="tabStore.goHome()">返回首页</el-button>
    </div>
  </div>
</template>

<script setup>
import {useTabsStore} from "@/stores/tabs";

const tabStore = useTabsStore();
</script>

<style scoped>
@import "../../styles/error.css";
</style>
