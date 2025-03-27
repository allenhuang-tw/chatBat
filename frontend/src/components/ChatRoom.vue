<!-- ChatRoom.vue -->
<template>
    <div class="chat-container">
      <header>
        <h2>聊天室</h2>
      </header>
      <main class="chat-main">
        <ul class="chat-messages">
          <li v-for="(msg, index) in messages" :key="index">
            <span class="user">{{ msg.user }}:</span>
            <span class="content">{{ msg.content }}</span>
          </li>
        </ul>
      </main>
      <footer class="chat-footer">
        <input
          v-model="inputMessage"
          type="text"
          placeholder="請輸入訊息..."
          @keyup.enter="sendMessage"
        />
        <button @click="sendMessage">送出</button>
      </footer>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue'

  const props = defineProps({
    username: String
  })
  
  // 初始訊息列表，可依需求改為從後端取得歷史訊息
  const messages = ref([
    { user: '系統', content: '歡迎加入聊天室！' }
  ])
  const inputMessage = ref('')
  
  // 模擬發送訊息
  const sendMessage = () => {
    if (inputMessage.value.trim() === '') return
    messages.value.push({
      user: props.username,
      content: inputMessage.value
    })
    inputMessage.value = ''
  }
  </script>
  
  <style scoped>
  .chat-container {
    width: 400px;
    margin: 50px auto;
    border: 1px solid #444;
    border-radius: 4px;
    display: flex;
    flex-direction: column;
    height: 500px;
    background-color: #2c2c2c; /* 深色背景 */
    color: #f1f1f1; /* 浅色文字 */
  }
  
  header {
    background-color: #3a3a3a; /* 深色背景 */
    padding: 10px;
    text-align: center;
    border-bottom: 1px solid #444;
    color: #fff; /* 浅色文字 */
  }
  
  .chat-main {
    flex: 1;
    padding: 10px;
    overflow-y: auto;
    background-color: #2c2c2c; /* 深色背景 */
  }
  
  .chat-messages {
    list-style: none;
    padding: 0;
    margin: 0;
  }
  
  .chat-messages li {
    margin-bottom: 10px;
  }
  
  .chat-messages .user {
    font-weight: bold;
    margin-right: 5px;
    color: #4caf50; /* 用户名使用绿色 */
  }
  
  .chat-footer {
    display: flex;
    padding: 10px;
    border-top: 1px solid #444;
    background-color: #3a3a3a; /* 深色背景 */
  }
  
  .chat-footer input[type="text"] {
    flex: 1;
    padding: 8px;
    border: 1px solid #555;
    border-radius: 4px;
    background-color: #444; /* 深色输入框背景 */
    color: #f1f1f1; /* 浅色文字 */
  }
  
  .chat-footer input[type="text"]::placeholder {
    color: #aaa; /* 浅色占位符 */
  }
  
  .chat-footer button {
    padding: 8px 12px;
    margin-left: 10px;
    border: none;
    background-color: #4caf50;
    color: #fff;
    border-radius: 4px;
    cursor: pointer;
  }
  
  .chat-footer button:hover {
    background-color: #45a049;
  }
  </style>
