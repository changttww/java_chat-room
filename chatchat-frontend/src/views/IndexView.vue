<script setup>
import {reactive,ref} from "vue";
import {get, post} from "@/net"
import {userStore} from "@/store";
import {ElNotification} from "element-plus";
const friends = ref(null)
const store = userStore()
const sendUser = reactive({
  sid: 0,
  name: '',
  avatar: ''
})
const messages = ref([])
const input = ref('')
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
    //发现消息进入,开始处理前端触发逻辑
    let userId = msg.data.split(',')[0];
    let sendId = msg.data.split(',')[1];
    let message = {
      sendId:  parseInt(sendId),
      toId:  parseInt(userId),
      message: msg.data.split(',')[2],
    }
    if(sendId === sendUser.sid.toString() || (userId === '0' && sendUser.sid === 0)){
      messages.value.push(message)
    }
    if(sendId !== store.user.id.toString()){
      ElNotification.success({
        title: '新消息',
        message: message.message
      })
    }
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
function getMessage(sid) {
  if(sid === 0){
    get(`/api/message/getAllMessage?id=${store.user.id}`, (data) => {
      messages.value = data;
      message.toId = sendUser.sid;
    })
  }
  else {
    get(`/api/message/getMessage?id=${store.user.id}&sid=${sendUser.sid}`, (data) => {
      messages.value = data;
      message.toId = sendUser.sid;
    })
  }
}

function messageSender(sid) {
  if(sid === 0)
    sendAllMessage();
  else
    sendMessage();
  input.value = ''
}

function sendMessage() {
  message.message = input.value
  post('/api/message/sendOneMessage',message,()=>{
    console.log("sendMessage success!")
    messages.value.push(message)
  })
}
function sendAllMessage() {
  message.message = input.value
  post('/api/message/sendAllMessage',message,()=>{
    console.log("sendAllMessage success!")
  })
}

function handleMenuClick(item,index){
  if(item !== null){
    sendUser.sid = item.id
    sendUser.name = item.username
    sendUser.avatar = item.avatar
  }
  else {
    sendUser.sid = parseInt(index);
  }
  getMessage(sendUser.sid);
}
get(`/api/user/friends?id=${store.user.id}`,(data)=>{
  friends.value = data
})
function findFriendName(sendId, toId) {
  let friendList = friends.value; // 假设 friends 是一个存储好友数据的数组
  if (toId === 0) {
    if(sendId === store.user.id){
      return store.user.username
    }
    for (let i = 0; i < friendList.length; i++) {
      if (friendList[i].id === sendId) {
        return friendList[i].username;
      }
    }
  } else {
    if(sendId === store.user.id)
      return store.user.username
    for (let i = 0; i < friendList.length; i++) {
      if (friendList[i].id === sendId) {
        return friendList[i].username;
      }
    }
  }
  return null; // 如果找不到匹配的好友，返回 null
}
function findAvatar(id){
  let friendList = friends.value;
  for (let i = 0; i < friendList.length; i++) {
    if (friendList[i].id === id) {
      return friendList[i].avatar;
    }
  }
  return null;
}
getMessage(0);
openSocket();
</script>

<template>
  <div style="height: 100vh; width: 100vw">
    <el-container style="height: 100%">
      <el-aside width="300px" style="text-align: center">
        <el-menu style="height: 100% ;background-image: linear-gradient(to bottom, #DFFFF2, #ffffff);">
          <el-menu-item index="0" @click="handleMenuClick(null,'0')">
            <template #title>
              <el-avatar src="https://image.itbaima.net/images/450/image-20231022176210313.jpeg"/>
              <span style="margin-left: 30px;">
                  <b>群聊消息:</b>
              </span>
            </template>
          </el-menu-item>
          <el-menu-item v-for="item in friends" :index="item.id.toString()" @click="handleMenuClick(item,null)">
            <template #title>
              <el-avatar :src="item.avatar"/>
              <div>

              </div>
              <span style="margin-left: 30px;">
                  <b>{{item.username}}:</b>
                  <b style="margin-left: 5px; color: grey">{{item.message}}</b>
              </span>
            </template>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main style="background-image: radial-gradient(circle at center bottom, #fff 0%, #fff 20%,#F0FFF9 20%, #E9FFF6 50%, #DFFFF2 50%)">
        <div style="height: 75vh" >
          <el-scrollbar style="height: 100%">
              <div v-for="message in messages">
                <div style="margin-top: 5px; display: flex; align-items: center;">
                  <el-avatar :src="message.sendId === store.user.id ? store.user.avatar : findAvatar(message.sendId)" size="small"/>
                  <span style="font-size: 10px; margin-left: 5px">{{findFriendName(message.sendId, message.toId)}}</span>
                </div>
                <div class="message-box">
                  <span>{{message.message}}</span>
                </div>

              </div>
          </el-scrollbar>
        </div>
        <div style="position: fixed; bottom: 10px; min-width: 200px; width: calc(100vw - 340px)">
          <el-input v-model="input" placeholder="Please input" type="textarea" :rows="5"/>
          <el-button type="success" style="margin-top: 5px; float: right" @click="messageSender(sendUser.sid)">发送</el-button>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<style scoped>
.message-box {
  border: solid 1px var(--el-border-color);
  border-radius: 5px;
  background-color: var(--el-bg-color);
  box-sizing: border-box;
  min-height: 20px;
  padding: 5px 10px;
  margin-top: 5px;
  margin-left: 20px;
  display: inline-block
}
</style>