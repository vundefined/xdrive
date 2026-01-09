<template>
  <div class="carbon module">
    <h6 class="title title-b">双碳管理</h6>
    <ul class="cbody">
      <template v-for="(item) in doubleCarbonData" :key="item.id">
        <li>
          <img class="img-size" :src="item.img" alt="">
          <div class="flex-cc">
            <span class="txt-a">{{item.name}}</span>
            <span class="txt-b">{{item.number}} {{item.unit}}</span>
          </div>
        </li>
      </template>
    </ul>
  </div>
</template>

<script setup>
  import {reactive} from "vue";
  import useAssets from "@/hooks/useAssets";

  const {getImageUrl} = useAssets();

  let doubleCarbonData = reactive([
    {
      id: 0,
      name: '总碳排放量',
      unit: 'tCO2',
      number: 0,
      img: getImageUrl("carbon03.png")
    },
    {
      id: 1,
      name: '年度碳排放量',
      unit: 'tCO2',
      number: 3,
      img: getImageUrl("carbon02.png")
    },
    {
      id: 2,
      name: '当月碳排放量',
      unit: 'tCO2',
      number: 5,
      img: getImageUrl("carbon04.png")
    },
    {
      id: 3,
      name: '万元co2排放量',
      unit: 'tCO2',
      number: 8,
      img: getImageUrl("carbon01.png")
    },
  ])
</script>

<style scoped lang="scss">
@import "@/styles/largescreen.scss";

.carbon {
  height: 270px;

  .cbody {
    height: 230px;
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    li {
      width: 232px;
      display: flex;
      flex-direction: row;
      justify-content: end;
      align-items: center;
    }
    .img-size {
      width: 60px;
      height: 67px;
    }
    .txt-a {
      color: #E7F3FF;
      font-size: 15px;
      font-weight: 400;
      opacity: 0.68;
    }
    .txt-b {
      width: 140px;
      text-align: center;
      font-size: 23px;
      font-family: FZDaHei-B02;
      font-weight: 400;
      color: #FFFFFF;
      background: linear-gradient(0deg, #79FFEE 0%, #8CB6FA 0%, #74CAFF 0%, #FFFFFF 100%);
      -webkit-background-clip: text;
      -webkit-text-fill-color: transparent;
    }
  }
}
</style>
