<template>
    <div>
      <p>home index</p>
      <p>I18n--> {{t('common.hello')}}</p>
      <!--<MTable :data="mtableData" :col-field="mtableField" :mt-style="mtableStyle"></MTable>-->
    </div>
</template>

<script setup>
import {useRoute} from "vue-router";
import {onMounted, reactive} from "vue";
import { useI18n } from 'vue-i18n';
import MTable from "@/components/MTable.vue";

const { t } = useI18n();
const route = useRoute();
let mtableStyle = {
  tscroll: {'height': '376px'},
  gtc: {'grid-template-columns': '1fr 1fr 1fr'}
};
let mtableField = reactive([
  {
    id: 1,
    label: '人员',
    prop: 'name',
    style: {'color':'#87BDFF'},
  },
  {
    id: 2,
    label: '进出时间',
    prop: 'openDoorTime',
    style: {'color':'#C084FC'},
  },
  {
    id: 3,
    label: '离开时间',
    prop: 'expectCloseDoorTime',
    style: {'color':'#24D3EE'},
  },
]);
let mtableData = reactive([
  {
    "id": 154,
    "code": "461e014a-3137-44b8-8bff-cbc4797b08a0",
    "name": "老王(不能动abcdefghijkmlnopkrstuvwxyz",
    "openDoorTime": "2024-03-26 14:43:16",
    "expectCloseDoorTime": "2024-03-26 14:43:16",
  },
  {
    "id": 153,
    "code": "defa2221-4879-4a8d-ac30-ce09c015e833",
    "name": "老王(不能动！)",
    "openDoorTime": "2024-03-26 14:43:12",
    "expectCloseDoorTime": "2024-03-26 14:43:16",
  },
  {
    "id": 152,
    "code": "67f83a55-4388-457f-933f-79cdbfa9c548",
    "name": "老王(不能动！)",
    "openDoorTime": "2022-10-09 17:16:12",
    "expectCloseDoorTime": "2024-03-26 14:43:16",
  },
  {
    "id": 151,
    "code": "8c3b0f23-de4c-4e31-8af3-1149a7637547",
    "name": "老王(不能动！)",
    "openDoorTime": "2021-12-24 14:22:32",
    "expectCloseDoorTime": "2024-03-26 14:43:16",
  },
  {
    "id": 150,
    "code": "1b3679c5-d5ad-4e65-bc40-a80e853d4832",
    "name": "老王(不能动！)",
    "openDoorTime": "2021-12-24 13:39:40",
    "expectCloseDoorTime": "2024-03-26 14:43:16",
  },
  {
    "id": 149,
    "code": "33d7f6e0-f9b1-4c51-9ff4-0c89767f48d0",
    "name": "老王(不能动！)",
    "openDoorTime": "2021-11-15 10:12:32",
    "expectCloseDoorTime": "2024-03-26 14:43:16",
  },
  {
    "id": 148,
    "code": "05d66ea7-3ee4-4496-9346-bd4070663544",
    "name": "老王(不能动！)",
    "openDoorTime": "2021-11-02 17:24:31",
    "expectCloseDoorTime": "2024-03-26 14:43:16",
  },
  {
    "id": 147,
    "code": "fbccdcaf-e094-4358-8536-62e90a2952d4",
    "name": "老王(不能动！)",
    "openDoorTime": "2021-11-01 16:36:41",
    "expectCloseDoorTime": "2024-03-26 14:43:16",
  },
]);

onMounted(() => {
  console.log(route.query, 'I18n-->', t('common.hello'))
})
</script>

<style scoped>
</style>
