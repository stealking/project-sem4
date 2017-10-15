<template>
  <div>
    <v-snackbar class="mg-top" v-bind:class="[color]" :timeout="timeout" :top="top === 'top'" :center="center === 'center'" v-model="snackbar">
      {{ text }}
    </v-snackbar>
    <v-container style="margin-top: 18vh">
      <v-layout row wrap>
        <v-flex md4 offset-md4 sm6 offset-sm3 xs12>
          <div v-if="user" class="card card-login">
            <h4 class="header-title"></h4>
            <v-form v-model="valid" ref="form" lazy-validation>
              <v-flex xs12>
                <v-text-field type='password' v-model="newPass" label="Mật khẩu mới" prepend-icon="fa-lock" :rules="newPassRules" @keyup.enter.native="submitForm()" required></v-text-field>
              </v-flex>
              <v-flex xs12>
                <v-text-field type='password' v-model="confirmPass" label="Nhập lại mật khẩu" prepend-icon="fa-lock" :rules="confirmPassRules" @keyup.enter.native="submitForm()" required></v-text-field>
              </v-flex>
              <div class="white--text text-xs-center">
                <v-btn class="btn-rose mt-3" @click="submitForm" large>Lưu</v-btn>
              </div>
            </v-form>
          </div>
          <div v-else class="card card-login text-xs-center">
            <h5 class="header-title red--text">Đường dẫn không hợp lệ.</h5>
            <p>Vui lòng kiểm tra lại.</p>
            <v-btn class="btn-rose mt-3" @click="goToHome" flat large>Trang chủ</v-btn>
          </div>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import router from '../../../router';
import auth from '../../../auth';
import service from '../../Admin/services';

function getUrlVars() {
  var vars = [], hash;
  var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
  for (var i = 0; i < hashes.length; i++) {
    hash = hashes[i].split('=');
    vars.push(hash[0]);
    vars[hash[0]] = hash[1];
  }
  return vars;
}
export default {
  data() {
    return {
      valid: false,
      color: '',
      snackbar: false,
      timeout: 5000,
      top: 'top',
      center: 'center',
      text: '',
      token: '',
      user: '',
      username: '',
      newPass: '',
      confirmPass: '',
      newPassRules: [
        (v) => !!v || 'Bắt buộc',
        (v) => v && v.length >= 4 || 'Mật khẩu phải nhiều hơn 4 kí tự',
      ],
      confirmPassRules: [
        (v) => !!v || 'Bắt buộc',
        (v) => v && v.length >= 4 || 'Mật khẩu phải nhiều hơn 4 kí tự',
        (v) => v && v == this.newPass || 'Mật khẩu không trùng khớp'
      ],
    };
  },
  async mounted(to, from, next) {
    this.token = getUrlVars().token;
    localStorage.setItem('access_token', this.token);
    this.user = await service.getUserInfo();
    auth.logout();
  },
  methods: {
    goToHome() {
      router.push({ name: 'Home' });
    },
    submitForm() {
      if (this.$refs.form.validate()) {
        this.user.password = this.newPass;
        service.resetPassword(this.user, this.token).then((response) => {
          if (response.status === 200) {
            this.text = 'Lưu thành công! Mời đăng nhập lại.';
            this.color = 'success';
            this.snackbar = true;
            setTimeout(() => {
              this.alert = false;
              router.push({ name: 'Login'});
            }, 5000);
          }
        });
      };
    },
  },
};
</script>
<style scoped>
.mg-top {
  margin-top: 80px;
}

.card {
  display: inline-block;
  position: relative;
  width: 100%;
  margin: 25px 0;
  box-shadow: 0 1px 4px 0 rgba(0, 0, 0, 0.14);
  border-radius: 6px;
  color: rgba(0, 0, 0, 0.87);
  background: #fff;
  border-radius: 3px;
  padding: 15px;
}

.card-login {
  box-shadow: 0 1px 4px 0 rgba(0, 0, 0, 0.14);
  border-radius: 6px;
  padding-bottom: 20px;
  -webkit-transform: translate3d(0, 0, 0);
  -moz-transform: translate3d(0, 0, 0);
  -o-transform: translate3d(0, 0, 0);
  -ms-transform: translate3d(0, 0, 0);
  transform: translate3d(0, 0, 0);
}

.card-header {
  margin-top: -40px;
  margin-bottom: 20px;
  border-bottom-left-radius: 6px;
  border-bottom-right-radius: 6px;
}

.card-title {
  padding-top: 25px;
  font-size: 18px;
  margin-bottom: 10px;
  font-weight: 700;
}

.card-login .social-line {
  margin-top: 15px;
  text-align: center;
  padding: 20px;
}

.btn-just-icon {
  font-size: 20px;
  padding: 20px 20px;
  line-height: 1em;
}

.btn-rose {
  background-color: #8e24aa!important;
  border-radius: 6px;
  color: white;
  box-shadow: none;
}
</style>
