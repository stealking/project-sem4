<template>
  <div>
    <v-snackbar class="mg-top" v-bind:class="[color]" :timeout="timeout" :top="top === 'top'" :center="center === 'center'" v-model="snackbar">
      {{ text }}
    </v-snackbar>
    <v-container style="margin-top: 18vh">
      <v-layout row wrap>
        <v-flex md4 offset-md4 sm6 offset-sm3 xs12>
          <div v-if="send === false" class="card card-login">
            <h4 class="header-title">Quên mật khẩu</h4>
            <p>Nhập địa chỉ email để nhận đường dẫn khôi phục mật khẩu.</p>
            <v-form v-model="valid" ref="form" lazy-validation>
              <v-flex xs12>
                <v-text-field type="email" class="black--text" v-model="email" label="Email" prepend-icon="fa-envelope" :rules="emailRules" @keyup.enter.native="submitForm()" required></v-text-field>
              </v-flex>
              <div class="white--text text-xs-center">
                <v-btn class="btn-rose mt-3 header-title" @click="submitForm" flat large>Gửi thư</v-btn>
              </div>
            </v-form>
          </div>
          <div v-else class="card card-login text-xs-center">
            <h4 class="header-title blue--text">Gửi thư thành công</h4>
            <p>Vui lòng kiểm tra email để khôi phục mật khẩu.</p>
            <v-btn class="purple mt-3" @click="goToHome" flat large>Trang chủ</v-btn>
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
      color: '',
      snackbar: false,
      timeout: 10000,
      top: 'top',
      center: 'center',
      text: '',
      valid: false,
      send: false,
      email: '',
      emailRules: [
        (v) => !!v || 'Bắt buộc',
        (v) => /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(v) || 'E-mail không hợp lệ'
      ],
    };
  },
  methods: {
    goToHome() {
      router.push({ name: 'Home' });
    },
    submitForm() {
      if (this.$refs.form.validate()) {
        service.forgetPassword(this.email).then((response) => {
          if (response.status === 200) {
            this.send = true;
          } else if (response.status === 404) {
            this.text = 'Email không tồn tại!';
            this.color = 'error';
            this.snackbar = true;
          } else {
            this.text = 'Xảy ra lỗi trong quá trình gửi!';
            this.color = 'error';
            this.snackbar = true;
          }
        });
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




