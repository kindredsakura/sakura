<script setup>
// 移除不需要的引用
</script>

<template>
  <div class="app-container">
    <router-view></router-view>
  </div>
</template>

<style>
.app-container {
  min-height: 100vh;
  background-color: var(--bg-color);
}

.logo {
  height: 6em;
  padding: 1.5em;
  will-change: filter;
  transition: filter 300ms;
}
.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}
.logo.vue:hover {
  filter: drop-shadow(0 0 2em #42b883aa);
}
</style>
