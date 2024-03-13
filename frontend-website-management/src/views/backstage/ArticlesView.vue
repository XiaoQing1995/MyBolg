<template>
  <div>
    <select v-model="selectedCategory" @change="filterByCategory">
      <option value="0">所有分類</option>
      <option
        v-for="categoryOption in categoryOptions"
        :key="categoryOption.articleClassId"
        :value="categoryOption.articleClassId"
      >
        {{ categoryOption.articleClassName }}
      </option>
    </select>

    <ArticleItem
      v-for="article in articles"
      :key="article.id"
      :article="article"
      @articleDeleted="refreshViewGetArticles"
    />

    <!-- Bootstrap 分頁元件 -->
    <Page v-if="showPage" :currentPage="currentPage" :totalPages="totalPages" :changePage="changePage" />
  </div>
</template>

<script setup>
import { ref, onMounted,nextTick } from "vue";
import { apiGet } from "@/api/api";
import { useRouter } from "vue-router";
import ArticleItem from "@/components/backstage/ArticleItem.vue";
import Page from "@/components/unit/Page.vue";

const urlPathArticle = "/v1/articles";
const urlPathArticleClasses = "/v1/articleclasses";
const router = useRouter();
const articles = ref([]);
const currentPage = ref(1);
const totalPages = ref(0);

const pageSize = 10; // 每頁顯示的文章數量

const categoryOptions = ref([]);
const selectedCategory = ref("0");

const showPage = ref(false);

onMounted(() => {
  getArticles();
  fetchCategoryOptions();
});

const getArticles = async () => {
  let response = null;

  // 如果是 0 搜尋所有分類，否則依照文章分類搜尋
  if (selectedCategory.value == 0) {
    response = await apiGet(
      `${urlPathArticle}?page=${currentPage.value - 1}&size=${pageSize}`,
      router
    );
  } else {
    response = await apiGet(
      `${urlPathArticle}/class/${selectedCategory.value}?page=${
        currentPage.value - 1
      }&size=${pageSize}`,
      router
    );
  }

  articles.value = response.data.content;
  totalPages.value = response.data.totalPages;

  showPageWhenDOMRender()
};

const refreshViewGetArticles = () => {
  getArticles();
};

const changePage = async (page) => {
  if (page >= 1 && page <= totalPages.value) {
    currentPage.value = page;
    await getArticles();
  }
};

const fetchCategoryOptions = async () => {
  const response = await apiGet(urlPathArticleClasses, router);
  categoryOptions.value = response.data;
};

const filterByCategory = () => {
  console.log(selectedCategory.value);
  currentPage.value = 1;
  getArticles();
};

const showPageWhenDOMRender = () => {
  nextTick(() => {
    showPage.value = true;
  });
};
</script>
