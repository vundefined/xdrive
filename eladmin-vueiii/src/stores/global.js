import {defineStore} from "pinia/dist/pinia";
import {ref} from "vue";

export const useGlobalStore = defineStore("global", () => {

  let isCollapse = ref(false);

  function setCollapse() {
    isCollapse.value = !isCollapse.value;
  }

  return {isCollapse, setCollapse}
}, {
  persist: {
    enabled: true,
    strategies: [
      {
        key: "global",
        storage: localStorage
        // storage: sessionStorage
      }
    ]
  }
});
