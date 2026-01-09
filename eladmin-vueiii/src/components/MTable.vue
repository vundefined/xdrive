<template>
  <ul class="t-head t-row" :style="mtStyle.gtc">
    <li
      class="t-col"
      v-for="headItem in colField"
      :key="headItem.id">
      {{headItem.label}}
    </li>
  </ul>
  <!-- -->
  <div class="t-scroll" :style="mtStyle.tscroll">
    <vue3-seamless-scroll :list="data" hover :modelValue="true" :visibleCount="20">
      <div class="t-body">
        <ul
          class="t-row"
          :style="mtStyle.gtc"
          v-for="rowItem in data"
          :key="rowItem.id">
          <li
            class="t-col"
            v-for="colItem in colField"
            :key="colItem.id"
            :style="colItem.style">
            {{ rowItem[colItem.prop] }}
          </li>
        </ul>
      </div>
    </vue3-seamless-scroll>
  </div>

  <!--
  <div class="t-scroll" :style="mtStyle.tscroll">
    <div class="t-body">
      <ul
        class="t-row"
        :style="mtStyle.gtc"
        v-for="rowItem in data"
        :key="rowItem.id">
        <li
          class="t-col"
          v-for="colItem in colField"
          :key="colItem.id"
          :style="colItem.style">
          {{ rowItem[colItem.prop] }}
        </li>
      </ul>
    </div>
  </div>
  -->
</template>

<script setup>
import {Vue3SeamlessScroll} from "vue3-seamless-scroll";

defineProps({
  data: {
    type: Array,
    required: true,
    default: []
  },
  colField: {
    type: Array,
    required: true,
    default: []
  },
  mtStyle: {
    type: Object,
    required: true,
    default: {
      tscroll: {'height': '300px'},
      gtc: {'grid-template-columns': '120px 160px 170px'}
    }
  }
});
</script>

<style scoped lang="scss">
.t-scroll {
  overflow: hidden;
}

.t-head {
  background-color: #242E43 !important;
  border: 1px solid #334255;
  li {
    font-size: 18px !important;
    font-weight: bold;
  }
}

.t-body {
  .t-row:nth-of-type(odd) {
    background-color: #3f3f3f;
  }
  .t-row:nth-of-type(even) {
    background-color: #e80909;
  }
}

.t-row {
  display: grid;
  background-color: rgba(36,46,67,0.5);
  border-bottom: 1px solid #334255;
  border-left: 1px solid #334255;
  border-right: 1px solid #334255;
}

.t-col {
  height: 30px;
  line-height: 30px;
  text-align: center;
  font-size: 14px;
  color: #469cc5;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
</style>
