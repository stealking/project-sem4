<template>
  <div>
    <v-snackbar class="mg-top" color="error" timeout="5000" top="true" center="true" v-model="snackbar">
      {{ text }}
    </v-snackbar>
    <v-container style="margin-top: 18vh">
      <v-layout row wrap>
        <v-flex md4 offset-md4 sm6 offset-sm3 xs12>
          <div class="card card-login">
            <div class="card-header text-xs-center purple darken-3">
              <h4 class="card-title white--text">Login</h4>
              <div class="social-line">
                <a href="#btn" class="btn-just-icon btn-simple">
                  <i class="fa fa-facebook-square"></i>
                </a>
                <a href="#pablo" class=" btn-just-icon btn-simple">
                  <i class="fa fa-twitter"></i>
                </a>
                <a href="#eugen" class=" btn-just-icon btn-simple">
                  <i class="fa fa-google-plus"></i>
                </a>
              </div>
            </div>
            <p class="category text-xs-center">
              Or Be Classical
            </p>
            <v-form v-model="valid" ref="form" lazy-validation>
              <v-flex xs12>
                <v-text-field color="purple darken-2" v-model="username" label="Tài khoản" prepend-icon="fa-user" :rules="usernameRules" required></v-text-field>
              </v-flex>
              <v-flex xs12>
                <v-text-field color="purple darken-2" type='password' v-model="pass" label="Mật khẩu" prepend-icon="fa-lock" :rules="passwordRules" required></v-text-field>
              </v-flex>
              <div class="white--text text-xs-center">
                <v-btn flat class="btn-rose btn-simple mt-3" @click="submitForm" large>Let's go</v-btn>
              </div>
            </v-form>
          </div>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import router from '../../../router';
import auth from '../../../auth';

export default {
  data() {
    return {
      valid: false,
      snackbar: false,
      text: '',
      credentials: {
        username: '',
        password: '',
      },
      error: '',
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
    };
  },
  methods: {
    submitForm() {
      if (this.$refs.form.validate()) {
        const credentials = {
          username: this.username,
          password: this.pass,
        };
        auth.login(credentials, 'Home', '').then((response) => {
          if (response === 200) {
            router.push({ name: 'Home'});
          } else {
            this.snackbar = true;
            this.text = 'Tài khoản hoặc mật khẩu không đúng!';
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

.btn-simple {
  background-color: transparent;
  color: #FFF;
  box-shadow: none;
  border: none;
  border-radius: 3px;
  position: relative;
  text-transform: uppercase;
  letter-spacing: 0;
  will-change: box-shadow, transform;
  transition: box-shadow 0.2s cubic-bezier(0.4, 0, 1, 1), background-color 0.2s cubic-bezier(0.4, 0, 0.2, 1);
}

.btn-rose {
  background-color: transparent;
  color: #7b1fa2;
  box-shadow: none;
}
</style>
