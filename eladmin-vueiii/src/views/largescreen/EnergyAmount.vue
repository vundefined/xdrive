<template>
  <ul class="use-number">
    <template v-for="item in availableResource" :key="item.energyType">
      <li class="un-li">
        <img :src="imgFilter(item.energyType)" alt="">
        <span class="right-box">
            <span style="color: #AEDEFA;font-size: 14px">用{{item.name}}量</span>
            <span>
              <span class="t-a">{{item.usedValue}}</span>
              <span class="t-b">&nbsp;{{item.unit}}</span>
            </span>
          </span>
      </li>
    </template>
  </ul>
</template>

<script setup>
import {ref} from 'vue';
import useAssets from "@/hooks/useAssets";

const {getImageUrl} = useAssets();
let availableResource = ref([
  {
    "energyType": 2,
    "name": "水",
    "unit": "t",
    "usedValue": 313.2,
    "availableValue": 0
  },
  {
    "energyType": 1,
    "name": "电",
    "unit": "kW‧h",
    "usedValue": 1536.83,
    "availableValue": 0
  },
  {
    "energyType": 8,
    "name": "热力",
    "unit": "mcal",
    "usedValue": -521,
    "availableValue": 0
  },
  {
    "energyType": 5,
    "name": "压缩空气",
    "unit": "m³",
    "usedValue": 1621.58,
    "availableValue": 0
  }
])

function imgFilter(energyType) {
  if (energyType == 1) {
    return getImageUrl("icon_a_02.png")
  }

  if (energyType == 2) {
    return getImageUrl("icon_a_04.png")
  }

  if (energyType == 5) {
    return getImageUrl("icon_a_01.png")
  }

  if (energyType == 8) {
    return getImageUrl("icon_a_03.png")
  }
}
</script>

<style scoped lang="scss">
.use-number {
  display: flex;
  flex-direction: row;
  position: absolute;
  top: 120px;
  left: 524px;
  li.un-li {
    height: 72px;
    margin: 0px 10px;
    display: flex;
    flex-direction: row;
    align-items: center;
    padding: 0px 0px 0px 20px;
    background-image: url("@/assets/images/icon_a_00.png");
    background-size: 100% 72px;
    background-repeat: no-repeat;
    img {
      width: 48px;
      height: 48px;
    }
  }
}

.right-box {
  width: 120px;
  display: flex;
  flex-direction: column;
  margin: 0px 0px 0px 10px;
}

.t-a {
  display: inline-block;
  font-size: 20px;
  color: #FFFFFF;
  background: linear-gradient(148deg, #7DD5EC 0%, #DCE8FF 47.7783203125%, #E9F1FF 100%);
  -webkit-background-clip: text;
}

.t-b {
  font-size: 14px;
  color: #FFFFFF;
}
</style>
