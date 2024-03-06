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
        <select
          class="form-select"
          v-model="article.articleClass.articleClassId"
          id="category"
        >
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

      <!-- 文章內容，使用 vue-quill-editor -->
      <div class="mb-3">
        <label for="content" class="form-label">文章內容</label>
        <ckeditor
          :editor="editor"
          v-model="article.articleContent"
          :config="editorConfig"
        ></ckeditor>
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
import Editor from "@ckeditor/ckeditor5-custom-build/build/ckeditor";
import Swal from "sweetalert2";

const router = useRouter();

const urlPathArticle = "/v1/articles";
const urlPathArticleClass = "/v1/articleclasses";

let editor = Editor;
let editorConfig = {}; // 設定值

const article = ref({
  articleTitle: "",
  articleContent: "",
  articleImg: null,
  articleDate: "",
  articleClass: {
    articleClassId: "",
  },
});

const categoryOptions = ref([]);

const imagePreview = ref(null);

const fetchCategoryOptions = async () => {
  try {
    const response = await apiGet(urlPathArticleClass, router);
    console.log(response.data);
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
    // 讀取並轉換為 base64
    const reader = new FileReader();
    reader.onload = () => {
      article.value.articleImg = getBase64String(reader.result);
      imagePreview.value = reader.result; // 設定預覽圖片
    };
    reader.readAsDataURL(file);
  }
};

const insertArticles = async () => {
  article.value.articleDate = new Date().toISOString().split("T")[0];
  const response = await apiPost(urlPathArticle, article.value, router);
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
    aritcleContent: "",
    articleImg: null,
    category: "",
  };
  imagePreview.value = null;
  const imageInput = document.getElementById("image");
  if (imageInput) {
    imageInput.value = null;
  }
  // 取消按鈕的處理邏輯，例如返回上一頁或導向列表頁面
};

// 這個函數將 base64 字符串轉換為不包含類型信息的部分
const getBase64String = (base64Data) => {
  return base64Data.split(";base64,")[1];
};

onMounted(async () => {
  await fetchCategoryOptions();
});
</script>

<style>
.ck-editor__editable {
  min-height: 50vh;
  max-height: 50vh;
}
</style>
