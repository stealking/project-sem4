<template lang="pug">
doctype html
.design
  Navigator
  .design.f1.mt4.center.top-title
    span.text.tour-title DU LỊCH HÀ GIANG - ĐỒNG VĂN - MÃ PÍ LÈNG - HÀ NỘI [MÙA TAM GIÁC MẠCH]
  .design.pt2.pb1
    .ui-swiper-demo
      .ui-swiper-container(v-swipe='swipeInit')
        .ui-swiper
          .ui-swiper-box(v-for='item in list')
            img.ui-swiper-filter(:src='item.smallImg', :data-src='item.img', alt='')

  .contain.column.p1
    .tour-image.shadow
    .design
      .design.m1.mb0
        span.text DU LỊCH HÀ GIANG - ĐỒNG VĂN - MÃ PÍ LÈNG - HÀ NỘI [MÙA TAM GIÁC MẠCH]
      .design.m1.mb0
        .design.row.mb1
          .f1
            .span THỜI GIAN
          .f1
            .span : 5 ngày 4 đêm
      .design.row.mb1
          .f1
            .span PHƯƠNG TIỆN
          .f1
            .span : Hàng không Vietjet
      .design.row.mb1
          .f1
            .span ĐIỂM XUẤT PHÁT
          .f1
            .span : TP. Hồ Chí Minh
      .design.row.mb1
          .f1
            .span ĐIỂM ĐẾN
          .f1
            .span : Đông Bắc

      .design.p1.pb0.shadow.mb1
        .design.row.mb1
          span.text THÔNG TIN TOUR CHI TIẾT
        .design.row.mb1
          .f1
            .span Ngày khởi hành
          .f1
            .span : 11/10/2017
        .design.row.mb1
          .f1
            .span Mã Tour
          .f1
            .span : STN084-2017-04458
        .design.row.mb1
          .f1
            .span Giá
          .f1
            .span : 7.429.000
        .design.row.mb1
          .f1
            .span Giá trẻ em
          .f1
            .span : 5.000.000
        .design.row.mb1
          .f1
            .span Giá trẻ sơ sinh
          .f1
            .span : 2.770.000
        .design.row.mb1
          .f1
            .span Đặt/Mua tour
          .f1
            .button Mua tour

      .design.p1.pb0.shadow.mb1
        .design.row.mb1
          span.text THÔNG TIN TOUR CHI TIẾT
        .design.row.mb1
          .f1
            .span Ngày khởi hành
          .f1
            .span : 11/10/2017
        .design.row.mb1
          .f1
            .span Mã Tour
          .f1
            .span : STN084-2017-04458
        .design.row.mb1
          .f1
            .span Giá
          .f1
            .span : 7.429.000
        .design.row.mb1
          .f1
            .span Giá trẻ em
          .f1
            .span : 5.000.000
        .design.row.mb1
          .f1
            .span Giá trẻ sơ sinh
          .f1
            .span : 2.770.000
        .design.row.mb1
          .f1
            .span Đặt/Mua tour
          .f1
            .button Mua tour
    v-tabs(dark centered).shadow
      v-tabs-bar.bcm.shadow
        v-tabs-slider.grey.lighten-1
        v-tabs-item(v-for='i in items', :key='i', :href="'#tab-' + i").f1
          strong {{ i }}
      v-tabs-items
        v-tabs-content(v-for='i in items', :key='i', :id="'tab-' + i")
          v-card(flat='')
            v-card-text {{ text }}


</template>
<script>
import _ from 'lodash';
import Navigator from './Navigator';
import HomeBody from './Home.Body';
import swipeDirective from 'vue-ui-swipe'
import 'vue-ui-swipe/lib/ui-swiper.css'

export default {
  name: 'hello',
  components: { Navigator, HomeBody },
  directives: {
    swipe: swipeDirective
  },
  data() {
    return{
      items: ['Chương trình tour', 'Chính sách tour'],
      text: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
      list: [],
      swipeInit: {
        delay: 3000,
        autoPlay: true
      }
    }
  },
  mounted(){
    let listData = [
      {
        img: 'http://opkvnn2sq.bkt.clouddn.com//test/b4ece1c7748bf985bc01ae44acd1cd6b.jpg',
        smallImg: 'http://opkvnn2sq.bkt.clouddn.com//test/b4ece1c7748bf985bc01ae44acd1cd6b.jpg?imageView2/2/w/60'
      },
      {
        img: 'http://opkvnn2sq.bkt.clouddn.com//test/3e75d8ffc3c28393f61eea25ed986888.png',
        smallImg: 'http://opkvnn2sq.bkt.clouddn.com//test/3e75d8ffc3c28393f61eea25ed986888.png?imageView2/2/w/60'
      },
      {
        img: 'http://opkvnn2sq.bkt.clouddn.com//test/9156b0f365ac42c3236be7915b8a6428.png',
        smallImg: 'http://opkvnn2sq.bkt.clouddn.com//test/9156b0f365ac42c3236be7915b8a6428.png?imageView2/2/w/60'
      },
      {
        img: 'http://opkvnn2sq.bkt.clouddn.com//test/1f337fd118f9f27ec869a2b3581fe2ae.jpg',
        smallImg: 'http://opkvnn2sq.bkt.clouddn.com//test/1f337fd118f9f27ec869a2b3581fe2ae.jpg?imageView2/2/w/60'
      },
      {
        img: 'http://opkvnn2sq.bkt.clouddn.com//test/ed25b2550ba66eda2059d5e939ae8a2c.jpg',
        smallImg: 'http://opkvnn2sq.bkt.clouddn.com//test/ed25b2550ba66eda2059d5e939ae8a2c.jpg?imageView2/2/w/60'
      }
    ]
    setTimeout(() => {
      this.$set(this, 'list', listData)
    }, 10)
  }
};
</script>

<style scoped lang="stylus">

.tour-image
  flex 1
  background-color #4F0112
  min-height 200px

.tour-detail
  border-top  solid 1px #000
.top-title
  background url("http://saigontourist.net/uploads/homepage/top-banner-bg-chitiettour.jpg")
  height 150px
.tour-title
  position absolute
  color white
  font-size 2rem
.pageTitle
  margin-top 64px
  height 200px
.pageTitle
  img
    height 100%
.el-carousel-item
  img
    width: 100%;
.el-carousel
  overflow-y hidden
.el-carousel__item h3
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
.el-carousel__item:nth-child(2n)
  background-color: #99a9bf;
.el-carousel__item:nth-child(2n+1)
  background-color: #d3dce6;
.tour-register
  display flex
  flex-direction row
  height 300px
  background-color #eeeeee
  width 100%

</style>
