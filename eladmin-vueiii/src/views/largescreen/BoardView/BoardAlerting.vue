<template>
  <div class="alerting module">
    <div class="header">
      <h6 class="title">智能告警</h6>
      <ul class="time-pick-b">
        <template v-for="(item, index) in energy" :key="item.type">
          <li :class="energyIndex == index ? 'selected' : ''" @click="timePickTap(index)">{{item.name}}</li>
        </template>
      </ul>
    </div>
    <div class="cbody">
      <el-table :data="tableData" stripe border height="300">
        <el-table-column prop="alarmDate" label="告警时间" width="100" />
        <el-table-column prop="meterName" label="告警设备" width="100" />
        <el-table-column prop="reason" label="告警详情" />
      </el-table>
    </div>
  </div>
</template>

<script setup>
import {ref, onMounted} from "vue";
import useTimePick from "@/hooks/useTimePick";
import { ElTable } from "element-plus";

const {energy, energyIndex} = useTimePick();

let tableData = ref([])

onMounted(() => {
  initData();
});

function timePickTap(index) {
  energyIndex.value = index;
}

/*
* 全部 0
* 水 2
* 电 1
* 油 6
* 气 5
* */
function initData() {
  tableData.value = [
    {
      "id": 510652,
      "code": "13faa0f9-0fc7-49b3-8d78-fcccc66bd341",
      "collectorCode": "5d6e39a3-0009-4aae-aa41-c7a3207ab894",
      "meterCode": "b4706cdd-2d3d-41d8-84d6-886dfa5a299a",
      "collectorName": "哈轴特3A配电房边设备",
      "meterName": "3AN8/AP54/射流机组",
      "collectType": 0,
      "collectSignalCode": null,
      "alarmStatus": 0,
      "reason": "3AN8/AP54/射流机组 电压三相不平衡，偏差度:994.640000",
      "alarmTime": "2024-01-09 08:41:47",
      "confirmTime": null,
      "alarmLevel": 3,
      "alarmValue": 994.64,
      "paramName": null,
      "paramCode": null,
      "deviceType": 1,
      "filename": null,
      "cameraUrl": null,
      "cameraCode": null,
      "alarmType": 7,
      "alarmEventType": null,
      "alarmHandler": null,
      "processingType": null,
      "alarmObjectName": "3AN8/AP54/射流机组",
      "alarmDate": "2024-01-09 16:41:47"
    },
    {
      "id": 510651,
      "code": "6c848a62-ca86-43c0-b8bc-b1c2d4c318e1",
      "collectorCode": "5d6e39a3-0009-4aae-aa41-c7a3207ab894",
      "meterCode": "ed3a9245-50e8-49a3-8602-78d56ec13606",
      "collectorName": "哈轴特3A配电房边设备",
      "meterName": "3AN8/AP53/射流机组",
      "collectType": 0,
      "collectSignalCode": null,
      "alarmStatus": 0,
      "reason": "3AN8/AP53/射流机组 电压三相不平衡，偏差度:69.520000",
      "alarmTime": "2024-01-09 08:41:47",
      "confirmTime": null,
      "alarmLevel": 3,
      "alarmValue": 69.52,
      "paramName": null,
      "paramCode": null,
      "deviceType": 1,
      "filename": null,
      "cameraUrl": null,
      "cameraCode": null,
      "alarmType": 7,
      "alarmEventType": null,
      "alarmHandler": null,
      "processingType": null,
      "alarmObjectName": "3AN8/AP53/射流机组",
      "alarmDate": "2024-01-09 16:41:47"
    },
    {
      "id": 510650,
      "code": "eecbe260-bac0-4b72-ac00-ee3f9adec049",
      "collectorCode": "5d6e39a3-0009-4aae-aa41-c7a3207ab894",
      "meterCode": "0ea343b3-a623-46e4-bd25-9ec11919ddd6",
      "collectorName": "哈轴特3A配电房边设备",
      "meterName": "3AN8/AP42/WC14",
      "collectType": 0,
      "collectSignalCode": null,
      "alarmStatus": 0,
      "reason": "3AN8/AP42/WC14 电压三相不平衡，偏差度:241.430000",
      "alarmTime": "2024-01-09 08:41:47",
      "confirmTime": null,
      "alarmLevel": 3,
      "alarmValue": 241.43,
      "paramName": null,
      "paramCode": null,
      "deviceType": 1,
      "filename": null,
      "cameraUrl": null,
      "cameraCode": null,
      "alarmType": 7,
      "alarmEventType": null,
      "alarmHandler": null,
      "processingType": null,
      "alarmObjectName": "3AN8/AP42/WC14",
      "alarmDate": "2024-01-09 16:41:47"
    },
    {
      "id": 510649,
      "code": "3f54aadd-e63a-4726-a865-d716a970a65c",
      "collectorCode": "5d6e39a3-0009-4aae-aa41-c7a3207ab894",
      "meterCode": "25d7b6bc-9aed-48cb-bfbe-bbc833f9d8e4",
      "collectorName": "哈轴特3A配电房边设备",
      "meterName": "3AN7/AP21/空压站",
      "collectType": 0,
      "collectSignalCode": null,
      "alarmStatus": 0,
      "reason": "3AN7/AP21/空压站 电压三相不平衡，偏差度:78.110000",
      "alarmTime": "2024-01-09 08:41:47",
      "confirmTime": null,
      "alarmLevel": 3,
      "alarmValue": 78.11,
      "paramName": null,
      "paramCode": null,
      "deviceType": 1,
      "filename": null,
      "cameraUrl": null,
      "cameraCode": null,
      "alarmType": 7,
      "alarmEventType": null,
      "alarmHandler": null,
      "processingType": null,
      "alarmObjectName": "3AN7/AP21/空压站",
      "alarmDate": "2024-01-09 16:41:47"
    },
    {
      "id": 510648,
      "code": "6ba8f0de-f8a5-4d52-b13f-f054c3f783eb",
      "collectorCode": "5d6e39a3-0009-4aae-aa41-c7a3207ab894",
      "meterCode": "1d472ee4-cfb1-42f0-a287-44348820e236",
      "collectorName": "哈轴特3A配电房边设备",
      "meterName": "3AN7/AP39/WC7、WC8",
      "collectType": 0,
      "collectSignalCode": null,
      "alarmStatus": 0,
      "reason": "3AN7/AP39/WC7、WC8 电压三相不平衡，偏差度:215.460000",
      "alarmTime": "2024-01-09 08:41:47",
      "confirmTime": null,
      "alarmLevel": 3,
      "alarmValue": 215.46,
      "paramName": null,
      "paramCode": null,
      "deviceType": 1,
      "filename": null,
      "cameraUrl": null,
      "cameraCode": null,
      "alarmType": 7,
      "alarmEventType": null,
      "alarmHandler": null,
      "processingType": null,
      "alarmObjectName": "3AN7/AP39/WC7、WC8",
      "alarmDate": "2024-01-09 16:41:47"
    },
    {
      "id": 510647,
      "code": "03af56e1-f909-416b-90cf-21110f781a4c",
      "collectorCode": "5d6e39a3-0009-4aae-aa41-c7a3207ab894",
      "meterCode": "f0cd97b1-556f-4fb4-a4a7-399a815e252d",
      "collectorName": "哈轴特3A配电房边设备",
      "meterName": "3AN6/AP3/制冷站",
      "collectType": 0,
      "collectSignalCode": null,
      "alarmStatus": 0,
      "reason": "3AN6/AP3/制冷站 电压三相不平衡，偏差度:49.010000",
      "alarmTime": "2024-01-09 08:41:46",
      "confirmTime": null,
      "alarmLevel": 3,
      "alarmValue": 49.01,
      "paramName": null,
      "paramCode": null,
      "deviceType": 1,
      "filename": null,
      "cameraUrl": null,
      "cameraCode": null,
      "alarmType": 7,
      "alarmEventType": null,
      "alarmHandler": null,
      "processingType": null,
      "alarmObjectName": "3AN6/AP3/制冷站",
      "alarmDate": "2024-01-09 16:41:46"
    },
    {
      "id": 510646,
      "code": "0b2c5145-6157-4285-b25b-0eaf1e2c57e8",
      "collectorCode": "5d6e39a3-0009-4aae-aa41-c7a3207ab894",
      "meterCode": "c48dcbd3-ed1e-4359-ad61-ac83a38e6244",
      "collectorName": "哈轴特3A配电房边设备",
      "meterName": "3AN6/AP5/生产设备用电",
      "collectType": 0,
      "collectSignalCode": null,
      "alarmStatus": 0,
      "reason": "3AN6/AP5/生产设备用电 电压三相不平衡，偏差度:551.190000",
      "alarmTime": "2024-01-09 08:41:46",
      "confirmTime": null,
      "alarmLevel": 3,
      "alarmValue": 551.19,
      "paramName": null,
      "paramCode": null,
      "deviceType": 1,
      "filename": null,
      "cameraUrl": null,
      "cameraCode": null,
      "alarmType": 7,
      "alarmEventType": null,
      "alarmHandler": null,
      "processingType": null,
      "alarmObjectName": "3AN6/AP5/生产设备用电",
      "alarmDate": "2024-01-09 16:41:46"
    }
  ];
}
</script>

<style scoped lang="scss">
@import "@/styles/largescreen.scss";

.alerting {
  height: 368px;
}

.cbody {
  height: 329px;
  padding: 18px 14px 10px 14px;
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

// 鼠标悬停时的背景颜色
:deep(.el-table tbody tr:hover>td) {
  background-color: #081B3B !important;
  //color: #FFFFFF;
}
// 鼠标点击时的背景颜色
:deep(.el-table__body tr.current-row>td) {
  //background-color: green !important;
  //color: #00FFF4
}

// table 四周边框
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
