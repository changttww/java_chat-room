Java 开发的聊天室系统 API 设计，包括用户管理、社区管理、聊天功能等模块。API 设计以 RESTful 风格为基础。

---

## **1. 用户管理模块**
### **1.1 用户注册**
- **接口路径**：`POST /api/users/register`
- **请求参数**：
  ```json
  {
    "username": "string",
    "password": "string",
    "name": "string",
    "head": "string (URL, optional)"
  }
  ```
- **返回结果**：
  ```json
  {
    "code": 200,
    "message": "User registered successfully",
    "data": {
      "userId": "integer"
    }
  }
  ```

### **1.2 用户登录**
- **接口路径**：`POST /api/users/login`
- **请求参数**：
  ```json
  {
    "username": "string",
    "password": "string"
  }
  ```
- **返回结果**：
  ```json
  {
    "code": 200,
    "message": "Login successful",
    "data": {
      "token": "string"
    }
  }
  ```

### **1.3 获取用户信息**
- **接口路径**：`GET /api/users/{userId}`
- **请求头**：`Authorization: Bearer {token}`
- **返回结果**：
  ```json
  {
    "code": 200,
    "message": "Success",
    "data": {
      "id": "integer",
      "username": "string",
      "name": "string",
      "head": "string"
    }
  }
  ```

### **1.4 修改用户信息**
- **接口路径**：`PUT /api/users/{userId}`
- **请求头**：`Authorization: Bearer {token}`
- **请求参数**：
  ```json
  {
    "name": "string",
    "head": "string (URL)"
  }
  ```
- **返回结果**：
  ```json
  {
    "code": 200,
    "message": "User information updated successfully"
  }
  ```

---

## **2. 社区管理模块**
### **2.1 创建社区**
- **接口路径**：`POST /api/communities`
- **请求头**：`Authorization: Bearer {token}`
- **请求参数**：
  ```json
  {
    "name": "string",
    "description": "string (optional)"
  }
  ```
- **返回结果**：
  ```json
  {
    "code": 200,
    "message": "Community created successfully",
    "data": {
      "communityId": "integer"
    }
  }
  ```

### **2.2 获取社区列表**
- **接口路径**：`GET /api/communities`
- **返回结果**：
  ```json
  {
    "code": 200,
    "message": "Success",
    "data": [
      {
        "id": "integer",
        "name": "string",
        "description": "string"
      }
    ]
  }
  ```

### **2.3 加入社区**
- **接口路径**：`POST /api/communities/{communityId}/join`
- **请求头**：`Authorization: Bearer {token}`
- **返回结果**：
  ```jso
