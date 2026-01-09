<template>
  <div class="fault module">
    <div class="header">
      <h6 class="title">故障与告警</h6>
      <ul class="time-pick-b">
        <template v-for="(item, index) in timePick" :key="item.type">
          <li :class="timePickIndex == index ? 'selected' : ''" @click="timePickTap(index)">{{item.name}}</li>
        </template>
      </ul>
    </div>
    <ul class="cbody">
      <div style="width: 440px; height: 240px" id="echartsViewFault"></div>
    </ul>
  </div>
</template>

<script setup>
import {onMounted} from "vue";
import useTimePick from "@/hooks/useTimePick";
import * as echarts from "echarts";

const {timePick, timePickIndex} = useTimePick();

let echartsInstance = null;

onMounted(() => {
  echartsInstance = echarts.init(document.getElementById('echartsViewFault'));
  initData();
});

function timePickTap(index) {
  timePickIndex.value = index;
}

/*
* timeType 时间类型 3:日, 4:月, 5:年
* */
function initData() {
  let _response = {
    "labelList": ["01月", "02月", "03月"],
    "dataList": [10, 2, 8],
    "unitList": ["个"]
  }
  drawEchates(_response.dataList, _response.labelList, _response.unitList[0]);
}

function drawEchates(dataList, labelList, unit) {
  echartsInstance.setOption({
    legend: {
      top: '10%',
      textStyle: {
        color: '#ffffff'
      }
    },
    grid: {
      top: "30%",
      bottom: "16%",
      left: "14%",
      right: "2%"
    },
    tooltip: {
      trigger: 'axis'
    },
    textStyle: {
      color: '#8dcdfc'
    },
    xAxis: {
      type: 'category',
      data: labelList,
      axisLine: {
        lineStyle: {color: '#285687'}
      }
    },
    yAxis: {
      name: `单位: ${unit}`,
      splitLine: {
        lineStyle: {
          color: '#285687'
        }
      },
      axisLine: {
        show: true,
        lineStyle: {color: '#285687'}
      }
    },
    series: [{
      type: 'bar',
      data: dataList,
      itemStyle: {
        color: '#00A571'
      }
    }]
  })
}
</script>

<style scoped lang="scss">
@import "@/styles/largescreen.scss";

.fault {
  height: 280px;
}
</style>
