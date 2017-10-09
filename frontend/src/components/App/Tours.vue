<template lang="pug">
doctype html
.design
  Navigator
  .design.f1.space-top
    .rowStart.f1.pl1
      .s6.bold Tiêu chí lựa chọn
    .design
      el-form(ref='form', :model='form')
        el-form-item(label='Loại hình du lịch')
          el-checkbox-group(v-model='form.type')
            el-checkbox(label='Online activities', name='type')
        el-form-item(label='Phương tiện')
          el-checkbox-group(v-model='form.type')
            el-checkbox(label='Online activities', name='type')
        el-form-item(label='Thời gian')
          el-checkbox-group(v-model='form.type')
            el-checkbox(label='Online activities', name='type')
        el-form-item(label='Khách sạn')
          el-checkbox-group(v-model='form.type')
            el-checkbox(label='Online activities', name='type')

  .design.row.columnOnNarrow.center.fw
    Info
    Info
    Info
  _Footer
</template>

<script>
import _ from 'lodash';
import Info from '../_reused/Info.vue';
import Navigator from './Navigator';
import HomeBody from './Home.Body';
import Footer from '../_reused/Footer.vue';
import swipeDirective from 'vue-ui-swipe'
import 'vue-ui-swipe/lib/ui-swiper.css'

export default {
  name: 'hello',
  components: { Navigator, HomeBody, Info, _Footer: Footer },
  directives: {
    swipe: swipeDirective
  },
  data() {
    return{
      form: {
        type: [],
      }
    }
  },
  mounted(){
//    let listData = [
//      {
//        img: 'http://opkvnn2sq.bkt.clouddn.com//test/b4ece1c7748bf985bc01ae44acd1cd6b.jpg',
//        smallImg: 'http://opkvnn2sq.bkt.clouddn.com//test/b4ece1c7748bf985bc01ae44acd1cd6b.jpg?imageView2/2/w/60'
//      },
//      {
//        img: 'http://opkvnn2sq.bkt.clouddn.com//test/3e75d8ffc3c28393f61eea25ed986888.png',
//        smallImg: 'http://opkvnn2sq.bkt.clouddn.com//test/3e75d8ffc3c28393f61eea25ed986888.png?imageView2/2/w/60'
//      },
//      {
//        img: 'http://opkvnn2sq.bkt.clouddn.com//test/9156b0f365ac42c3236be7915b8a6428.png',
//        smallImg: 'http://opkvnn2sq.bkt.clouddn.com//test/9156b0f365ac42c3236be7915b8a6428.png?imageView2/2/w/60'
//      },
//      {
//        img: 'http://opkvnn2sq.bkt.clouddn.com//test/1f337fd118f9f27ec869a2b3581fe2ae.jpg',
//        smallImg: 'http://opkvnn2sq.bkt.clouddn.com//test/1f337fd118f9f27ec869a2b3581fe2ae.jpg?imageView2/2/w/60'
//      },
//      {
//        img: 'http://opkvnn2sq.bkt.clouddn.com//test/ed25b2550ba66eda2059d5e939ae8a2c.jpg',
//        smallImg: 'http://opkvnn2sq.bkt.clouddn.com//test/ed25b2550ba66eda2059d5e939ae8a2c.jpg?imageView2/2/w/60'
//      }
//    ]
//    setTimeout(() => {
//      this.$set(this, 'list', listData)
//    }, 10)
  }
};
</script>

<style scoped lang="stylus">
.space-top
  margin-top 4rem
  @media(max-width: 600px)
    margin-top 3rem
</style>
