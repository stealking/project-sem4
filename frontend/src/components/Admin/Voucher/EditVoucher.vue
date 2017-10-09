<template>
  <div class="right-side-content">
    <div class="content-header">
      <h4>Edit</h4>
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/admin' }">Homepage</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/admin/voucher' }">Voucher</el-breadcrumb-item>
        <el-breadcrumb-item>Edit</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="panel" style="margin-top: 20px; background-color: white; padding: 10px">
      <el-row :gutter="20">
        <el-col :span="24">
          <div class="detail-content">
            <el-form :model="voucherDetailsForm" :rules="rules" ref="voucherDetailsForm" class="demo-voucherDetailsForm" label-width="180px">
              <el-form-item label="Content" prop="content">
                <el-input v-model="voucherDetailsForm.content"></el-input>
              </el-form-item>
              <el-form-item label="Discount(%)" prop="discountPercent">
                <el-input v-model.number="voucherDetailsForm.discountPercent"></el-input>
              </el-form-item>
              <el-form-item label="Introduction" prop="introduction">
                <el-input v-model="voucherDetailsForm.introduction"></el-input>
              </el-form-item>
              <el-form-item label="Expired On" prop="expiredOn">
                <el-date-picker type="date" placeholder="Pick a date" v-model="voucherDetailsForm.expiredOn" style="width: 100%;"></el-date-picker>
              </el-form-item>
              <el-form-item label="Image" prop="image">
                <el-col>
                  <div>
                    <el-upload class="avatar-uploader" action="https://jsonplaceholder.typicode.com/posts/" :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
                      <img v-if="voucherDetailsForm.image" :src="voucherDetailsForm.image" class="avatar">
                      <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                  </div>
                </el-col>
              </el-form-item>
              <el-form-item>
                <el-button type="success" @click="submitForm('voucherDetailsForm')">Submit</el-button>
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

import router from '../../../router';
import service from '../services';
import * as moment from 'moment';

export default {
  data() {
    return {
      pathImage: 'http://localhost:8080/upload/',
      fileImage: File,
      voucherDetailsForm: {
        id: '',
        image: '',
        discountPercent: '',
        introduction: '',
        content: '',
        expiredOn: '',
        updatedBy: '',
        updatedOn: '',
      },
      rules: {
        content: [
          { required: true, message: 'Please input content', trigger: 'blur' },
          { min: 4, message: 'Length should be min 4', trigger: 'blur' },
        ],
        introduction: [
          { required: true, message: 'Please input introduction', trigger: 'blur' },
          { min: 4, message: 'Length should be min 4', trigger: 'blur' },
        ],
        discountPercent: [
          { type: 'number', message: 'age must be a number' }
        ],
      },
    };
  },
  mounted() {
    moment.locale('vi');
    this.voucherDetailsForm.id = this.$route.params.id;
    service.getVoucherById(this.voucherDetailsForm.id).then((response) => {
      this.voucherDetailsForm = { ...this.voucherDetailsForm, ...response }
      this.voucherDetailsForm.discountPercent = parseFloat(this.voucherDetailsForm.discountPercent) || '';
      this.voucherDetailsForm.image = `${this.pathImage}${response.image}` || '';
      if (response.image) {
        this.voucherDetailsForm.image = `${this.pathImage}${response.image}`;
      } else {
        this.voucherDetailsForm.image = '';
      }
    });
  },
  methods: {
    handleAvatarSuccess(res, file) {
      this.voucherDetailsForm.image = URL.createObjectURL(file.raw);
      this.fileImage = file.raw;
    },
    beforeAvatarUpload(file) {
      // DOM updated
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('The picture must be JPG format!');
      }
      if (!isLt2M) {
        this.$message.error('The picture size can not exceed 2MB!');
      }
      return isJPG && isLt2M;
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const voucher = this.voucherDetailsForm;
          voucher.expiredOn = moment(voucher.expiredOn).format();
          service.updateVoucher(voucher, this.fileImage).then((response) => {
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
    refreshDatatable(tourId) {
      this.moreDetails = [];
      service.getTourDetailsByTourId(tourId).then((response) => {
        this.total = response.totalElements;;
        let list = response.content;
        list.forEach((element) => {
          let dateString = moment(element.departmentDate).format('L');
          if (dateString === 'Invalid date') {
            dateString = ''
          }
          element.departmentDate = dateString;
          this.moreDetails.push(element);
        }, this);
      });
    },
    back() {
      router.push({ name: 'Voucher' });
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
  width: 150px;
  height: 150px;
  line-height: 150px;
  text-align: center;
}

.avatar {
  width: 150px;
  height: 150px;
  display: block;
}
</style>




