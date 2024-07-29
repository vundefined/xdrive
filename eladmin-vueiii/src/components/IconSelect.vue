<template>
  <div>
    <el-input v-model="iconName" clearable placeholder="请输入图标名称" @clear="filterIcons" @input="filterIcons"
              :suffix-icon="Search"></el-input>
    <ul class="icon-select-list">
      <template v-for="[key, component] of Object.entries(ElementPlusIconsVue)" :key="key">
        <li>
          <el-icon @click="selectedIcon(key)" size="26">
            <component :is="component"></component>
          </el-icon>
          <span>{{key}}</span>
        </li>
      </template>
    </ul>
  </div>
</template>

<script setup lang="ts">
import {ref} from "vue";
import {Search} from "@element-plus/icons-vue";
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

const iconName = ref("");
const emit = defineEmits(["selected"]);

function filterIcons() {
  console.log('iconName---', iconName.value);
}

function selectedIcon(name) {
  emit("selected", name);
  document.body.click();
}

function reset() {

}

defineExpose({
  reset
});
</script>

<style scoped lang="scss">
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
  width: 100px;
  cursor: pointer;
  margin: 0px 0px 14px 0px;
  text-align: center;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

span {
  margin: 0px 0px 0px 10px;
}
</style>
