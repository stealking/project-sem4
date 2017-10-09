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
              <el-form-item label="Departure" prop="departureId">
                <el-select v-model="tourDetailsForm.departureId" placeholder="Select" style="width: 100%">
                  <el-option v-for="item in optionsDeparture" :key="item.value" :label="item.label" :value="item.value"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="Journey" prop="journeyId">
                <el-select v-model="tourDetailsForm.journeyId" placeholder="Select" style="width: 100%">
                  <el-option v-for="item in optionsJourney" :key="item.value" :label="item.label" :value="item.value"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="Tour Type" prop="tourTypeId">
                <el-select v-model="tourDetailsForm.tourTypeId" placeholder="Select" style="width: 100%">
                  <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="Transport" prop="transportId">
                <el-select v-model="tourDetailsForm.transportId" placeholder="Select" style="width: 100%">
                  <el-option v-for="item in optionsTransport" :key="item.value" :label="item.label" :value="item.value"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="Total Time" prop="totalTime">
                <el-input v-model="tourDetailsForm.totalTime"></el-input>
              </el-form-item>
              <el-form-item label="Introduction" prop="introduction">
                <el-input type="textarea" :autosize="{ minRows: 4, maxRows: 10}" v-model="tourDetailsForm.introduction"></el-input>
              </el-form-item>
              <el-form-item label="Detail" prop="detail">
                <el-input type="textarea" :autosize="{ minRows: 4, maxRows: 10}" v-model="tourDetailsForm.detail"></el-input>
              </el-form-item>
              <el-form-item label="Summary" prop="summary">
                <el-input type="textarea" :autosize="{ minRows: 4, maxRows: 10}" v-model="tourDetailsForm.summary"></el-input>
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
    var check = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('Please choose a value!'));
      }
      callback();
    }
    return {
      pathImage: 'http://localhost:8080/upload/',
      fileImage: File,
      activeName: 'first',
      moreDetails: [],
      optionsTransport: [],
      optionsJourney: [],
      optionsDeparture: [],
      tourDetailsForm: {
        id: '',
        tourTypeId: '',
        transport: '',
        transportId: '',
        departure: '',
        departureId: '',
        journey: '',
        journeyId: '',
        introduction: '',
        detail: '',
        totalTime: '',
        image: '',
      },
      options: [{
        value: 1,
        label: 'Khách Lẻ'
      }, {
        value: 2,
        label: 'Khách theo đoàn'
      }],
      rules: {
        tourTypeId: [
          { validator: check, trigger: 'change' }
        ],
        transportId: [
          { validator: check, trigger: 'change' }
        ],
        departureId: [
          { validator: check, trigger: 'change' }
        ],
        journeyId: [
          { validator: check, trigger: 'change' }
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
      },
    };
  },
  mounted() {
    moment.locale('vi');
    service.findAllTransport().then((response) => {
      let list = response;
      list.forEach((element) => {
        this.optionsTransport.push({ value: element.id, label: element.name });
      }, this);
    });

    service.findAllJourney().then((response) => {
      let list = response;
      list.forEach((element) => {
        this.optionsJourney.push({ value: element.id, label: element.name });
      }, this);
    });

    service.findAllDeparture().then((response) => {
      let list = response;
      list.forEach((element) => {
        this.optionsDeparture.push({ value: element.id, label: element.name });
      }, this);
    });
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
          let tour = { ...this.tourDetailsForm };
          tour.tourType = { id: this.tourDetailsForm.tourTypeId };
          tour.journey = { id: this.tourDetailsForm.journeyId };
          tour.departure = { id: this.tourDetailsForm.departureId };
          tour.transport = { id: this.tourDetailsForm.transportId };
          tour.image = '';
          delete tour.tourTypeId;
          delete tour.departureId;
          delete tour.transportId;
          delete tour.journeyId;
          console.log(tour);
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




