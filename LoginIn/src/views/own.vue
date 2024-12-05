<template>
  <div class="home3-container">
    <!-- 顶栏 -->
    <div class="topbar3">
      <div class="topbar3-content">
        <span class="topbar3-title">StarBBPark</span>
        <div class="topbar3-links">
          <a href="http://localhost:5173/home" class="topbar3-link">首页社区</a>
          <a href="http://localhost:5173/chat" class="topbar3-link">消息</a>
          <a href="http://localhost:5173/own" class="topbar3-link1">个人中心</a>
        </div>
      </div>
    </div>

    <!-- 页面主体内容 -->
    <div class="home3-content">
      <!-- 左侧边栏选择 -->
      <div class="func-choose">
        <a href="http://localhost:5173/own" class="self-info">个人信息</a>
        <hr>
        <a href="http://localhost:5173/own/blacklist" class="blacklist">黑名单</a>
        <hr>
        <a href="http://localhost:5173/own/settings" class="setting">设置</a>
      </div>

      <!-- 右边主体内容 -->
      <!-- <div class="info-container"> -->
        <div class="profile-box">
          <div class="locate">
          <div class="avatar-section">
            <img url="src\assets\images\ENFP-竞选者.png" alt="头像" class="avatar">
            <button class="edit-avatar">修改头像</button>
          </div>
          <div class="user-info">
            <div>
              <strong>用户名：</strong>张三
              <button class="edit-avatar">修改用户名</button>
            </div>
            <br>
            <div>
              <strong>账号：</strong>zhangsan123
            </div>
            <br>
            <div>
              <strong>密码：</strong>********
              <button class="edit-avatar">修改密码</button>
            </div>
          </div>
        </div>
        </div>
      <!-- </div> -->
    </div>
  </div>
</template>
  
  <script setup>
  // 你可以在这里添加任何需要的 Vue 逻辑
  </script>
  
<style lang="scss">
  .home3-container {
    width: 100%;
    min-height: 100vh;
    display: flex;
    flex-direction: column;
    background-color: #fbfcfc; /* 背景色保持一致 */
  }
  /* 顶栏样式 */
  .topbar3 {
    background-color: rgb(83,135,124); /* 与 card 颜色一致 */
    padding: 15px;
    width: 100%;
    box-sizing: border-box;
    display: flex;
    justify-content: space-between; /* 左右对齐 */
    align-items: center;
  }

  .topbar3-content {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 100%;
  }

  .topbar3-title {
    font-size: 24px;
    color: white;
    font-weight: bold;
  }

  .topbar3-links {
    display: flex;
    gap: 20px;
  }

  .topbar3-link1 {
    font-size: 20 px;
    color: white;
    text-decoration: none;
    font-weight: normal;
    transition: color 0.3s;

    &:hover {
      color: #24d97f; /* 鼠标悬停时的颜色 */
    }
  }

  .topbar3-link {
    font-size: 16px;
    color: white;
    text-decoration: none;
    font-weight: normal;
    transition: color 0.3s;

    &:hover {
      color: #24d97f; /* 鼠标悬停时的颜色 */
    }
  }

  /* 页面主体内容 */
  .home3-content {
    position: relative;
    display: flex;
    flex-direction: column;
    flex: 1; /* 让 content 填充剩余空间 */
    height: 100vh; /* 使页面高度填充视口 */
  }

  .func-choose {
    position: absolute;
    width: 250px; /* 设置左侧侧边栏的宽度 */
    background-color: #87aca2; /* 设置侧边栏背景颜色 */
    box-sizing: border-box; /* 包括内边距和边框 */
    display: flex;
    height: 100%;
    flex-direction: column; /* 垂直排列 */
  }

  .self-info{
    font-size: 25px;
    text-align: right;
  }

  .func-choose a {
    justify-content: center;
    align-items: center;
    background-color: #d9ede0; /* 设置项的背景色 */
    padding: 12px;
    text-decoration: none;
    color: white;
    font-weight: bold;
    cursor: pointer; /* 鼠标悬停时显示为指针 */
    transition: background-color 0.3s ease; /* 设置背景色过渡效果 */
  }

  .func-choose a:hover {
    background-color: #e0e0e0; /* 鼠标悬停时背景色变浅 */
  }

  // .info-container {
  //   position: absolute;
  //   flex: 1;
  //   margin-left: 20px;
  //   background-color: #ddd;
  //   width: 80%;
  //   height: 100%;
  //   padding: 20px;
  // }

  /* 个人信息容器样式 */
  .profile-box {
    position: absolute;
    border: 3px double #87aca2;
    padding: 20px;
    background-color: #fbfcfc;
    margin: 40px 50px 50px 300px;
    justify-content: center;
    border-radius: 8px;
    height: 70%;
    width: 70%;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  }

  .locate {
    display: flex;
    flex-direction: column;
    //justify-content: center;
  }

  /* 头像部分样式 */
  .avatar-section {
    display: flex;
    align-items: center;
    margin-bottom: 20px;
  }

  .avatar {
    background-color: #87aca2;
    width: 100px;
    height: 100px;
    border-radius: 50%;
    object-fit: cover;
    margin-top: 30px;
    margin-left: 50px;
    margin-right: 20px;
    border: 2px solid #ddd;
  }

  .edit-avatar {
    background-color: #87aca2;
    padding: 5px 10px;
    font-size: 14px;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }

  .edit-avatar:hover {
    font-size: 16px;
  }

  /* 用户信息样式 */
  .user-info {
    background-color: #fbfcfc;
    margin-left: 50px;
    //color: #87aca2;
    //line-height: 10%;
    font-size: 16px;
    line-height: 1.6;
  }

  .user-info strong {
    color: #87aca2;
  }

</style>
  