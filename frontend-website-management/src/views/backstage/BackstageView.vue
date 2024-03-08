<template>
  <div>
    <Navbar />
    <RouterView />
  </div>
</template>

<script setup>
import Navbar from "@/components/backstage/Navbar.vue";
import { useRouter } from "vue-router";
const isLogin = localStorage.getItem("isLogin");
const router = useRouter();
if (!isLogin) {
  router.push("login");
}
</script>
