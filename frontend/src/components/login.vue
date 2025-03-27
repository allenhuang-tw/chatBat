<template>
    <div class="login-container">
      <h2>登入</h2>
      <form @submit.prevent="handleLogin">
        <div class="form-group">
          <label for="username">使用者名稱</label>
          <input
            id="username"
            v-model="username"
            type="text"
            placeholder="請輸入使用者名稱"
            required
          />
        </div>
        <div class="form-group">
          <label for="password">密碼</label>
          <input
            id="password"
            v-model="password"
            type="password"
            placeholder="請輸入密碼"
            required
          />
        </div>
        <button type="submit">登入</button>
      </form>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue'
  
  const username = ref('')
  const password = ref('')
  const emit = defineEmits(['login-success', 'username'])

  
  
  const handleLogin = () => {
    if (username.value.trim() && password.value.trim()) {
      alert(`歡迎, ${username.value}!`) // 模擬登入成功
      emit('login-success') // 通知父組件登入成功
      //傳遞使用者名稱
      emit('username', username.value)
    } else {
      alert('請輸入有效的使用者名稱和密碼')
    }
  }
  </script>
  
  <style scoped>
  .login-container {
    width: 300px;
    margin: 100px auto;
    padding: 20px;
    border: 1px solid #444;
    border-radius: 4px;
    background-color: #2c2c2c; /* 深色背景 */
    color: #f1f1f1; /* 浅色文字 */
    text-align: center;
  }
  
  h2 {
    margin-bottom: 20px;
  }
  
  form {
    display: flex;
    flex-direction: column; /* 垂直排列子元素 */
    align-items: center; /* 水平置中 */
  }
  
  .form-group {
    width: 100%; /* 讓輸入框寬度填滿 */
    margin-bottom: 15px;
  }
  
  label {
    display: block;
    margin-bottom: 5px;
    color: #aaa;
  }
  
  input {
    width: 100%;
    padding: 8px;
    border: 1px solid #555;
    border-radius: 4px;
    background-color: #444;
    color: #f1f1f1;
  }
  
  input::placeholder {
    color: #aaa;
  }
  
  button {
    width: 100%; /* 讓按鈕寬度填滿 */
    padding: 10px;
    border: none;
    background-color: #4caf50;
    color: #fff;
    border-radius: 4px;
    cursor: pointer;
  }
  
  button:hover {
    background-color: #45a049;
  }
  </style>