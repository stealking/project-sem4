<template>
  <div class="box">
    <div class="row content">
      <side-bar>
        <router-view></router-view>
      </side-bar>
    </div>
  </div>
</template>
<script>
import SideBar from './SideBar';

export default {
  name: 'admin',
  components: { SideBar },
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
