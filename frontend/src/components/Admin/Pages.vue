<template>
  <div style="overflow: hidden;" class="bg-img">
    <Navigator></Navigator>
    <router-view></router-view>
  </div>
</template>
<script>

import Navigator from '../App/Navigator';

export default {
  name: 'admin',
  components: { Navigator },
  data() {
    return {
      activeIndex: '1',
      activeIndex2: '1',
    };
  },
  methods: {
    handleSelect() {
    },
  },
};
</script>

<style scope>
.bg-img {
  height: 100vh;
  background: url(../../../static/login.jpg)no-repeat center top;
  background-size: cover;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  -ms-background-size: cover;
  background-attachment: fixed;
  background-position: center;
}
</style>



