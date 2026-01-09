import {defineStore} from "pinia";
import {whiteList, HOME_URL} from "@/router/index.js";
import router from '@/router/index.js'
import {ref} from "vue";

export const useTabsStore = defineStore("tabs", () => {

  const curTabs = ref(HOME_URL);
  let tabsList = ref([{
    path: HOME_URL,
    title: "首页",
    close: false
  }]);

  function addTabs(tabItem) {
    if (whiteList.includes(tabItem.path)) return;
    if (tabsList.value.every((item) => item.path !== tabItem.path)) {
      tabsList.value.push(tabItem);
    }
    curTabs.value = tabItem.path;
  }

  function removeTabs(tabPath) {
    tabsList.value.forEach((item, index) => {
      if (item.path == tabPath) {
        tabsList.value.splice(index, 1);
      }
      if (curTabs.value == tabPath) {
        let nextTab = tabsList.value[index + 1] || tabsList.value[index - 1];
        router.push(nextTab.path);
      }
    });
  }

  function closeMultipleTab(curTabs) {
    tabsList.value = tabsList.value.filter((item) => {
      return item.path === curTabs || item.path === HOME_URL;
    });
  }

  function goHome() {
    router.push(HOME_URL);
    curTabs.value = HOME_URL;
  }

  return {curTabs, tabsList, addTabs, removeTabs, closeMultipleTab, goHome};
});
