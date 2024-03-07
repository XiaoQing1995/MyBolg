<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
      <router-link to="/" class="navbar-brand">My Blog</router-link>
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
        <ul class="navbar-nav ms-auto">
          <li class="nav-item">
            <router-link to="/" class="nav-link" :class="{ active: $route.path === '/' }"
              >Home</router-link
            >
          </li>
          <li class="nav-item">
            <router-link
              to="/about"
              class="nav-link"
              :class="{ active: $route.path === '/about' }"
              >About</router-link
            >
          </li>
          <li class="nav-item">
            <router-link
              to="/contact"
              class="nav-link"
              :class="{ active: $route.path === '/contact' }"
              >Contact</router-link
            >
          </li>
          <!-- Add more navigation items as needed -->
        </ul>
      </div>
    </div>
  </nav>
</template>

<script setup>
import { useRouter } from "vue-router";
const router = useRouter();
</script>

<style scoped>
/* Add your component-specific styles here */
.nav-link.active {
  color: #007bff; /* 調整選中時的文字顏色為Bootstrap的主題顏色 */
}
</style>
