import Vue from 'vue'
import VueRouter from 'vue-router'

import login from "../views/Login.vue"
import register from "../views/Register.vue"
import home from "../views/Home.vue"
import book from "../views/Home/Book.vue"
import borrow from "../views/Home/Borrow.vue"
import reader from "../views/Home/Reader.vue"

Vue.use(VueRouter)

const routes = [
  
  {
    path: '/',
    name: 'login',
    component:login,
    // children:[{
    //   path: 'home',
    //   name: 'home',
    //   component:home,
    // }]
  },
  {
    path: '/register',
    name: 'register',
    component:register,
  },
  {
    path: '/home',
    name: 'home',
    component:home,
    children:
    [{
      path:'/',
      name:'book',
      component:book,
    },
    {
      path:'reader',
      name:'reader',
      component:reader,
    },
    {
      path:'borrow',
      name:'borrow',
      component:borrow,
    },]
  }
]

const router = new VueRouter({
  routes
})

export default router
