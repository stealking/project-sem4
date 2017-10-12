<template lang="pug">
v-app#inspire
  div.icon-color.card__text.indigo(xs12) Thông tin tài khoản  
  v-alert.success.mb-5(icon="check_circle" value="true" v-model="alert") Cập nhật thành công!
  v-container(fluid)
    v-layout(row, wrap)
      v-flex(xs12, md3, justify-center, layout, column)
        v-flex(align--center)
          v-avatar.grey.lighten-4(:size="avatarSize")
            img(:src="getImage()"  alt='avatar')
        v-flex(align--center)
          div(style="width: 80%")
            v-text-field(prepend-icon='attach_file', single-line, v-model='fileName', :label='label', @click.native='onFocus', :disabled='disabled', ref='fileTextField')
            input(hidden, type='file', :accept='accept', :multiple='false', :disabled='disabled', ref='fileInput', @change='onFileChange')

      v-flex(xs12, md9)
        v-form(v-model='valid', ref='form', lazy-validation)
          v-text-field(label='E-mail', v-model='user.email', :rules='emailRules', required='')
          v-text-field(label='First Name', v-model='user.firstName', :rules='firstNameRules', required)
          v-text-field(label='Last Name', v-model='user.lastName', :rules='lastNameRules', required)
          v-text-field(label='Address', v-model='user.address')
          v-text-field(label='Phone', v-model='user.phone')
          <v-menu lazy :close-on-content-click="false" v-model="menu" transition="scale-transition" offset-y full-width :nudge-right="40" max-width="290px" min-width="290px">
            <v-text-field slot="activator" label="Picker in menu" v-model="user.dob" prepend-icon="event" readonly></v-text-field>
              <v-date-picker v-model="user.dob" no-title scrollable actions>
                <template scope="{ save, cancel }">
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn flat color="primary" @click="cancel">Cancel</v-btn>
                    <v-btn flat color="primary" @click="save">OK</v-btn>
                  </v-card-actions>
                </template>
              </v-date-picker>
          </v-menu>
          v-btn(@click='submit', :disabled='!valid') submit
</template>
<script>

import service from '../Admin/services';
import * as moment from 'moment';

export default {
  props: {
    value: {
      type: [Array, String]
    },
    accept: {
      type: String,
      default: "*"
    },
    label: {
      type: String,
      default: "Xin chọn ảnh..."
    },
    required: {
      type: Boolean,
      default: false
    },
    disabled: {
      type: Boolean,
      default: false
    },
    multiple: {
      type: Boolean, // not yet possible because of data
      default: false
    }
  },
  name: 'Account',
  data() {
    return {
      alert: false,
      pathImage: 'http://localhost:8080/upload/',
      fileName: '',
      valid: false,
      menu: false,
      user: {
        firstName: '',
        lastName: '',
        email: '',
        address: '',
        phone: '',
        dob: '',
        avatar: '',    
      },
      firstNameRules: [
        (v) => !!v || 'First Name is required',
        (v) => v && v.length >= 4 || 'First Name must be larger than 4 characters'
      ],
      lastNameRules: [
        (v) => !!v || 'Last name is required',
        (v) => v && v.length >= 4 || 'Last name must be larger than 4 characters'
      ],
       emailRules: [
        (v) => !!v || 'E-mail is required',
        (v) => /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(v) || 'E-mail must be valid'
      ],
    }
  },
  watch: {
    value(v) {
      this.fileName = v;
    }
  },
  mounted() {
    this.fileName = this.value;
    service.getUserInfo().then((response) => {
      this.user = {...this.user, ...response};
      this.user.dob = this.convertToDateTime(this.user.dob);
      this.pathImage = `${this.pathImage}${this.user.avatar}`;
    });
  },
  computed: {
    avatarSize() {
      return `150px`;
    }
  },
  methods: {
    submit() {
      if (this.$refs.form.validate()) {
        service.updateUser(this.user, null).then((response) => {
          this.pathImage = '';
          this.user = response.data;
          this.user.dob = this.convertToDateTime(this.user.dob);
          this.pathImage = `http://localhost:8080/upload/${this.user.avatar}`;
          this.alert = true;
          setTimeout(() => {
            this.alert = false;
          }, 3000);
        });
      }
    },
    convertToDateTime(date) {
      let dateString = moment(date).format('L');
      return dateString;
    },
    getImage(){
      return `http://localhost:8080/upload/${this.user.avatar}?x=${Math.random() * 2}`
    },
    getFormData(files) {
      const data = new FormData();
      [...files].forEach(file => {
        data.append('data', file, file.name); // currently only one file at a time
      });
      return data;
    },
    onFocus() {
      if (!this.disabled) {
        this.$refs.fileInput.click();
      }
    },
    onFileChange($event) {
      const files = $event.target.files || $event.dataTransfer.files;
      const form = this.getFormData(files);
      if (files) {
        if (files.length > 0) {
          this.fileName = [...files].map(file => file.name).join(', ');
        } else {
          this.fileName = null;
        }
      } else {
        this.fileName = $event.target.value.split('\\').pop();
      }
      this.$emit('input', this.fileName);
      this.$emit('formData', form);
      service.updateUser(this.user, files[0]).then((response) => {
        this.pathImage = '';
        this.user = response.data;
        this.pathImage = `http://localhost:8080/upload/${this.user.avatar}`;
        this.alert = true;
        setTimeout(() => {
          this.alert = false;
        }, 3000);
      });
    }
  }
};
</script>

<style scoped lang="stylus">
.icon-color 
  color: white
.align--center
  text-align: center
</style>
