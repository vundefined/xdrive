<template>
  <div class="tabs-box">
    <el-tabs v-model="tabStore.curTabs" type="card" @tab-click="tabClick" @tab-remove="removeTab">
      <el-tab-pane
        v-for="item in tabStore.tabsList"
        :key="item.path"
        :path="item.path"
        :label="item.title"
        :name="item.path"
        :closable="item.close">
        <template #label>
          <el-icon>
            <component :is="HomeFilled"></component>
          </el-icon>
          <span style="margin-left:2px">{{ item.title }}</span>
        </template>
      </el-tab-pane>
    </el-tabs>
    <MoreButton></MoreButton>
  </div>
</template>

<script setup>
import {watch} from "vue";
import {useRoute, useRouter} from "vue-router";
import {useTabsStore} from "@/stores/tabs";
import {HomeFilled} from "@element-plus/icons-vue";
import MoreButton from "./MoreButton.vue";

const tabStore = useTabsStore();
const route = useRoute();
const router = useRouter();

watch(
  () => route.path,
  () => {
    if (route.meta.hidden == false) {
      tabStore.addTabs({
        path: route.path,
        title: route.meta.title,
        close: true
      });
    }
  },
  {
    immediate: true
  }
);

function tabClick(pane) {
  router.push(pane.props.name);
}

function removeTab(name) {
  tabStore.removeTabs(name);
}
</script>

<style scoped>
.tabs-box {
  position: relative;
  background-color: #ffffff;
}

:deep(.el-tabs__header) {
  height: 40px;
  padding: 0 10px;
  margin: 0;
}

:deep(.el-tabs__nav-wrap) {
  position: absolute;
  width: calc(100% - 120px);
}

:deep(.el-tabs__nav) {
  border: none !important;
}

:deep(.el-tabs__item) {
  color: #cccccc;
  border: none !important;
}

:deep(.is-active) {
  color: #409eff;
  border-bottom: 2px solid #409eff !important;
}
</style>
