<template>
  <div class="chat-container">
    <header>
      <h2>聊天室 (STOMP)</h2>
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
import { ref, onMounted, onBeforeUnmount } from 'vue'
import SockJS from 'sockjs-client/dist/sockjs.min.js'
import { Client } from '@stomp/stompjs'

const messages = ref([
  { user: '系統', content: '歡迎加入聊天室！' }
])
const inputMessage = ref('')
let stompClient = null

// 初始化 STOMP WebSocket 連線
const initWebSocket = () => {
  const socket = new SockJS('http://localhost:8080/ws') // 後端 Spring Boot 的 STOMP endpoint
  stompClient = new Client({
    webSocketFactory: () => socket,
    reconnectDelay: 5000,
    debug: (str) => {
      console.log(str)
    },
    onConnect: () => {
      console.log('STOMP 已連線')
      // 訂閱 topic
      stompClient.subscribe('/topic/chat', (message) => {
        const data = JSON.parse(message.body)
        messages.value.push(data)
      })
    },
    onStompError: (frame) => {
      console.error('STOMP 發生錯誤:', frame)
    }
  })
  stompClient.activate()
}

// 發送訊息
const sendMessage = () => {
  if (inputMessage.value.trim() === '') return
  const message = {
    user: '我',
    content: inputMessage.value
  }
  messages.value.push(message) // 本地先顯示
  stompClient.publish({
    destination: '/app/chat', // 後端 MessageMapping 路徑
    body: JSON.stringify(message)
  })
  inputMessage.value = ''
}

onMounted(() => {
  initWebSocket()
})

onBeforeUnmount(() => {
  if (stompClient) {
    stompClient.deactivate()
  }
})
</script>

<style scoped>
/* 和你原本的 CSS 可以共用 */
.chat-container {
  width: 400px;
  margin: 50px auto;
  border: 1px solid #444;
  border-radius: 4px;
  display: flex;
  flex-direction: column;
  height: 500px;
  background-color: #2c2c2c;
  color: #f1f1f1;
}

header {
  background-color: #3a3a3a;
  padding: 10px;
  text-align: center;
  border-bottom: 1px solid #444;
  color: #fff;
}

.chat-main {
  flex: 1;
  padding: 10px;
  overflow-y: auto;
  background-color: #2c2c2c;
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
  color: #4caf50;
}

.chat-footer {
  display: flex;
  padding: 10px;
  border-top: 1px solid #444;
  background-color: #3a3a3a;
}

.chat-footer input[type="text"] {
  flex: 1;
  padding: 8px;
  border: 1px solid #555;
  border-radius: 4px;
  background-color: #444;
  color: #f1f1f1;
}

.chat-footer input[type="text"]::placeholder {
  color: #aaa;
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