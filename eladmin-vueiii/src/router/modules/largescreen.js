const LargeScreen = () => import("@/views/largescreen/LargeScreen.vue");

export default {
  path: "/largescreen",
  name: "LargeScreen",
  component: LargeScreen,
  children: [
    {
      path: 'board',
      name: 'BoardView',
      component: () => import('@/views/largescreen/BoardView/BoardView.vue'),
      meta: {index: 0}
    },
    {
      path: 'energy',
      name: 'EnergyView',
      component: () => import('@/views/largescreen/EnergyView/EnergyView.vue'),
      meta: {index: 1}
    }
  ]
}
