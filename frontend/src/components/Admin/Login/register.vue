<template>
  <el-row :gutter="20" class="login-main" style="margin-left: 0; margin-right: 0;">
    <el-col :span="12" :offset="6">
      <div class="panel" style="margin-top: 20vh; background-color: white; padding: 10px">
        <h2 class="text-center">Register</h2>
        <el-row :gutter="20">
          <el-col :span="22">
            <div class="detail-content">
              <el-form :model="ruleForm" label-width="100px" :rules="rules" ref="ruleForm" class="demo-ruleForm">
                <el-form-item label="Email" prop="email">
                  <el-input v-model="ruleForm.email" @keyup.enter.native="submitForm('ruleForm')"></el-input>
                </el-form-item>
                <el-form-item label="Username" prop="username">
                  <el-input v-model="ruleForm.username" @keyup.enter.native="submitForm('ruleForm')"></el-input>
                </el-form-item>
                <el-form-item label="Password" prop="pass">
                  <el-input type="password" v-model="ruleForm.pass" auto-complete="off" @keyup.enter.native="submitForm('ruleForm')"></el-input>
                </el-form-item>
                <el-form-item class="text-center">
                  <el-button type="success" @click="submitForm('ruleForm')">Submit</el-button>
                </el-form-item>
              </el-form>
            </div>
          </el-col>
        </el-row>
      </div>
    </el-col>
  </el-row>
</template>
<script>

import service from '../../Admin/services';
import router from '../../../router';
import auth from '../../../auth';

export default {
  data() {
    var checkEmail = (rule, value, callback) => {
      setTimeout(() => {
        service.checkEmail(value).then((response) => {
          if (response) {
            return callback(new Error('This email was exist!'));
          } else {
            callback();
          }
        });
      }, 1000);
    };
    var checkUsername = (rule, value, callback) => {
      setTimeout(() => {
        service.checkUsername(value).then((response) => {
          console.log(response);
          if (response) {
            return callback(new Error('This username was exist!'));
          } else {
            callback();
          }
        });
      }, 1000);
    };
    return {
      pathImage: 'http://localhost:8080/upload/',
      fileImage: File,
      ruleForm: {
        id: '',
        email: '',
        username: '',
        pass: '',
      },
      rules: {
        email: [
          { required: true, message: 'Please input email address', trigger: 'blur' },
          { type: 'email', message: 'Please input correct email address', trigger: 'blur,change' },
          { validator: checkEmail, trigger: 'blur' }
        ],
        username: [
          { required: true, message: 'Please input username', trigger: 'blur' },
          { min: 3, message: 'Length should be min 3', trigger: 'blur' },
          { validator: checkUsername, trigger: 'blur' }
        ],
        pass: [
          { required: true, message: 'Please input password', trigger: 'blur' },
          { min: 6, message: 'Length should be min 6', trigger: 'blur' },
        ],
        // phone: [
        //   { validator: checkPhone, trigger: 'blur' },
        // ],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
            const user = {
              email: this.ruleForm.email,
              username: this.ruleForm.username,
              password: this.ruleForm.pass,
              firstName: this.ruleForm.firstName,
              lastName: this.ruleForm.lastName,
              address: this.ruleForm.address,
              phone: this.ruleForm.phone,
              dob: this.ruleForm.dob,
              authorities: [{ id: 3 }],
            };
            console.log(JSON.stringify(user))
            service.createUser(user).then((response) => {
              if (response.status === 200) {
                this.$message.success('Create successed! Please Login!');
                auth.logout();
                router.push({ name: 'Login' });
              } else {
                this.$message.error('Create failed!');
              }
            });
        } else {
          return this.$message.error('Some field is not valid!');
        }
        return true;
      });
    },
  },
};

</script>
<style scope>
.right-side-content {
  padding: 0 10px 0 10px;
}

.content-header {
  background-color: #FAFAFA;
  padding: 0 10px 10px 10px;
  /* box-shadow: 0 2px 18px #E5E5E5; */
  border-radius: 4px;
}

.panel {
  border: 1px solid #dcdcdc;
  box-shadow: 0 0 14px rgba(0, 0, 0, 0.2);
  border-radius: 4px;
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

.text-center {
  text-align: center;
}
</style>




