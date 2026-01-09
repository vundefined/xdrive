import {createApp, watch} from 'vue';
import {createPinia} from 'pinia';
import App from './App.vue';
import router from './router/index.js';
import piniaPersist from "pinia-plugin-persist";
import i18n from "./language/index.js";
import {useGlobalStore} from "@/stores/global";

import './styles/base.scss';
import './styles/transition.css';
// import './styles/element/index.scss'
import "element-plus/theme-chalk/src/index.scss";
// import 'element-plus/theme-chalk/dark/css-vars.css'

const app = createApp(App);
app.use(createPinia().use(piniaPersist));
app.use(router);

const globalStore = useGlobalStore();
i18n.global.locale.value = globalStore.tcI18n;

watch(() => globalStore.tcI18n, (newLocale) => {
  i18n.global.locale.value = newLocale;
});

app.use(i18n);
app.mount('#app');
app.directive('permission', {
  mounted(el, binding) {
    if (binding.value == true) {
      el.remove()
    }
  }
})
