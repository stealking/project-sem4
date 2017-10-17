<template>
  <div class="main">
    <el-row>
      <el-col :span="18" :offset="3">
        <h4>Change password</h4>
        <hr>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" class="demo-ruleForm">
          <el-form-item label="Current password" prop="currentPass">
            <el-input type="password" v-model="ruleForm.currentPass" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="New password" prop="newPass">
            <el-input type="password" v-model="ruleForm.newPass" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="Confirm" prop="checkPass">
            <el-input type="password" v-model="ruleForm.checkPass" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="success" @click="submitForm('ruleForm')">Change password</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>
<script>

import service from '../../Admin/services';

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
        callback(new Error('Please input the new password again'));
      } else if (value !== this.ruleForm.newPass) {
        callback(new Error('Two inputs don\'t match!'));
      } else {
        callback();
      }
    };
    return {
      user: '',
      ruleForm: {
        currentPass: '',
        newPass: '',
        checkPass: '',
      },
      rules: {
        currentPass: [
          { required: true, message: 'Please input the password', trigger: 'blur' },
          { validator: validatePass, trigger: 'blur' },
        ],
        newPass: [
          { required: true, message: 'Please input the password', trigger: 'blur' },
          { validator: validatePass, trigger: 'blur' },
        ],
        checkPass: [
          { required: true, message: 'Please input the new password again', trigger: 'blur' },
          { validator: validatePass2, trigger: 'blur' },
        ],
      },
    };
  },
  created() {
    service.getUserInfo().then((response) => {
      this.user = response;
    });
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const passwordConfirm = {
            oldPassword: this.ruleForm.currentPass,
            newPassword: this.ruleForm.newPass,
          }
          service.changePassword(passwordConfirm).then((response) => {
            if (response.status === 200) {
              this.$message.success('Change password successed!');
              localStorage.removeItem('access_token');
              localStorage.setItem('access_token', response.data.token);
            } else {
              this.$message.error("Current password was not matched!");
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
.main {
  margin-top: 10px;
  background-color: #FAFAFA;
  padding: 0 10px 0 10px;
  height: 100%;
  overflow-y: auto;
  border-radius: 4px;
}

.main h2 {
  margin-top: 10px;
}

hr {
  margin-top: 20px;
  margin-bottom: 20px;
  border: 0;
  border-top: 1px solid #eee;
}

.help-block {
  display: block;
  margin-top: 5px;
  color: #737373;
}
</style>




