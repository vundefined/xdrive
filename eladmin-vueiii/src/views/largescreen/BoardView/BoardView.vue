<template>
  <div class="large-module">
    <div class="left">
      <BoardDevice></BoardDevice>
      <div style="height: 17px"></div>
      <BoardEnergy></BoardEnergy>
    </div>
    <div class="right">
      <BoardCarbon></BoardCarbon>
      <div style="height: 10px"></div>
      <BoardFault></BoardFault>
      <div style="height: 17px"></div>
      <BoardAlerting></BoardAlerting>
    </div>
  </div>
</template>

<script setup>
import BoardAlerting from './BoardAlerting.vue';
import BoardEnergy from './BoardEnergy.vue';
import BoardDevice from './BoardDevice.vue';
import BoardCarbon from './BoardCarbon.vue';
import BoardFault from './BoardFault.vue';
</script>

<style scoped lang="scss">
</style>
