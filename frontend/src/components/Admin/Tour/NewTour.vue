<template>
  <div class="right-side-content">
    <div class="content-header">
      <h4>Edit</h4>
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/admin' }">Homepage</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/admin/tour' }">Tour</el-breadcrumb-item>
        <el-breadcrumb-item>New</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="panel" style="margin-top: 20px; background-color: white; padding: 10px">
      <el-row :gutter="20">
        <el-col :span="24">
          <div class="detail-content">
            <el-form :model="tourDetailsForm" :rules="rules" ref="tourDetailsForm" class="demo-tourDetailsForm" label-width="180px">
              <el-form-item label="Department Point" prop="departmentPoint">
                <el-input v-model="tourDetailsForm.departmentPoint"></el-input>
              </el-form-item>
              <el-form-item label="Destination" prop="destination">
                <el-input v-model="tourDetailsForm.destination"></el-input>
              </el-form-item>
              <el-form-item label="Journey" prop="journey">
                <el-input v-model="tourDetailsForm.journey"></el-input>
              </el-form-item>
              <el-form-item label="Tour Type" prop="tourTypeId">
                <el-select class="full-width" v-model="tourDetailsForm.tourTypeId" placeholder="Select" style="width: 100%" >
                  <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="Introduction" prop="introduction">
                <el-input type="textarea" :autosize="{ minRows: 4, maxRows: 10}" v-model="tourDetailsForm.introduction"></el-input>
              </el-form-item>
              <el-form-item label="Detail" prop="detail">
                <el-input type="textarea" :autosize="{ minRows: 4, maxRows: 10}" v-model="tourDetailsForm.detail"></el-input>
              </el-form-item>
              <el-form-item label="Total Time" prop="totalTime">
                <el-input v-model="tourDetailsForm.totalTime"></el-input>
              </el-form-item>
              <el-form-item label="Transport" prop="transport">
                <el-input v-model="tourDetailsForm.transport"></el-input>
              </el-form-item>
              <el-form-item label="Image" prop="image">
                <el-col>
                  <div>
                    <el-upload class="avatar-uploader" action="https://jsonplaceholder.typicode.com/posts/" :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
                      <img v-if="tourDetailsForm.image" :src="tourDetailsForm.image" class="avatar">
                      <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                  </div>
                </el-col>
              </el-form-item>
              <el-form-item>
                <el-button type="success" @click="submitForm('tourDetailsForm')">Submit</el-button>
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
      activeName: 'first',
      moreDetails: [],
      total: 0,
      currentPage: 1,
      pageSize: 10,
      sort: 'asc',
      column: 'id',
      tourDetailsForm: {
        id: '',
        tourTypeId: '',
        departmentPoint: '',
        destination: '',
        journey: '',
        introduction: '',
        detail: '',
        totalTime: '',
        transport: '',
        image: '',
        tourType: '',
      },
      options: [{
        value: 1,
        label: 'Khách Lẻ'
      }, {
        value: 2,
        label: 'Khách theo đoàn'
      }],
      rules: {
        departmentPoint: [
          { required: true, message: 'Please input department point', trigger: 'blur' },
        ],
        destination: [
          { required: true, message: 'Please input destination', trigger: 'blur' },
        ],
        journey: [
          { required: true, message: 'Please input journey', trigger: 'blur' },
        ],
        introduction: [
          { required: true, message: 'Please input introduction', trigger: 'blur' },
        ],
        detail: [
          { required: true, message: 'Please input detail', trigger: 'blur' },
        ],
        totalTime: [
          { required: true, message: 'Please input total time', trigger: 'blur' },
        ],
        image: [
          { required: true, message: 'Please choose image', trigger: 'blur' },
        ],
        transport: [
          { required: true, message: 'Please input transport', trigger: 'blur' },
        ],
      },
    };
  },
  methods: {
    handleAvatarSuccess(res, file) {
      this.tourDetailsForm.image = URL.createObjectURL(file.raw);
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
          let tour = this.tourDetailsForm;
          tour.tourType = {id: this.tourDetailsForm.tourTypeId};
          tour.image = '';
          delete tour.tourTypeId;
          service.createTour(tour, this.fileImage).then((response) => {
            if (response.status === 200) {
              this.$message.success('Create successed!');
              router.push({ name: 'Tour' });
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
    back() {
      router.push({ name: 'Tour' });
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

.full-width {
  width: 100%;
}
</style>




