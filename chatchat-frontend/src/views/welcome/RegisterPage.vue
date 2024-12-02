<script setup>

import {Lock, Message, User} from "@element-plus/icons-vue";
import {ref,reactive} from "vue";
import {post} from "@/net";
import {ElMessage} from "element-plus";
import router from "@/router";

const form = reactive({
  username: '',
  password: '',
  password_repeat: '',
  email: ''
})

const validateUsername = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请输入用户名'))
  } else if(!/^[a-zA-Z0-9\u4e00-\u9fa5]+$/.test(value)){
    callback(new Error('用户名不能包含特殊字符，只能是中文/英文'))
  } else {
    callback()
  }
}

const validatePassword = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请再次输入密码'))
  } else if (value !== form.password) {
    callback(new Error("两次输入的密码不一致"))
  } else {
    callback()
  }
}

const rules = {
  username: [
    { validator: validateUsername, trigger: ['blur', 'change'] },
    { min: 2, max: 8, message: '用户名的长度必须在2-8个字符之间', trigger: ['blur', 'change'] },
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, max: 16, message: '密码的长度必须在6-16个字符之间', trigger: ['blur', 'change'] }
  ],
  password_repeat: [
    { validator: validatePassword, trigger: ['blur', 'change'] },
  ],
  email: [
    { required: true, message: '请输入邮件地址', trigger: 'blur' },
    {type: 'email', message: '请输入合法的电子邮件地址', trigger: ['blur', 'change']}
  ]
}

const formRef = ref()
const isEmailValid = ref(false)

const onValidate = (prop, isValid) => {
  if(prop === 'email')
    isEmailValid.value = isValid
}
function register() {
  post('/api/auth/register',{
    username: form.username,
    password: form.password,
    email: form.email
  },() => {
    ElMessage.success('注册成功')
    router.push("/")
  })
}

</script>

<template>
<div style="width: 100%; height: 100%; text-align: center">
  <div style="margin-top: 30px">
    <div style="font-size: 20px;font-weight: bold">注册新用户</div>
  </div>
  <div style="margin-top: 20px; padding: 20px">
    <el-form :model="form" :rules="rules" @validate="onValidate" ref="formRef">
      <el-form-item prop="username">
        <el-input v-model="form.username" :maxlength="8" type="text" placeholder="用户名">
          <template #prefix>
            <el-icon><User /></el-icon>
          </template>
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input v-model="form.password" :maxlength="16" type="password" placeholder="密码">
          <template #prefix>
            <el-icon><Lock /></el-icon>
          </template>
        </el-input>
      </el-form-item>
      <el-form-item prop="password_repeat">
        <el-input v-model="form.password_repeat" :maxlength="16" type="password" placeholder="重复密码">
          <template #prefix>
            <el-icon><Lock /></el-icon>
          </template>
        </el-input>
      </el-form-item>
      <el-form-item prop="email">
        <el-input v-model="form.email" type="email" placeholder="电子邮件地址">
          <template #prefix>
            <el-icon><Message /></el-icon>
          </template>
        </el-input>
      </el-form-item>
    </el-form>
    <el-button type="success" style="width: 260px" @click="register">注册</el-button>
  </div>
</div>
</template>

<style scoped>

</style>