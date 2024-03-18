import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router'
import CKEditor from '@ckeditor/ckeditor5-vue';
app.provide('tinyApiKey', import.meta.env.VITE_API_TINYMCE_API_KEY)
const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(CKEditor)

app.mount('#app')


