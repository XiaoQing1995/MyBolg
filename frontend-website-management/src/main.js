import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router'
import CKEditor from '@ckeditor/ckeditor5-vue';

const app = createApp(App)
app.provide('tinyApiKey', import.meta.env.VITE_API_TINYMCE_API_KEY)
app.use(createPinia())
app.use(router)
app.use(CKEditor)

app.mount('#app')


