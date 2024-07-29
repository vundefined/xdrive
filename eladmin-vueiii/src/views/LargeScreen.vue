<template>
  <div class="large-screen">
    <el-table class="my-table" :data="tableData" stripe border height="300">
      <el-table-column prop="alarmDate" label="告警时间" width="180"/>
      <el-table-column prop="meterName" label="告警设备" width="100"/>
      <el-table-column prop="reason" label="告警详情" width="200"/>
    </el-table>
  </div>
</template>

<script setup>
import {ref} from "vue";

let tableData = ref([{
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
</script>

<style scoped>
.large-screen {
  height: 100vh;
  min-width: 550px;
  min-height: 500px;
  background-image: url("../assets/images/welcome02.jpg");
  background-repeat: no-repeat;
  background-size: cover;
}

:deep(.el-table) {
  background-color: transparent;
  color: #8DCDFC;
}

:deep(.el-table thead) {
  color: #DEE1E8;
}

:deep(.el-table th.el-table__cell) {
  background-color: #083777;
}

:deep(.el-table__cell) {
  border-bottom: none !important;
}

:deep(.el-table--border .el-table__cell) {
  border-right: 1px solid #0B2652;
}

:deep(.el-table__row) {
  background-color: #081C3B;
}

:deep(.el-table--striped .el-table__body tr.el-table__row--striped td.el-table__cell) {
  background-color: #082D62;
}

/*鼠标悬停时的背景颜色*/
:deep(.el-table tbody tr:hover>td) {
  background-color: #081B3B !important;
  /*color: #FFFFFF;*/
}

/*鼠标点击时的背景颜色
:deep(.el-table__body tr.current-row>td) {
   background-color: green !important;
   color: #00FFF4
}
 */

/*table 四周边框*/
:deep(.el-table--border::before, .el-table--border::after) {
  content: none;
}

:deep(.el-table--border::after) {
  content: none;
}

:deep(.el-table__inner-wrapper::before) {
  content: none;
}

:deep(.el-table__inner-wrapper::after) {
  content: none;
}

:deep(.el-table__border-left-patch) {
  width: 0;
  background-color: transparent !important;
}
</style>
