<template lang="pug">
doctype html
.design
  Navigator
  .design.f1.center.top-title
    span.text.s6.tour-title DU LỊCH HÀ GIANG - ĐỒNG VĂN - MÃ PÍ LÈNG - HÀ NỘI [MÙA TAM GIÁC MẠCH]
  .design.row.pt2
    .f1.pl1.pr1.contain.removeYPaddingYMarginOnVeryNarrow
      v-stepper(v-model='e6', vertical='')
        v-stepper-step(step='1', v-bind:complete='e6 > 1') Nhập số lượng khách
        v-stepper-content(step='1')
          InputQuality(@moveStep='moveStep')

        v-stepper-step(step='2', v-bind:complete='e6 > 2') Nhập số thông tin khách
        v-stepper-content(step='2')
          InputTourist(@moveStep='moveStep')

        v-stepper-step(step='3', v-bind:complete='e6 > 3') Thành công
        v-stepper-content(step='3')
          v-card.grey.lighten-1.mb-5(height='200px')
          v-btn(primary='', @click.native='e6 = 4') Continue
          v-btn(flat='') Cancel
    .sidebar.mr1
      LeftInfo
  .design.row.columnOnNarrow.fw.safa
    Info
    Info
    Info
  _Footer
</template>

<script>
import _ from 'lodash';
import Info from '../_reused/Info.vue';
import Navigator from './Navigator';
import InputTourist from './TourDetail.InputTourist.vue';
import InputQuality from './TourDetail.InputQuality.vue';
import Summary from './TourDetail.Summary.vue';
import LeftInfo from './TourDetail.LeftInfo.vue';
import Footer from '../_reused/Footer.vue';
import swipeDirective from 'vue-ui-swipe'
import 'vue-ui-swipe/lib/ui-swiper.css'

export default {
  name: 'hello',
  components: { Navigator, Info, _Footer: Footer, InputTourist, InputQuality, _Summary: Summary, LeftInfo },
  directives: {
    swipe: swipeDirective
  },
  data() {
    return{
      e6:1
    }
  },
  methods: {
    moveStep(){
      this.e6 += 1
    }
  },
  mounted(){
    this.$store.dispatch({
      type: 'fetchTourDetail',
      id: 1,
    }).then((res) => {
      console.log()
    }, (err) => {
      console.log(err);
    });
  }
};
</script>

<style scoped lang="stylus">
.sidebar
  width 300px
  @media(max-width: 1024px)
    display none
.tour-image
  background-color #4F0112
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
