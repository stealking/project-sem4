<template>
  <div>
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



