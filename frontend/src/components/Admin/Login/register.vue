<template>
  <el-row>
    <el-col :span="8" :offset="8">
      <div class="right-side-content">
        <div class="panel" style="margin-top: 20px; background-color: white; padding: 10px">
          <h4 class="text-center">Quick Register</h4>
          <el-row :gutter="20">
            <el-col :span="24">
              <div class="detail-content text-center">
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" class="demo-ruleForm">
                  <el-form-item  prop="email">
                    <el-input v-model="ruleForm.email" placeholder="Email"></el-input>
                  </el-form-item>
                  <el-form-item prop="username">
                    <el-input v-model="ruleForm.username" placeholder="Username"></el-input>
                  </el-form-item>
                  <el-form-item prop="pass">
                    <el-input type="password" v-model="ruleForm.pass" auto-complete="off" placeholder="Password"></el-input>
                  </el-form-item>
                  <el-form-item>
                    <el-button type="success" @click="submitForm('ruleForm')">Submit</el-button>
                  </el-form-item>
                </el-form>
              </div>
            </el-col>
          </el-row>
        </div>
      </div>

    </el-col>
  </el-row>
</template>
<script>

import service from '../../Admin/services';
import router from '../../../router';

export default {
  data() {
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please input the password'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    const validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please input the password again'));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('Two inputs don\'t match!'));
      } else {
        callback();
      }
    };
    return {
      pathImage: 'http://localhost:8080/upload/',
      fileImage: File,
      ruleForm: {
        id: '',
        email: '',
        username: '',
        pass: '',
        checkPass: '',
        firstName: '',
        lastName: '',
        address: '',
        phone: '',
        dob: '',
        imageUrl: '',
      },
      rules: {
        email: [
          { required: true, message: 'Please input email address', trigger: 'blur' },
          { type: 'email', message: 'Please input correct email address', trigger: 'blur,change' },
        ],
        username: [
          { required: true, message: 'Please input username', trigger: 'blur' },
          { min: 3, message: 'Length should be min 3', trigger: 'blur' },
        ],
        pass: [
          { required: true, validator: validatePass, trigger: 'blur' },
          { min: 6, message: 'Length should be min 6', trigger: 'blur' },
        ],
      },
    };
  },
  methods: {
    
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          service.checkEmail(this.ruleForm.email).then((res) => {
            if (res === false) {
              this.$message.warning('This email is exist.');
            } else {
              const user = {
                email: this.ruleForm.email,
                username: this.ruleForm.username,
                password: this.ruleForm.pass,
                firstName: this.ruleForm.firstName,
                lastName: this.ruleForm.lastName,
                address: this.ruleForm.address,
                phone: this.ruleForm.phone,
                dob: this.ruleForm.dob,
              };
              service.createUser(user).then((response) => {
                if (response.status === 200) {
                  this.$message.success('Create successed!');
                  router.push({ name: 'User' });
                } else {
                  this.$message.error('Create failed!');
                }
              });
            }
          });
        } else {
          return this.$message.error('Some field is not valid!');
        }
        return true;
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    back() {
      router.push({ name: 'User' });
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

.text-center {
  text-align: center;
}

</style>




