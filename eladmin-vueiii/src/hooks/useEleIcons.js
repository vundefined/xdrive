import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import {onBeforeMount, ref} from "vue";

export const useEleIcons = () => {
  const iconName = ref([]);
  const iconComponent = ref([]);

  function initIcon() {
    let _keys = [];
    let _components = [];
    for (let [key, component] of Object.entries(ElementPlusIconsVue)) {
      _keys.push(key);
      _components.push(component);
    }
    iconName.value = _keys;
    iconComponent.value = _components;
  }

  onBeforeMount(() => {
    initIcon();
  });

  return {iconName, iconComponent}
}
