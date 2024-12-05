<template>
  <div class="home-container">
    <!-- 顶栏 -->
    <div class="topbar">
      <div class="topbar-content">
        <span class="topbar-title">StarBBPark</span>
        <div class="topbar-links">
          <a href="http://localhost:5173/home" class="topbar-link3">首页社区</a>
          <a href="http://localhost:5173/chat" class="topbar-link">消息</a>
          <a href="http://localhost:5173/own" class="topbar-link">个人中心</a>
        </div>
      </div>
    </div>

    <hr>

    <!-- 副栏 -->
    <div class="secondbar">
      <div class="secondbar-content">
        <div class="secondbar-links">
          <button class="secondbar-link" @click="showModal = true">创建房间</button>
          <button class="secondbar-link" @click="showTagModal = true">标签分类</button>


          <!-- 搜索输入框和按钮 -->
          <div class="search-container">
            <input type="text" class="search-input" placeholder="请输入搜索内容">
            <button class="secondbar-link">搜索</button>
          </div>
        </div>
      </div>
    </div>

    <!-- 全部标签弹窗 -->
     <!-- 在弹窗中动态渲染标签按钮 -->
    <div v-if="showTagModal" class="modal-overlay2">
      <div class="modal-content2">
        <h3>全部标签</h3>
        <div class="tags-container">
          <button
            class="tag-btn"
            v-for="(tag, index) in tags"
            :key="index"
            :style="{ backgroundColor: tag.color }"
            @click="console.log('tag.tagname:', tag.tagname); console.log('tag.color:', tag.color)"
          >
          {{ tag.tagname }}
          </button>
        </div>
        <button class="modal-close" @click="showTagModal = false">关闭</button>
      </div>
    </div>

    <!-- 创建房间弹窗 -->
    <div v-if="showModal" class="modal-overlay">
      <div class="modal-content">
        <h3>创建房间</h3>
        <!-- 房间头像 -->
        <div class="avatar-section2">
          <img :src="avatar2Url || defaultAvatar" alt="头像" class="avatar2">
          <input type="file" @change="uploadAvatar" />
        </div>
        <form @submit.prevent="createRoom">
          <!-- 房间号 -->
          <div>
            <label>房间号：</label>
            <span>{{ roomId }}</span>
          </div>

          <!-- 房间名 -->
          <div>
            <label>房间名：</label>
            <input type="text" v-model="roomName" required />
          </div>

          <!-- 标签 -->
          <div>
            <label>标签：</label>
            <input type="text" v-model="roomTag" />
          </div>

          <!-- 属性选择 -->
          <div>
            <label>属性：</label>
            <label>
              <input type="radio" value="public" v-model="roomType" /> 公开
            </label>
            <label>
              <input type="radio" value="private" v-model="roomType" /> 私密
            </label>
          </div>

          <!-- 按钮 -->
          <div class="modal-buttons">
            <button type="submit">确认</button>
            <button type="button" @click="closeModal">取消</button>
          </div>
        </form>
      </div>
    </div>

    <!-- 页面主体内容 -->
    <div class="home-content">
      <div class="container">
        <div class="top-container1">
          <!-- 根据后台标签数实时查询，将房间数最多的10个标签，设置为按钮 -->
          <!-- 10个大小不一的彩色按钮 -->
          <button class="random-btn" style="top: 20px; left: 30px; background-color: #6A5ACD">Kpop</button>
          <button class="random-btn" style="top: 50px; left: 1050px;background-color: red">第五人格</button>
          <button class="random-btn" style="top: 100px; left: 380px;background-color: royalblue">原</button>
          <button class="random-btn" style="top: 120px; left: 1400px;background-color: gold">jazz</button>
          <button class="random-btn" style="top: 170px; left: 500px;background-color: greenyellow">hpop</button>
          <button class="random-btn" style="top: 280px; left: 300px;background-color: hotpink">小说</button>
          <button class="random-btn" style="top: 240px; left: 1250px;background-color: paleturquoise">塔</button>
          <button class="random-btn" style="top: 150px; left: 50px;background-color: salmon">手账</button>
          <button class="random-btn" style="top: 180px; left: 780px;background-color: cadetblue">新疆旅游</button>
          <button class="random-btn" style="top: 80px; left: 600px;background-color: firebrick">国际政治</button>
        </div>
        <div class="top-container2">
          <!-- 复制按钮，使得循环更加无缝 -->
          <button class="random-btn" style="top: 20px; left: 30px; background-color: #6A5ACD">Kpop</button>
          <button class="random-btn" style="top: 50px; left: 1050px;background-color: red">第五人格</button>
          <button class="random-btn" style="top: 100px; left: 380px;background-color: royalblue">原</button>
          <button class="random-btn" style="top: 120px; left: 1400px;background-color: gold">jazz</button>
          <button class="random-btn" style="top: 170px; left: 500px;background-color: greenyellow">hpop</button>
          <button class="random-btn" style="top: 280px; left: 300px;background-color: hotpink">小说</button>
          <button class="random-btn" style="top: 240px; left: 1250px;background-color: paleturquoise">塔</button>
          <button class="random-btn" style="top: 150px; left: 50px;background-color: salmon">手账</button>
          <button class="random-btn" style="top: 180px; left: 780px;background-color: cadetblue">新疆旅游</button>
          <button class="random-btn" style="top: 80px; left: 600px;background-color: firebrick">国际政治</button>
        </div>
      </div>
    </div>
    <div class="bottom-container">
      <!-- 新添加的图片容器 -->
      <div class="image-container">
        <img :src="'/images/ENFP-竞选者.png'" alt="Image" class="image-content">
      </div>

      <div class="group_sug1" v-for="(room, index) in rooms" :key="index">
        <!-- 房间头像 -->
        <div class="grp1_img">
          <img :src="room.avatarUrl || defaultAvatar" alt="房间头像" class="room-avatar">
        </div>

        <!-- 房间名称 -->
        <div class="room-name">{{ room.roomName }}</div>

        <!-- 房间标签 -->
        <div class="room-tag">{{ room.roomTag }}</div>

        <!-- 在线人数 -->
        <div class="room-online">{{ room.onlineCount }} 在线</div>
      </div>
    </div>
  </div>
</template>
  
  <script setup>
  // 你可以在这里添加任何需要的 Vue 逻辑
  import { ref } from 'vue';
  import { useRouter } from 'vue-router'; // 引入 useRouter
  import axios from 'axios';
  import { onMounted } from 'vue';

  // 弹窗控制
  const showModal = ref(false);

  // 表单数据
  const roomId = ref(Math.floor(Math.random() * 1000000)); // 自动生成房间号
  const roomName = ref('');
  const roomTag = ref(''); 
  const roomType = ref('public');
  const avatar2 = ref(null);
  const avatar2Url = ref(defaultAvatar); // 上传头像的预览URL
  const rooms = ref([]); // 用于存储从后端获取的房间数据
  const showTagModal = ref(false);
  const tags = ref([]);

  const defaultAvatar = "/images/ENFP-竞选者.png";

  // 关闭弹窗
  const closeModal = () => {
    showModal.value = false;
    resetForm();
  };

  const resetForm = () => {
  roomName.value = ''; 
  roomTag.value = ''; 
  roomType.value = 'public'; 
  avatar2.value = null; 
  avatar2Url.value = defaultAvatar;
  // Any other fields you want to reset
};

  // 创建房间
  const createRoom = async () => {
    const formData = new FormData();
    formData.append('roomId', roomId.value);
    formData.append('roomName', roomName.value);
    formData.append('roomTag', roomTag.value);
    formData.append('roomType', roomType.value);
    if (avatar2.value) {
      formData.append('avatar', avatar2.value); // 上传头像文件
    }

    try {
      const response = await axios.post('https://fccb0fa2-e2a0-4749-8131-2c5991792be7.mock.pstmn.io/createroom', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });
      console.log('房间创建成功', response.data);
      showModal.value = false; // 关闭弹窗
      resetForm();
    } catch (error) {
      console.error('创建房间失败', error);
    }
  };

// 上传头像
const uploadAvatar = (event) => {
  const file = event.target.files[0];
  if (file) {
    avatar2.value = file;
    const reader = new FileReader();
    reader.onload = () => {
      avatar2Url.value = reader.result;
    };
    reader.readAsDataURL(file);
  }
};

// 页面加载时请求房间数据
onMounted(async () => {
  try {
    const response = await axios.get('https://fccb0fa2-e2a0-4749-8131-2c5991792be7.mock.pstmn.io/get6rooms'); // 替换为实际的 API 地址
    rooms.value = response.data.rooms; // 假设 API 返回的数据结构是 { rooms: [...] }
  } catch (error) {
    console.error('获取房间数据失败', error);
  }
  try {
    const response = await axios.get('https://fccb0fa2-e2a0-4749-8131-2c5991792be7.mock.pstmn.io/tags'); // 获取标签数据
    tags.value = response.data; // 假设返回的格式是 [{ name: 'Kpop', color: '#6A5ACD' }, ...]
    console.log("标签加载成功",response.data);
  } catch (error) {
    console.error('获取标签数据失败', error);
  }
});
  </script>
  
  <style lang="scss">
  .home-container {
    width: 100%;
    min-height: 100vh;
    display: flex;
    flex-direction: column;
    background-color: #62A89B;
    /* 背景色保持一致 */

    /* 顶栏样式 */
    .topbar {
      background-color: rgba(78, 124, 114, 0.75);
      /* 与 card 颜色一致 */
      padding: 15px;
      width: 100%;
      box-sizing: border-box;
      display: flex;
      justify-content: space-between;
      /* 左右对齐 */
      align-items: center;
    }

    .topbar-content {
      display: flex;
      justify-content: space-between;
      align-items: center;
      width: 100%;
    }

    .topbar-title {
      font-size: 24px;
      color: white;
      font-weight: bold;
    }

    .topbar-links {
      display: flex;
      gap: 20px;
    }

    .topbar-link3 {
      font-size: 20 px;
      color: white;
      text-decoration: none;
      font-weight: normal;
      transition: color 0.3s;

      &:hover {
        color: #24d97f;
        /* 鼠标悬停时的颜色 */
      }
    }

    .topbar-link {
      font-size: 16px;
      color: white;
      text-decoration: none;
      font-weight: normal;
      transition: color 0.3s;

      &:hover {
        color: #24d97f;
        /* 鼠标悬停时的颜色 */
      }
    }

    //副栏样式
    .secondbar {
      background-color: rgba(78, 124, 114, 0.75);
      padding: 10px;
      width: 100%;
      box-sizing: border-box;
      display: flex;
      justify-content: center;
      align-items: center;
    }

    .secondbar-content {
      width: 100%;
    }

    .secondbar-links {
      display: flex;
      justify-content: space-between;
      width: 100%;
      padding: 0 20px;
    }

    /* 创建房间和标签分类按钮样式 */
    .secondbar-link {
      width: 30%;
      /* 每个按钮占 30% 宽度，保证它们均匀分布 */
      height: 40px;
      background: rgb(249, 250, 250);
      color: #5da877;
      border: none;
      outline: none;
      border-radius: 5px;
      font-weight: bold;
      font-size: 16px;
      cursor: pointer;
      transition: all 0.3s ease;

      &:hover {
        font-size: 18px;
      }
    }

    /* 搜索容器 */
    .search-container {
      display: flex;
      width: 30%;
      justify-content: space-between;
      /* 输入框和按钮之间的间距 */
    }

    .search-input {
      width: 80%;
      height: 40px;
      padding: 0 10px;
      border-radius: 5px;
      border: none;
      font-size: 14px;
      outline: none;
    }

    .secondbar-link3 {
      width: 20%;
      /* 搜索按钮占 20% 宽度 */
      background-color: #b0ea53;
      color: white;
      /* 其他样式与按钮一致 */
    }

    //横线样式
    hr {
      border: 0;
      /* 去掉默认的边框 */
      height: 2px;
      /* 设置线条的高度 */
      background-color: #fff;
      /* 设置背景颜色为黑色 */
      //margin: 20px 0;       /* 设置上下的间距 */
    }

    /* 页面整体样式 */
    .home-content {
      font-family: Arial, sans-serif;
      /* 设置字体为 Arial 或 sans-serif 字体 */
      background-color: white;
      /* 设置背景颜色为浅灰色 */
      padding: 20px;
      /* 为容器添加内边距，防止内容紧贴边缘 */
      text-align: center;
      /* 内容居中对齐 */
    }

    /* 圆角容器 */
    .container {
      position: relative;
      /* 设置容器为相对定位，便于子元素绝对定位 */
      background-image: url('/images/INFP调停者-04.png');
      /* 设置背景图片，图片链接待替换 */
      background-size: cover;
      /* 让背景图片覆盖整个容器，保持比例填充 */
      border-radius: 15px;
      /* 设置容器圆角半径为15px，产生圆角效果 */
      padding: 30px;
      /* 为容器添加40px的内边距，避免内容贴近边缘 */
      margin-top: 3px;
      /* 设置容器的上外边距为30px，避免与上方元素紧贴 */
      min-height: 380px;
      /* 设置容器的最小高度为300px，确保足够展示内容 */
      overflow: hidden;
      /* 隐藏溢出容器的部分 */

    }

    /* 定义滚动动画 */
    @keyframes scrollLeftToRight1 {
      0% {
        transform: translateX(100%);
        /* 从右侧外部开始 */
      }

      100% {
        transform: translateX(-100%);
        /* 滚动到左侧外部 */
      }
    }

    /* 定义滚动动画 */
    @keyframes scrollLeftToRight2 {
      0% {
        visibility: visible;
        transform: translateX(100%);
        /* 从右侧外部开始 */
      }

      100% {
        transform: translateX(-100%);
        /* 滚动到左侧外部 */
      }
    }

    .top-container1 {
      display: flex;
      position: absolute;
      white-space: pre-wrap;
      /* 防止按钮换行 */
      animation: scrollLeftToRight1 20s linear infinite;
      /* 定义动画 */
      animation-delay: 0s;
      width: 100%;
      /* 设置宽度为父容器的100%，占满可用空间 */
      height: 200px;
      /* 设置高度为200px，控制按钮区域的高度 */
    }

    .top-container2 {
      display: flex;
      position: absolute;
      white-space: pre-wrap;
      /* 防止按钮换行 */
      //opacity: 0;                /* 默认透明 */
      visibility: hidden;
      /* 默认隐藏 */
      animation: scrollLeftToRight2 20s linear infinite;
      /* 定义动画 */
      animation-delay: 10s;
      width: 100%;
      /* 设置宽度为父容器的100%，占满可用空间 */
      height: 200px;
      /* 设置高度为200px，控制按钮区域的高度 */
    }

    /* 随机按钮样式 */
    .random-btn {
      position: absolute;
      /* 设置按钮为绝对定位，确保其位置根据top和left调整 */
      padding: 10px 20px;
      /* 设置按钮内边距，增加点击区域 */
      border-radius: 8px;
      /* 设置按钮的圆角半径为8px */
      border: none;
      /* 去掉按钮的边框 */
      color: white;
      /* 设置按钮文字颜色为白色 */
      font-size: 16px;
      /* 设置按钮文字大小为16px */
      cursor: pointer;
      /* 设置鼠标悬停时显示为指针，表示可点击 */
      margin-right: 50px;
      /* 按钮之间的间隔 */
    }


    /* 下半部分按钮 */
    .bottom-container {
      display: flex;
      justify-content: space-around;
      /* 左对齐 */
      align-items: center;
      /* 垂直居中对齐 */
      margin-top: 10px;
      //gap: 20px;
      /* 添加间距 */
    }


    .image-container {
      width: 180px;
      /* 宽度设置为100px */
      height: 180px;
      /* 高度设置为100px，确保它是方形的 */
      overflow: hidden;
      /* 隐藏超出部分 */
    }

    .image-content {
      width: 100%;
      /* 图片宽度占满容器 */
      height: 100%;
      /* 图片高度占满容器 */
      object-fit: cover;
      /* 保持图片比例并填满容器 */
    }

    .group_sug1 {
      width: 150px;
      height: 175px;
      border-radius: 8px;
      background-color: rgb(249, 250, 250);
      padding: 10px;
      text-align: center;
    }

    .grp1_img {
      width: 80px;
      height: 80px;
      margin-left: 25px;
      overflow: hidden;
    }

    .room-avatar {
      width: 100%;
      height: 100%;
      object-fit: cover;
    }

    .room-name {
      font-weight: bold;
      font-size: 16px;
      margin-bottom: 5px;
    }

    .room-tag {
      font-size: 14px;
      color: #777;
      margin-bottom: 5px;
    }

    .room-online {
      font-size: 12px;
      color: #24d97f;
    }


    /* 弹窗遮罩层 */
    .modal-overlay {
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background-color: rgba(0, 0, 0, 0.5);
      display: flex;
      justify-content: center;
      align-items: center;
      z-index: 1000;
    }

    /* 弹窗内容 */
    .modal-content {
      background-color: white;
      color: #87aca2;
      padding: 20px;
      border-radius: 8px;
      width: 400px;
      box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    }

    /* 头像部分样式 */
    .avatar-section2 {
      display: flex;
      align-items: center;
      margin-bottom: 20px;
    }

    .avatar2 {
      background-color: #87aca2;
      width: 100px;
      height: 100px;
      border-radius: 50%;
      object-fit: cover;
      margin-right: 20px;
      border: 2px solid #ddd;
    }

    .modal-content h3 {
      margin-bottom: 20px;
    }

    .modal-content form div {
      margin-bottom: 15px;
    }

    .modal-buttons {
      display: flex;
      justify-content: flex-end;
      gap: 10px;
    }

    .modal-buttons button {
      background-color: #87aca2;
      width: 100px;
      padding: 5px 10px;
      font-size: 14px;
      color: white;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }
  }

  .modal-overlay2 {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 1000;
  }

  /* 弹窗内容 */
  .modal-content2 {
    background-color: white;
    color: #87aca2;
    padding: 20px;
    border-radius: 8px;
    width: 800px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  }

  .tags-container {
    display: flex;
    flex-wrap: wrap;
    gap: 10px;
    overflow-y: auto;
    max-height: 300px;
  }

  .tag-btn {
    padding: 10px 20px;
    border-radius: 8px;
    width: 110px;
    height: 30px;
    color: rgb(0, 0, 0);
    font-size: 14px;
    cursor: pointer;
    border: none;
    transition: background-color 0.3s ease;
  }

  .tag-btn:hover {
    opacity: 0.8;
    background-color: #ddd;
  }

  .modal-close {
    background-color: #87aca2;
    width: 80px;
    height: 30px;
    font-size: 14px;
    color: white;
    border: none;
    border-radius: 4px;
    text-align: center;
    margin-left: 630px;
    margin-top: 10px;
    cursor: pointer;

    &:hover{
      font-size: 16px;
    }
  }
</style>
  