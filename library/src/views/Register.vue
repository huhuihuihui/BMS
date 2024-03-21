<template>
  <div class="poster">
    <div class="wenzi">
      <h1 class="a">
        <span style="--i: 1">欢</span>
        <span style="--i: 2">迎</span>
        <span style="--i: 3">注</span>
        <span style="--i: 4">册</span>
        <span style="--i: 5">图</span>
        <span style="--i: 6">书</span>
        <span style="--i: 7">管</span>
        <span style="--i: 8">理</span>
        <span style="--i: 9">系</span>
        <span style="--i: 10">统</span>
      </h1>
    </div>
    <div class="center register"><div class="login"><div class="image"></div>
    <el-form
      :model="ruleForm"
      :rules="rules"
      ref="ruleForm"
      label-width="0px"
      label-position="left"
      class="login-container"
    >
      <h3 class="login_title">
        注册<hr>
      </h3>
      <el-form-item label="" prop="userName">
        <el-input 
        type="text"
        v-model="ruleForm.userName"
        autocomplete="off"
        placeholder="用户名"
        prefix-icon="el-icon-user-solid"
        ></el-input>
      </el-form-item>
      <el-form-item label="" prop="userMail">
        <el-input 
        type="text"
        v-model="ruleForm.userMail"
        autocomplete="off"
        placeholder="邮箱"
        prefix-icon="el-icon-user-solid"
        ></el-input>
      </el-form-item>
      <el-form-item label="" prop="password">
        <el-input
          type="password"
          v-model="ruleForm.password"
          autocomplete="off"
          placeholder="密码"
          show-password
          prefix-icon="el-icon-lock"
        ></el-input>
      </el-form-item>
      <el-form-item  style="text-align: center;">
        <button class="button" style="width:100px;margin-right: 20px;" @click="toLogin">返回</button>
        <button class="button" style="width:100px;" @click="submitForm('ruleForm')"
          >注册</button
        >
      </el-form-item>
    </el-form>
    </div>
    </div>
  </div>
</template>

<script>
export default {
  name:'Register',
  data() {
      return {
        ruleForm: {
          userId:"",
          userName:"",
          userMail:"",
          password: "",
        },
        rules: {
          userName:[
            { required:true,message:"请输入用户名",trigger:'blur' },
            ],
          password: [
            { required:true,message:"请输入密码", trigger: 'blur' }
          ],
          userMail: [
            { required:true,message:"请输入邮箱", trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$store.dispatch("addUser",this.ruleForm);
          this.ruleForm={};
        } else {
          console.log("error submit!!");
          return false;
        }
      });
      },
      toLogin(){
        this.$router.push({path:'/'})
      }
    }
  }

</script>

<style>
.register{
  margin:0px auto;
}


</style>