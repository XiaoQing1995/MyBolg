<template>
  <div id="admin-app">
    <div class="sidebar">
      <router-link to="/admin/posts">文章列表</router-link>
      <router-link to="/admin/edit">新增文章</router-link>
      <router-link to="/admin/accounts">帳戶管理</router-link>
    </div>
    <div class="content">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  name: "AdminApp",
};
</script>

<style>
#admin-app {
  display: flex;
  height: 100vh;
}

.sidebar {
  width: 200px;
  background-color: #333;
  color: white;
  padding: 20px;
  box-sizing: border-box;
}

.sidebar a {
  display: block;
  color: white;
  text-decoration: none;
  margin-bottom: 10px;
}

.content {
  flex: 1;
  padding: 20px;
  box-sizing: border-box;
}
</style>
