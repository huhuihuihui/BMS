<template>
  <div>
    <nav class="navbar" style="display: flex;align-items: center;">
  <ul class="navbar__menu" style="">
    <li class="navbar__item">
      <span class="navbar__link"><i data-feather="arrow-down-circle"></i></span>        
    </li>
    <li class="navbar__item">
      <router-link to="/home" class="navbar__link"><i data-feather="book-open"></i><span>书籍信息</span></router-link>
    </li>
    <li class="navbar__item">
      <router-link to="/home/reader" class="navbar__link"><i data-feather="users"></i><span>学生信息</span></router-link>        
    </li>
    <li class="navbar__item">
      <router-link to="/home/borrow" class="navbar__link"><i data-feather="book"></i><span>借阅记录</span></router-link>        
    </li>
    <li class="navbar__item">
      <span class="navbar__link"><i data-feather="arrow-up-circle"></i></span>        
    </li>
    <!-- <li class="navbar__item">
      <a href="#" class="navbar__link"><i data-feather="folder"></i><span>Projects</span></a>        
    </li>
    <li class="navbar__item">
      <a href="#" class="navbar__link"><i data-feather="archive"></i><span>Resources</span></a>        
    </li>
    <li class="navbar__item">
      <a href="#" class="navbar__link"><i data-feather="help-circle"></i><span>Help</span></a>        
    </li>
    <li class="navbar__item">
      <a href="#" class="navbar__link"><i data-feather="settings"></i><span>Settings</span></a>        
    </li> -->
  </ul>
</nav>
  </div>
</template>

<script>
import { layout } from '@/script/layout'; 
export default {
    name:'layout',
    mounted(){
      layout()
    }
};
</script>

<style lang="scss">
@import '@/style/layout.scss';
</style>