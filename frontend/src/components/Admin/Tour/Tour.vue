<template>
  <div class="right-side-content">
    <div class="content-header">
      <h4>Tour</h4>
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ name: 'Admin' }">Homepage</el-breadcrumb-item>
        <el-breadcrumb-item>Tour</el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div class="panel" style="margin-top: 20px; background-color: white; padding: 10px">

      <div style="margin: 15px 0;">
        <el-row>
          <el-col :xs="24" :sm="18" :md="16" :lg="16">
            <el-row :gutter="20">
              <el-col :span="6">
                <el-select v-model="select" placeholder="Tour type" @change="searchTourType()">
                  <el-option label="All tour type" value="0"></el-option>
                  <el-option label="Tour khách lẻ" value="1"></el-option>
                  <el-option label="Tour theo đoàn" value="2"></el-option>
                </el-select>
              </el-col>
              <el-col :span="9" style="padding-right: 0">
                <el-input placeholder="Departure" v-model="depPointSearch" @keyup.enter.native="search()" />
              </el-col>
              <el-col :span="9" style="padding-right: 0">
                <el-input placeholder="Journey" v-model="journeySearch" @keyup.enter.native="search()">
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
        <el-table-column prop="departure.name" label="Departure" sortable="custom"/>
        <el-table-column prop="journey.name" label="Journey" sortable="custom"/>
        <el-table-column prop="totalTime" label="Total time" sortable="custom"/>
        <el-table-column prop="tourType.name" label="Tour type" sortable="custom"/>
        <el-table-column prop="transport.name" label="Transport" sortable="custom"/>
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
      datas: [],
      total: 0,
      currentPage: 1,
      pageSize: 10,
      type: 'tours',
      sort: 'asc',
      column: 'id',
      field: '',
      depPointSearch: '',
      journeySearch: '',
      select: '0',
    };
  },
  mounted() {
    moment.locale('vi');
    service.getAllTour().then((response) => {
      this.refresh(response);
    });
  },
  methods: {
    handleSizeChange(val) {
      this.pageSize = val;
      service.findTour(this.depPointSearch, this.journeySearch, this.select, this.currentPage, this.pageSize, this.sort, this.column).then((response) => {
        this.refresh(response);
      });
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      service.findTour(this.depPointSearch, this.journeySearch, this.select, this.currentPage, this.pageSize, this.sort, this.column).then((response) => {
        this.refresh(response);
      });
    },
    refreshDatatable() {
      this.datas = [];
      service.getAllTour().then((response) => {
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
        const list = response.content;
        list.forEach((element) => {
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
      console.log(val);
      if (val.order === 'ascending') {
        this.sort = 'asc';
      } else {
        this.sort = 'desc';
      }
      this.column = val.prop;
      service.findTour(this.depPointSearch, this.journeySearch, this.select, this.currentPage, this.pageSize, this.sort, this.column).then((response) => {
        this.refresh(response);
      });
    },
    handleNew() {
      router.push({ name: 'NewTour' });
    },
    handleEdit(index, row) {
      const id = row.id;
      router.push({ path: `/admin/edittour/${id}` });
    },
    handleDelete(index, row) {
      this.$confirm('This will permanently delete the file. Continue?', 'Warning', {
        confirmButtonText: 'OK',
        cancelButtonText: 'Cancel',
        type: 'warning',
      }).then(() => {
        service.deleteTour(row.id).then((response) => {
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
      service.findTour(this.depPointSearch, this.journeySearch, this.select, this.currentPage, this.pageSize, this.sort, this.column).then((response) => {
        if (response === false) {
          this.$message({
            type: 'error',
            message: 'Search failed!',
          });
        } else {
          this.refresh(response)
        }
      });
    },
    searchTourType() {
      service.findTour(this.depPointSearch, this.journeySearch, this.select, this.currentPage, this.pageSize, this.sort, this.column).then((response) => {
        if (response === false) {
          this.$message({
            type: 'error',
            message: 'Search failed!',
          });
        } else {
          this.refresh(response)
        }
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




