以下是一份涵盖上述功能的完整 API 接口文档，涵盖用户管理、聊天室管理、消息管理和同步功能。每个接口都包含方法类型、URL 路径、请求参数、响应格式以及功能说明。

---

### **1. 用户管理模块**

#### **1.1 用户注册**
- **方法类型：** POST  
- **接口路径：** `api/users/register`  
- **请求参数：**
  ```json
  {
    "userName": "string",
    "userId": "int",
    "password": "string"
  }
  ```
- **响应格式：**
  ```json
  {
    "code": 200,
    "msg": "Registration successful"
  }
  ```

#### **1.2 用户登录**
- **方法类型：** POST  
- **接口路径：** `api/users/login`  
- **请求参数：**
  ```json
  {
    "userId": "int",
    "password": "string"
  }
  ```
- **响应格式：**
  ```json
  {
    "code": 200,
    "msg": "Login successful",
    "data": {
         "token": "string"
    }
  }
  ```
- **说明：** 用户登录成功后，返回 JWT Token 用于身份验证。

#### **1.3 获取用户信息**
- **方法类型：** Post  
- **接口路径：** `api/users/own`  
- **请求参数：**
  - headers: {
        'Authorization': `Bearer ${token}`
    }
- **响应格式：**
  ```json
  {
    "code": 200,
    "message": "User info fetched successfully",
    "data": {
      "userId": int,
      "userName": "string",
      "userAvatar": "url",
      "blacklist": [uid3]
    }
  }
  ```
- **说明：** 查询用户详细信息。

#### **1.4 更新用户信息**
- **说明：** 更新用户的头像。
- **方法类型：** POST
- **接口路径：** `api/users/upload-avatar`  
- **请求参数：**
  ```json
  {
    headers: {
        'Authorization': `Bearer ${token}`
    },
    "userAvatar":"url"
  }
  ```
- **响应格式：**
  ```json
  {
    "code": 200,
    "message": "User info updated successfully"
  }
  ```
- **说明：** 更新用户的用户名。
- - **方法类型：** POST
- **接口路径：** `api/users/upload-username`  
- **请求参数：**
  ```json
  {
    headers: {
        'Authorization': `Bearer ${token}`
    },
    "newUsername": "string"
  }
  ```
- **响应格式：**
  ```json
  {
    "code": 200,
    "message": "User info updated successfully"
  }
  ```
- **说明：** 更新用户的密码。
- - **方法类型：** POST
- **接口路径：** `/api/users/upload-password
- **请求参数：**
  ```json
  {
    headers: {
        'Authorization': `Bearer ${token}`
    },
    "newPassword": "string"
  }
  ```
- **响应格式：**
  ```json
  {
    "code": 200,
    "message": "User info updated successfully"
  }
  ```

#### **1.5 更新黑名单**
- **方法类型：** PUT  
- **接口路径：** `@/blacklist`  
- **请求参数：**
  ```json
  {
    "action": "add/remove",
    "target-userId": int
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

#### **1.6 搜索用户信息**
- **方法类型：** GET  
- **接口路径：** `/getUserByUserId`  
- **请求参数：**
  - "headers": {
        'Authorization': `Bearer ${token}`
    },
    "userId":"int"
    
- **响应格式：**
  ```json
  {
    "code": 200,
    "message": "User info fetched successfully",
    "users": {
      "userName": "string",
      "userAvatar": "url"
    }
  }
  ```
- **说明：** 查询用户详细信息。
---

### **2. 聊天室管理模块**

---

#### **2.1. 创建房间**
- **方法类型：** POST
- **接口路径：** `api/rooms/create`
- **描述：** 创建新房间（支持私聊、群聊）。

**请求参数：**
//这里增加了一个gruop群聊类型,删除了用户id的传入，id是自增的
```json
{
  "roomType": "public/private/group",       
  "roomName": "string",   
  "roomAvatar": "url",    
  "roomTag": "string"
  
}
```

**响应格式：**
```json
{
  "code": 200,
  "message": "Room created successfully"
}
```

---

#### **2.2. 查询房间详情**
- **方法类型：** GET
- **接口路径：** `api/rooms/{roomId}`
- **描述：** 获取房间的详细信息。

**请求参数：**
- Path 参数：`roomId`（房间 ID）

**响应格式：**
```json
{
  "code": 200,
  "message": "Room details fetched successfully",
  "data": {
    "roomId": int,
    "roomName": "string",
    "roomType": "public/private",
    "ownerId": int,
    "roomAvatar": "url",
    "roomTag": "string",
    "roomPeopleCount": int,
    "members": [
      {
        "userId": int,
        "username": "string",
        "roomAvatar": "url"
      },
      {
        "userId": int,
        "username": "string",
        "roomAvatar": "url"
      }
    ]
  }
}

```

---

#### **2.3. 加入房间**
- **方法类型：* POST
- **接口路径：** `/api/rooms/join`
- **描述：** 通过 Room ID 或邀请码加入房间。

**请求参数：**
```json
{
  headers: {
     'Authorization': `Bearer ${token}`
  },
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

#### **2.4. 离开房间**
- **方法类型：** POST
- **接口路径：** `/api/rooms/{roomId}/leave`
- **描述：** 用户主动退出房间。
**请求参数：**
```json
{
  headers: {
     'Authorization': `Bearer ${token}`
  },
  "roomId": int
}
```
**响应格式：**
```json
{
  "code": 200,
  "message": "Left room successfully"
}
```

---

#### **2.5. 删除房间**
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

#### **2.6. 修改房间信息**
- **方法类型：** PUT
- **接口路径：** `/api/rooms/{roomId}/update`
- **描述：** 修改房间名称、头像等信息。

**请求参数：**
```json
{
  "roomName": "新房间名称",
  "maxMembers": 100,
  "roomAvatar": "👾",
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

---

#### **2.8. 搜索房间**
- **方法类型：** GET
- **接口路径：** `/api/rooms/search`
- **描述：** 按房间名称搜索房间。

**请求参数：**
```json
{
  "query": "编程"
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
      "roomPeopleCount": 50,
      "maxMembers": 100
    }
  ]
}
```

---

#### **2.9. 获得6个推荐房间
- **方法类型：** GET
- **接口路径：** `api/rooms/get6rooms`

**请求参数：**


**响应格式：**
```json
{
  "code": 200,
  "message": "Rooms fetched successfully",
  "data": {
      "rooms": [
        {
            "roomAvatar": "url",
            "roomName": "string",
            "roomId": int,
            "roomTag": "string",
            "roomPeopleCount": int
        }
      ]
  }
}
```
---
#### **2.10. 获得全部标签
- **方法类型：** GET
- **接口路径：** `api/rooms/tags`

**请求参数：**


**响应格式：**
```json
{
  "code": 200,
  "message": "Rooms fetched successfully",
  "data": {
      "tags": [
        {
            "tag": "string",
            "color": "#E4080A"  (每次有新tag时随机生成一个颜色）
        },
      ]
}
```
---
#### **2.11. 获得用户所有加入房间的基本信息
- **方法类型：** GET
- **接口路径：** `api/rooms/room-choose`

**请求参数：**
  ```json
  {
    headers: {
        'Authorization': `Bearer ${token}`
    }
  }
  ```
**响应格式：**
```json
{
  "code": 200,
  "message": "Rooms fetched successfully",
  "data": {
      "rooms": [
        {
            "roomAvatar": "url",
            "roomName": "string",
            "roomId": int,
            "roomTag": "string",
            "roomPeopleCount": int
        }
      ]
  }
}
```
---
#### **2.12. 获取20个推荐房间标签
- **方法类型：** GET
- **接口路径：** `api/rooms/sugTags`

**请求参数：**
 
**响应格式：**
```json
{
  "code": 200,
  "message": "Rooms fetched successfully",
  "data": {
      "sugTags": [
        {
            "tag": "string",
            "color": "string"
        },
        {
            "tag": "string",
            "color": "string"
        }
      ]
  }
}
```
---
#### **2.13. 通过标签获得房间
- **方法类型：** GET
- **接口路径：** `api/rooms/getRoomsByTag/{Tag}`

**请求参数：**

  **响应格式：**
```json
{
  "code": 200,
  "message": "Rooms fetched successfully",
  "data": {
      "rooms": [
        {
            "roomAvatar": "url",
            "roomName": "string",
            "roomId": int,
            "roomTag": "string",
            "roomPeopleCount": int,
            "latestMsg": "string",
            "latestMsgTime":"string"
        },
      ]
  }
}

```

#### **2.13. 搜索房间**
- **方法类型：** GET
- **接口路径：** `/api/rooms/myroom-search`
- **描述：** 按房间名称或类型搜索房间。

**请求参数：**
```json
{
  "query": "编程"
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
      "roomPeopleCount": 50,
      "maxMembers": 100
    }
  ]
}

```
---

### 2.15 根据房间 ID 获取房间详情 (`/api/rooms/getRoomsByRoomId`)

**方法类型：** GET  
**接口路径：** `/api/rooms/getRoomsByRoomId/{roomId}`  
**请求参数：**

**响应格式：**
```json
{
  "code": 200,
  "message": "Room details fetched successfully",
  "data": {
    "roomId": 1,
    "roomName": "编程爱好者",
    "roomType": "group",
    "roomPeopleCount": 50,
    "maxMembers": 100,
    "roomTags": ["编程", "学习"]
  }
}
```
---
        
        
        
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
- **连接路径：** `/ws/chat/{roomId}/{uid}`  
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

#### **4.3 私聊创建通知**  
- **连接路径：** `/ws/{uid}`
- **发送格式：**
```json
{
  "roomId": 123, // 私聊房间号
  "uid": 1, // 发起私聊的人的UID
  "type": "TEXT", 
  "content": {
    "text": "2", // 被私聊的对象的UID（转换为字符串）
    "url": null, 
    "meta": null 
  },
  "userName":"UserA", // 发起私聊的人的昵称
  "userAvatar":"...." // 他的头像url
}
```
- **接收格式：**
```json
{
  "roomId": 123, //私聊房间号
  "uid": 1, // 发起私聊的人的UID
  "type": "TEXT",
  "content": {
    "text": "2", // 被私聊的对象的UID（转换为字符串）
    "url": null,
    "meta": null
  },
  "sendTime": "2024-11-23T10:30:00"
  "userName":"UserA", // 发起私聊的人的昵称
  "userAvatar":"...." // 他的头像url
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
