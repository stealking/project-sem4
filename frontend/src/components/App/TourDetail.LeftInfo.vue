<template lang="pug">
doctype html
.design.f1.wrapper.shadow
  img.image(src="http://saigontourist.net/uploads/destination/TrongNuoc/Phanthiet/Mui-Ne-Phan-Thiet-Vietnam_371109257.jpg")
  .design.rowStart.p1
    .text.s5 Du Lịch Phan Thiết - Mũi Né
  .design.rowStart.p1
    .text.s4 Code 134135134
  .design.rowStart.p1
    .text.s4 Ngày đi 22/12/2015
  .design.rowStart.p1
    .text.s4 Thời gian: 3 ngày
  .design.rowStart.p1
    .text.s4 Giá Người lớn 1.900.000 VND
  .design.rowStart.p1
    .text.s4 Giá trẻ em 1.900.000 VND
  .design.rowStart.p1
    .text.s4 Giá em bé  1.900.000 VND
  .design.rowStart.p1
    .text.s6 Tổng  3.900.000 VND
</template>

<script>
import _ from 'lodash';
import Info from '../_reused/Info.vue';
import Navigator from './Navigator';
import InputTourist from './TourDetail.InputTourist.vue';
import InputQuality from './TourDetail.InputQuality.vue';
import Summary from './TourDetail.Summary.vue';
import Footer from '../_reused/Footer.vue';
import swipeDirective from 'vue-ui-swipe'
import 'vue-ui-swipe/lib/ui-swiper.css'

export default {
};
</script>

<style scoped lang="stylus">
.image
  width  100%
  height auto
.wrapper
 justify-content   flex-start
</style>
