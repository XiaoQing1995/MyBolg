<template>
  <div class="article-item card p-0">
    <!-- 添加 p-0 移除 card 的 padding -->
    <div class="row">
      <div class="col-md-1">
        <img
          v-if="article.articleImg"
          :src="`data:image/jpeg;base64,${article.articleImg}`"
          class="img-fluid"
          alt="Article Image"
        />
      </div>
      <div class="col-md-8">
        <div class="card-body">
          <p class="card-text">文章標題: {{ article.articleTitle }}</p>
          <p class="card-text">文章類別: {{ article.articleClass.articleClassName }}</p>
          <p class="card-text">發文日期: {{ article.articleDate }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { defineProps } from "vue";

const props = defineProps(["article"]);


</script>

<style scoped>
.article-item {
  margin-bottom: 20px;
}

.img-fluid {
  max-width: 100%;
  height: auto;
}
</style>
