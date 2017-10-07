<template lang="pug">
doctype html
.design
  el-form.demo-ruleForm(:model='myForm' :rules="rules"  label-position='top' ref='myForm', label-width='100px')
    el-form-item(label='Số lượng người lớn'  prop='adult', )
      el-input(type='number', v-model.number='myForm.adult', auto-complete='off')
    el-form-item(label='Số lượng trẻ em'  prop='children', )
      el-input(type='number', v-model.number='myForm.children', auto-complete='off')
    el-form-item(label='Số lượng em bé'  prop='baby', )
      el-input(type='number', v-model.number='myForm.baby', auto-complete='off')
    el-form-item(label='Phương thức thanh toán' )
      el-radio-group.design(v-model='myForm.payMethod')
        el-radio.ml1(label='1') Thanh toán trực tiếp
        el-radio.ml1(label='2' ) Thanh toán qua thẻ
    Policy
    el-form-item( prop='checked' )
      el-checkbox-group(v-model='myForm.checked')
        el-checkbox.ml1(label='true', name='checked') Đồng ý

    el-form-item
      v-btn(primary  @click="submitForm('myForm')") Submit
      v-btn(@click="resetForm('myForm')") Reset

</template>

<script>
import Policy from './TourDetail.InputQuality.Policy.vue'
export default {
  components: {Policy},
  props:['moveStep'],
  data() {
    return {
      myForm: {
        adult: '',
        children: '',
        baby: '',
        payMethod: '1',
        checked: []
      },
      rules:{
        adult: [
          { required: true, message: 'Vui lòng nhập số liệu'},
          { type: 'number', message: 'Số liệu phải ở dạng số'}
        ],
        children: [
          { required: true, message: 'Vui lòng nhập số liệu'},
          { type: 'number', message: 'Số liệu phải ở dạng số'}
        ],
        baby: [
          { required: true, message: 'Vui lòng nhập số liệu'},
          { type: 'number', message: 'Số liệu phải ở dạng số'}
        ],
        checked: [
          { type: 'array', required: true,
            message: 'Vui lòng nhập dữ liệu', trigger: 'change' }
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
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
