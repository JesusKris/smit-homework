import './assets/index.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'


const API_PORT = import.meta.env.SECRET_CONTACTS_API_PORT || "8070"
const API_ADDRESS = import.meta.env.SECRET_CONTACTS_API_ADDRESS || "http://localhost"
const API_ENDPOINT = "/api/secret-contacts"

export const SECRET_CONTACTS_API = API_ADDRESS + ":" + API_PORT + API_ENDPOINT
 
const app = createApp(App)

app.use(router)

app.mount('#app')
