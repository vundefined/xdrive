<template>
  <div class="cost module">
    <h6 class="title title-b">本周试验计划</h6>
    <div class="cbody">
      <ul class="mlist">
        <li class="mlitem">
          <img class="left" src="@/assets/images/icon_b_01.png" alt="">
          <div class="right">
            <p>本周试验计划</p>
            <p>34次</p>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>
</script>

<style scoped lang="scss">
@import "@/styles/largescreen.scss";

.cost {
  height: 303px;
}

.cbody {
  height: 264px;
  padding: 0px 12px;
}

.mlist {
  height: 90px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  .mlitem {
    width: 207px;
    height: 73px;
    display: flex;
    flex-direction: row;
    background-color: #093760;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    .left {
      width: 33px;
      height: 38px;
      margin-right: 10px;
    }
    .right {
      p:nth-child(1) {
        color: #EEF1F5;
        font-size: 12px;
        font-weight: 400;
      }
      p:nth-child(2) {
        font-weight: 400;
        color: #EEF1F5;
        background: linear-gradient(0deg, #3DEAFF 0%, #FFFFFF 100%);
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
      }
    }
  }
}
</style>
