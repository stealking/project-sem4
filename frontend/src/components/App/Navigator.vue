<template lang='pug'>
doctype html
div
  v-navigation-drawer.drawer-layout(temporary, v-model='drawer', light, overflow, absolute, :floating='true')
    v-list.pa-1
      v-list-tile(avatar)
        v-list-tile-avatar
          img(src='https://randomuser.me/api/portraits/men/85.jpg')
        v-list-tile-content
          v-list-tile-title John Leider
    v-list.pt-0(v-for='(menu,key) in menus' dense two-line subheader :key='key')
      v-divider
      v-subheader(inset) {{menu.header.title}}
      v-list-tile(v-for='item in menu.items', :key='item.title', @click='navigate(item.click)')
        v-list-tile-action
          v-icon {{ item.icon }}
        v-list-tile-content
          v-list-tile-title {{ item.title }}
  v-toolbar.white.toolbar-layout(:fixed="shouldAbsolute()===false" :absolute="shouldAbsolute()===true" dark)
    v-toolbar-side-icon.hidden-sm-and-up(@click.stop='drawer = !drawer')
    v-toolbar-title.home-content(@click="goToHome()") Trang chủ
     v-icon.white--text(style="float: left; padding-right: 15px") home
    v-toolbar-items.hidden-sm-and-down.toolbar-items-layout
      v-menu(v-for='(menu,key) in menus' transition='scale-transition' offset-y :key='key')
        v-btn(flat slot='activator')
          v-icon {{menu.header.icon}}
          span.navigation-label {{menu.header.title}}
        v-list
          v-list-tile(v-for='item in menu.items', :key='item.title', @click='navigate(item.click)')
            v-list-tile-action
              v-icon {{ item.icon }}
            v-list-tile-content
              v-list-tile-title {{ item.title }}
      v-spacer
      v-menu(transition='scale-transition' offset-y)
        v-btn(flat slot='activator')
          v-icon people
        v-list(v-if="isAdmin === true")
          v-list-tile(v-for='item in adminMenu.items', :key='item.title', @click='navigate(item.click)')
            v-list-tile-action
              v-icon {{ item.icon }}
            v-list-tile-content
              v-list-tile-title {{ item.title }}  
        v-list(v-else-if="authenticated === true")
          v-list-tile(v-for='item in userMenu.items', :key='item.title', @click='navigate(item.click)')
            v-list-tile-action
              v-icon {{ item.icon }}
            v-list-tile-content
              v-list-tile-title {{ item.title }}
        v-list(v-else)
          v-list-tile(v-for='item in userMenu2.items', :key='item.title', @click='navigate(item.click)')
            v-list-tile-action
              v-icon {{ item.icon }}
            v-list-tile-content
              v-list-tile-title {{ item.title }}
</template>

<script>
import router from '../../router'
import auth from '../../auth/index'
import services from '../Admin/services'

export default {
  name: 'hello',
  mounted() {
    auth.checkAuth();
    this.authenticated = auth.user.authenticated;
    if(this.authenticated) {
      services.getUserInfo().then((response) => {
        let authen = response.authorities;
        authen.forEach(function(element) {
          this.isAdmin = (element.id === 1 || element.id === 2) ? true : false;
        }, this);
      })
    }
  },
  methods: {
    goToHome() {
      router.push({ name: 'Home' });
    },
    shouldAbsolute(){
      if(router.currentRoute.path === '/') return true;
      return false
    },
    toggleLeftSidenav() {
      this.$refs.leftSidenav.toggle();
    },
    open() {
//      console.log('Opened: ' + ref);
    },
    close() {
//      console.log('Closed: ' + ref);
    },
    navigate(content) {
      switch(content){
        case 1: 
          router.push({ name: 'Register'})
          break;
        case 2:
          router.push({ name: 'AccountInfo' })
          break;
        case 3:
          auth.logout();
          window.location.reload();
          break;
        case 4:
          router.push({ name: 'Login' })
          break;
        case 5:
          router.push({ name: 'Admin' })
          break;
        case 6:
          router.push({ name: 'AccountChangePassword' })
          break;
        default:
          break;
      }
    },
  },
  data() {
    return {
      drawer: null,
      header: 'Header',
      authenticated: false,
      isAdmin: false,
      adminMenu : {
        header: { title: 'User', icon: 'fa-user' },
        items: [
          { title: 'Admin', icon: 'fa-users', click: 5 },
          { title: 'Đăng kí', icon: 'fa-registered', click: 1 },
          { title: 'Thông tin tài khoản', icon: 'fa-address-card', click: 2 },
          { title: 'Đăng xuất', icon: 'fa-sign-out', click: 3 },
        ],    
      },
      userMenu: {
        header: { title: 'User', icon: 'fa-user-plus' },
        items: [
          { title: 'Thông tin tài khoản', icon: 'fa-address-card', click: 2 },
          { title: 'Đổi mật khẩu', icon: 'fa-unlock-alt', click: 6 },
          { title: 'Đăng xuất', icon: 'fa-sign-out', click: 3 },
        ],
      },
      userMenu2: {
        header: { title: 'User', icon: 'fa-user' },
        items: [
          { title: 'Đăng nhập', icon: 'fa-sign-in', click: 4 },
          { title: 'Đăng kí', icon: 'fa-user-plus', click: 1 },
        ],  
      },
      menus: {
        bookTourMenu: {
          header: { title: 'Đặt tour', icon: 'fa-fire' },
          items: [
            { title: 'Tour khách lẻ', icon: 'person', click: 0.0 },
            { title: 'Tour khách theo đoàn', icon: 'people', click: 0.1 },
            { title: 'Khuyến mãi', icon: 'fa-usd', click: 0.2 },
          ],
        },
        bookServiceMenu: {
          header: { title: 'Đăng kí dịch vụ', icon: 'fa-free-code-camp' },
          items: [
            { title: 'Dịch vụ khách sạn - nghỉ dưỡng', icon: 'fa-h-square', click: 1.0 },
            { title: 'Dịch vụ nhà hàng', icon: 'fa-cutlery', click: 1.1 },
          ],
        },
        touristAttractionMenu: {
          header: { title: 'Thông tin địa điểm', icon: 'fa-compass' },
          items: [
            { title: 'Địa điểm trong nước', icon: 'fa-map-o', click: 2.0 },
            { title: 'Địa điểm ngoài nước', icon: 'fa-plane', click: 2.1 },
          ],
        },
        aboutUsMenu: {
          header: { title: 'Về chúng tôi', icon: 'fa-cubes' },
          items: [
            { title: 'Liên hệ', icon: 'fa-question-circle-o', click: 3.0 },
            { title: 'Góp ý', icon: 'question_answer', click: 3.1 },
          ],
        },
      },
      right: null,
    };
  },
};
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped lang='stylus'>
.toolbar-layout
  font-weight bold
  background-color rgba(0,0,0,0.3)!important
.toolbar-items-layout
  flex 1
.navigation-label
  padding-left 20px
ul.list
  margin-bottom 0px
i.icon
  font-size 25px
.drawer-layout
  z-index 100
  position: fixed
.home-content
  cursor pointer
</style>
