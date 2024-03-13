<template>
  <div>
    <div v-for="image in images" :key="image.imgId">
      <img :src="`http://localhost:8080/xiaoqingblog${image.path}`" :alt="`Image ${image.imgId}`" />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";

const images = ref([]);

onMounted(async () => {
  try {
    const response = await axios.get(
      "http://localhost:8080/xiaoqingblog/api/v1/imageTest/download"
    );
    images.value = response.data; // 假设这个API返回一个图片信息数组
    console.log(images.value)
  } catch (error) {
    console.error("加载图片错误：", error);
  }
});
</script>
