<template>
  <div class="total module">
    <h6 class="title title-b">碳排放总量趋势</h6>
    <div style="width: 460px; height: 264px" id="echartsTotal"></div>
  </div>
</template>

<script setup>
import {onMounted} from "vue";
import * as echarts from "echarts";

let echartsInstance = null;
onMounted(() => {
  echartsInstance = echarts.init(document.getElementById('echartsTotal'));
  initData();
})

function initData() {
  let response = {
    "nameList": ["实际排放"],
    "unitList": ["tCO₂"],
    "labelList": ["1月", "2月", "3月"],
    "dataList": [1, 3, 6]
  }
  drawEchates(response);
}

/*
* 绘制图表
* */
function drawEchates(response) {
  echartsInstance.setOption({
    legend: {
      top: '4%',
      textStyle: {
        color: '#ffffff'
      }
    },
    grid: {
      top: "26%",
      bottom: "14%",
      left: "20%",
      right: "4%"
    },
    tooltip: {
      trigger: 'axis'
    },
    textStyle: {
      color: '#8dcdfc'
    },
    xAxis: {
      type: 'category',
      axisLine: {
        lineStyle: {color: '#285687'}
      },
      data: response.labelList
    },
    yAxis: {
      name: `单位: ${response.unitList[0]}`,
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
    series: [
      {
        type: 'bar',
        name: response.nameList[0],
        itemStyle: {
          color: '#20E6A4'
        },
        data: response.dataList
      }
    ]
  })
}
</script>

<style scoped lang="scss">
@import "@/styles/largescreen.scss";

.total {
  height: 303px;
}
</style>
