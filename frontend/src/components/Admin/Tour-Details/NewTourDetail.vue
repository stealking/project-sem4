<template>
  <div class="right-side-content">
    <div class="content-header">
      <h4>New</h4>
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
              <el-form-item label="Department Date" prop="departmentDate">
                <el-date-picker type="date" placeholder="Pick a date" v-model="tourDetailsForm.departmentDate" style="width: 100%;"></el-date-picker>
              </el-form-item>
              <el-form-item label="Details" prop="details">
                <el-input type="textarea" v-model="tourDetailsForm.details"></el-input>
              </el-form-item>
              <el-form-item label="ExCost" prop="exCost">
                <el-input-number class="full-width" v-model="tourDetailsForm.exCost" :min="1" :max="1000000000"></el-input-number>
              </el-form-item>
              <el-form-item label="Price Adult" prop="priceAdult">
                <el-input-number class="full-width" v-model="tourDetailsForm.priceAdult" :min="1" :max="1000000000"></el-input-number>
              </el-form-item>
              <el-form-item label="Price Children" prop="priceChildren">
                <el-input-number class="full-width" v-model="tourDetailsForm.priceChildren" :min="1" :max="1000000000"></el-input-number>
              </el-form-item>
              <el-form-item label="Price Baby" prop="priceBaby">
                <el-input-number class="full-width" v-model="tourDetailsForm.priceBaby" :min="1" :max="1000000000"></el-input-number>
              </el-form-item>
              <el-form-item label="Voucher" prop="voucherContent">
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
      tour_id: '',
      options: [],
      tourDetailsForm: {
        id: '',
        tour: '',
        departmentDate: '',
        details: '',
        exCost: 0,
        priceAdult: 0,
        priceBaby: 0,
        priceChildren: 0,
        voucherContent: '',
        voucherId: '',
      },
      rules: {
        departmentDate: [
          { type: 'date', required: true, message: 'Please input department date', trigger: 'blur' },
        ],
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
    this.tour_id = this.$route.params.tour_id;
    service.findAllVouchers().then((response) => {
      let list = response;
      list.forEach((element) => {
        this.options.push({ value: element.id, label: element.content });
      }, this);
    });
    console.log(this.options);
  },
  methods: {
    handleClick(tab, event) {
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let tourDetails = this.tourDetailsForm;
          this.tourDetailsForm.voucherId == '' ? null : tourDetails.voucher = { id: tourDetails.voucherId };
          this.tour_id == '' ? null : tourDetails.tour = { id: this.tour_id };
          delete tourDetails.voucherContent;
          delete tourDetails.voucherId;
          tourDetails.departmentDate = moment(tourDetails.departmentDate).format();
          let tourId= parseInt(tourDetails.tour.id);
          console.log(tourDetails)
          service.createTourDetails(tourDetails, tourId).then((response) => {
            if (response.status === 200) {
              this.$message.success('Update successed!');
              this.back();
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
      const id = this.tour_id;
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

.full-width {
  width: 100%;
}
</style>




