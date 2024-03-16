<template>
  <div class="article-item card p-0">
    <!-- 添加 p-0 移除 card 的 padding -->
    <div class="row">
      <div class="col-md-1">
        <img
          v-if="fullImagePath"
          :src="fullImagePath"
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
      <div class="col-md-3 d-flex justify-content-end align-items-center mt-2">
        <button class="btn btn-primary mx-2" @click="editArticle">修改</button>
        <button class="btn btn-danger mx-2" @click="deleteArticle">刪除</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { defineProps, defineEmits, computed  } from "vue";
import { useRouter } from "vue-router";
import { apiDelete } from "@/api/api";
import Swal from "sweetalert2";
const router = useRouter();

const pathUrl = "/v1/articles"
const imageUrl = import.meta.env.VITE_API_AZURE_BLOB_STORAGE;

const fullImagePath = computed(() => {
  return `${imageUrl}${props.article.articleThumbnailImage}`;
});

const props = defineProps(["article"]);
const emit = defineEmits('articleDeleted');
const editArticle = () => {
  router.push({ name: "articlesEdit", params: { id: props.article.articleId } });
};

const deleteArticle = async () => {
  Swal.fire({
    title: "刪除?",
    text: "確定要刪除這篇文章嗎?",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "確定",
    cancelButtonText: "取消", // 設定取消按鈕的文字
  }).then(async (result) => {
    if (result.isConfirmed) {
        const response = await apiDelete(`${pathUrl}/${props.article.articleId}`, router);
        const httpStatus = response.status;
        if (httpStatus == 204) {
          Swal.fire({
          title: "刪除!",
          text: "刪除成功",
          icon: "success",
        }).then(() => {
          emit('articleDeleted','');
        });
        } else {
          Swal.fire({
          title: "錯誤",
          text: "刪除失敗",
          icon: "error",
        }).then(() => {
          emit('articleDeleted','');
        });
        }

    }
  });
  // 處理刪除文章的邏輯
};
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
