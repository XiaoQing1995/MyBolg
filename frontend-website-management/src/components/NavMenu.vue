<template>
  <nav class="navbar navbar-expand-md navbar-light bg-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">XiaoQing</a>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <router-link to="/backstage/articles" class="nav-link">文章列表</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/backstage/articlesInsert" class="nav-link"
              >新增文章</router-link
            >
          </li>
          <li class="nav-item">
            <router-link to="/backstage/articleClassesManagement" class="nav-link"
              >分類管理</router-link
            >
          </li>
          <li class="nav-item" v-if="isAdmin">
            <router-link to="/backstage/accountsManagement" class="nav-link"
              >帳戶管理</router-link
            >
          </li>
        </ul>
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" @click="logout">登出</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script setup>
import { useRouter } from "vue-router";
const router = useRouter();
const isAdmin = parseJwtGetRole(localStorage.getItem("token")) === "ADMIN";

function parseJwtGetRole(token) {
  if (token != undefined) {
    var base64Url = token.split(".")[1];
    var base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
    var jsonPayload = decodeURIComponent(
      window
        .atob(base64)
        .split("")
        .map(function (c) {
          return "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2);
        })
        .join("")
    );

    var parsedPayload = JSON.parse(jsonPayload);
    return parsedPayload.role[0].authority;
  }

  return null;
}

const logout = () => {
  // 清空 localStorage
  localStorage.clear();
  // 回到登入頁面
  router.push("/login");
};
</script>

<style scoped>
/* 自定義樣式 */
.router-link-active,
.nav-link:hover {
  color: #007bff; /* 調整選中時和滑過時的文字顏色為 Bootstrap 的主題顏色 */
}

.nav-link {
  cursor: pointer; /* 將光標變為手指狀態 */
}

.form-select {
  max-width: 150px; /* 設定下拉選單的最大寬度 */
}
</style>
