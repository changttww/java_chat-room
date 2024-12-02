<template>
  <div class="home2-container">
    <!-- 顶栏 -->
    <div class="topbar2">
      <div class="topbar2-content">
        <span class="topbar2-title">StarBBPark</span>
        <div class="topbar2-links">
          <a href="http://localhost:5173/home" class="topbar2-link">首页社区</a>
          <a href="http://localhost:5173/chat" class="topbar2-link2">消息</a>
          <a href="http://localhost:5173/own" class="topbar2-link">个人中心</a>
        </div>
      </div>
    </div>

    <!-- 页面主体内容 -->
    <div class="home2-content">
      <!-- 左侧聊天选择 -->
      <div class="chat_choose">
        <div class="chat_search">
          <input type="text" class="search2-input" placeholder="请输入搜索内容">
          <button class="secondbar2-link">搜索</button>
        </div>
        <hr>
        <ul>
          <li>聊天室 1</li>
          <li>聊天室 2</li>
          <li>聊天室 3</li>
          <li>聊天室 4</li>
          <li>聊天室 5</li>
          <li>聊天室 6</li>
          <li>聊天室 7</li>
          <li>聊天室 8</li>
          <li>聊天室 9</li>
          <li>聊天室 10</li>
          <li>聊天室 11</li>
          <li>聊天室 12</li>
          <li>聊天室 13</li>
        </ul>
      </div>

      <!-- 右侧聊天区域 -->
      <div class="chat_area">
        <!-- 上侧信息展示 -->
        <div class="chat-info">
          信息展示
        </div>
        <hr>
        <!-- 聊天内容区域 -->
        <div class="chat-container">
          <!-- 消息气泡可以按需动态加载 -->
          <div class="message-bubble left">你好，请问有什么问题吗？</div>
          <div class="message-bubble right">你好，我想了解一下产品的情况。</div>
        </div>

        <hr>
        <!-- 输入框和发送按钮 -->
        <div class="chat-input-container">
          <!-- 功能按钮区 -->
          <div class="func-logo">
            <div class="document">文件</div>
            <div class="emoji">表情</div>
            <!-- <div class="call">通话</div> -->
            <div class="voice">语音</div>
          </div>

          <!-- 消息发送区 -->
          <div class="send_area">
            <textarea type="text" class="chat-input" placeholder="请输入消息..." />
            <button class="send-button">发送</button>
          </div>

        </div>
      </div>
    </div>
  </div>
</template>
  
  <script setup>
  // 你可以在这里添加任何需要的 Vue 逻辑
  </script>
  
  <style lang="scss">
  .home2-container {
    height: 100vh; /* 使容器的高度等于视口的高度 */
    width: 100%;
    display: flex;
    flex-direction: column;
    background-color: #62A89B;
    /* 背景色保持一致 */

    /* 顶栏样式 */
    .topbar2 {
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

    .topbar2-content {
      display: flex;
      justify-content: space-between;
      align-items: center;
      width: 100%;
    }

    .topbar2-title {
      font-size: 24px;
      color: white;
      font-weight: bold;
    }

    .topbar2-links {
      display: flex;
      gap: 20px;
    }

    .topbar2-link2 {
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

    .topbar2-link {
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

    /* 页面主体内容 */
    .home2-content {
      display: flex;
      /* 使用 Flexbox 布局 */
      flex-direction: row;
      /* 设置 Flex 容器的子元素按垂直方向排列 */
      //flex: 1;
      /* 让 content 填充剩余的空间 */
      color: white;
      /* 设置字体颜色为白色 */
      padding: 0px;
      /* 给容器添加内边距，防止内容紧贴容器边缘 */
    }

    .chat_search {
      display: flex;
      justify-content: space-between;
      /* 输入框和按钮之间的间距 */
    }

    .search2-input{
      width: 80%;
      height: 30px;
      padding: 0 10px;
      border-radius: 5px;
      border: none;
      font-size: 14px;
      outline: none;
    }

    .secondbar2-link{
      width: 30%;
      /* 每个按钮占 30% 宽度，保证它们均匀分布 */
      height: 30px;
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

    .chat_choose {
      width: 350px;
      /* 设置侧边栏的宽度 */
      background-color: #87aca2;
      border-right: 1px solid #ddd;
      /* 给侧边栏加一个右边框 */
      padding: 20px;
      box-sizing: border-box;
      //height: 100%;
    }

    .chat_choose ul {
      list-style: none;
      padding: 0;
      margin-top: 15px;
      max-height: 80vh;
      /* 设置最大高度为视口高度的 80% */
      overflow-y: auto;
      /* 启用垂直滚动条，超出内容时可滚动 */
      padding: 0;
      /* 去掉ul的内边距 */
      margin-top: 15px;
      /* 去掉ul的外边距 */
      list-style-type: none;
      /* 去掉默认的列表样式 */
    }

    .chat_choose li {
      margin-bottom: 15px;
      /* 设置每个列表项底部的外边距为 15px，确保它们之间有一定的间距 */
      padding: 10px;
      /* 设置每个列表项内边距为 10px，增加点击区域，增强用户体验 */
      background-color: #d9ede0;
      /* 设置每个列表项的背景色为浅灰色 */
      border-radius: 8px;
      /* 设置每个列表项的圆角半径为 8px，产生圆角效果 */
      cursor: pointer;
      /* 鼠标悬停时显示为指针，表示该项是可点击的 */
      transition: background-color 0.3s ease;
      /* 设置背景颜色变化的过渡效果，持续 0.3 秒，呈现平滑的变化 */
    }


    .chat_choose li:hover {
      background-color: #e0e0e0;
    }

    .chat_area {//右侧一整个
      flex: 1;
      display: flex;
      flex-direction: column;
      padding: 10px;
      color: #000;
      background-color: #87aca2;
      height: 100%;
      width: 100%;
    }

    .chat-container {//消息展示区
      flex: 1;
      padding: 20px;
      overflow-y: auto;
      background-color: #ffffff;
    }

    .message-bubble {
      max-width: 60%;
      padding: 12px;
      margin-bottom: 12px;
      border-radius: 18px;
      font-size: 16px;
      line-height: 1.5;
      display: inline-block;
      word-wrap: break-word;
    }

    .left {
      background-color: #bfbfbf;
      color: #000;
      margin-left: 10px;
      margin-right: auto;
    }

    .right {
      background-color: #9fdab8;
      color: rgb(8, 8, 8);
      margin-right: 10px;
      margin-left: auto;
    }

    .chat-input-container {
      display: flex;
      flex-direction: column;
      height: 180px;
      background-color: #ffffff;
      box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.1);
    }

    .func-logo {
      display: flex; /* 使用 flex 布局，使子元素水平排列 */
      gap: 20px; /* 设置子元素之间的间距为 20px */
      //justify-content: center; /* 在水平方向上居中对齐子元素 */
      align-items: center; /* 在垂直方向上居中对齐子元素 */
      width: 100%; /* 设置容器的宽度为父元素的 100%，确保容器宽度自适应 */
      height: 30px; /* 设置容器的高度为 30px */
      background-color: #ffffff; /* 设置容器的背景色为 #87aca2（绿色调） */
    }


    .func-logo > div {
      padding: 2px; /* 给每个子元素添加一些内边距，提升点击区域 */
      font-size: 15px;
      color: #797979;
      background-color: #ffffff; /* 背景颜色 */
      //border-radius: 8px; /* 设置圆角 */
      cursor: pointer; /* 鼠标悬停时显示为指针 */
    }

    .func-logo > div:hover {
      background-color: #e0e0e0; /* 鼠标悬停时改变背景颜色 */
    }


    .chat-input {
      flex: 1; /* 让输入框占据父容器中剩余的空间 */
      height: 140px; /* 设置输入框的高度为 40px */
      width: 1100px;
      padding: 2px 15px 5px 10px; /* 设置左右内边距为 15px，使输入框内容不贴边 */
      border: 1px solid #ffffff; /* 设置边框为 1px 宽，颜色为浅灰色（#ddd） */
      font-size: 16px; /* 设置输入框字体大小为 16px */
      outline: none; /* 移除输入框聚焦时的默认轮廓（边框阴影） */
      transition: border-color 0.3s ease; /* 设置边框颜色的平滑过渡效果 */
      white-space: pre-wrap; /* 保证换行符被正确显示 */
      word-wrap: break-word; /* 使超出宽度的内容自动换行 */
      line-height: 1.5; /* 设置行高，以改善文本显示 */
    }

    .chat-input:focus {
      border-color: #5da877; /* 当输入框获得焦点时，边框颜色变为蓝色 (#0084FF) */
    }

    .send-button {
      background-color: rgb(249, 250, 250); /* 设置按钮的背景颜色为蓝色 (#0084FF) */
      color: #5da877; /* 设置按钮文字颜色为白色 */
      font-size: 16px; /* 设置按钮文字大小为 16px */
      padding: 10px 20px; /* 设置按钮的内边距，上下 10px，左右 20px */
      border: none; /* 去掉按钮的边框 */
      border-radius: 20px; /* 设置按钮的圆角为 20px，使按钮边缘圆滑 */
      cursor: pointer; /* 设置鼠标悬停时，按钮显示为指针，表示可以点击 */
      margin-right: 10px; /* 设置按钮与输入框之间的左外边距为 10px，提供一些间隔 */
      margin-bottom: 10px;
      transition: background-color 0.3s ease; /* 设置按钮背景颜色的平滑过渡效果 */
    }

    .send-button:hover {
      background-color: #e0e0e0; /* 鼠标悬停时，按钮背景颜色变为深蓝色 (#005bb5) */
    }
  }
</style>
  