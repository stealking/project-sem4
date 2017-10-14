<template>
  <div>
    <v-snackbar class="mg-top" v-bind:class="[color]" :timeout="timeout" :top="top === 'top'" :center="center === 'center'" v-model="snackbar">
      {{ text }}
    </v-snackbar>
    <v-container style="margin-top: 18vh">
      <v-layout row wrap>
        <v-flex md4 offset-md4 sm6 offset-sm3 xs12>
          <div class="card card-login">
            <h4 class="header-title">Register</h4>
            <v-form v-model="valid" ref="form" lazy-validation>
              <v-flex xs12>
                <v-text-field type="email" class="black--text" color="purple darken-2" v-model="email" label="Email" prepend-icon="fa-envelope" :rules="emailRules" @keyup.enter.native="submitForm()" required></v-text-field>
              </v-flex>
              <v-flex xs12>
                <v-text-field color="purple darken-2" v-model="username" label="Tài khoản" prepend-icon="fa-user" :rules="usernameRules" @keyup.enter.native="submitForm()" required></v-text-field>
              </v-flex>
              <v-flex xs12>
                <v-text-field color="purple darken-2" type='password' v-model="pass" label="Mật khẩu" prepend-icon="fa-lock" :rules="passwordRules" @keyup.enter.native="submitForm()" required></v-text-field>
              </v-flex>
              <div class="white--text text-xs-center">
                <v-btn class="btn-rose mt-3 header-title" @click="submitForm" flat large>Get started</v-btn>
              </div>
            </v-form>
          </div>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>
<script>

import service from '../../Admin/services';
import router from '../../../router';
import auth from '../../../auth';

export default {
  data() {
    return {
      valid: false,
      color: '',
      snackbar: false,
      timeout: 10000,
      top: 'top',
      center: 'center',
      text: '',
      credentials: {
        username: '',
        password: '',
      },
      error: '',
      email: '',
      username: '',
      pass: '',
      usernameRules: [
        (v) => !!v || 'Bắt buộc',
        (v) => v && v.length >= 4 || 'Tài khoản phải nhiều hơn 4 kí tự'
      ],
      passwordRules: [
        (v) => !!v || 'Bắt buộc',
        (v) => v && v.length >= 4 || 'Mật khẩu phải nhiều hơn 4 kí tự'
      ],
      emailRules: [
        (v) => !!v || 'Bắt buộc',
        (v) => /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(v) || 'E-mail không hợp lệ'
      ],
    };
  },
  methods: {
    submitForm() {
      if (this.$refs.form.validate()) {
        const user = {
          email: this.email,
          username: this.username,
          password: this.pass,
        };
        let isExist = true;
        service.checkEmail(this.email).then((response) => {
          this.isExist = response;
          if (response) {
            this.timeout = 5000;
            this.text = 'Email đã tồn tại!';
            this.snackbar = true;
            this.color = "error";
          }
        });
        service.checkUsername(this.username).then((response) => {
          this.isExist = response;
          if (response) {
            this.timeout = 5000;
            this.color = 'error';
            this.text = 'Tài khoản đã tồn tại!';
            this.snackbar = true;
          } 
        });
        if (!this.isExist) {
          service.register(user).then((response) => {
            if (response.status === 200) {
              this.timeout = 10000;
              this.color = 'success';
              this.text = 'Đăng kí không thành công! Vui lòng kiểm tra mail để kích hoạt tài khoản.';
              this.snackbar = true;
              auth.logout();
            } else {
              this.timeout = 5000;
              this.color = 'error';
              this.text = 'Đăng kí không thành công!';
              this.snackbar = true;
            }
          });
        }
      };
    },
  },
};

</script>
<style scope>
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
  background-color: #7b1fa2!important;
  color: white!important;
  box-shadow: none;
  border-radius: 50px;
  text-transform: none;
}

.application--light .input-group:not(.input-group--error):not(.input-group--focused) .input-group__input .input-group__append-icon,
.application--light .input-group:not(.input-group--error):not(.input-group--focused) .input-group__input .input-group__prepend-icon {
  color: black;
}

.header-title {
  text-align: center;
  font-weight: 300;
  line-height: 1.5em;
}
</style>




