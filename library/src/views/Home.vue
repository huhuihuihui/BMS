<template>
  <div class="home">
    <!-- <el-header class="header">图书管理系统</el-header> -->
    <el-container
      style="
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        border: 1px solid #eee;
      "
    >
      <el-aside width="130px">
        <layout />
      </el-aside>
      <el-container
        style="margin: 1rem 1rem 1rem 0rem; padding: 30px 30px 30px 70px"
        ><el-container style="box-shadow: -10px -10px 10px #909083">
          <el-header
            style="
              display: flex;
              justify-content: left;
              text-align: right;
              font-size: 16px;
              padding: 0px;
              height: 50px;
            "
          >
            <body>
              <div class="a">
                <span style="--i: 1">图</span>
                <span style="--i: 2">书</span>
                <span style="--i: 3">管</span>
                <span style="--i: 4">理</span>
                <span style="--i: 5">系</span>
                <span style="--i: 6">统</span>
              </div>
            </body>
            <div style="width: 20%; padding: 15px 50px">
              <span style="color: #a2a8ac; padding-right: 30px">{{
                $store.state.user.userName
              }}</span>
              <el-dropdown>
                <i class="el-icon-setting"></i>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item @click.native="setVisible = true">
                    修改用户信息
                  </el-dropdown-item>
                  <el-dropdown-item @click.native="deleteUser">
                    注销用户
                  </el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </div>
          </el-header>
          <el-dialog
            title="修改用户信息"
            :visible.sync="setVisible"
            width="30%"
            center
          >
            <el-form label-width="0px" label-position="left">
              <el-form-item label="">
                <el-input
                  type="text"
                  autocomplete="off"
                  placeholder="用户名"
                  v-model="self.userName"
                  prefix-icon="el-icon-user-solid"
                ></el-input>
              </el-form-item>
              <el-form-item label="">
                <el-input
                  type="text"
                  autocomplete="off"
                  placeholder="密码"
                  show-password
                  v-model="self.password"
                  prefix-icon="el-icon-lock"
                ></el-input>
              </el-form-item>
              <el-form-item style="text-align: center">
                <el-button @click="setVisible = false">取消</el-button>
                <el-button type="primary" style="border: none" @click="setSelf"
                  >提交</el-button
                >
              </el-form-item>
            </el-form>
          </el-dialog>
          <el-main>
            <router-view></router-view>
          </el-main> </el-container
      ></el-container>
    </el-container>
  </div>
</template>

<script>
import Layout from "./Home/Layout.vue";
export default {
  name: "Home",
  components: { Layout },
  data() {
    return {
      input3: "",
      select: "",
      setVisible: false,
      self: {
        userId: this.$store.state.user.userId,
      },
    };
  },
  methods: {
    setSelf() {
      console.log(this.self);
      this.$store.dispatch("updateUser", this.self);
    },
    deleteUser() {
      this.$confirm("是否确认注销", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        this.$store.dispatch("deleteUser", this.self).then((res)=>{}).catch(err=>{});
      }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消'
          });
        });
    },
  },
};
</script>

<style scoped>
.el-header {
  height: 50px;
  padding: 20px;
  background-color: #f8fcfc;
  color: #333;
}

.el-menu-item {
  text-align: center;
}
.header {
  height: 10vh;
  padding: 10px;
  text-align: center;
  color: white;
  background-color: #2a3541;
  font: bold 2em "Courier New", Courier, monospace;
}

.el-aside {
  color: #333;
  overflow: hidden;
}

.el-main {
  background-color: #bedfea;
  overflow: hidden;
}

body {
  width: 80%;
  padding-left: 20px;
  background-color: transparent;
  display: flex;
  justify-content: left;
}
.a {
  position: relative;
  top: 0px;
  font: 1000 30px "宋体";
  color: #19446e;
  cursor: pointer;
}
.a span {
  transition: 0.5s;
  transition-delay: calc(var(--i) * 0.1s);
}
.a:hover span {
  color: #4c90b5;
  text-shadow: 0 0 10px #fff, 0 0 20px #95d7f4, 0 0 30px #fff, 0 0 50px #fff,
    0 0 80px #fff;
}
</style>