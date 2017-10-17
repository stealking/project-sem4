<template>
  <div class="right-side-content">
    <div class="content-header">
      <h4>Edit</h4>
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/admin' }">Homepage</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/admin/tour' }">Tour</el-breadcrumb-item>
        <el-breadcrumb-item>Edit</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="panel" style="margin-top: 20px; background-color: white; padding: 10px">

      <el-row :gutter="20">
        <el-col :span="24">
          <div class="detail-content">
            <el-form :model="tourDetailsForm" :rules="rules" ref="tourDetailsForm" class="demo-tourDetailsForm" label-width="180px">
              <el-form-item label="Department Date" prop="departmentDate">
                <el-date-picker type="date" placeholder="Pick a date" v-model="tourDetailsForm.departmentDate" style="width: 100%;"></el-date-picker>
              </el-form-item>
              <el-form-item label="Details" prop="details">
                <el-input type="textarea" v-model="tourDetailsForm.details"></el-input>
              </el-form-item>
              <el-form-item label="ExCost" prop="exCost">
                <el-input v-model.number="tourDetailsForm.exCost" />
              </el-form-item>
              <el-form-item label="Price Adult" prop="priceAdult">
                <el-input v-model.number="tourDetailsForm.priceAdult" />
              </el-form-item>
              <el-form-item label="Price Children" prop="priceChildren">
                <el-input v-model.number="tourDetailsForm.priceChildren" />
              </el-form-item>
              <el-form-item label="Price Baby" prop="priceBaby">
                <el-input v-model.number="tourDetailsForm.priceBaby" />
              </el-form-item>
              <el-form-item label="Voucher" prop="voucherId">
                <el-select clearable v-model="tourDetailsForm.voucherId" placeholder="Select" style="width: 100%">
                  <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
                </el-select>
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
    const checkInteger = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('Please input value'));
      }
      setTimeout(() => {
        if (!Number.isInteger(value)) {
          callback(new Error('Please input digits'));
        } else if (value < 0) {
          callback(new Error('Value must be greater than 0'));
        } else {
          callback();
        }
      }, 1000);
      return false;
    };
    return {
      tourId: '',
      options: [],
      tourDetailsForm: {
        id: '',
        departmentDate: '',
        details: '',
        exCost: 0,
        priceAdult: 0,
        priceBaby: 0,
        priceChildren: 0,
        voucherContent: '',
        voucherId: '',
        updatedBy: '',
        updatedOn: '',
      },
      rules: {
        details: [
          { required: true, message: 'Please input details', trigger: 'blur' },
        ],
        exCost: [
          { validator: checkInteger, trigger: 'blur' },
        ],
        priceAdult: [
          { validator: checkInteger, trigger: 'blur' },
        ],
        priceBaby: [
          { validator: checkInteger, trigger: 'blur' },
        ],
        priceChildren: [
          { validator: checkInteger, trigger: 'blur' },
        ],
      },
    };
  },
  created() {
    this.tourDetailsForm.id = this.$route.params.id;
    this.tourId = this.$route.params.tourId;
    service.getTourDetailsById(this.tourDetailsForm.id).then((response) => {
      this.tourDetailsForm = { ...this.tourDetailsForm, ...response }
      if (response.voucher !== null) {
        this.tourDetailsForm.voucherContent = response.voucher.content;
        this.tourDetailsForm.voucherId = response.voucher.id;
      }
    });
    service.findAllVouchers().then((response) => {
      let list = response;
      list.forEach((element) => {
        this.options.push({ value: element.id, label: element.content });
      }, this);
    });
  },
  methods: {
    handleClick(tab, event) {
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let tourDetails = { ...this.tourDetailsForm};
          tourDetails.voucherId == '' ? null : tourDetails.voucher = { id: tourDetails.voucherId };
          delete tourDetails.voucherContent;
          delete tourDetails.voucherId;
          tourDetails.departmentDate = moment(tourDetails.departmentDate).format();
          service.updateTourDetails(tourDetails, this.tourId).then((response) => {
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

    back() {
      const id = this.tourId
      router.push({ name: 'EditTour', params: { id } });
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




