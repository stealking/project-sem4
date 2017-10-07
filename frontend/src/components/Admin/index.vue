<template>
  <div class="box">
    <div class="row content">
      <app-nav></app-nav>
      <side-bar>
        <router-view></router-view>
      </side-bar>
    </div>
  </div>
</template>
<script>
import AppNav from './AppNav';
import SideBar from './SideBar';
// import auth from '../../auth';
// import router from '../../router';

export default {
  name: 'admin',
  components: { AppNav, SideBar },
  data() {
    return {
    };
  },
  methods: {
  },
};
</script>
<style scoped>
@import '../../assets/css/style-admin.css';
</style>
