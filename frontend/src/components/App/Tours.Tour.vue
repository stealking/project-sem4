<template lang="pug">
doctype html
.design.f6.columnStart.p3
  .design.row
    .design.columnStart
      img.tour-image.sd(src="http://saigontourist.net/uploads/destination/TrongNuoc/mienbac/buckwheat-flower-season-hagiang.jpg")
    .design.column
      .design.f1.row
        .design.f1.columnStart.p2
          .s5 {{tour.journey.name.toUpperCase()}}
          .s4 Khởi hành từ: {{tour.departure.name}}
          .s4 Giới thiệu: {{tour.introduction}}
          .s4 Thời gian: {{formatDateNum(tour.totalTime)}}
          .s4 Phương tiện: {{tour.transport.name }}
        .design.p2.button
          .s4.text.cw.pb2 Giá từ
          .s5.text.b.cw.pb1 {{formatPrice(tour.price)}}
          .button.p1
            .s4.text.cw Chi tiết
      .design
        .design.rowStart.p1.buttonHeight(v-for="tourDetail in tour.tourDetails")
          .design.labelBox.pl4.pr4.buttonHeight.mr1
            .s4.text
              i.fa.fa-calendar.mr1(aria-hidden="true")
              span {{formatDate(tourDetail.departmentDate)}}
          .design.labelBox.pl4.pr4.buttonHeight.mr1
            .s4.text
              i.fa.fa-money.mr1(aria-hidden="true")
              span 3,259,000
          .p2.button.buttonHeight.mr1
            .s4.pl4.pr4 Mua tour
</template>

<script>
import _ from 'lodash';
import store from '../../store';
import numeral from 'numeral'
import moment from 'moment'

export default {
  props:['tour'],
  data() {
    return {
    }
  },
  methods:{
    formatPrice(value){
      return numeral(value).format('0,0');
    },
    formatDateNum(value){
      let str = ''
      str += `${Math.floor(value)} ngày`
      if(value % 2 === 1)  str += ' 1 đêm'
      return str
    },
    formatDate(value){
      return moment(value).format('DD/MM/YYYY')
    }
  }
};
</script>

<style scoped lang="stylus">
.space-top
  margin-top 4rem
  @media(max-width: 600px)
    margin-top 3rem
.searchBar
  width: 300px
.tour-image
  width: 300px
  height auto
</style>
