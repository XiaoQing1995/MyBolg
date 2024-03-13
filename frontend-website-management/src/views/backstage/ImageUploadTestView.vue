<template>
  <div>
    <input type="file" @change="handleFileChange" />
    <button @click="uploadImage">上传图片</button>
  </div>
</template>

<script setup>
import { ref } from "vue";
import axios from "axios";

const selectedFile = ref(null);

function handleFileChange(event) {
  selectedFile.value = event.target.files[0];
}

async function uploadImage() {
  if (!selectedFile.value) {
    alert("请选择一个文件。");
    return;
  }

  const formData = new FormData();
  formData.append("image", selectedFile.value);

  try {
    const response = await axios.post("http://localhost:8080/xiaoqingblog/api/v1/imageTest/upload", formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
    alert("图片上传成功！");
    console.log("Uploaded image path:", response.data);
  } catch (error) {
    console.error("图片上传错误：", error);
  }
}
</script>
