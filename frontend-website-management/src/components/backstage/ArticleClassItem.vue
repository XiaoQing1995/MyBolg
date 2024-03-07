<template>
  <div class="article-item card p-0">
    <!-- 添加 p-0 移除 card 的 padding -->
    <div class="row">
      <div class="col-md-8">
        <div class="card-body">
          <p class="card-text">{{ articleClass.articleClassName }}</p>
        </div>
      </div>
      <div class="col-md-3 d-flex justify-content-end align-items-center mt-2">
        <button class="btn btn-primary mx-2" @click="triggerEditArticleClass">
          修改
        </button>
        <button class="btn btn-danger mx-2" @click="deleteArticleClass">刪除</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { defineProps, defineEmits, ref } from "vue";
import { useRouter } from "vue-router";
import { apiDelete, apiUpdate } from "@/api/api";
import { whenErrorCheckHttpStatus } from "@/plugin/httpErrorPlugin";
import Swal from "sweetalert2";
const router = useRouter();

const props = defineProps(["articleClass"]);
const emit = defineEmits(["deleteArticleClass", "updateArticleClass"]);

const urlPath = "/v1/articleclasses";

const triggerEditArticleClass = () => {
  // 觸發 editArticle 事件，通知父組件
  Swal.fire({
    title: "分類名稱",
    input: "text",
    inputAttributes: {
      autocapitalize: "off",
    },
    inputValue: props.articleClass.articleClassName,
    showCancelButton: true,
    confirmButtonText: "儲存",
    cancelButtonText: "取消",
  }).then(async (result) => {
    if (result.isConfirmed) {
      // 在這裡處理使用者按下確定後的邏輯
      const enteredValue = result.value;
      const editArticleClass = ref({
        articleClassId: props.articleClass.articleClassId,
        articleClassName: enteredValue,
      });
      try {
        console.log(editArticleClass);
        const response = await apiUpdate(urlPath, editArticleClass.value, router);
        const httpStatus = response.status;
        if (httpStatus == 200) {
          Swal.fire({
            title: "修改成功",
            icon: "success",
            confirmButtonText: "確定",
          }).then(() => {
            emit("updateArticleClass");
          });
        }
      } catch (error) {
        whenErrorCheckHttpStatus(error, router);
      }
    }
  });
};

const deleteArticleClass = () => {
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
      const response = await apiDelete(
        `${urlPath}/${props.articleClass.articleClassId}`,
        router
      );
      const httpStatus = response?.status
      if (httpStatus == 204) {
        Swal.fire({
        title: "刪除!",
        text: "刪除成功",
        icon: "success",
      }).then(() => {
        emit("deleteArticleClass");
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
