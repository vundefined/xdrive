<template>
  <header class="header">
    <div class="left">
      <img src="@/assets/images/icon-address.png" alt="">
      <span class="localtionnn" @click="toManage">哈尔滨</span>
    </div>
    <h1 class="system-title">能源管理系统</h1>
    <div class="date-picker">
      <img class="icon-time" src="@/assets/images/icon_time.png" alt="">
      <span>{{ dayjs(new Date()).format('YYYY-MM-DD') }}&nbsp;{{ getDayInWeekByTime() }} &nbsp;&nbsp;&nbsp;</span>
      <span>{{ time }}</span>
    </div>
  </header>
</template>

<script setup>
import dayjs from "dayjs"
import isoWeek from "dayjs/plugin/isoWeek"
import {onMounted, ref} from "vue"
import {useRouter} from "vue-router";

dayjs.extend(isoWeek)

const time = ref("")
const router = useRouter()

onMounted(() => {
  getTime();
})

function getDayInWeekByTime(isChinese = true) {
  const d = new Date().getDay()
  if (isChinese) {
    return '星期' + '日一二三四五六'.charAt(d)
  } else {
    return d
  }
}

function getTime() {
  time.value = dayjs(new Date()).format('HH:mm:ss')
  setTimeout(() => {
    getTime()
  }, 1000)
}

function toManage() {
  router.push({path: "/home/index"})
}
</script>

<style scoped lang="scss">
@import "@/styles/largescreen.scss";

.header {
  width: $designWidth;
  height: 79px;
  background-image: url("@/assets/images/bg_header.png");
  background-repeat: no-repeat;
  background-size: $designWidth 79px;
  position: relative;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  z-index: 1;

  .left {
    position: absolute;
    left: 27px;
    top: 22px;
    display: flex;
    img {
      vertical-align: sub;
    }
  }
}

.system-title {
  font-size: 41px;
  font-weight: 400;
  color: #FFFFFF;
  user-select: none;
}

.date-picker {
  width: 300px;
  font-size: 18px;
  font-weight: 500;
  color: #FFFFFF;
  position: absolute;
  top: 20px;
  right: 0px;
  display: flex;
  align-items: center;
  user-select: none;
  .icon-time {
    width: 17px;
    height: 18px;
    //vertical-align: middle;
    margin: 0px 6px 0px 0px;
  }
}

.localtionnn {
  color: #64D2FB;
  font-size: 16px;
  cursor: pointer;
  user-select: none;
}
</style>
