<template>
  <div>
    <DeskTop />
    <Navbar />
    <RouterView />
  </div>
</template>

<script setup>
import Navbar from "@/components/frontdesk/Navbar.vue";
import DeskTop from "@/components/frontdesk/DeskTop.vue";
</script>

<style scoped>
.home-view {
  position: relative;
}

.background-image {
  width: 100%;
  height: auto;
  position: absolute;
  top: 0;
  left: 0;
  z-index: -1;
}
</style>
