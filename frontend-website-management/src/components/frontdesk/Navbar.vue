<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light sticky-top">
    <div class="container">
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarNav"
        aria-controls="navbarNav"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav d-flex justify-content-between w-100">
          <li class="nav-item">
            <router-link
              to="/home/articles"
              class="nav-link"
              :class="{ active: $route.path === '/home/articles' }"
              >回首頁</router-link
            >
          </li>

          <li class="nav-item ml-auto" v-if="articleClasses.length > 0">
            <div class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle"
                href="#"
                role="button"
                data-bs-toggle="dropdown"
                aria-haspopup="true"
                aria-expanded="false"
              >
                文章分類
              </a>
              <ul class="dropdown-menu">
                <li
                  v-for="articleClass in articleClasses"
                  :key="articleClass.articleClassId"
                >
                  <router-link
                    :to="`/home/articles/classes/${articleClass.articleClassId}`"
                    class="dropdown-item"
                    >{{ articleClass.articleClassName }}</router-link
                  >
                </li>
              </ul>
            </div>
          </li>
          <li class="nav-item">
            <router-link to="/" class="nav-link" :class="{ active: $route.path === '/' }"
              >Home</router-link
            >
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>
<script setup>
import { ref, onMounted, onUnmounted } from "vue";
import { apiGet } from "@/api/api";
import { useRouter } from "vue-router";

const isNavbarFixed = ref(false);
const urlPathArticleClasses = "/v1/articleclasses";
const router = useRouter();

const articleClasses = ref([]);

const handleScroll = () => {
  isNavbarFixed.value = window.scrollY > 0;
};

const getArticleClasses = async () => {
  const response = await apiGet(urlPathArticleClasses, router);
  articleClasses.value = response.data;
};

onMounted(() => {
  window.addEventListener("scroll", handleScroll);
  getArticleClasses();
});

onUnmounted(() => {
  window.removeEventListener("scroll", handleScroll);
});
</script>
