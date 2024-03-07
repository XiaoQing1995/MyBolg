<template>
  <div>
    <Navbar />
    <div class="container mt-5">
      <ArticleItem :article="articleData" @articleDeleted="handleArticleDeleted" />
    </div>
  </div>
</template>

<script setup>
import Navbar from "@/components/frontdesk/Navbar.vue";
import ArticleItem from "@/components/frontdesk/ArticleItem.vue";

// 假資料
const articleData = {
  articleId: 1,
  articleTitle: "Learning Vue.js",
  articleClass: {
    articleClassName: "Technology",
    articleClassId: 1,
  },
  articleDate: "2024-03-01",
  articleImg: "base64-encoded-image-string", // 假設這是一張 base64 編碼的圖片
};

const handleArticleDeleted = () => {
  // 處理文章被刪除後的邏輯
};
</script>

<style scoped>
/* 其他樣式 */
</style>
