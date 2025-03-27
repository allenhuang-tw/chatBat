<script setup>
import { ref } from 'vue'
import Login from './components/login.vue'
import ChatRoom from './components/ChatRoom.vue'

const isLoggedIn = ref(false) // 登入狀態

const username = ref('') // 使用者名稱

const handleLoginSuccess = () => {
  isLoggedIn.value = true // 更新登入狀態為已登入
}

const handleUsernameUpdate = (newUsername) => {
  username.value = newUsername // 更新使用者名稱
}
</script>

<template>
  <div>
    <!-- 根據登入狀態切換顯示 -->
    <Login v-if="!isLoggedIn" 
    @login-success="handleLoginSuccess"
    @username="handleUsernameUpdate"  />
    <ChatRoom v-else :username="username" />
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
