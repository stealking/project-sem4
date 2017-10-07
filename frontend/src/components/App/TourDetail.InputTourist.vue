<template lang="pug">
doctype html
.design
  el-form(:model='myForm', ref='myForm', label-width='120px')
    .design.fl(v-for='(domain, index) in myForm.domains')
      .text.s6 Khách thứ # {{domain.key}}
      el-form-item( label="Tên", :key='domain.key', :prop="'domains.' + index + '.name'", :rules="rules")
        el-input(v-model='domain.name')
      el-form-item( label="Địa chỉ", :key='domain.key', :prop="'domains.' + index + '.address'", :rules="rules")
        el-input(v-model='domain.address')
      el-form-item( label="Số điện thoại", :key='domain.key', :prop="'domains.' + index + '.phone'", :rules="rules")
        el-input(v-model='domain.phone')
    el-form-item
      el-button(type='primary', @click="submitForm('myForm')") Submit
      el-button(@click="resetForm('myForm')") Reset


</template>

<script>
import Policy from './TourDetail.InputQuality.Policy.vue'
export default {
  components: {Policy},
  props:['moveStep'],
  data() {
    return {
      myForm: {
        domains: [{
          key: 1,
          name: '',
          gender: '',
          address: '',
          phone: '',
        },{
          key: 2,
          name: '',
          gender: '',
          address: '',
          phone: '',
        },{
          key: 3,
          name: '',
          gender: '',
          address: '',
          phone: '',
        }],
      },
      rules: {
        required: true, message: 'Vui lòng nhập vào giá trị', trigger: 'blur'
      },
    };
  },
  methods: {
    submitForm(formName) {
      console.log(this.myForm)
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$emit('moveStep')
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  }
};
</script>

<style scoped lang="stylus">


</style>
