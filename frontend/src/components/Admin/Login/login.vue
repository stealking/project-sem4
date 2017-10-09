<template>
  <div class="center-container login-main">
    <div class="main-content-agile">
      <div class="sub-main-w3">
        <div class="wthree-pro">
          <h2>Login Quick</h2>
        </div>
        <div class="error-danger">{{ error }}</div>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" class="">
          <div class="pom-agile">
            <el-form-item prop="username">
              <el-input v-model="ruleForm.username" placeholder="Username" @keyup.enter.native="submitForm('ruleForm')"></el-input>
            </el-form-item>
            <span class="icon1">
              <i class="fa fa-user" aria-hidden="true"></i>
            </span>
          </div>
          <div class="pom-agile">
            <el-form-item prop="pass">
              <el-input type="password" v-model="ruleForm.pass" class="pass" placeholder="Password" @keyup.enter.native="submitForm('ruleForm')"></el-input>
            </el-form-item>
            <span class="icon2">
              <i class="fa fa-unlock" aria-hidden="true"></i>
            </span>
          </div>
          <div class="sub-w3l">
            <h6>
              <a href="#">Forgot Password?</a>
            </h6>
            <div class="right-w3l" style="text-align: center">
              <el-form-item>
                <el-button @click="submitForm('ruleForm')">Login</el-button>
              </el-form-item>
            </div>
          </div>
        </el-form>
      </div>
    </div>

  </div>
</template>

<script>
// import router from '../../../router';
import auth from '../../../auth';
export default {
  data() {
    return {
      credentials: {
        username: '',
        password: '',
      },
      error: '',
      ruleForm: {
        username: '',
        pass: '',
      },
      rules: {
        username: [
          { required: true, message: 'Please input username', trigger: 'blur' },
        ],
        pass: [
          { required: true, trigger: 'blur' },
          { min: 4, message: 'Length should be min 5', trigger: 'blur' },
        ],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const credentials = {
            username: this.ruleForm.username,
            password: this.ruleForm.pass,
          };
          auth.login(credentials, 'Home', '').then(() => {
            this.error = 'Wrong username or password!';
          });
        }
      });
    },
  },
};
</script>
<style scoped>
@import '../../../assets/css/style.css';
.el-button {
  color: white;
  background: #1ab394;
  border: none;
  padding: .7em 0;
  outline: none;
  width: 44%;
  line-height: 1.6;
  font-size: 1em;
  cursor: pointer;
  letter-spacing: 1px;
  font-weight: 400;
  font-family: 'Open Sans', sans-serif;
  transition: 0.5s all;
  -webkit-transition: 0.5s all;
  -o-transition: 0.5s all;
  -moz-transition: 0.5s all;
  -ms-transition: 0.5s all;
  border-radius: 30px;
  text-align: center;
}

.el-button:hover {
  background: #fff;
  color: black;
  transition: 0.5s all;
  -webkit-transition: 0.5s all;
  -o-transition: 0.5s all;
  -moz-transition: 0.5s all;
  -ms-transition: 0.5s all;
}

.row-bg {
  padding-top: 20vh;
}

.error-class {
  text-align: center;
  color: red;
}


.bg-info {
  background-color: transparent !important;
}

.sub-w3l h6 a {
  font-size: 1em;
}

.login-main {
  height: 100vh;
  top: 0px;
  right: 0px;
  margin-top: 0px;
  position: absolute;
  width: 100%;
  margin-left: 0px;
  background: url(../../../../static/1.jpg)no-repeat center top;
  background-size: cover;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  -ms-background-size: cover;
  background-attachment: fixed;
  background-position: center;
  font-family: 'Open Sans', sans-serif;
}
</style>
