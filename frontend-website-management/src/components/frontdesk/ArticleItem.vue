<template>
  <div class="article-item card p-0">
    <div class="row">
      <div class="col-md-12">
        <router-link
          :to="`/home/articlesDetails/${article.articleId}`"
          class="custom-link"
        >
          <div class="img-container">
            <img
              v-if="article.articleImg"
              :src="`data:image/jpeg;base64,${article.articleImg}`"
              class="img-fluid"
              alt="Article Image"
            />
          </div>
        </router-link>
      </div>
      <div class="col-md-12">
        <div class="card-body">
          <div class="date-category">
            <p>{{ article.articleDate }}</p>
            <!-- 移除 router-link 的默认样式，添加自定义样式 -->
            <router-link
              :to="`/home/articles/classes/${article.articleClass.articleClassId}`"
              class="custom-link"
            >
              <p>{{ article.articleClass.articleClassName }}</p>
            </router-link>
          </div>
          <router-link
            :to="`/home/articlesDetails/${article.articleId}`"
            class="custom-link"
          >
            <h5 class="article-title">{{ article.articleTitle }}</h5>
          </router-link>
          <p  style="max-height: 15vh;"
            class="card-text content article-content"
            v-html="article.articleContent"
          ></p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { defineProps } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();
const props = defineProps(["article"]);

// const truncateContent = (content, maxLength) => {
//   if (content.length > maxLength) {
//     return content.slice(0, maxLength) + "...";
//   }
//   return content;
// };
</script>

<style scoped>
.article-title {
  font-size: 1.5rem;
  font-weight: bold; /* 将文字改成粗体 */
}

.article-item {
  margin-bottom: 20px;
  border: none;
  border-radius: 5px;
}

.img-container {
  width: 100%;
  height: 0;
  padding-top: 75%;
  position: relative;
}

.img-fluid {
  width: 100%;
  height: 100%;
  object-fit: cover;
  position: absolute;
  top: 0;
  left: 0;
}

.card-body {
  padding: 15px;
}

.date-category {
  font-size: 14px;
  color: #555;
  margin-bottom: 10px;
  display: flex;
  justify-content: space-between;
}

.card-title {
  font-size: 1.5rem;
  margin-bottom: 10px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.content {
  font-size: 14px;
  color: #333;
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

.article-content {
  overflow: hidden;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 3; /* 限制显示的行数 */
}
</style>
