<template>
  <footer class="nav nav-list">
    <template v-for="(item, index) in nav" v-bing:key="item.id">
      <div
        class="nav-item"
        :style="{backgroundImage: 'url('+ (navCur == index ? item.imgSelect : item.imgNormal) + ')'}"
        v-on:click="navItemTap(index)">
        {{item.name}}
      </div>
    </template>
  </footer>
</template>

<script setup>
import {onMounted, ref, reactive} from "vue";
import useAssets from "@/hooks/useAssets";
import { useRouter, useRoute } from "vue-router"

const {getImageUrl} = useAssets();
const router = useRouter()

let navCur = ref(0)
const nav = reactive([
  {
    id: 0,
    imgSelect: getImageUrl("nav_kanban_02.png"),
    imgNormal: getImageUrl("nav_kanban_01.png"),
    name: "综合看板",
    path: "/largescreen/board"
  },
  {
    id: 2,
    imgSelect: getImageUrl("nav_nengyuan_02.png"),
    imgNormal: getImageUrl("nav_nengyuan_01.png"),
    name: "能源管理",
    path: "/largescreen/energy"
  }
])

onMounted(() => {
  navCur.value = useRoute().meta.index;
})

function navItemTap(index) {
  navCur.value = index;
  router.push({path: nav[index].path})
}
</script>

<style scoped lang="scss">
@import "@/styles/largescreen.scss";

.nav-list {
  width: 919px;
  height: 194px;
  background-image: url("@/assets/images/nav_bg.png");
  background-repeat: no-repeat;
  background-size: 919px 64px;
  background-position: bottom;
  padding: 0px 107px 0px 107px;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  position: absolute;
  bottom: 20px;
  left: 500px;
  z-index: 1;
}

.nav-item {
  width: 166px;
  height: 155px;
  color: #ffffff;
  font-size: 20px;
  background-repeat: no-repeat;
  background-size: 147px 132px;
  background-position: center;
  padding: 91px 0px 44px 0px;
  text-align: center;
  cursor: pointer;
  user-select: none;
}
</style>
