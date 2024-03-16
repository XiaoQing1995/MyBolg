<template>
  <div class="container mt-4">
    <h2>Edit Article</h2>
    <form @submit.prevent="insertArticle">
      <!-- 文章標題 -->
      <div class="mb-3">
        <label for="title" class="form-label">標題</label>
        <input
          type="text"
          class="form-control"
          v-model="article.articleTitle"
          id="title"
          required
        />
      </div>

      <!-- 文章分類，下拉式選單 -->
      <div class="mb-3">
        <label for="category" class="form-label">文章分類</label>
        <select class="form-select" v-model="article.articleClassId" id="category">
          <option
            v-for="categoryOption in categoryOptions"
            :key="categoryOption.articleClassId"
            :value="categoryOption.articleClassId"
          >
            {{ categoryOption.articleClassName }}
          </option>
        </select>
      </div>

      <!-- 圖片上傳和預覽 -->
      <div class="mb-3">
        <label for="image" class="form-label">封面圖片</label>
        <input id="image" type="file" @change="handleImageUpload" accept="image/*" />
      </div>
      <img
        v-if="imagePreview"
        :src="imagePreview"
        alt="Image Preview"
        style="width: 300px; height: 200px; object-fit: contain"
      />

      <!-- 概要內容 -->
      <div class="mb-3">
        <label for="summaryContent" class="form-label">概要內容</label>
        <input
          type="text"
          class="form-control"
          v-model="article.articleSummaryContent"
          required
        />
      </div>

      <!-- 文章內容，使用 vue-quill-editor -->
      <div class="mb-3">
        <label for="content" class="form-label">文章內容</label>
        <Editor
          api-key="ovyy5uubb3bz95whlsxmd1vvnnhsz98viov27xsvgwp41jzg"
          v-model="article.articleContent"
          :init="{
            height: '50vh', // 預設高度
            autoresize_bottom_margin: 20, // 設定底部邊距
            toolbar_mode: 'sliding',
            plugins:
              'anchor autolink charmap codesample emoticons image link lists media searchreplace table visualblocks wordcount checklist image a11ychecker',
            tinycomments_mode: 'embedded',
            tinycomments_author: 'Author name',
            a11y_file_allowed_types: 'image/*',
            mergetags_list: [
              { value: 'First.Name', title: 'First Name' },
              { value: 'Email', title: 'Email' },
            ],
            ai_request: (request, respondWith) =>
              respondWith.string(() =>
                Promise.reject('See docs to implement AI Assistant')
              ),
          }"
        />
      </div>

      <!-- 操作按鈕，取消和儲存 -->
      <div class="text-center">
        <button type="button" class="btn btn-secondary me-2" @click="resetArticles">
          重置
        </button>
        <button type="submit" class="btn btn-primary" @click="insertArticles">
          儲存
        </button>
      </div>
    </form>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { apiGet, apiPost } from "@/api/api";
import { useRouter } from "vue-router";
import Swal from "sweetalert2";
import Editor from "@tinymce/tinymce-vue";

const router = useRouter();

const urlPathArticle = "/v1/articles";
const urlPathArticleClass = "/v1/articleclasses";

const article = ref({
  articleTitle: "",
  articleSummaryContent: "",
  articleContent: "",
  articleDate: "",
  articleFile: "",
  articleClassId: "",
});

const categoryOptions = ref([]);

const imagePreview = ref(null);

const fetchCategoryOptions = async () => {
  try {
    const response = await apiGet(urlPathArticleClass, router);
    categoryOptions.value = response.data;
  } catch (error) {
    const httpStatus = error.response?.status;
    if (httpStatus == 403) {
      router.push("../login");
    }
  }
};

const handleImageUpload = (event) => {
  const file = event.target.files[0];
  if (file) {
    imagePreview.value = URL.createObjectURL(file); // 使用文件的 URL 用于本地预览

    // 将文件对象存储到 Vue 实例中，用于后续上传
    article.value.articleFile = file;
  }
};

const insertArticles = async () => {
  if (!validateArticle()) {
    return;
  }
  Swal.fire({
    title: "新增中",
    html: "請稍等，正在處理中",
    allowOutsideClick: false,
    didOpen: () => {
      Swal.showLoading();
    },
  });

  article.value.articleDate = new Date().toISOString().split("T")[0];

  const formData = new FormData();
  formData.append("articleTitle", article.value.articleTitle);
  formData.append("articleSummaryContent", article.value.articleSummaryContent);
  formData.append("articleContent", article.value.articleContent);
  formData.append("articleDate", article.value.articleDate);
  formData.append("articleClassId", article.value.articleClassId);
  formData.append("articleFile", article.value.articleFile);

  const response = await apiPost(urlPathArticle, formData, router);
  const httpStatus = response.status;
  if (httpStatus == 200) {
    Swal.fire({
      title: "新增成功",
      icon: "success",
      confirmButtonText: "確定",
    }).then(() => {
      router.push("/backstage/articles");
    });
  }
};

const resetArticles = () => {
  article.value = {
    articleTitle: "",
    articleContent: "",
    articleFile: "",
    articleClassId: "",
  };
  imagePreview.value = null;
  const imageInput = document.getElementById("image");
  if (imageInput) {
    imageInput.value = null;
  }
};


onMounted(async () => {
  await fetchCategoryOptions();
});

const validateArticle = () => {
  if (!article.value.articleTitle.trim() || 
  !article.value.articleSummaryContent.trim() ||
  !article.value.articleContent.trim() || 
  !article.value.articleClassId|| 
  !article.value.articleFile) {
    Swal.fire("錯誤", "請填寫所有欄位", "error");
    return false;
  }
  return true;
};
</script>

<style>
.ck-editor__editable {
  min-height: 50vh;
  max-height: 50vh;
}
</style>
