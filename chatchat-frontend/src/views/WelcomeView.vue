<script setup>

</script>

<template>
    <div style="width: 100vw; height: 100vh; overflow: hidden;">
      <el-image style="width: 100%; height: 100%;" fit="cover"
        src="https://image.itbaima.net/images/450/image-20231022176210313.jpeg"/>
      <div class="login-page">
        <router-view v-slot="{ Component }">
          <transition name="el-fade-in-linear" mode="out-in">
            <component :is="Component" style="height: 100%"/>
          </transition>
        </router-view>
      </div>
    </div>
</template>

<style scoped>
.login-page {
  position: absolute;
  left: 50vw;
  top: 50vh;
  transform: translate(-50%, -50%);
  width: 300px;
  height: 450px;
  border-radius: 10px;
  background-color: white;
}
</style>