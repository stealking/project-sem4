<template lang="pug">
  v-app#inspire
    v-navigation-drawer(clipped, persistent, v-model="drawer", enable-resize-watcher, app)
      v-list(dense, style="margin-top: 20px")
        v-list-tile(v-for='item in menu.items', :key='item.title', @click='navigate(item.click)')
          v-list-tile-action
            v-icon  {{ item.icon }}
          v-list-tile-content
            v-list-tile-title {{ item.title }}
    v-toolbar(fixed)
      v-toolbar-side-icon(@click.stop='drawer = !drawer', style="color: white")
      v-toolbar-title.white--text
      v-btn.white--text(icon, @click="navigate(0)")
        v-icon home
      v-spacer
      
    main    
      router-view
    v-footer
      v-spacer
        div &copy; {{ new Date().getFullYear() }}
  
</template>
<script>

import router from '../../router'
import service from '../Admin/services';

export default {
  name: 'Account',
  data() {
    return {
      drawer: true,
      valid: false,
      menu: {
        items: [
          { title: 'Thông tin tài khoản', icon: 'fa-address-card', click: 1 },
          { title: 'Đổi mật khẩu', icon: 'fa-lock', click: 2 },
        ],
      },
      user: '',
    }
  },
  computed: {
    avatarSize() {
      return `150px`;
    }
  },
  
  methods: {
    submit() {
      if (this.$refs.form.validate()) {

      }
    },
    navigate(content) {
      switch (content) {
        case 0:
          router.push({ name: 'Home' })
          break;
        case 1:
          router.push({ name: 'AcountInfo' })
          break;
        case 2:
          router.push({ name: 'AcountInfo' })
          break;
        default:
          break;
      }
    },
  },
};
</script>

<style scoped lang="stylus">
.content-margin-top 
  margin-top: 64px
.content-margin-bottom 
  margin-bottom: 50px
.icon-color 
  color: white

</style>
