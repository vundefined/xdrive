import { createApp } from 'vue'
import { createPinia } from 'pinia'
import ElementPlus from "element-plus"
import App from './App.vue'
import router from './router/index.js'
import piniaPersist from "pinia-plugin-persist";

import './styles/base.css'
import './styles/main.css'
import './styles/transition.css'
import './styles/element.css'
import "element-plus/dist/index.css"

const app = createApp(App)
app.use(createPinia().use(piniaPersist))
app.use(router)
app.use(ElementPlus)
app.mount('#app')
