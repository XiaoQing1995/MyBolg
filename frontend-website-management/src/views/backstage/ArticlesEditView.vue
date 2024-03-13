<template>
  <div class="container mt-4">
    <h2>Edit Article</h2>
    <form @submit.prevent="updateArticle">
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
          <!-- 使用您的分類數據填充下拉式選單選項 -->
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
        <button type="button" class="btn btn-secondary me-2" @click="cancelUpdate">
          取消
        </button>
        <button type="button" class="btn btn-primary" @click="updateArticle">儲存</button>
      </div>
    </form>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from "vue";
import { apiGet, apiUpdate } from "@/api/api";
import { useRouter, useRoute } from "vue-router";
import { whenErrorCheckHttpStatus } from "@/plugin/httpErrorPlugin";
import Swal from "sweetalert2";
import Editor from "@tinymce/tinymce-vue";

const articleId = ref(null);
const router = useRouter();
const route = useRoute();

const urlPathArticles = "/v1/articles";
const urlPathArticleClasses = "/v1/articleclasses";
const imageUrl = import.meta.env.VITE_API_SERVERURL;

const article = ref({
  articleId: "",
  articleTitle: "",
  articleContent: "",
  articleImagePath: "",
  articleThumbnailImagePath: "",
  articleFile: "",
  articleDate: "",
  articleClass: {
    articleClassId: "",
  },
});

const categoryOptions = ref([]);

const imagePreview = ref(null);

const getArticleDetails = async (articleId) => {
  try {
    const response = await apiGet(`${urlPathArticles}/${articleId}`, router);
    article.value = response.data;
    imagePreview.value = `${imageUrl}${article.value.articleImagePath}`;
    console.log(article.value);
  } catch (error) {
    whenErrorCheckHttpStatus(error, router);
  }
};

const fetchCategoryOptions = async () => {
  try {
    const response = await apiGet(urlPathArticleClasses, router);
    console.log("fetch");
    console.log(response.data);
    categoryOptions.value = response.data;
    console.log("cate");
    console.log(categoryOptions.value);
  } catch (error) {
    whenErrorCheckHttpStatus(error, router);
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

const updateArticle = async () => {
  Swal.fire({
    title: "修改中",
    html: "請稍等，正在處理中",
    allowOutsideClick: false,
    didOpen: () => {
      Swal.showLoading();
    },
  });

  const formData = new FormData();
  formData.append("articleId", article.value.articleId);
  formData.append("articleTitle", article.value.articleTitle);
  formData.append("articleContent", article.value.articleContent);
  formData.append("articleImagePath", article.value.articleImagePath);
  formData.append("articleThumbnailImagePath", article.value.articleThumbnailImagePath);
  formData.append("articleDate", article.value.articleDate);
  formData.append("articleClassId", article.value.articleClass.articleClassId);

  if (article.value.articleFile) {
    console.log("articleFile");
    formData.append("articleFile", article.value.articleFile);
  }

  const response = await apiUpdate(urlPathArticles, formData, router);
  const httpStatus = response.status;
  if (httpStatus == 200) {
    Swal.fire({
      title: "修改成功",
      icon: "success",
      confirmButtonText: "確定",
    }).then(() => {
      router.push("/backstage/articles");
    });
  }
};

const cancelUpdate = () => {
  router.push("/backstage/articles");
};

onMounted(() => {
  articleId.value = route.params.id;
  getArticleDetails(articleId.value);
  fetchCategoryOptions();
});
</script>

<style>
.ck-editor__editable {
  min-height: 50vh;
  max-height: 50vh;
}

@media (min-width: 1024px) {
  #sample {
    display: flex;
    flex-direction: column;
    place-items: center;
    width: 100%;
  }
}
</style>
