<template>
  <div>
    <ArticleItem v-for="article in articles" :key="article.id" :article="article" />

    <!-- Bootstrap 分頁元件 -->
    <Page :currentPage="currentPage" :totalPages="totalPages" :changePage="changePage" />
  </div>
</template>

<script setup>
import { ref, onMounted, watchEffect, watch } from "vue";
import { apiGet } from "@/api/api";
import { useRouter, useRoute } from "vue-router";
import ArticleItem from "@/components/frontdesk/ArticleItem.vue";
import Page from "@/components/unit/Page.vue";

const urlPathArticles = "/v1/articles";
const urlPathArticleClasses = "/v1/articleclasses";

const router = useRouter();
const route = useRoute();
const currentPage = ref(1);
const totalPages = ref(0);

const pageSize = 10; // 每頁顯示的文章數量
const articles = ref([]);
const categoryOptions = ref([]);

onMounted(() => {
  getArticles();
  fetchCategoryOptions();
  console.log(route);
  if (route.name === "home.articles.classes.id") {
    console.log(route.params.id);
  }
});

const getArticles = async () => {
  const response = await apiGet(
    `${urlPathArticles}?page=${currentPage.value - 1}&size=${pageSize}`,
    router
  );
  articles.value = response.data.content;
  totalPages.value = response.data.totalPages;
};

const getArticlesByClassId = async () => {
  const articleClassId = route.params.id;
  const response = await apiGet(
    `${urlPathArticles}/class/${articleClassId}?page=${
      currentPage.value - 1
    }&size=${pageSize}`,
    router
  );
  articles.value = response.data.content;
  totalPages.value = response.data.totalPages;
};

const changePage = async (page) => {
  if (page >= 1 && page <= totalPages.value) {
    currentPage.value = page;
    if (route.name === "home.articles") {
      await getArticles();
    } else if (route.name === "home.articles.classes.id") {
      await getArticlesByClassId();
    }
  }
};

const fetchCategoryOptions = async () => {
  const response = await apiGet(urlPathArticleClasses, router);
  categoryOptions.value = response.data;
};

// 當 route 變化時執行
watchEffect(() => {
  console.log("watchEffect");
  if (route.name === "home.articles") {
    getArticles();
  } else if (route.name === "home.articles.classes.id") {
    getArticlesByClassId();
  }
});

watch(
  () => route.params.id,
  (newId, oldId) => {
    currentPage.value = 1;
  }
);
</script>
