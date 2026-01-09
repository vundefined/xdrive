import {defineStore} from "pinia/dist/pinia";
import {ref} from "vue";

export const useGlobalStore = defineStore("global", () => {

  let isCollapse = ref(false);
  let tcI18n = ref('zh-cn'); // 默认初始语言，可选值"<zh-cn|en>"，默认 zh-cn
  let theme = ref('dark');

  function setTcI18n(value) {
    tcI18n.value = value;
  }

  function setTheme(value) {
    theme.value = value;
  }

  function setCollapse() {
    isCollapse.value = !isCollapse.value;
  }

  return {isCollapse, setCollapse, tcI18n, setTcI18n, theme, setTheme}
}, {
  persist: {
    enabled: true,
    strategies: [
      {
        key: "global",
        // storage: localStorage
        storage: sessionStorage
      }
    ]
  }
});
