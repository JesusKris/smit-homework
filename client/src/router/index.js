import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
      meta: {
        title: 'Secret Contacts'
      }
    },
  ]
})


router.beforeEach((to, from) => {
  if (typeof to.meta.title === 'string') {
    document.title = to.meta.title || 'Secret Contacts'
  }
})


export default router