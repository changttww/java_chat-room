<script setup>
import {reactive, ref} from "vue";
import {get, post} from "@/net"
import {userStore} from "@/store";
const props = defineProps({
  avatar: String,
  name: String,
  sid: Number,
})
const messages = ref([])
const input = ref('')
const store = userStore();
const toUserID = '';
const message = reactive({
  sendId: userStore().user.id,
  toId: 0,
  message: ''
})


let socket;
function openSocket() {
  const userId = store.user.id;
  const socketUrl = "ws://localhost:8080/websocket/"+userId;
  console.log(socketUrl);
  if(socket!=null){
    socket.close();
    socket=null;
  }
  socket = new WebSocket(socketUrl);
  //打开事件
  socket.onopen = function() {
    console.log("websocket已打开");
  };
  //获得消息事件
  socket.onmessage = function(msg) {
    console.log(msg.data);
    //发现消息进入,开始处理前端触发逻辑

  };
  //关闭事件
  socket.onclose = function() {
    console.log("websocket已关闭");
  };
  //发生了错误事件
  socket.onerror = function() {
    console.log("websocket发生了错误");
  }
}
function sendMessage() {
  message.message = input.value
  post('/api/message/sendOneMessage',message,()=>{
    console.log("success!")
    messages.value.push(message)
    console.log(message)
  })
}



get(`/api/message/getMessage?id=${store.user.id}&sid=${props.sid}`, (data) => {
  messages.value = data;
  message.toId = props.sid;
  console.log(data)
})
openSocket();
</script>

<template>
  <div style="height: 75vh">
    <el-scrollbar style="height: 100%">
      <div>{{sid}}</div>
      <div v-for="message in messages">
        {{message.message}}
      </div>
    </el-scrollbar>
  </div>
  <div style="position: fixed; bottom: 10px; min-width: 200px; width: calc(100vw - 340px)">
    <el-input v-model="input" placeholder="Please input" type="textarea" :rows="5"/>
    <el-button type="success" style="margin-top: 5px; float: right" @click="sendMessage">发送</el-button>
  </div>

</template>

<style scoped>

</style>