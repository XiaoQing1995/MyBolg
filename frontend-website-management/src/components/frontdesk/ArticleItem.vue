<template>
  <div class="container mt-5">
    <div class="article-item card p-0">
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
        <div class="col-md-3 d-flex justify-content-end align-items-center mt-2">
          <button class="btn btn-primary mx-2" @click="editArticle">修改</button>
          <button class="btn btn-danger mx-2" @click="deleteArticle">刪除</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
// 移除 defineEmits 的 import
import { defineProps } from "vue";
import { useRouter } from "vue-router";
import { apiDelete } from "@/api/api";
import { whenErrorCheckHttpStatus } from "@/plugin/httpErrorPlugin";
import Swal from "sweetalert2";
const router = useRouter();

const pathUrl = "/v1/articles";

// 使用 defineProps 取得 article props
const props = defineProps(["article"]);

// 不需要再手動引入 defineEmits，直接在 setup 中使用
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
    cancelButtonText: "取消",
  }).then(async (result) => {
    if (result.isConfirmed) {
      try {
        await apiDelete(`${pathUrl}/${props.article.articleId}`, router);
        Swal.fire({
          title: "刪除!",
          text: "刪除成功",
          icon: "success",
        }).then(() => {
          // 使用 setup 中的 context.emit 來發送事件
          emit("articleDeleted");
        });
      } catch (error) {
        whenErrorCheckHttpStatus(error, router);
      }
    }
  });
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
