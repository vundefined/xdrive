import {reactive, ref} from "vue";

export default function useTimePick() {
  let timePickIndex = ref(1)
  let energyIndex = ref(0)

  let energy = reactive([
    {
      id: 0,
      name: '全部',
      type: 0,
    },
    {
      id: 1,
      name: '水',
      type: 2,
    },
    {
      id: 2,
      name: '电',
      type: 1,
    },
    {
      id: 3,
      name: '油',
      type: 6,
    },
    {
      id: 4,
      name: '气',
      type: 5,
    }
  ])
  let timePick = reactive([
    {
      type: 3,
      name: '日',
    },
    {
      type: 4,
      name: '月',
    },
    {
      type: 5,
      name: '年',
    }
  ])

  return {timePick, timePickIndex, energy, energyIndex}
}
