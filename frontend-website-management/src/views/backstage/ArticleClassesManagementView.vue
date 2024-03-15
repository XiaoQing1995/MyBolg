<template>
  <button class="btn btn-success" @click="insertArticleClass">新增分類</button>
  <ArticleClassItem
    v-for="articleClass in articleClasses"
    :key="articleClass.articleClassId"
    :articleClass="articleClass"
    @deleteArticleClass="refreshViewGetArticleClasses"
    @updateArticleClass="refreshViewGetArticleClasses"
  />
</template>

<script setup>
import { ref, onMounted } from "vue";
import { apiGet, apiPost } from "@/api/api";
import ArticleClassItem from "@/components/backstage/ArticleClassItem.vue";
import Swal from "sweetalert2";
import { useRouter } from "vue-router";

const urlPathArticleClasses = "/v1/articleclasses";

// const articleClass = ref([]);
const articleClasses = ref([]);

const router = useRouter();

const getArticleClasses = async () => {
  const response = await apiGet(urlPathArticleClasses, router);
  articleClasses.value = response.data;
};

const refreshViewGetArticleClasses = () => {
  getArticleClasses();
};

const insertArticleClass = () => {
  Swal.fire({
    title: "新增分類",
    input: "text",
    inputAttributes: {
      autocapitalize: "off",
    },
    showCancelButton: true,
    confirmButtonText: "新增",
    cancelButtonText: "取消",
  }).then(async (result) => {
    if (result.isConfirmed) {
      // 在這裡處理使用者按下確定後的邏輯
      const enteredValue = result.value;
      if (!validateArticleClass(enteredValue)) {
        return;
      }
      const insertArticleClass = ref({
        articleClassName: enteredValue,
      });
      const response = await apiPost(urlPathArticleClasses, insertArticleClass.value, router);
      const httpStatus = response.status;
      if (httpStatus == 200) {
        Swal.fire({
          title: "新增成功",
          icon: "success",
          confirmButtonText: "確定",
        }).then(() => {
          getArticleClasses();
        });
      }
    }
  });
};

onMounted(async () => {
  getArticleClasses();
});

const validateArticleClass = (articleClassName) => {
  if (!articleClassName.trim()) {
    Swal.fire("錯誤", "請填寫所有欄位", "error");
    return false;
  }
  return true;
};
</script>
