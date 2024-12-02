// import { createRouter, createWebHistory } from 'vue-router'
// import Home from '../views/Home.vue'
// import loginin from '../views/loginin.vue';


// const router = createRouter({
//   history: createWebHistory(import.meta.env.BASE_URL),
//   routes: [
//     {
//       path: '/about',
//       name: 'about',
//       // route level code-splitting
//       // this generates a separate chunk (About.[hash].js) for this route
//       // which is lazy-loaded when the route is visited.
//       component: () => import('../views/AboutView.vue'),
//     },
//     {
//       path: '/',
//       name: 'loginIn',
      
//       component: () => import('../views/loginin.vue'),
//     },
//     {
//       path: '/home',
//       name: 'Home',
      
//       component: () => import('../views/Home.vue'),
//     },
//     {
//       path: '/chat',
//       name: 'chat',
      
//       component: () => import('../views/chat.vue'),
//     },
//     {
//       path: '/own',
//       name: 'own',
      
//       component: () => import('../views/own.vue'),
//     },
//   ],
// })
// export default router;


import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/Home.vue';
import loginin from '../views/loginin.vue';
import chat from '../views/chat.vue';
import own from '../views/own.vue';
import blacklist from '../views/blacklist.vue';
import settings from '../views/settings.vue';

const routes = [
  {
    path: '/home',
    name: 'Home',
    component: Home, // 替换为你的主页组件
  },
  {
    path: '/',
    name: 'loginin',
    component: loginin, // 替换为你的登录/注册组件
  },
  {
    path: '/chat',
    name: 'chat',
    component: chat, // 替换为你的主页组件
  },
  {
    path: '/own',
    name: 'own',
    component: own, // 替换为你的登录/注册组件
  },
  {
    path: '/own/blacklist',
    name: 'blacklist',
    component: blacklist, // 替换为你的登录/注册组件
  },
  {
    path: '/own/settings',
    name: 'settings',
    component: settings, // 替换为你的登录/注册组件
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

 export default router;