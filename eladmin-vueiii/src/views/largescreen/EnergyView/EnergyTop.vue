<template>
  <div class="fault module">
    <div class="header">
      <h6 class="title">用能单位用能排名</h6>
    </div>
    <div style="width: 460px; height: 264px" id="echartsTop"></div>
  </div>
</template>

<script setup>
import {onMounted} from "vue";
import * as echarts from "echarts";

let echartsInstance = null;
onMounted( () => {
  echartsInstance = echarts.init(document.getElementById('echartsTop'));
  energyRanking([{orgName: "aaaa", value: 32}], [{orgName: "bbbb", value: 45}]);
})

function energyRanking(month, lastMonth) {
  echartsInstance.setOption({
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'cross',
        crossStyle: {
          color: '#999'
        }
      }
    },
    textStyle: {
      color: '#8dcdfc'
    },
    legend: {
      top: '10%',
      data: ['上月', '本月'],
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
    xAxis: [
      {
        type: 'category',
        data: month.map(item => item.orgName),
        axisPointer: {
          type: 'shadow'
        },
        axisLine: {
          lineStyle: {color: '#285687'}
        },
      }
    ],
    yAxis: [
      {
        type: 'value',
        name: '吨标煤',
        axisLabel: {
          formatter: '{value} tce'
        },
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
    ],
    series: [
      {
        name: '上月',
        type: 'bar',
        tooltip: {
          valueFormatter: function (value) {
            return value + ' tce';
          }
        },
        data: lastMonth.map(item => (item.value / 1000).toFixed(2)),
      },
      {
        name: '本月',
        type: 'bar',
        tooltip: {
          valueFormatter: function (value) {
            return value + ' tce';
          }
        },
        data: month.map(item => (item.value / 1000).toFixed(2)),
      },
    ]
  });
}
</script>

<style scoped lang="scss">
@import "@/styles/largescreen.scss";

.fault {
  height: 303px;
}
</style>
