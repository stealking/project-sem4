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

      <el-tabs v-model="activeName">
        <el-tab-pane label="Tour Detail" name="first">
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
                    <el-input v-model.number="tourDetailsForm.totalTime"></el-input>
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
                        <div :span="24">
                          <img v-if="tourDetailsForm.image" :src="getImage()" class="avatar">
                          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </div>
                        <div slot="tip" class="el-upload__tip">{{ fileName }}</div>
                        <el-button class="mt-4" size="small" type="primary" @click.native="onFocus">Click to upload</el-button>
                        <input hidden type="file" :accept="accept" :multiple="false" :disabled="disabled" ref="fileInput" @change="onFileChange" />
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

        </el-tab-pane>
        <el-tab-pane label="More Detail" name="second">
          <div class="panel" style="margin-top: 20px; background-color: white; padding: 10px">
            <p>
              <el-button type="primary" size="small" @click="handleNew()">Create New</el-button>
            </p>
            <br/>
            <el-table :data="moreDetails" border style="width: 100%" @sort-change="handleSort">
              <el-table-column prop="details" label="Details" sortable="custom" />
              <el-table-column prop="departmentDate" label="Department Date" sortable="custom" />
              <el-table-column prop="priceAdult" label="PriceAdult" sortable="custom" />
              <el-table-column prop="priceChildren" label="Price Children" sortable="custom" />
              <el-table-column prop="priceBaby" label="Price Baby" sortable="custom" />
              <el-table-column prop="exCost" label="ExCost" sortable="custom" />
              <el-table-column prop="voucher.content" label="Voucher" />
              <el-table-column label="Action">
                <template scope="scope">
                  <el-row :gutter="10">
                    <el-col :xs="24" :sm="24" :md="24" class="btnEditTourDetail">
                      <el-button size="small" @click="handleEdit(scope.$index, scope.row)">
                        <i class="el-icon-edit"></i> Edit</el-button>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" class="btnDeleteTourDetail">
                      <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">
                        <i class="el-icon-delete"></i> Delete</el-button>
                    </el-col>
                  </el-row>
                </template>
              </el-table-column>
            </el-table>
            <br/>
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="this.currentPage" :page-sizes="[10, 20, 30, 50]" :page-size="this.pageSize" layout="total, sizes, prev, pager, next" :total="this.total">
            </el-pagination>
          </div>
        </el-tab-pane>
      </el-tabs>
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
    var check = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('Please choose a value!'));
      }
      callback();
    }
    var checkTotalTime = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('Please input the total time'));
      }
      setTimeout(() => {
        if (!Number.isInteger(value)) {
          callback(new Error('Please input digits'));
        } else {
          if (value < 0) {
            callback(new Error('Total time must be greater than 0'));
          } else {
            callback();
          }
        }
      }, 1000);
    };
    return {
      fileName: '',
      fileImage: '',
      pathImage: 'http://localhost:8080/upload/',
      optionsTransport: [],
      optionsJourney: [],
      optionsDeparture: [],
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
          { validator: checkTotalTime, trigger: 'blur' }
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
    this.tourDetailsForm.id = this.$route.params.id;
    service.getTourById(this.tourDetailsForm.id).then((response) => {
      this.tourDetailsForm = { ...this.tourDetailsForm, ...response }
      this.tourDetailsForm.tourTypeId = response.tourType.id;
      this.tourDetailsForm.transportId = response.transport.id;
      this.tourDetailsForm.journeyId = response.journey.id;
      this.tourDetailsForm.departureId = response.departure.id;
      this.getImage();
    });

    service.getTourDetailsByTourId(this.tourDetailsForm.id).then((response) => {
      let list = response.content;
      list.forEach((element) => {
        let dateString = moment(element.departmentDate).format('L');
        if (dateString === 'Invalid date') {
          dateString = '';
        }
        element.departmentDate = dateString;
        this.moreDetails.push(element);
      }, this);
    });

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
      this.tourDetailsForm.image = this.tourDetailsForm.image || '';
      return this.tourDetailsForm.image == '' ? '' : `http://localhost:8080/upload/${this.tourDetailsForm.image}?x=${Math.random() * 2}`;
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let tour = { ...this.tourDetailsForm };
          tour.tourType = { id: this.tourDetailsForm.tourTypeId };
          tour.journey = { id: this.tourDetailsForm.journeyId };
          tour.departure = { id: this.tourDetailsForm.departureId };
          tour.transport = { id: this.tourDetailsForm.transportId };
          delete tour.tourTypeId;
          delete tour.departureId;
          delete tour.transportId;
          delete tour.journeyId;
          service.updateTour(tour, this.fileImage).then((response) => {
            this.tourDetailsForm = { ...this.tourDetailsForm, ...response.data }
            this.tourDetailsForm.tourTypeId = response.data.tourType.id;
            this.tourDetailsForm.transportId = response.data.transport.id;
            this.tourDetailsForm.journeyId = response.data.journey.id;
            this.tourDetailsForm.departureId = response.data.departure.id;
            this.fileName = '';
            this.fileImage = '';
            this.getImage();
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
    handleEdit(index, row) {
      const id = row.id;
      const tourId = this.tourDetailsForm.id
      router.push({ name: 'EditTourDetail', params: { id, tourId } });
    },
    handleDelete(index, row) {
      this.$confirm('This will permanently delete the file. Continue?', 'Warning', {
        confirmButtonText: 'OK',
        cancelButtonText: 'Cancel',
        type: 'warning',
      }).then(() => {
        service.deleteTourDetails(row.id).then((response) => {
          if (response.status === 200) {
            let tourId = this.tourDetailsForm.id;
            this.refreshDatatable(tourId);
            this.$message({
              type: 'success',
              message: 'Delete completed',
            });
          }
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: 'Delete canceled',
        });
      });
    },
    handleSizeChange(val) {
      this.pageSize = val;
      service.changePageTourDetails(this.currentPage, this.tourDetailsForm.id, this.pageSize, this.sort, this.column).then((response) => {
        this.datas = [];
        this.total = response.totalElements;;
        const listUser = response.content;
        listUser.forEach((element) => {
          let dateString = moment(element.dob).format('L');
          if (dateString === 'Invalid date') {
            dateString = ''
          }
          element.dob = dateString;
          this.datas.push(element);
        }, this);
      });
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      service.changePageTourDetails(this.currentPage, this.tourDetailsForm.id, this.pageSize, this.sort, this.column).then((response) => {
        this.datas = [];
        this.total = response.totalElements;;
        const listUser = response.content;
        listUser.forEach((element) => {
          let dateString = moment(element.dob).format('L');
          if (dateString === 'Invalid date') {
            dateString = ''
          }
          element.dob = dateString;
          this.datas.push(element);
        }, this);
      });
    },
    handleNew() {
      let tour_id = this.tourDetailsForm.id;
      router.push({ name: 'NewTourDetail', params: { tour_id } });
    },
    handleSort(val) {
      this.sort = val.order === 'ascending' ? 'asc' : 'desc';
      this.column = val.prop;
      service.changePageTourDetails(this.currentPage, this.tourDetailsForm.id, this.pageSize, this.sort, this.column).then((response) => {
        this.moreDetails = [];
        this.total = response.totalElements;;
        const listUser = response.content;
        listUser.forEach((element) => {
          let dateString = moment(element.dob).format('L');
          if (dateString === 'Invalid date') {
            dateString = ''
          }
          element.dob = dateString;
          this.moreDetails.push(element);
        }, this);
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
      router.push({ name: 'Tour' });
    },
  },
};

</script>
<style scope>
@media screen and (min-width: 1367px) {
  .btnEditTourDetail {
    width: 50%;
  }

  .btnDeleteTourDetail {
    width: 50%;
  }
}

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




