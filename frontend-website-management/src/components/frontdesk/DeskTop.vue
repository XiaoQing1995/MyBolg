<template>
  <div class="desktop">
    <div class="logo-container">
      <router-link to="/home/articles" class="logo-text custom-link"
        >XiaoQing</router-link
      >
      <img src="@/assets/img/homeScape.jpg" alt="XiaoQingBlog Logo" class="logo-image" />
    </div>
  </div>
</template>

<style scoped>
.desktop {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  width: 100%;
  background-color: #f7f0ff;
}

.logo-container {
  width: 100%;
}

.logo-text {
  font-size: 1.5rem;
  margin-bottom: 10px;
  margin-left: 10px;
}

.logo-image {
  width: 100%;
  height: 50vh;
}

.custom-link {
  color: inherit; /* 保留原來的文字顏色 */
  text-decoration: none; /* 移除下劃線 */
  cursor: pointer; /* 讓鼠標移動到時顯示手指圖案 */
}

.custom-link:hover {
  color: inherit; /* 鼠標懸停時的文字顏色，你可以根據需要調整 */
}
</style>
