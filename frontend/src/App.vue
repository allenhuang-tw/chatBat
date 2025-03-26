<script setup>
import { ref } from 'vue'
import Login from './components/login.vue'
import ChatRoom from './components/ChatRoom.vue'

const isLoggedIn = ref(false) // 登入狀態

const handleLoginSuccess = () => {
  isLoggedIn.value = true // 更新登入狀態為已登入
}
</script>

<template>
  <div>
    <!-- 根據登入狀態切換顯示 -->
    <Login v-if="!isLoggedIn" @login-success="handleLoginSuccess" />
    <ChatRoom v-else />
  </div>
</template>

<style scoped>
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
/* 可選：添加全局樣式 */
</style>
