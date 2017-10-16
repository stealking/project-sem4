<template lang="pug">
v-app#inspire
  div.icon-color.card__text.blue.lighten-1(xs12) Thay đổi mật khẩu 
  v-alert.mb-5(v-bind:class="alertType" v-bind:icon="alertIcon" value="true" v-model="alert") {{ this.alertContent }}
  v-container(fluid)
    v-layout(row, wrap)
      v-flex(xs12, md9)
        v-form(v-model='valid', ref='form', lazy-validation)
          v-text-field(type='password' label='Mật khẩu hiện tại ', v-model='currentPass', :rules='currentPassRules', required)
          v-text-field(type='password' label='Mật khẩu mới ', v-model='newPass', :rules='newPassRules', required)
          v-text-field(type='password' label='Nhập lại mật khẩu ', v-model='confirmPass', :rules='confirmPassRules', required)
          v-btn.primary(@click='submit', :disabled='!valid') Đổi mật khẩu
</template>
<script>

import service from '../Admin/services';

export default {
  data() {
    return {
      alertIcon: 'check_circle',
      alertType: 'success',
      alert: false,
      alertContent: '',
      valid: false,
      currentPass: '',
      newPass: '',
      confirmPass: '',
      user: '',
      currentPassRules: [
        (v) => !!v || 'Bắt buộc',
        (v) => v && v.length >= 4 || 'Mật khẩu phải nhiều hơn 4 kí tự'
      ],
      newPassRules: [
        (v) => !!v || 'Bắt buộc',
        (v) => v && v.length >= 4 || 'Mật khẩu phải nhiều hơn 4 kí tự',
      ],
      confirmPassRules: [
        (v) => !!v || 'Bắt buộc',
        (v) => v && v.length >= 4 || 'Mật khẩu phải nhiều hơn 4 kí tự',
        (v) => v && v == this.newPass || 'Mật khẩu không trùng khớp'
      ],
    }
  },
  mounted() {
    service.getUserInfo().then((response) => {
      this.user = { ...this.user, ...response };
    });
  },
  methods: {
    submit() {
      if (this.$refs.form.validate()) {
        
        const passwordConfirm = {
          oldPassword: this.currentPass,
          newPassword: this.newPass,
        }
        service.changePassword(passwordConfirm).then((response) => {
          if (response.status === 200) {
            this.user = response.data;
            this.alert = true;
            this.alertIcon = 'check_circle';
            this.alertType = 'success';
            this.alertContent = "Cập nhật thông tin thành công!";
            setTimeout(() => {
              this.alert = false;
            }, 6000);
          } else {
            this.alert = true;
            this.alertIcon = 'error';
            this.alertType = 'error';
            this.alertContent = "Mật khẩu hiện tại không đúng!";
            setTimeout(() => {
              this.alert = false;
            }, 6000);
          }
        });
      }
    },
  }
};
</script>

<style scoped lang="stylus">
.icon-color 
  color: white
.align--center
  text-align: center
@media screen and (min-width: 1024px)
  .input-file-margin
    margin-top: -200px
</style>
