<template>
  <div class="article-details" v-if="article">
    <h1 class="article-title">{{ article.articleTitle }}</h1>
    <div class="article-meta">
      <div class="meta-item">
        <p>{{ article.articleDate }}</p>
        <p v-if="article.articleClass" class="category">
          <router-link
            :to="`/home/articles/classes/${article.articleClass.articleClassId}`"
            class="custom-link"
          >
            {{ article.articleClass.articleClassName }}
          </router-link>
        </p>
      </div>
    </div>
    <img
      v-if="fullImagePath"
      :src="fullImagePath"
      class="img-fluid"
      alt="Article Image"
    />
    <div class="article-content" v-html="article.articleContent"></div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import { apiGetForFrontDeskUse } from "@/api/api";
import { URL_PATH_ARTICLES } from "@/stores/urlPath";

const articleId = ref();
const route = useRoute();
const router = useRouter();
const article = ref(null);

const imageUrl = import.meta.env.VITE_API_SERVERURL;

const fullImagePath = computed(() => {
  return `${imageUrl}${article.value.articleImagePath}`;
});

const getArticle = async () => {
  const response = await apiGetForFrontDeskUse(
    `${URL_PATH_ARTICLES}/${articleId.value}`,
    router
  );
  article.value = response.data;
};

onMounted(() => {
  articleId.value = route.params.id;
  getArticle();
});
</script>

<style scoped>
.article-details {
  max-width: 100vh;
  margin: 0 auto;
  padding: 20px;
}

.article-title {
  font-size: 2rem;
  margin-bottom: 10px;
  word-wrap: break-word; /* 將長單字強制換行 */
}

.article-meta {
  font-size: 14px;
  color: #555;
  margin-bottom: 20px;
}

.meta-item {
  display: flex;
  justify-content: flex-start; /* 讓內容靠左對齊 */
}

.category {
  margin-left: 10px; /* 加入左邊距，調整分類與日期的間距 */
}

.article-image {
  width: 100%;
  height: auto;
  margin-bottom: 20px;
}

.article-content {
  font-size: 16px;
  line-height: 1.6;
  color: #333;
  word-wrap: break-word; /* 將長單字強制換行 */
}

/* 添加自定义链接样式 */
.custom-link {
  color: black;
  text-decoration: none;
  cursor: pointer;
  transition: color 0.3s ease-in-out;
}

/* 鼠标悬停时更改颜色 */
.custom-link:hover {
  color: orange;
}
</style>
