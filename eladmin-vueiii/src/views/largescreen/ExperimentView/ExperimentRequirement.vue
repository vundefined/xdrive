<template>
  <div class="cost module">
    <h6 class="title title-b">本周能源需求</h6>
  </div>
</template>

<script setup>
import {reactive, onMounted} from "vue";

let tableData = reactive([{
  alarmDate: "2023-10-10 12:00",
  meterName: 'aaaa',
  reason: 'bbbb'
}, {
  alarmDate: "2023-10-10 12:00",
  meterName: 'aaaa',
  reason: 'bbbb'
}, {
  alarmDate: "2023-10-10 12:00",
  meterName: 'aaaa',
  reason: 'bbbb'
}, {
  alarmDate: "2023-10-10 12:00",
  meterName: 'aaaa',
  reason: 'bbbb'
}])
onMounted(() => {

})
</script>

<style scoped lang="scss">
@import "@/styles/largescreen.scss";

.cost {
  height: 303px;
}
</style>
