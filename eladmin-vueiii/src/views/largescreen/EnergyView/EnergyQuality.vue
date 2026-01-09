<template>
  <div class="quality module">
    <div class="header">
      <h6 class="title">供电用量情况</h6>
    </div>
    <div style="height: 260px" id="echartsQuality"></div>
  </div>
</template>

<script setup>
import {onMounted} from "vue";
import * as echarts from "echarts";

let echartsInstance = null;
onMounted(() => {
  echartsInstance = echarts.init(document.getElementById('echartsQuality'));
  initData();
})

function initData() {
  let response = {
    "nameList": ["正向有功电度"],
    "unitList": ["kW‧h"],
    "labelList": ["01日", "02日", "03日",],
    "dataList": [2, 5, 9]
  };
  let _xAxisData = response.labelList;
  let _seriesData = response.dataList;
  let _legendData = response.nameList;
  drawEchates(_xAxisData, _seriesData, _legendData, '单位: ' + response.unitList[0]);
}

/*
* 绘制图表
* */
function drawEchates(xAxisData, seriesData, legendData, unit) {
  echartsInstance.setOption({
    textStyle: {
      color: '#8dcdfc'
    },
    tooltip: {
      show: true,
      trigger: 'item',
    },
    legend: {
      top: '4%',
      textStyle: {
        color: '#ffffff'
      }
    },
    xAxis: {
      type: 'category',
      data: xAxisData,
      axisLine: {
        lineStyle: {color: '#285687'}
      }
    },
    yAxis: {
      name: unit,
      type: 'value',
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
    grid: {
      top: "20%",
      bottom: "22%",
      left: "15%",
      right: "4%"
    },
    series: [
      {
        name: legendData,
        data: seriesData,
        type: 'line',
        itemStyle: {
          normal: {
            color: '#2AD7B4'
          },
        },
        lineStyle: {
          color: '#2AD7B4'
        },
      }
    ]
  })
}
</script>

<style scoped lang="scss">
@import "@/styles/largescreen.scss";

.quality {
  height: 303px;
}
</style>
