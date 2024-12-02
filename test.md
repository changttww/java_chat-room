以下是一份涵盖上述功能的完整 API 接口文档，涵盖用户管理、聊天室管理、消息管理和同步功能。每个接口都包含方法类型、URL 路径、请求参数、响应格式以及功能说明。

---

### **1. 用户管理模块**

#### **1.1 用户注册**
- **方法类型：** POST  
- **接口路径：** `/api/users/register`  
- **请求参数：**
  ```json
  {
    "username": "string",
    "id": int,
    "password": "string"
  }
  ```
- **响应格式：**
  ```json
  {
    "code": 200,
    "message": "Registration successful",
    "data": {
      "uid": "string"
    }
  }
  ```
- **说明：** 创建新用户，生成唯一的 UID。

#### **1.2 用户登录**
- **方法类型：** POST  
- **接口路径：** `/api/users/login`  
- **请求参数：**
  ```json
  {
    "username": "string",
    "password": "string"
  }
  ```
- **响应格式：**
  ```json
  {
    "code": 200,
    "message": "Login successful",
    "data": {
      "token": "string"
    }
  }
  ```
- **说明：** 用户登录成功后，返回 JWT Token 用于身份验证。

#### **1.3 获取用户信息**
- **方法类型：** GET  
- **接口路径：** `/api/users/{uid}`  
- **请求参数：**
  - Path 参数：`uid`（用户唯一 ID）
- **响应格式：**
  ```json
  {
    "code": 200,
    "message": "User info fetched successfully",
    "data": {
      "uid": "string",
      "username": "string",
      "avatar": "string",
      "friends": ["uid1", "uid2"],
      "blacklist": ["uid3"]
    }
  }
  ```
- **说明：** 查询用户详细信息。

#### **1.4 更新用户信息**
- **方法类型：** PUT  
- **接口路径：** `/api/users/{uid}`  
- **请求参数：**
  ```json
  {
    "username": "string",
    "avatar": "string"
  }
  ```
- **响应格式：**
  ```json
  {
    "code": 200,
    "message": "User info updated successfully"
  }
  ```
- **说明：** 更新用户的基本信息。

#### **1.5 更新黑名单**
- **方法类型：** PUT  
- **接口路径：** `/api/users/{uid}/blacklist`  
- **请求参数：**
  ```json
  {
    "action": "add/remove",
    "targetUid": "string"
  }
  ```
- **响应格式：**
  ```json
  {
    "code": 200,
    "message": "Blacklist updated successfully"
  }
  ```
- **说明：** 添加或移除黑名单。

---

### **2. 聊天室管理模块**

#### **2.1 创建房间**
- **方法类型：** POST  
- **接口路径：** `/api/rooms/create`  
- **请求参数：**
  ```json
  {
    "roomType": "public/private/group",
    "ownerUid": "string",
    "maxMembers": 10
  }
  ```
- **响应格式：**
  ```json
  {
    "code": 200,
    "message": "Room created successfully",
    "data": {
      "roomId": "string",
      "inviteCode": "string" // 群聊专用
    }
  }
  ```
- **说明：** 创建房间，并返回 Room ID 和邀请码（若为群聊）。

#### **2.2 加入房间**
- **方法类型：** POST  
- **接口路径：** `/api/rooms/join`  
- **请求参数：**
  ```json
  {
    "roomId": "string",
    "inviteCode": "string" // 群聊时必填
  }
  ```
- **响应格式：**
  ```json
  {
    "code": 200,
    "message": "Joined room successfully"
  }
  ```
- **说明：** 通过 Room ID 或邀请码加入房间。

#### **2.3 查询房间详情**
- **方法类型：** GET  
- **接口路径：** `/api/rooms/{roomId}`  
- **请求参数：**
  - Path 参数：`roomId`（房间 ID）
- **响应格式：**
  ```json
  {
    "code": 200,
    "message": "Room details fetched successfully",
    "data": {
      "roomId": "string",
      "roomType": "public/private/group",
      "ownerUid": "string",
      "members": ["uid1", "uid2"],
      "maxMembers": 10
    }
  }
  ```
- **说明：** 获取房间的详细信息。

---

### **3. 消息管理模块**

#### **3.1 发送消息**
- **方法类型：** POST  
- **接口路径：** `/api/messages/send`  
- **请求参数：**
  ```json
  {
    "roomId": "string",
    "uid": "string",
    "content": "string"
  }
  ```
- **响应格式：**
  ```json
  {
    "code": 200,
    "message": "Message sent successfully"
  }
  ```
- **说明：** 用户在指定房间发送消息。

#### **3.2 同步消息**
- **方法类型：** GET  
- **接口路径：** `/api/messages/sync`  
- **请求参数：**
  - Query 参数：
    - `roomId`：房间 ID
    - `lastTime`：上次同步的时间
- **响应格式：**
  ```json
  {
    "code": 200,
    "message": "Messages fetched successfully",
    "data": [
      {
        "roomId": "string",
        "uid": "string",
        "content": "string",
        "sendTime": "timestamp"
      }
    ]
  }
  ```
- **说明：** 返回指定房间自 `lastTime` 以来的所有消息。

---

### **4. 实时消息推送模块**

#### **4.1 建立 WebSocket 连接**
- **连接路径：** `/ws/chat`  
- **说明：** 客户端通过 WebSocket 协议连接到服务器，用于实时接收消息。

#### **4.2 消息格式**
- **发送格式：**
  ```json
  {
    "roomId": "string",
    "uid": "string",
    "content": "string"
  }
  ```
- **接收格式：**
  ```json
  {
    "roomId": "string",
    "uid": "string",
    "content": "string",
    "sendTime": "timestamp"
  }
  ```

---

### **5. 返回状态码说明**

| 状态码 | 含义                     |
| ------ | ------------------------ |
| 200    | 成功                     |
| 400    | 请求参数错误             |
| 401    | 未授权                   |
| 403    | 权限不足                 |
| 404    | 资源不存在               |
| 500    | 服务器内部错误           |

---

这份 API 文档涵盖了聊天平台的主要功能，您可以根据项目需求进一步扩展和调整。是否需要补充特定功能的实现示例？
