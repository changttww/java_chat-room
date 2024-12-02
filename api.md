一、登陆注册模块
### 1. **注册 API**
- **请求路径**: `/users/register`
- **请求方式**: `POST`
- **请求体**:
  ```json
  {
    "username": "string",
    "password": "string",
    "name": "string",
    "head": "string"  // 可选，默认为表情符号
  }
  ```
- **响应**:
    - **成功响应**:
      ```json
      {
        "code": 200,
        "message": "Successfully",
        "data": "uid={userId}"
      }
      ```
    - **失败响应**:
      ```json
      {
        "code": 0,
        "message": "id_error",
        "data": null
      }
      ```

### 2. **登录 API**
- **请求路径**: `/users/login`
- **请求方式**: `POST`
- **请求体**:
  ```json
  {
    "username": "string",
    "password": "string"
  }
  ```
- **响应**:
    - **成功响应**:
      ```json
      {
        "code": 200,
        "message": "Successfully",
        "data": {
          "id": 1,
          "username": "string1",
          "name": "User One",
          "head": "😊"
        }
      }
      ```
    - **失败响应**:
      ```json
      {
        "code": 0,
        "message": "Error",
        "data": null
      }
      ```

---



#消息管理模块
### 1. **插入聊天记录 API**
- **请求路径**: `/chatRecord/add`
- **请求方式**: `POST`
- **请求体**: 暂无实际功能代码，但可以推测这是用于插入聊天记录的 API，虽然未完成实现。
- **响应**:
    - **成功响应**:
      ```json
      {
        "code": 200,
        "message": "Successfully",
        "data": "null"
      }
      ```

### 2. **获取某个聊天室/社区的聊天记录 API**
- **请求路径**: `/chatRecord/messageRecord`
- **请求方式**: `POST`
- **请求体**:
  ```json
  {
    "community": "string"  // 聊天室/社区名
  }
  ```
- **响应**:
    - **成功响应**:
      ```json
      {
        "code": 200,
        "message": "Successfully",
        "data": [
          {
            "id": 1,
            "community": "string",
            "message": "string"
          },
          {
            "id": 2,
            "community": "string",
            "message": "string"
          }
          // 更多记录...
        ]
      }
      ```
    - **失败响应**:
      ```json
      {
        "code": 0,
        "message": "Error",
        "data": null
      }
      ```

### 3. **插入聊天记录到数据库 API** (由 `ChatRecordMapper` 处理)
- **请求路径**: 无直接访问路径，属于数据库层操作。
- **方法**: `insertMessage`
- **请求参数**:
    - `chatRecord`: 包含社区名和消息内容的对象
        - `community`: 聊天室/社区名
        - `message`: 消息内容
- **返回值**: 返回一个整数，表示插入操作的结果

---



### **3. 聊天室管理模块**

#### **2.1 创建房间**
- **方法类型：** POST
- **接口路径：** `/api/rooms/create`
- **请求参数：**
  ```json
  {
  "roomName":"test1",
  "roomType": "public", // public/private/group
  "ownerUid": "2",
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

