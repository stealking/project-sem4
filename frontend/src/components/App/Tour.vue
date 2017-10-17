<template lang="pug">
doctype html
.design
  Navigator
  .design.f1.center.top-title
    span.text.s6.tour-title DU LỊCH HÀ GIANG - ĐỒNG VĂN - MÃ PÍ LÈNG - HÀ NỘI [MÙA TAM GIÁC MẠCH]
  .design.row.pt2
    .design.f1.pl1.pr1.contain.removeYPaddingYMarginOnVeryNarrow
      .design.pb1
        .ui-swiper-demo
          .ui-swiper-container(v-swipe='swipeInit')
            .ui-swiper
              .ui-swiper-box(v-for='item in list')
                img.ui-swiper-filter(:src='item.smallImg', :data-src='item.img', alt='')

      .column
        .design.details.fw.row
          ._card.m1.tour-image.shadow.removeYPaddingYMarginOnVeryNarrow
          ._card.m1.removeYPaddingYMarginOnVeryNarrow
            .design.m1.mb0
              span.text.s5.bold DU LỊCH HÀ GIANG - ĐỒNG VĂN - MÃ PÍ LÈNG - HÀ NỘI [MÙA TAM GIÁC MẠCH]
            .design.m1.mb0.row.mb1
              .f1
                .span THỜI GIAN
              .f1
                .span : 5 ngày 4 đêm
            .design.row.m1
              .f1
                .span PHƯƠNG TIỆN
              .f1
                .span : Hàng không Vietjet
            .design.row.m1.mt0
              .f1
                .span ĐIỂM XUẤT PHÁT
              .f1
                .span : TP. Hồ Chí Minh
            .design.row.m1.mt0
              .f1
                .span ĐIỂM ĐẾN
              .f1
                .span : Đông Bắc
          ._card.m1.removeYPaddingYMarginOnVeryNarrow
            .design.pt1.mb1.appearOnNarrow
              .design.mb1
                span.text.s5.bold THÔNG TIN TOUR CHI TIẾT
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
        .design.f1.details.hideOnNarrow
          .text.bold.s7 THÔNG TIN TOUR CHI TIẾT
          .design.p1
            table.tableInfo
              tr
                th KHỞI HÀNH
                th MÃ TOUR
                th GIÁ NGƯỜI LỚN
                th GIÁ TRẺ EM
                th GIÁ EM BÉ
              tr
                td 12/3/2017
                td 1542524542
                td 1.300.000 VND
                td 400.000 VND
                td 600.000 VND
                td
                  .button Mua tour

        v-tabs(dark centered).shadow
          v-tabs-bar.bcm.shadow
            v-tabs-slider.grey.lighten-1
            v-tabs-item(v-for='i in tabTitles', :key='i', :href="'#tab-' + i").f1
              strong {{ i }}
          v-tabs-items
            v-tabs-content(v-for='i in tabTitles', :key='i', :id="'tab-' + i")
              v-_card.m1(flat='').removeYPaddingYMarginOnVeryNarrow
                v-_card.m1-text {{ text }}
    .design.sidebar.mr1
      .text Quảng cáo
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
      tabTitles: ['Chương trình tour', 'Chính sách tour'],
      text: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
      list: [],
      swipeInit: {
        delay: 2000,
        autoPlay: true
      },
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
.sidebar
  width 300px
  height 1000px
  /*background-color #13ce66*/
  @media(max-width: 1024px)
    display none
.tour-image
  background-image url('http://www.hitt-initiative.org/wp/wp-content/uploads/2011/03/vietnam_head_large.jpg')
  min-height 200px

.tour-detail
  border-top  solid 1px #000
.top-title
  background url("http://saigontourist.net/uploads/homepage/top-banner-bg-chitiettour.jpg")
  height 70px
  margin-top 4rem
  @media(max-width: 600px)
    height 150px
    margin-top 3rem
.tour-title
  position absolute
  color white
.details
  background-color #eeeeee

.tour-register
  display flex
  flex-direction row
  height 300px
  background-color #eeeeee
  width 100%

.columnOnNarrow
  @media(max-width: 600px)
    flex-direction column
.hideOnNarrow
  @media(max-width: 600px)
    display none
.appearOnNarrow
  display none
  @media(max-width: 600px)
    display flex
.label
  display: none;
  font-size: 35px;
  color: #ffffff !important;
  font-family: sans-serif;
  text-align: center;
  margin: auto;
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  height: 50px;
  cursor: pointer;
  /*text-decoration: none;*/


.parent
  width: 100%;
  margin: 20px;
  height: 300px;
  border: 1px solid blue;
  overflow: hidden;
  position: relative;
  float: left;
  display: inline-block;
  cursor: pointer;

.child
  height: 100%;
  width: 100%;
  background-size: cover;
  background-repeat: no-repeat;
  transition: all .5s;
.bg-one {background-image: url(https://media.timeout.com/images/101602611/image.jpg);}


.parent:hover .amimated
  color red

.parent:hover .child, .parent:focus .child
  transform: scale(1.2);


.parent:hover .child:before, .parent:focus .child:before
  display: block;


.parent:hover a, .parent:focus a
  display: block;


.child:before
  content: "";
  display: none;
  height: 100%;
  width: 100%;
  position: absolute;
  top: 0;
  left: 0;
  background-color: rgba(52,73,94,0.75);
.tableInfo
  width 100%
  td, th
    text-align left
</style>
