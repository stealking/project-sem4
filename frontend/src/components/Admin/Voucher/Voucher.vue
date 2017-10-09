<template>
  <div class="right-side-content">
    <div class="content-header">
      <h4>Voucher</h4>
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ name: 'Admin' }">Homepage</el-breadcrumb-item>
        <el-breadcrumb-item>Voucher</el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div class="panel" style="margin-top: 20px; background-color: white; padding: 10px">
      <div style="margin: 15px 0;">
        <el-row>
          <el-col :xs="24" :sm="18" :md="16" :lg="16">
            <el-row :gutter="20">
              <el-col :span="9" style="padding-right: 0">
                <el-input placeholder="Search" v-model="inputSearch" @keyup.enter.native="search()">
                  <el-button slot="append" icon="search" @click="search()"></el-button>
                </el-input>
              </el-col>
            </el-row>

          </el-col>
          <el-col :xs="24" :sm="6" :md="8" :lg="8">
            <div class="pull-right">
              <el-button type="primary" size="small" @click="handleNew()">Create New</el-button>
            </div>
          </el-col>
        </el-row>
      </div>

      <el-table :data="datas" border style="width: 100%" @sort-change="handleSort">
        <el-table-column prop="content" label="Content" sortable="custom" />
        <el-table-column prop="discountPercent" label="Discount (%)" sortable="custom" />
        <el-table-column prop="introduction" label="Introduction" sortable="custom" />
        <el-table-column prop="expiredOn" label="Expired On" />
        <el-table-column prop="image" label="Image" />
        <el-table-column label="Action">
          <template scope="scope">
            <el-row :gutter="10">
              <el-col :xs="24" :sm="24" :md="24" :lg="12">
                <el-button size="small" @click="handleEdit(scope.$index, scope.row)">
                  <i class="el-icon-edit"></i> Edit</el-button>
              </el-col>
              <el-col :xs="24" :sm="24" :md="24" :lg="12">
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
  </div>
</template>
<script>

// import axios from 'axios';
import router from '../../../router';
import service from '../services';
import * as moment from 'moment';

export default {
  data() {
    return {
      datas: [{
        id: '',
        image: '',
        discountPercent: '',
        introduction: '',
        content: '',
        expiredOn: '',
        updatedBy: '',
        updatedOn: '',
      }],
      total: 0,
      currentPage: 1,
      pageSize: 10,
      type: 'vouchers',
      sort: 'asc',
      column: 'id',
      field: '',
      inputSearch: '',
    };
  },
  mounted() {
    moment.locale('vi');
    service.getAllVoucher().then((response) => {
      this.refresh(response);
    });
  },
  methods: {
    handleSizeChange(val) {
      this.pageSize = val;
      service.changePage(this.currentPage, this.type, this.pageSize, this.sort, this.column).then((response) => {
        this.refresh(response);
      });
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      service.changePage(this.currentPage, this.type, this.pageSize, this.sort, this.column).then((response) => {
        this.refresh(response);
      });
    },
    refreshDatatable() {
      this.datas = [];
      service.getAllVoucher().then((response) => {
        this.refresh(response);
      });
    },
    refresh(response) {
      if (response !== null || response !== undefined || response !== '') {
        this.datas = [];
        this.total = response.totalElements;;
        const list = response.content;
        list.forEach((element) => {
          let updateOn = moment(element.updateOn).format('L');
          let expiredOn = moment(element.expiredOn).format('L');
          if (updateOn === 'Invalid date') {
            updateOn = '';
          }
          if (expiredOn === 'Invalid date') {
            expiredOn = '';
          }
          element.updateOn = updateOn;
          element.expiredOn = expiredOn;
          this.datas.push(element);
        }, this);
      }
    },
    handleSort(val) {
      if (val.order === 'ascending') {
        this.sort = 'asc';
      } else {
        this.sort = 'desc';
      }
      this.column = val.prop;
      service.changePage(this.currentPage, this.type, this.pageSize, this.sort, this.column).then((response) => {
        this.refresh(response);
      });
    },
    handleNew() {
      router.push({ name: 'NewVoucher' });
    },
    handleEdit(index, row) {
      const id = row.id;
      router.push({ path: `/admin/edit-voucher/${id}` });
    },
    handleDelete(index, row) {
      this.$confirm('This will permanently delete the file. Continue?', 'Warning', {
        confirmButtonText: 'OK',
        cancelButtonText: 'Cancel',
        type: 'warning',
      }).then(() => {
        service.deleteVoucher(row.id).then((response) => {
          if (response.status === 200) {
            this.refreshDatatable();
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
    search() {
      service.searchVoucher(this.inputSearch).then((response) => {
        this.refresh(response);
      });
    },
  },
};

</script>
<style scope>
.right-side-content {
  padding: 0 10px 0 10px;
  height: 100%;
  overflow-y: auto;
}

.content-header {
  background-color: #FAFAFA;
  padding: 20px;
  margin-top: 10px;
  /* box-shadow: 0 2px 18px #E5E5E5; */
  border-radius: 4px;
}

.content-header>h1 {
  margin-bottom: 10px;
}

.panel {
  border: 1px solid #dcdcdc;
  box-shadow: 0 0 14px rgba(0, 0, 0, 0.2);
  border-radius: 4px;
}

.sc-table .action-list {
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

.no-padding-left {
  padding-left: 0;
}

.no-padding-right {
  padding-right: 0;
}
</style>
