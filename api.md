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



二、聊天室管理模块
### 1. **插入聊天室记录 API**
- **请求路径**: `/rooms/insertUserCommunityRecord`
- **请求方式**: `POST`
- **请求体**:
  ```json
  {
    "userId": "string",   // 用户 ID
    "community": "string" // 社区名称
  }
  ```
- **响应**:
    - **成功响应**:
      ```json
      {
        "code": 200,
        "msg": "successfully",
        "data": null
      }
      ```
    - **失败响应**:
      ```json
      {
        "code": 0,
        "msg": "Error",
        "data": null
      }
      ```

### 2. **插入聊天室记录到数据库 API** (由 `UserCommunityRecordMapper` 处理)
- **请求路径**: 无直接访问路径，属于数据库层操作。
- **方法**: `insertUserCommunityRecord`
- **请求参数**:
    - `userCommunityRecord`: 包含用户 ID 和社区名称的对象
        - `userId`: 用户 ID
        - `community`: 社区名称
- **返回值**: 返回一个整数，表示插入操作的结果。如果大于 0，则表示插入成功。

---