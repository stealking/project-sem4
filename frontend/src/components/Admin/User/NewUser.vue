<template>
  <div class="right-side-content">
    <div class="content-header">
      <h4>New User</h4>
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/admin' }">Homepage</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/admin/user' }">User</el-breadcrumb-item>
        <el-breadcrumb-item>New User</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="panel" style="margin-top: 20px; background-color: white; padding: 10px">
      <el-row :gutter="20">
        <el-col :span="6">
          <div class="avatar-content">
            <el-upload class="avatar-uploader" action="https://jsonplaceholder.typicode.com/posts/" :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
              <img v-if="ruleForm.imageUrl" :src="`${this.pathImage}`+ruleForm.imageUrl" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </div>
        </el-col>
        <el-col :span="18">
          <div class="detail-content">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" class="demo-ruleForm">
              <el-form-item label="Email" prop="email">
                <el-input v-model="ruleForm.email"></el-input>
              </el-form-item>
              <el-form-item label="Username" prop="username">
                <el-input v-model="ruleForm.username"></el-input>
              </el-form-item>
              <el-form-item label="Password" prop="pass">
                <el-input type="password" v-model="ruleForm.pass" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="Confirm" prop="checkPass">
                <el-input type="password" v-model="ruleForm.checkPass" auto-complete="off"></el-input>
              </el-form-item>
              <el-row :gutter="20">
                <el-col :span="12">
                  <el-form-item label="First name" prop="firstName">
                    <el-input v-model="ruleForm.firstName"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="Last name" prop="lastName">
                    <el-input v-model="ruleForm.lastName"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-form-item label="Address" prop="address">
                <el-input v-model="ruleForm.address"></el-input>
              </el-form-item>
              <el-row :gutter="20">
                <el-col :span="12">
                  <el-form-item label="Phone" prop="phone">
                    <el-input v-model="ruleForm.phone" auto-complete="off"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="Birthdate" prop="dob">
                    <el-date-picker type="date" placeholder="Pick a date" v-model="ruleForm.dob" style="width: 100%;"></el-date-picker>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-form-item>
                <el-button type="success" @click="submitForm('ruleForm')">Submit</el-button>
                <el-button type="default" @click="resetForm('ruleForm')">Reset</el-button>
                <el-button type="default" @click="back()">Back</el-button>
              </el-form-item>
            </el-form>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
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
        checkPass: [
          { required: true, validator: validatePass2, trigger: 'blur' },
          { min: 6, message: 'Length should be min 6', trigger: 'blur' },
        ],
        firstName: [
          { min: 3, message: 'Length should be min 3', trigger: 'blur' },
        ],
        lastName: [
          { min: 3, message: 'Length should be min 3', trigger: 'blur' },
        ],
        address: [
          { min: 3, message: 'Length should be min 3', trigger: 'blur' },
        ],
        // phone: [
        //   { validator: checkPhone, trigger: 'blur' },
        // ],
      },
    };
  },
  methods: {
    handleAvatarSuccess(res, file) {
      this.ruleForm.imageUrl = URL.createObjectURL(file.raw);
      this.fileImage = file.raw;
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('Avatar picture must be JPG format!');
      }
      if (!isLt2M) {
        this.$message.error('Avatar picture size can not exceed 2MB!');
      }
      return isJPG && isLt2M;
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          service.checkEmail(this.ruleForm.email).then((res) => {
            if (res === true) {
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

.sc-table .action-list .el-button {
  display: inline-block;
  line-height: 1;
  white-space: nowrap;
  cursor: pointer;
  background: #fff;
  border: 1px solid #c4c4c4;
  color: #1f2d3d;
  margin: 0;
  padding: 5px 10px;
  border-radius: 4px;
}

.avatar-content {
  padding: 0 10px 10px 10px;
  margin-top: 10px;
  text-align: center;
  /* box-shadow: 0 2px 18px #E5E5E5; */
}

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #20a0ff;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>




