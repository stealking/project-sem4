<template>
  <div class="main">
    <el-row :gutter="20">
      <el-col :span="6">
        <div class="avatar-content">
          <el-upload class="avatar-uploader" action="https://jsonplaceholder.typicode.com/posts/" :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
            <img v-if="ruleForm.imageUrl" :src="ruleForm.imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </div>
      </el-col>
      <el-col :span="18">
        <h4>Account details</h4>
        <hr>
        <div class="detail-content">
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" class="demo-ruleForm">
            <el-form-item label="Email" prop="email">
              <el-input v-model="ruleForm.email" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="Username" prop="username">
              <el-input v-model="ruleForm.username" :disabled="true"></el-input>
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
                  <el-input type="age" v-model="ruleForm.phone" auto-complete="off"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="Birthdate" prop="dob">
                  <el-date-picker type="date" placeholder="Pick a date" v-model="ruleForm.dob" style="width: 100%;"></el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>
            <el-form-item>
              <el-button type="success" @click="submitForm('ruleForm')">Change details</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>

import service from '../../Admin/services';

export default {
  data() {
    const checkPhone = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('Please input phone number'));
      }
      setTimeout(() => {
        if (!Number.isInteger(parseInt(value, 0))) {
          callback(new Error('Please input digits'));
        } else if (value.length < 10) {
          callback(new Error('Phone number must be greater than 10'));
        } else {
          callback();
        }
      }, 1000);
      return false;
    };
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please input the password if you\'re changing your details'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
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
          { min: 5, message: 'Length should be min 5', trigger: 'blur' },
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
        phone: [
          { validator: checkPhone, trigger: 'blur' },
        ],
      },
    };
  },
  created() {
    service.getUserInfo().then((response) => {
      this.ruleForm = { ...this.ruleForm, ...response }
      if (response.avatar !== '' && response.avatar !== null && response.avatar !== undefined) {
        this.ruleForm.imageUrl = `${this.pathImage}${response.avatar}`;
      } else {
        this.ruleForm.imageUrl = '';
      }
    });
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
          delete this.ruleForm.imageUrl;
          const profileUser = this.ruleForm;
          service.updateUser(profileUser, this.fileImage).then((response) => {
            if (response.status === 200) {
              this.$message.success('Update successed!');
            } else {
              this.$message.error('Update failed!');
            }
          });
        } else {
          return this.$message.error('Some field is not valid!');
        }
        return true;
      });
    },
    // resetForm(formName) {
    //   this.$refs[formName].resetFields();
    // },
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




