<template>
  <div class="device module">
    <h6 class="title title-b">设备在线状态</h6>
    <ul class="dbody">
      <template v-for="item in deviceData" :key="item.id">
        <li class="item">
          <img class="iimg" :src="filterImg(item.deviceType)" alt="">
          <ul class="width-a">
            <li class="txt-c">设备名称</li>
            <li class="txt-f">{{filterType(item.deviceType)}}</li>
          </ul>
          <ul class="width-a">
            <li class="txt-c">在线</li>
            <li>
              <span class="txt-d">{{item.inCount}}</span>
              <span class="txt-e">台</span>
            </li>
          </ul>
          <ul class="width-a">
            <li class="txt-c">离线</li>
            <li>
              <span class="txt-d">{{item.outCount}}</span>
              <span class="txt-e">台</span>
            </li>
          </ul>
        </li>
      </template>
    </ul>
  </div>
</template>

<script setup>
  import {ref, onMounted} from "vue";
  import useAssets from "@/hooks/useAssets";

  const {getImageUrl} = useAssets();

  const deviceData = ref([
    {
      inCount: 1,
      outCount: 2,
      deviceType: 0
    },
    {
      inCount: 1,
      outCount: 2,
      deviceType: 1
    },
    {
      inCount: 1,
      outCount: 2,
      deviceType: 2
    },
  ])

  onMounted(() => {})

  function filterType(type) {
    if (type === 0) {
      return '电';
    }
    if (type === 1) {
      return '水';
    }
    if (type === 2) {
      return '压缩空气';
    }
  }
  function filterImg(type) {
    if (type === 0) {
      return getImageUrl("device01.png");
    }
    if (type === 1) {
      return getImageUrl("device02.png");
    }
    if (type === 2) {
      return getImageUrl("device03.png");
    }
    return getImageUrl("device03.png");
  }
</script>

<style scoped lang="scss">
@import "@/styles/largescreen.scss";

.device {
  height: 350px;
}

.dbody {
  height: 311px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  .item {
    width: 411px;
    height: 64px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    background-image: url("@/assets/images/di.png");
    background-repeat: no-repeat;
    background-size: 411px 27px;
    background-position: bottom;
    padding: 0px 20px;
    margin: 0px 0px 24px 0px;
    .iimg {
      width: 55px;
      height: 55px;
    }
  }
}

.color-a {
  color: #53D1FB;
}

.txt-a {
  font-size: 28px;
}

.txt-b {
  font-size: 13px;
  color: #FFFFFF;
}

.txt-c {
  font-size: 14px;
  color: #8DCDFC;
}

.txt-d {
  font-size: 20px;
  color: #E2F3FF;
}

.txt-e {
  color: #E2F3FF;
}

.txt-f {
  font-size: 16px;
  color: #E2F3FF;
}

.width-a {
  width: 100px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
</style>
