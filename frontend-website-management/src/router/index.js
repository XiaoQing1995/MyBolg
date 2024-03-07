import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../views/frontdesk/HomeView.vue')
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/backstage/LoginView.vue')
    },
    {
      path: '/backstage',
      name: 'backstage',
      component: () => import('../views/backstage/BackstageView.vue'),
      children: [
        {
          path: 'articles',
          name: 'articles',
          component: () => import('../views/backstage/ArticlesView.vue')
        },
        {
          path: 'accountsManagement',
          name: 'accountsManagement',
          component: () => import('../views/backstage/AccountsManagementView.vue')
        },
        {
          path: 'articlesEdit/:id',
          name: 'articlesEdit',
          component: () => import('../views/backstage/ArticlesEditView.vue')
        },
        {
          path: 'articlesInsert',
          name: 'articlesInsert',
          component: () => import('../views/backstage/ArticlesInsertView.vue')
        },
        {
          path: 'articleClassesManagement',
          name: 'articleClassesManagement',
          component: () => import('../views/backstage/ArticleClassesManagementView.vue')
        },
      ]
    },
  ]
})

export default router
