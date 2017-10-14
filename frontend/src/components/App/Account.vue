<template lang="pug">
  v-app#inspire
    v-layout(row justify-center)
      v-dialog(v-model="dialog" persistent)
        v-card
          v-card-title.headline Bạn có muốn đăng xuất?
          v-card-actions
            v-spacer
            v-btn.light.darken-1(flat @click="dialog = false") Không
            v-btn.light.darken-1(flat @click="logout") Có
    v-navigation-drawer(dark persistent, v-model="drawer", enable-resize-watcher, app)
      v-list.pa-0
        v-list-tile(avatar)
          v-list-tile-avatar
            img(src="https://randomuser.me/api/portraits/men/85.jpg")
          v-list-tile-content
            v-list-tile-title.title Admin
      v-divider
      v-list(dense, style="margin-top: 20px")
        v-list-tile(v-for='item in menu.items', :key='item.title', @click='navigate(item.click)')
          v-list-tile-action
            v-icon.icon-color   {{ item.icon }}
          v-list-tile-content
            v-list-tile-title.body-2 {{ item.title }}
    v-toolbar.hidden-md-and-up(dark)
      v-toolbar-side-icon(@click.stop='drawer = !drawer', style="color: white")
      v-toolbar-title.white--text
      v-spacer
    
    main    
      router-view
    v-footer
      v-spacer
        div &copy; {{ new Date().getFullYear() }}
  
</template>
<script>

import router from '../../router'
import auth from '../../auth/index'

export default {
  name: 'Account',
  data() {
    return {
      dialog: false,
      drawer: true,
      valid: false,
      menu: {
        items: [
          { title: 'Trang chủ', icon: 'fa-home', click: 0 },
          { title: 'Thông tin tài khoản', icon: 'fa-address-card', click: 1 },
          { title: 'Chuyến du lịch đã đặt', icon: 'fa-plane', click: 2 },
          { title: 'Đổi mật khẩu', icon: 'fa-lock', click: 3 },
          { title: 'Đăng xuất', icon: 'fa-sign-out', click: 4 },
        ],
      },
      user: '',
    }
  },
  methods: {
    logout() {
      auth.logout();
      router.push({ name: 'Home'});
    },
    navigate(content) {
      switch (content) {
        case 0:
          router.push({ name: 'Home' })
          break;
        case 1:
          router.push({ name: 'AccountInfo' })
          break;
        case 2:
          router.push({ name: 'BookedTour' })
          break;
        case 3:
          router.push({ name: 'AccountChangePassword' })
          break;
        case 4:
          this.dialog = true;
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
.icon-color 
  color: white
.bg-image
   background: url(../../../static/login.jpg)no-repeat center top transparent;
   opacity: .8
.navigation-drawer ul li
  margin-bottom 10px
  &:hover
    background-color #9c27b0!important
    border-color #9c27b0!important
</style>
