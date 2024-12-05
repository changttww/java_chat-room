<template>
    <div class="home4-container">
      <!-- 顶栏 -->
      <div class="topbar4">
        <div class="topbar4-content">
          <span class="topbar4-title">StarBBPark</span>
          <div class="topbar4-links">
            <a href="http://localhost:5173/home" class="topbar4-link">首页社区</a>
            <a href="http://localhost:5173/chat" class="topbar4-link">消息</a>
            <a href="http://localhost:5173/own" class="topbar4-link1">个人中心</a>
          </div>
        </div>
      </div>
  
      <!-- 页面主体内容 -->
      <div class="home4-content">
        <!-- 左侧边栏选择 -->
        <div class="func-choose4">
          <a href="http://localhost:5173/own" class="self-info4">个人信息</a>
          <hr>
          <a href="http://localhost:5173/own/blacklist" class="blacklist4">黑名单</a>
          <hr>
          <a href="http://localhost:5173/own/settings" class="setting4">设置</a>
        </div>

        <!-- 右侧黑名单列表 -->
         <div class="list-container">
          <div class="list-user1">

          </div>

         </div>

      </div>
    </div>
  </template>
  
  <script setup>
  // 你可以在这里添加任何需要的 Vue 逻辑
  </script>
  
<style lang="scss">
  .home4-container {
    width: 100%;
    min-height: 100vh;
    display: flex;
    flex-direction: column;
    background-color: #fbfcfc; /* 背景色保持一致 */
  }
  /* 顶栏样式 */
  .topbar4 {
    background-color: rgba(78, 124, 114, 0.75); /* 与 card 颜色一致 */
    padding: 15px;
    width: 100%;
    box-sizing: border-box;
    display: flex;
    justify-content: space-between; /* 左右对齐 */
    align-items: center;
  }

  .topbar4-content {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 100%;
  }

  .topbar4-title {
    font-size: 24px;
    color: white;
    font-weight: bold;
  }

  .topbar4-links {
    display: flex;
    gap: 20px;
  }

  .topbar4-link1 {
    font-size: 20 px;
    color: white;
    text-decoration: none;
    font-weight: normal;
    transition: color 0.3s;

    &:hover {
      color: #24d97f; /* 鼠标悬停时的颜色 */
    }
  }

  .topbar4-link {
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
  .home4-content {
    position: relative;
    display: flex;
    flex-direction: column;
    flex: 1; /* 让 content 填充剩余空间 */
    //padding: 20px;
    height: 100vh; /* 使页面高度填充视口 */
  }

  .func-choose4 {
    position: absolute;
    width: 250px; /* 设置左侧侧边栏的宽度 */
    background-color: #87aca2; /* 设置侧边栏背景颜色 */
    box-sizing: border-box; /* 包括内边距和边框 */
    display: flex;
    height: 100%;
    flex-direction: column; /* 垂直排列 */
    //gap: 15px; /* 设置每个项之间的间距 */
  }

  .blacklist4{
    font-size: 25px;
    text-align: right;
  }

  .func-choose4 a {
    justify-content: center;
    align-items: center;
    background-color: #d9ede0; /* 设置项的背景色 */
    padding: 12px;
    //font-size: 20px;
    text-decoration: none;
    color: white;
    font-weight: bold;
    cursor: pointer; /* 鼠标悬停时显示为指针 */
    transition: background-color 0.3s ease; /* 设置背景色过渡效果 */
  }

  .func-choose4 a:hover {
    background-color: #e0e0e0; /* 鼠标悬停时背景色变浅 */
  }

  .list-container {
    position: absolute;
    border: 3px double #87aca2;
    padding: 20px;
    background-color: #fbfcfc;
    margin: 40px 50px 50px 300px;
    float: right;
    //border-radius: 8px;
    height: 85%;
    width: 70%;
    box-shadow: 0 4px 6px rgba(18, 12, 12, 0.1);
  }

  


</style>
  