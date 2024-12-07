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
  "roomType": "group",        // 房间类型：'public', 'private' 或 'group'
  "roomName": "编程爱好者",    // 房间名，仅对 'public' 和 'group' 类型有效
  "maxMembers": 50,            // 最大成员数，仅对 'public' 和 'group' 类型有效
  "head": "😀"                 // 头像，仅对'public'和 'group'有效
  }
  ```
- **响应格式：**
  ```json
  {
  "code": 200,
  "message": "Room created successfully",
  "data": {
    "roomId": 1,
    "roomName": "编程爱好者",
    "roomType": "group",
    "ownerUid": 1,
    "maxMembers": 50,
    "inviteCode": "x9a2bdf39ef517346aaa6742b2f7796d8",
    "head": "👨‍💻",
    "createdAt": "2024-12-05T12:34:56",
    "updatedAt": "2024-12-05T12:34:56"
          }
  }


  ```
- **说明：** 创建房间，并返回 Room ID 和邀请码（若为群聊）。
---

#### **2.2 加入房间**
- **方法类型：** POST  
- **接口路径：** `/api/rooms/join`  
- **请求参数：**
  ```json
  
  {
    "roomId": 14,
    "inviteCode": "string" // 群聊时才填
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
### **完整 API 设计：聊天室管理模块**

---

#### **1. 创建房间**
- **方法类型：** POST
- **接口路径：** `/api/rooms/create`
- **描述：** 创建新房间（支持私聊、群聊、公聊）。

**请求参数：**
```json
{
  "roomType": "group",         // 房间类型：'public', 'private', 'group'
  "roomName": "编程爱好者",     // 房间名称，仅对 'public' 和 'group' 类型有效
  "maxMembers": 50,            // 最大成员数，仅对 'public' 和 'group' 类型有效
  "head": "😀",                // 房间头像，仅对 'public' 和 'group' 类型有效
  "receiverUid": 2             // 私聊时接收者用户 ID，仅对 'private' 类型有效
}
```

**响应格式：**
```json
{
  "code": 200,
  "message": "Room created successfully",
  "data": {
    "roomId": 1,
    "roomName": "编程爱好者",
    "roomType": "group",
    "ownerUid": 1,
    "maxMembers": 50,
    "inviteCode": "x9a2bdf3",
    "head": "👨‍💻",
    "createdAt": "2024-12-07T12:00:00"
  }
}
```

---

#### **2. 查询房间详情**
- **方法类型：** GET
- **接口路径：** `/api/rooms/{roomId}`
- **描述：** 获取房间的详细信息。

**请求参数：**
- Path 参数：`roomId`（房间 ID）

**响应格式：**
```json
{
  "code": 200,
  "message": "Room details fetched successfully",
  "data": {
    "roomId": 1,
    "roomName": "编程爱好者",
    "roomType": "group",
    "ownerUid": 1,
    "members": [
      {
        "userId": 1,
        "username": "admin",
        "head": "👨‍💻",
        "joinedAt": "2024-12-07T12:05:00"
      },
      {
        "userId": 2,
        "username": "user1",
        "head": "😀",
        "joinedAt": "2024-12-07T12:10:00"
      }
    ],
    "maxMembers": 50
  }
}

```

---

#### **4. 加入房间**
- **方法类型：** POST
- **接口路径：** `/api/rooms/join`
- **描述：** 通过 Room ID 或邀请码加入房间。

**请求参数：**
```json
{
  "roomId": 1,
  "inviteCode": "x9a2bdf3" // 群聊时才需要
}
```

**响应格式：**
```json
{
  "code": 200,
  "message": "Joined room successfully"
}
```

---

#### **5. 离开房间**
- **方法类型：** POST
- **接口路径：** `/api/rooms/{roomId}/leave`
- **描述：** 用户主动退出房间。

**响应格式：**
```json
{
  "code": 200,
  "message": "Left room successfully"
}
```

---

#### **6. 删除房间**
- **方法类型：** DELETE
- **接口路径：** `/api/rooms/{roomId}`
- **描述：** 房主解散房间。

**响应格式：**
```json
{
  "code": 200,
  "message": "Room deleted successfully"
}
```

---

#### **7. 修改房间信息**
- **方法类型：** PUT
- **接口路径：** `/api/rooms/{roomId}/update`
- **描述：** 修改房间名称、头像等信息。

**请求参数：**
```json
{
  "roomName": "新房间名称",
  "maxMembers": 100,
  "head": "👾",
  "description": "更新后的描述"
}
```

**响应格式：**
```json
{
  "code": 200,
  "message": "Room updated successfully"
}
```

---

#### **8. 转让房主**
- **方法类型：** POST
- **接口路径：** `/api/rooms/{roomId}/transfer-ownership`
- **描述：** 将房主权限转让给其他成员。

**请求参数：**
```json
{
  "newOwnerId": 2
}
```

**响应格式：**
```json
{
  "code": 200,
  "message": "Ownership transferred successfully"
}
```

---

#### **9. 搜索房间**
- **方法类型：** GET
- **接口路径：** `/api/rooms/search`
- **描述：** 按房间名称或类型搜索房间。

**请求参数：**
```json
{
  "query": "编程",
  "roomType": "group"
}
```

**响应格式：**
```json
{
  "code": 200,
  "message": "Rooms fetched successfully",
  "data": [
    {
      "roomId": 1,
      "roomName": "编程爱好者",
      "roomType": "group",
      "membersCount": 50,
      "maxMembers": 100
    }
  ]
}
```

---

#### **10. 获取我的房间**
- **方法类型：** GET
- **接口路径：** `/api/rooms/my-rooms`
- **描述：** 查询当前用户创建的所有房间。

**响应格式：**
```json

{
  "code": 200,
  "message": "Rooms fetched successfully",
  "data": [
    {
      "roomId": 13,
      "roomName": "编程爱好者",
      "roomType": "group",
      "ownerUid": 1,
      "maxMembers": 50,
      "inviteCode": "x9a2bdf39ef517346aaa6742b2f7796d8",
      "head": "👨‍💻",
      "description": null,
      "createdAt": "2024-12-05T18:32:39.000+00:00",
      "updatedAt": "2024-12-05T18:32:39.000+00:00"
    },
    {
      "roomId": 24,
      "roomName": "新房间名称",
      "roomType": "group",
      "ownerUid": 1,
      "maxMembers": 100,
      "inviteCode": "03c0102a",
      "head": "👾",
      "description": "更新后的描述",
      "createdAt": "2024-12-05T20:03:54.000+00:00",
      "updatedAt": "2024-12-05T20:03:54.000+00:00"
    }
    ]
}
  
```

---

### **附加功能接口**
#### **11. 踢出成员**
- **方法类型：** POST
- **接口路径：** `/api/rooms/{roomId}/remove-member`
- **描述：** 移除指定成员。

**请求参数：**
```json
{
  "userId": 3
}
```

**响应格式：**
```json
{
  "code": 200,
  "message": "Member removed successfully"
}
```

#### **12. 禁言成员**
- **方法类型：** POST
- **接口路径：** `/api/rooms/{roomId}/mute-member`
- **描述：** 禁言房间中的某成员。

**请求参数：**
```json
{
  "userId": 3,
  "durationMinutes": 30
}
```

**响应格式：**
```json
{
  "code": 200,
  "message": "Member muted successfully"
}
```

---

以上设计包含房间管理、成员管理、权限控制等核心功能，提升了聊天室的完整性和用户体验。

### **3. 消息管理模块**

#### **3.1 发送消息**
- **方法类型：** POST  
  - **接口路径：** `/api/messages/send`  
    - **请求参数：**
  ```json
  {
  "roomId": "123",
  "uid": "001",
  "type": "TEXT",  // 消息类型可能是TEXT, IMAGE, EMOJI
  "content": {
  "text": "Hello, world!",
  "url": null, // 图片资源的URL
  "meta": null // 附加信息，图片宽、高等等
  },
  "userName":"UserA",
  "userAvatar":"...." // 用户头像url
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
      "roomId": "123",
      "uid": "001",
      "type": "IMAGE",  // 消息类型可能是TEXT, IMAGE, EMOJI
      "content": {
        "text": null,
        "url": "...", // 图片资源的URL
        "meta":{
          "width": 1024,
          "height": 768
        } // 附加信息，图片宽、高等等
      },
      "sendTime": "2024-11-23T10:30:00"
      "userName":"UserA",
      "userAvatar":"...." // 用户头像的url
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
  "roomId": "123",
  "uid": "001",
  "type": "TEXT",  // 消息类型可能是TEXT, IMAGE, EMOJI
  "content": {
  "text": "Hello, world!",
  "url": null, // 图片资源的URL
  "meta": null // 附加信息，图片宽、高等等
  },
  "userName":"UserA",
  "userAvatar":"...." // 用户头像url
  }
  ```
- **接收格式：**
  ```json
  {
  "roomId": "123",
  "uid": "001",
  "type": "IMAGE",  // 消息类型可能是TEXT, IMAGE, EMOJI
  "content": {
        "text": null,
        "url": "...", // 图片资源的URL
        "meta":{
          "width": 1024,
          "height": 768
        } // 附加信息，图片宽、高等等
  },
  "sendTime": "2024-11-23T10:30:00"
  "userName":"UserA",
  "userAvatar":"...." // 用户头像的url
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
