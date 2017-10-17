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
                    <div :span="24">
                      <img v-if="voucherDetailsForm.image" :src="getImage()" class="avatar">
                      <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </div>
                    <div slot="tip" class="el-upload__tip">{{ fileName }}</div>
                    <el-button class="mt-4" size="small" type="primary" @click.native="onFocus">Click to upload</el-button>
                    <input hidden type="file" :accept="accept" :multiple="false" :disabled="disabled" ref="fileInput" @change="onFileChange" />
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
    return {
      pathImage: 'http://localhost:8080/upload/',
      fileName: '',
      fileImage: '',
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
  mounted() {
    moment.locale('vi');
    this.voucherDetailsForm.id = this.$route.params.id;
    service.getVoucherById(this.voucherDetailsForm.id).then((response) => {
      this.voucherDetailsForm = { ...this.voucherDetailsForm, ...response }
      this.voucherDetailsForm.discountPercent = parseFloat(this.voucherDetailsForm.discountPercent) || '';
      this.getImage();
    });
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
    getImage() {
      this.voucherDetailsForm.image = this.voucherDetailsForm.image || '';
      return this.voucherDetailsForm.image == '' ? '' : `http://localhost:8080/upload/${this.voucherDetailsForm.image}?x=${Math.random() * 2}`;
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const voucher = this.voucherDetailsForm;
          voucher.expiredOn = moment(voucher.expiredOn).format();
          service.updateVoucher(voucher, this.fileImage).then((response) => {
            if (response.status === 200) {
              this.voucherDetailsForm = { ...this.voucherDetailsForm, ...response.data }
              this.voucherDetailsForm.discountPercent = parseFloat(this.voucherDetailsForm.discountPercent) || '';
              this.getImage();
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




