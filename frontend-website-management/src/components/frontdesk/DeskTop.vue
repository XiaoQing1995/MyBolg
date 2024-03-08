<template>
  <div class="desktop">
    <div class="logo-container">
      <p class="logo-text">XiaoQing</p>
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
</style>
