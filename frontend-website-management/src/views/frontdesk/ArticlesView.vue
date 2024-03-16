<template>
  <div class="article-item-container">
    <div class="row">
      <div class="col-md-4" v-for="article in articles" :key="article.id">
        <div>
          <ArticleItem :article="article" />
        </div>
      </div>
    </div>
  </div>
  <!-- Bootstrap 分頁元件 -->
  <Page
    v-if="showPage"
    :currentPage="currentPage"
    :totalPages="totalPages"
    :changePage="changePage"
  />
</template>

<style scoped>
.article-item-container {
  margin-top: 2%;
  margin-left: 20%;
  margin-right: 20%;
}
</style>

<script setup>
import { ref, onMounted, watchEffect, watch, nextTick } from "vue";
import { apiGetForFrontDeskUse } from "@/api/api";
import { useRouter, useRoute } from "vue-router";
import ArticleItem from "@/components/frontdesk/ArticleItem.vue";
import Page from "@/components/unit/Page.vue";

const urlPathArticles = "/v1/articles";
const urlPathArticleClasses = "/v1/articleclasses";

const router = useRouter();
const route = useRoute();
const currentPage = ref(1);
const totalPages = ref(0);

const pageSize = 9; // 每頁顯示的文章數量
const articles = ref([]);
const categoryOptions = ref([]);

const showPage = ref(false);

onMounted(() => {
  fetchCategoryOptions();
});

const getArticles = async () => {
  const response = await apiGetForFrontDeskUse(
    `${urlPathArticles}?page=${currentPage.value - 1}&size=${pageSize}`,
    router
  );
  articles.value = response.data.content;
  totalPages.value = response.data.totalPages;
  showPageWhenDOMRender()
};

const getArticlesByClassId = async () => {
  const articleClassId = route.params.id;
  const response = await apiGetForFrontDeskUse(
    `${urlPathArticles}/class/${articleClassId}?page=${
      currentPage.value - 1
    }&size=${pageSize}`,
    router
  );
  articles.value = response.data.content;
  totalPages.value = response.data.totalPages;
  showPageWhenDOMRender()
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

const showPageWhenDOMRender = () => {
  nextTick(() => {
    showPage.value = true;
  });
};

const fetchCategoryOptions = async () => {
  const response = await apiGetForFrontDeskUse(urlPathArticleClasses, router);
  categoryOptions.value = response.data;
};

// 當 route 變化時執行
watchEffect(() => {
  if (route.name === "home.articles") {
    getArticles();
  } else if (
    route.name === "home.articles.classes.id" ||
    route.name === "home.articlesDetails"
  ) {
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

<style scoped></style>
