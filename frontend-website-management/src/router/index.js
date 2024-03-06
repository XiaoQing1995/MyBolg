import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../views/HomeView.vue')
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/LoginView.vue')
    },
    {
      path: '/backstage',
      name: 'backstage',
      component: () => import('../views/BackstageView.vue'),
      children: [
        {
          path: 'articles',
          name: 'articles',
          component: () => import('../views/ArticlesView.vue')
        },
        {
          path: 'accountsManagement',
          name: 'accountsManagement',
          component: () => import('../views/AccountsManagementView.vue')
        },
        {
          path: 'articlesEdit/:id',
          name: 'articlesEdit',
          component: () => import('../views/ArticlesEditView.vue')
        },
        {
          path: 'articlesInsert',
          name: 'articlesInsert',
          component: () => import('../views/ArticlesInsertView.vue')
        },
        {
          path: 'articleClassesManagement',
          name: 'articleClassesManagement',
          component: () => import('../views/ArticleClassesManagementView.vue')
        },
      ]
    },
  ]
})

export default router
