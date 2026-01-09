<template>
  <div class="large-module">
    <div class="left">
      <EnergyStatistics></EnergyStatistics>
      <div style="height: 18px;"></div>
      <EnergyCost></EnergyCost>
      <div style="height: 18px;"></div>
      <EnergyQuality></EnergyQuality>
    </div>
    <div class="right">
      <EnergyTop></EnergyTop>
      <div style="height: 18px;"></div>
      <EnergyTotal></EnergyTotal>
      <div style="height: 18px;"></div>
      <EnergySource></EnergySource>
    </div>
  </div>
</template>

<script setup>
import EnergyStatistics from './EnergyStatistics.vue';
import EnergyCost from './EnergyCost.vue';
import EnergyQuality from './EnergyQuality.vue';
import EnergyTotal from './EnergyTotal.vue';
import EnergySource from './EnergySource.vue';
import EnergyTop from './EnergyTop.vue';
</script>

<style scoped lang="scss">
</style>
