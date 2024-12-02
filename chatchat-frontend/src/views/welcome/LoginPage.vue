<script setup>
import {User, Lock} from '@element-plus/icons-vue'
import router from "@/router";
import {reactive, ref} from "vue";
import {login} from "@/net";
import {userStore} from "@/store";

const formRef = ref()
const form = reactive({
  username: '',
  password: '',
  remember: false
})
const store = userStore();
const rules = {
  username: [
    { required: true, message: '请输入用户名' }
  ],
  password: [
    { required: true, message: '请输入密码'}
  ]
}

function userLogin() {
  formRef.value.validate((isValid) => {
    if(isValid) {
      login(form.username, form.password, form.remember, (data) => {
        router.push("/index")
        store.user.id = data.id;
        store.user.role = data.role;
        store.user.username = data.username;
        store.user.avatar = data.avatar;
        store.user.registerTime = data.registerTime;
        })
    }
  });
}
</script>

<template>
  <div style="text-align: center">
    <div>
      <el-avatar :size="100" style="margin-top: 30px"
          src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
      />
    </div>
    <div style="margin: 30px 20px 0;">
      <el-form :model="form" :rules="rules" ref="formRef">
        <el-form-item prop="username">
          <el-input v-model="form.username" maxlength="10" type="text" placeholder="用户名/邮箱">
            <template #prefix>
              <el-icon>
                <User/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.password" type="password" maxlength="20" style="margin-top: 10px" placeholder="密码">
            <template #prefix>
              <el-icon>
                <Lock/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-row style="margin-top: 5px">
          <el-col :span="12" style="text-align: left">
            <el-form-item prop="remember">
              <el-checkbox v-model="form.remember" label="记住我"/>
            </el-form-item>
          </el-col>
          <el-col :span="12" style="text-align: right">
            <el-link @click="router.push('/forget')">忘记密码？</el-link>
          </el-col>
        </el-row>
      </el-form>
    </div>
    <div style="margin-top: 5px">
      <el-button @click="userLogin()" style="width: 260px" type="success" plain>立即登录</el-button>
    </div>
    <div style="margin-top: 20px">
      <el-row style="margin-top: 5px">
        <el-col :span="11" style="text-align: right">
          <el-link @click="router.push('/forget')">服务条款</el-link>
        </el-col>
        <el-col :span="2">
          <el-divider direction="vertical"/>
        </el-col>
        <el-col :span="11" style="text-align: left">
          <el-link @click="router.push('/register')">立即注册</el-link>
        </el-col>
      </el-row>

    </div>
  </div>
</template>

<style scoped>

</style>