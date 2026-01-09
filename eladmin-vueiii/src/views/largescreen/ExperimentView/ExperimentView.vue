<template>
  <div class="large-module">
    <div class="left">
      <ExperimentPlan></ExperimentPlan>
      <div style="height: 18px;"></div>
      <ExperimentRequirement></ExperimentRequirement>
      <div style="height: 18px;"></div>
      <ExperimentTask></ExperimentTask>
    </div>
    <div class="right">
      <ExperimentCost></ExperimentCost>
      <div style="height: 18px;"></div>
      <ExperimentType></ExperimentType>
      <div style="height: 18px;"></div>
      <ExperimentUnit></ExperimentUnit>
    </div>
  </div>
</template>

<script setup>
import ExperimentPlan from './ExperimentPlan.vue';
import ExperimentRequirement from './ExperimentRequirement.vue';
import ExperimentTask from './ExperimentTask.vue';
import ExperimentCost from './ExperimentCost.vue';
import ExperimentType from './ExperimentType.vue';
import ExperimentUnit from './ExperimentUnit.vue';
</script>

<style scoped lang="scss">
</style>
