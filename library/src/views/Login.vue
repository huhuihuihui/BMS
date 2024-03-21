<template>
  <div class="poster">
    <div class="wenzi">
      <h1 class="a">
        <span style="--i: 1">欢</span>
        <span style="--i: 2">迎</span>
        <span style="--i: 3">登</span>
        <span style="--i: 4">录</span>
        <span style="--i: 5">图</span>
        <span style="--i: 6">书</span>
        <span style="--i: 7">管</span>
        <span style="--i: 8">理</span>
        <span style="--i: 9">系</span>
        <span style="--i: 10">统</span>
      </h1>
    </div>
    <div class="center"><div class="login"><div class="image"></div> <el-form
      size="medium"
      label-width="0px"
      label-position="left"
      class="login-container"
      center
    >
      <h3 class="login_title" style="">
        登录
        <hr />
      </h3>

      <el-form-item label="" prop="userName">
        <el-input
          type="text"
          v-model="user.userName"
          autocomplete="off"
          placeholder="用户名"
          prefix-icon="el-icon-user-solid"
          class=""
        ></el-input>
      </el-form-item>
      <el-form-item label="" prop="password">
        <el-input
          type="password"
          v-model="user.password"
          autocomplete="off"
          placeholder="密码"
          show-password
          prefix-icon="el-icon-lock"
        ></el-input>
      </el-form-item>
      <el-form-item style="text-align: center">
        <button class="button"  @click="login">登录</button><br />
        <el-link
          type="primary"
          :underline="false"
          style="font-size: small"
          @click="toRegister"
          >还没有账号？立即注册</el-link
        >
      </el-form-item>
    </el-form></div></div>
    
   
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      user: {
        userId: "",
        userName: "",
        password: "",
        userMail: "",
      },
    };
  },
  methods: {
    login() {
      this.$store.dispatch("getUser", this.user);
    },
    toRegister() {
      this.$router.push({ path: "/register" });
    },
  },
};
</script>

<style lang="scss">
.poster {
  background-position: center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
  margin: 0;
  padding: 0;
background-color: #fff;
  // background-image: url(../assets/bookL.png);
  // background-repeat: no-repeat;
  // background-clip: border-box;
  // background-position: center center;
  // background-size: 100% 100%;
}
.center{
  display:flex;
  justify-content: center;
}
.login{
  display: flex;
  justify-content: center;
  width:50%;
  padding:0;
  margin: 80px auto;
  box-shadow: 0 0 25px #9fa5aa;
}
.image{

  background-clip: border-box;
  width: 60%;
  background-image: url(../assets/bookL.png);
  background-repeat: no-repeat;
  background-clip: border-box;
  background-position: center center;
  background-size: 100% 100%;
}
.login-container {
  background-clip: border-box;
  width: 40%;
  padding: 35px 35px 15px 35px;
  background: rgba(255,255,255,0.9);
}

.login_title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #14181b;
}

.button{
  background-color: rgba(255, 255, 255, 0.6);border:none;
  border-radius: 20px;
  width:40%;
  height:30px;
  box-shadow: 0 5px #b1b0b0;
  color:cadetblue
}
.button:hover {
  border:1px solid #66afe9;
  background-color: #fff;
  transition-duration: 0.4s;
  cursor: pointer;
}
.button:active{
  background-color: #fff;
  box-shadow: 0 1px #666;
  transform:translateY(4px);
}
.el-input__inner {
  height: 35px !important;
  color: #101f2b !important;
  background-color: rgba(255,255,255,0.7) !important;
}
.el-input__inner:focus {
  background-color: rgba(255,255,255,0.8)  !important;
}

.wenzi {
  background-color: transparent;
  display: flex;
  justify-content: center;
}
.a {
  position: relative;
  top: 10px;
  font: 900 50px "";
  color: #404c68;
  cursor: pointer;
}
.a span {
  transition: 0.5s;
  transition-delay: calc(var(--i) * 0.1s);
}
.a:hover span {
  color: #29a3ee;
  text-shadow: 0 0 10px #f2f4f5, 0 0 20px #fff, 0 0 30px #fff, 0 0 50px #fff,
    0 0 80px #fff;
}
</style>