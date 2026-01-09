<template>
  <div class="cost module">
    <h6 class="title title-b">成本占比</h6>
    <div style="width: 460px; height: 264px" id="echartsCost"></div>
  </div>
</template>

<script setup>
import {onMounted} from "vue";
import * as echarts from "echarts";

let echartsInstance = null;
onMounted(() => {
  echartsInstance = echarts.init(document.getElementById('echartsCost'));
  initData()
})

function initData() {
  drawEchates([
    {
      "energyType": 1,
      "name": "电",
      "unit": "元",
      "value": 845923.63,
      "categoryCode": null,
      "description": null
    },
    {
      "energyType": 2,
      "name": "水",
      "unit": "元",
      "value": 272946.81,
      "categoryCode": null,
      "description": null
    },
    {
      "energyType": 8,
      "name": "热力",
      "unit": "元",
      "value": 0,
      "categoryCode": null,
      "description": null
    },
    {
      "energyType": 5,
      "name": "压缩空气",
      "unit": "元",
      "value": 0,
      "categoryCode": null,
      "description": null
    }
  ]);
}

/*
* 绘制图表
* */
function drawEchates(seriesData) {
  echartsInstance.setOption({
    legend: {
      top: 'middle',
      left: '54%',
      orient: 'vertical',
      textStyle: {
        color: '#95D2FF'
      },
      data: seriesData,
      formatter: function(name) {
        let v;
        seriesData.forEach((item) => {
          if (item.name === name) {
            v = item.value;
          }
        });
        return name + ' ' + v;
      }
    },
    tooltip: {
      show: true,
      backgroundColor: '#04877C',
      textStyle: {
        color: '#ffffff'
      },
      borderWidth: 0
    },
    series: [
      {
        type: 'pie',
        radius: [50, 100],
        center: ['30%', '50%'],
        label: {
          show: false
        },
        labelLine: {
          show: false
        },
        color: ['#07D1FA', '#20E6A4', '#0783FA', '#FFD15C'],
        selectedMode: true,
        data: seriesData
      }
    ]
  });
}
</script>

<style scoped lang="scss">
@import "@/styles/largescreen.scss";

.cost {
  height: 303px;
}
</style>
