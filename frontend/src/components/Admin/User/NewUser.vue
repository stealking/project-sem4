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
          <div>
            <div :span="24" class="text-xs-center">
              <img v-if="ruleForm.image" :src="getImage()" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              <div slot="tip" class="el-upload__tip">{{ fileName }}</div>
              <el-button class="mt-4" size="small" type="primary" @click.native="onFocus">Click to upload</el-button>
              <input hidden type="file" :accept="accept" :multiple="false" :disabled="disabled" ref="fileInput" @change="onFileChange" />
            </div>
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
  props: {
    value: {
      type: [Array, String]
    },
    accept: {
      type: String,
      default: "*"
    },
    label: {
      type: String,
      default: "Xin chọn ảnh..."
    },
    required: {
      type: Boolean,
      default: false
    },
    disabled: {
      type: Boolean,
      default: false
    },
    multiple: {
      type: Boolean, // not yet possible because of data
      default: false
    }
  },
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
      fileName: '',
      fileImage: '',
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
          { min: 6, message: 'Length should be min 4', trigger: 'blur' },
        ],
        checkPass: [
          { required: true, validator: validatePass2, trigger: 'blur' },
          { min: 6, message: 'Length should be min 4', trigger: 'blur' },
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
  watch: {
    value(v) {
      this.fileName = v;
    }
  },
  computed: {
    avatarSize() {
      return `150px`;
    }
  },
  methods: {
    getFormData(files) {
      const data = new FormData();
      [...files].forEach(file => {
        data.append('data', file, file.name); // currently only one file at a time
      });
      return data;
    },
    onFocus() {
      if (!this.disabled) {
        this.$refs.fileInput.click();
      }
    },
    onFileChange($event) {
      const files = $event.target.files || $event.dataTransfer.files;
      const form = this.getFormData(files);
      if (files) {
        if (files.length > 0) {
          this.fileName = [...files].map(file => file.name).join(', ');
        } else {
          this.fileName = null;
        }
      } else {
        this.fileName = $event.target.value.split('\\').pop();
      }
      this.$emit('input', this.fileName);
      this.$emit('formData', form);
      this.fileImage = files[0];
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          service.checkEmail(this.ruleForm.email).then((res) => {
            if (res) {
              this.$message.warning('This email is exist.');
            } else {
              service.checkUsername(this.ruleForm.username).then((res) => {
                if (res) {
                  this.$message.warning('This username is exist.');
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
                  service.createUser(user, this.fileImage).then((response) => {
                    if (response.status === 200) {
                      this.$message.success('Create successed!');
                      router.push({ name: 'User' });
                    } else {
                      this.$message.error('Create failed!');
                    }
                  });
                }
              })
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

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 150px;
  height: 150px;
  line-height: 150px;
  text-align: center;
  border: 1px solid
}

.avatar {
  width: 150px;
  height: 150px;
  display: block;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  display: -webkit-inline-box;
  display: -ms-inline-flexbox;
  display: inline-flex;
  -webkit-box-pack: center;
  -ms-flex-pack: center;
  justify-content: center;
  text-align: center;
}
</style>




