<template>
  <div class="source module">
    <h6 class="title title-b">碳排放来源</h6>
    <div style="width: 460px; height: 264px" id="echartsSource"></div>
  </div>
</template>

<script setup>
import {onMounted} from "vue";
import * as echarts from "echarts";

let echartsInstance = null;
onMounted( () => {
  echartsInstance = echarts.init(document.getElementById('echartsSource'));
  initData();
})

function initData() {
  let resData = [
    {
      name: "电",
      value: 78,
    },
    {
      name: "热",
      value: 5,
    }
  ]
  drawEchates(resData);
}

/*
* 绘制图表
* */
function drawEchates(resData) {
  echartsInstance.setOption({
    legend: {
      show: true,
      icon:"circle",
      top: "15%",
      left: '60%',
      width: 50,
      itemGap: 5,
      itemWidth: 28,
      itemHeight: 8,
      formatter: function(name) {
        let _value = 0;
        resData.forEach(item => {
          if (name === item.name) {
            _value = item.value;
          }
        })
        return  name + ' ' + _value
      },
      textStyle: {
        color:'#fff',
        fontSize:14,
      },
      backgroundColor: '#081B3B'
    },
    tooltip: {
      show: true,
      trigger: "item",
      formatter: "{b}:<br>{c}"
    },
    color: ['#20E6A4', '#07D1FA', '#FFD15C', '#9C78EA', '#0783FA'],
    grid: {
      top: '15%',
      left: "0",
      containLabel: false
    },
    series: getSeries(resData)
  })
}

function getSeries(resData) {
  let series = [];
  for (let i = 0; i < resData.length; i++) {
    series.push({
      name: '',
      type: 'pie',
      clockWise: false, // 顺时加载
      hoverAnimation: false, //  鼠标移入变大
      radius: [73 - i * 15 + '%', 68 - i * 15 + '%'],
      center: ["50%", "50%"],
      label: {
        show: false
      },
      itemStyle: {
        label: {
          show: false,
        },
        labelLine: {
          show: false
        },
        borderWidth: 5,
      },
      data: [{
        value: resData[i].value,
        name: resData[i].name
      }, {
        value: 100 - resData[i].value,
        name: '',
        itemStyle: {
          color: "rgba(0,0,0,0)",
          borderWidth: 0
        },
        tooltip: {
          show: false
        },
        hoverAnimation: false
      }]
    });
    series.push({
      name: '',
      type: 'pie',
      silent: true,
      z: 1,
      clockWise: false, //顺时加载
      hoverAnimation: false, //鼠标移入变大
      radius: [73 - i * 15 + '%', 68 - i * 15 + '%'],
      center: ["50%", "50%"],
      label: {
        show: false
      },
      itemStyle: {
        label: {
          show: false,
        },
        labelLine: {
          show: false
        },
        borderWidth: 5,
      },
      data: [{
        value: 7.5,
        itemStyle: {
          color: "rgb(3, 31, 62)",
          borderWidth: 0
        },
        tooltip: {
          show: false
        },
        hoverAnimation: false
      }]
    });
  }
  return series;
}
</script>

<style scoped lang="scss">
@import "@/styles/largescreen.scss";

.source {
  height: 303px;
}
</style>
