import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path:'/',
      serve: "/index.html",
      statusCode:200,
      redirect: '/home/articles'
    },
    {
      "navigationFallback": {
        "rewrite": "/index.html"
      }
    },
    {
      path: '/home',
      name: 'home',
      component: () => import('../views/frontdesk/HomeView.vue'),
      redirect: '/home/articles',
      children: [
        {
          path: 'articles',
          name: 'home.articles',
          component: () => import('../views/frontdesk/ArticlesView.vue'),
        },
        {
          path: 'articles/:id',
          name: 'home.articles.id',
          component: () => import('../views/frontdesk/ArticlesDetailsView.vue'),
        },
        {
          path: 'articles/classes/:id',
          name: 'home.articles.classes.id',
          component: () => import('../views/frontdesk/ArticlesView.vue'),
        },
        {
          path: 'articlesDetails/:id',
          name: 'home.articlesDetails',
          component: () => import('../views/frontdesk/ArticlesDetailsView.vue')
        },
        {
          path: 'aboutMe',
          name: 'home.aboutMe',
          component: () => import('../views/frontdesk/AboutMeView.vue')

        }
      ]
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
    {
      path: '/imageUploadTest',
      name: 'imageUploadTest',
      component: () => import('../views/backstage/ImageUploadTestView.vue')
    },
    {
      path: '/imageDownloadTest',
      name: 'imageDownloadTest',
      component: () => import('../views/backstage/ImageDownloadTestView.vue')
    },
  ]
})

export default router
