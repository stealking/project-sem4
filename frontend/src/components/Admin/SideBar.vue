<template>
  <div id="wrappers">
    <el-row>
      <el-col :span="4" class="left-side-bar">
        <div>
          <div class="nav-profile">
            <div>
              <a href="/#/" class="simple-text">
                <div class="logo-img">
                  <img src="../../assets/logo.png" />
                </div>
              </a>
              <el-dropdown @command="handleCommand">
                <span class="el-dropdown-link" style="color:white">
                  Admin
                  <i class="el-icon-caret-bottom el-icon--right"></i>
                </span>
                <el-dropdown-menu slot="dropdown" class="menu-account">
                  <el-dropdown-item command="My profile">My Profile</el-dropdown-item>
                  <el-dropdown-item command="Change password">Change password</el-dropdown-item>
                  <el-dropdown-item command="Log out">Log Out</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </div>
          </div>
        </div>
        <nav class="navigation">
          <ul class="list-unstyled">
            <li class="profile">
              <router-link to="/" class="text-xs-left ml-3 subheading">
                <i class="fa fa-user icon_menus"></i>
                <span class="nav-label">Home page</span>
              </router-link>
            </li>
            <li class="profile">
              <router-link to="/admin/user" class="text-xs-left ml-3 subheading">
                <i class="fa fa-user icon_menus"></i>
                <span class="nav-label">User</span>
              </router-link>
            </li>
            <li>
              <router-link to="/admin/tour" class="text-xs-left ml-3 subheading">
                <i class="fa fa-plane icon_menus"></i>
                <span class="nav-label">Tour</span>
              </router-link>
            </li>
            <li>
              <router-link to="/admin/voucher" class="text-xs-left ml-3 subheading">
                <i class="fa fa-gift icon_menus"></i>
                <span class="nav-label">Voucher</span>
              </router-link>
            </li>
  
          </ul>
        </nav>
        
      </el-col>
      <el-col :span="20" style=" margin-left: 16.6667%; ">
        <div class="right-side-bar">
          <router-view></router-view>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import auth from '../../auth';
import router from '../../router';

export default {
  name: 'sidebar',
  data() {
    return {
    };
  },
  methods: {
    handleCommand(command) {
      if (command === 'Log out') {
        auth.logout();
        router.push({path: '/'});
      } else if (command === 'My profile') {
        router.push({ name: 'Profile' });
      } else if (command === 'Change password') {
        router.push({ name: 'ChangePassword' });
      }
    },
  },
};
</script>

<style scope>
.right-side-bar {
  padding: 0 10px 0 10px;
}

.left-side-bar a {
  text-decoration: none !important;
}

.left-side-bar {
  background-color: rgb(33, 33, 32);
  height: 100vh;
  position: fixed;
}

.el-dropdown-menu {
  background-color: #c8c8c8;
}

.el-menu-item:hover {
  color: #fff;
  content: '';
  width: 100% !important;
  background-color: #065E52;
  padding: 0;
  -webkit-transition: width 1s;
  /* Safari 3.1 to 6.0 */
  transition: width 1s;
}

.el-menu-item a {
  display: block;
  width: 0%;
  color: #FFFFFF;
  margin: 10px 0px;
}

.nav-profile {
  border-bottom: 1px solid rgba(255, 255, 255, 0.3);
  background-color: #212120;
  color: #FFFFFF;
}

.nav-profile a {
  color: #FFFFFF;
  padding: 13px 0;
  margin: 0 20px;
}

.logo-img {
  width: 40px;
  display: inline-block;
  height: 40px;
  margin-left: 0px;
  margin-right: 10px;
  background: white;
  border-radius: 40px;
  text-align: center;
}

.logo-img img {
  max-width: 21px;
  vertical-align: middle;
}

.simple-text {
  padding: 4px 0px;
  display: inline-block;
  font-size: 14px;
  text-align: center;
  font-weight: 600;
  line-height: 40px;
}

.menu-account {
  background-color: #333333;
  color: white;
  border:0;
}
</style>




