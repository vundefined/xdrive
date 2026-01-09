<template>
  <main id="screen" class="largescreen">
    <MHeader></MHeader>
    <EnergyAmount></EnergyAmount>
    <MNavigation></MNavigation>
    <img src="@/assets/images/bg_01.jpg" class="mesh-img"  alt="">
    <router-view></router-view>
  </main>
</template>

<script setup>
import {onMounted} from "vue";
import MHeader from './MHeader.vue';
import EnergyAmount from './EnergyAmount.vue';
import MNavigation from './MNavigation.vue'

onMounted(() => {
  initWindowScale();
})

window.onresize = function () { // 实时获取window的宽高
  initWindowScale();
}

function initWindowScale() {
  let _screen = document.getElementById("screen");
  let _designWidth = _screen.clientWidth;
  let _designHeight = _screen.clientHeight;
  let _screenWidth = window.document.documentElement.clientWidth;
  let _screenHeight = window.document.documentElement.clientHeight;
  let _scaleWidth = _screenWidth / _designWidth;
  let _scaleHeight = _screenHeight / _designHeight;

  _screen.style = `transform: scale(${_scaleWidth}, ${_scaleHeight}); transform-origin: 0 0;`;
}
</script>

<style scoped lang="scss">
@import "@/styles/largescreen.scss";

.largescreen {
  width: $designWidth;
  height: $designHeight;
  position: relative;
  background-color: #153560;
}

.mesh-img {
  width: $designWidth;
  height: $designHeight;
  position: absolute;
  top: 30px;
  left: 0px;
  z-index: -2;
}
</style>
