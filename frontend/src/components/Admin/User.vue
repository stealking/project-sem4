<template>
  <div class="right-side-content">
    <div class="content-header">
      <h4>User</h4>
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ name: 'Admin' }">Homepage</el-breadcrumb-item>
        <el-breadcrumb-item>User</el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div class="panel" style="margin-top: 20px; background-color: white; padding: 10px">
      <div style="margin-bottom: 15px"> 
        <el-row>
          <el-col :xs="24" :sm="18" :md="16" :lg="16">
            <el-input placeholder="Please input" v-model="inputSearch" @keyup.enter.native="search()">
              <el-select v-model="select" slot="prepend" placeholder="Select">
                <el-option label="Email" value="1"></el-option>
                <el-option label="Username" value="2"></el-option>
                <el-option label="Address" value="3"></el-option>
                <el-option label="Phone" value="4"></el-option>
              </el-select>
              <el-button slot="append" icon="search" @click="search()"></el-button>
            </el-input>
          </el-col>
          <el-col :xs="24" :sm="6" :md="8" :lg="8">
            <div class="pull-right">
              <el-button type="primary" size="small" @click="handleNew()">Create New</el-button>
            </div>
          </el-col>
        </el-row>
      </div>

      <el-table :data="datas" border style="width: 100%" @sort-change="handleSort">
        <el-table-column prop="email" label="Email" width="180" sortable="custom" />
        <el-table-column prop="username" label="Username" width="180" sortable="custom" />
        <el-table-column prop="address" label="Address" width="180" sortable="custom" />
        <el-table-column prop="phone" label="Phone" width="180" sortable="custom" />
        <el-table-column prop="dob" label="Birthday" width="180" sortable="custom" />
        <el-table-column label="Action">
          <template scope="scope">
            <el-row :gutter="10">
              <el-col :xs="24" :sm="24" :md="24" :lg="10">
                <el-button size="small" @click="handleEdit(scope.$index, scope.row)">
                  <i class="el-icon-edit"></i> Edit</el-button>
              </el-col>
              <el-col :xs="24" :sm="24" :md="24" :lg="14">
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
import router from '../../router';
import service from '../Admin/services';
import * as moment from 'moment';

export default {
  data() {
    return {
      datas: [],
      total: 0,
      currentPage: 1,
      pageSize: 10,
      type: 'users',
      sort: 'asc',
      column: 'id',
      field: '',
      inputSearch: '',
      select: '',
    };
  },
  mounted() {
    moment.locale('vi');  
    service.findAllUser().then((response) => {
      this.total = response.totalElements;
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
  methods: {
    handleSizeChange(val) {
      this.pageSize = val;
      service.changePage(this.currentPage, this.type, this.pageSize, this.sort, this.column, this.field, this.inputSearch).then((response) => {
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
      service.changePage(this.currentPage, this.type, this.pageSize, this.sort, this.column, this.field, this.inputSearch).then((response) => {
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
    refreshDatatable() {
      this.datas = [];
      service.findAllUser().then((response) => {
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
    refresh(response) {
      if (response !== null || response !== undefined || response !== '') {
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
      }
    },

    handleSort(val) {
      if (val.order === 'ascending') {
        this.sort = 'asc';
      } else {
        this.sort = 'desc';
      }
      this.column = val.prop;
      service.changePage(this.currentPage, this.type, this.pageSize, this.sort, this.column, this.field, this.inputSearch).then((response) => {
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
      router.push({ name: 'NewUser' });
    },
    handleEdit(index, row) {
      const id = row.id;
      router.push({ name: 'Edit', params: { id } });
    },
    handleDelete(index, row) {
      this.$confirm('This will permanently delete the file. Continue?', 'Warning', {
        confirmButtonText: 'OK',
        cancelButtonText: 'Cancel',
        type: 'warning',
      }).then(() => {
        service.deleteUser(row.id).then((response) => {
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
      let value = this.select;
      switch (value) {
        case "1":
          service.findByEmail(this.inputSearch).then((response) => {
            if (response === false) {
              this.$message({
                type: 'info',
                message: 'Search failed!',
              });
            } else {
              this.type = 'users/findByEmail';
              this.field = 'email';
              this.refresh(response);
            }
          })
          break;
        case "2":
          service.findByUsername(this.inputSearch).then((response) => {
            if (response === false) {
              this.$message({
                type: 'info',
                message: 'Search failed!',
              });
            } else {
              this.type = 'users/findByUsername';
              this.field = 'username';
              this.refresh(response);
            }
          })
          break;
        case "3":
          service.findByAddress(this.inputSearch).then((response) => {
            if (response === false) {
              this.$message({
                type: 'info',
                message: 'Search failed!',
              });
            } else {
              this.type = 'users/findByAddress';
              this.field = 'address';
              this.refresh(response);
            }
          })
          break;
        case "4":
        service.findByPhone(this.inputSearch).then((response) => {
            if (response === false) {
              this.$message({
                type: 'info',
                message: 'Search failed!',
              });
            } else {
              this.type = 'users/findByPhone';
              this.field = 'phone';
              this.refresh(response);
            }
          })
          break;
        default:
          this.type = 'users';
          this.field = '';
          this.refreshDatatable();
          break;
      };
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

.pull-left {
  float: left;
}

.pull-right {
  float: right;
}

.el-select .el-input {
  width: 10vw;
}

</style>




