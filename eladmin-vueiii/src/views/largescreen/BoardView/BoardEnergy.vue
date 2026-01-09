<template>
  <div class="water-fire module">
    <h6 class="title title-b">趋势分析</h6>
    <div style="height: 20px"></div>
    <div style="height: 500px" id="echartsOG"></div>
  </div>
</template>

<script setup>
import {onMounted} from "vue";
import * as echarts from "echarts";

let echartsInstance = null;
onMounted(() => {
  echartsInstance = echarts.init(document.getElementById('echartsOG'));
  let _response = {
    "labelList": ["01日", "02日", "03日"],
    "dataList": [10, 4, 7]
  }
  drawEchates(_response.labelList, '单位 kW‧h', _response.dataList)
});

function drawEchates(labelList, yAxisName, dataList) {
  echartsInstance.setOption({
    tooltip: {
      trigger: 'axis'
    },
    textStyle: {
      color: '#8dcdfc'
    },
    grid: {
      top: "10%",
      bottom: "4%",
      left: "10%",
      right: "4%"
    },
    xAxis: {
      type: 'category',
      boundaryGap: false,
      data: labelList,
      axisLine: {
        lineStyle: {color: '#285687'}
      }
    },
    yAxis: {
      name: yAxisName,
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
    series: [{
      type: 'line',
      data: dataList,
      itemStyle: {
        normal: {
          color: '#32D0C5'
        },
      },
      lineStyle: {
        color: '#32D0C5'
      },
      areaStyle: {
        color: '#24606E'
      }
    }]
  });
}
</script>

<style scoped lang="scss">
@import "@/styles/largescreen.scss";

.water-fire{
  height: 575px;
}
</style>
