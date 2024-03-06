<template>
  <NavMenu />
  <RouterView />

</template>

<script setup>
import NavMenu from '@/components/NavMenu.vue'
import { useRouter } from 'vue-router';
const isLogin = localStorage.getItem("isLogin")
const router = useRouter()
if (!isLogin) {
  router.push("login")
}
</script>
