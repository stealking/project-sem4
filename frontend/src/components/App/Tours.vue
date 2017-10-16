<template lang="pug">
doctype html
.design
  Navigator
  .design.f1.space-top.column.row600.column
    .design.searchBar
      .rowStart.f1.pl1
        .s6.bold Tiêu chí lựa chọn
      .design.pl2.pr3
        el-form(ref='form', :model='form'  label-width="120px" label-position="top")
          el-form-item(label='Loại hình du lịch').mt1
            el-select(v-model='form.tourTypeId', filterable='', placeholder='Select')
              el-option(v-for='item in optionTourTypes', :key='item.value.id', :label='item.label', :value='item.value')

          el-form-item(label='Phương tiện').mt1
            el-select(v-model='form.transportId', filterable='', placeholder='Select')
              el-option(v-for='item in optionTransports', :key='item.value.id', :label='item.label', :value='item.value')

          el-form-item(label='Khuyến mãi').mt1
            el-select(v-model='form.saleOff', filterable='', placeholder='Select')
              el-option(v-for='item in optionSaleOffs', :key='item.value.id', :label='item.label', :value='item.value')

          el-form-item(label='Nơi xuất phát').mt1
           el-select(v-model='form.departureId', filterable='', placeholder='Select')
              el-option(v-for='item in optionDepartures', :key='item.value.id', :label='item.label', :value='item.value')

          el-form-item(label='Hành trình').mt1
           el-select(v-model='form.journeyId', filterable='', placeholder='Select')
              el-option(v-for='item in optionJourneys', :key='item.value.id', :label='item.label', :value='item.value')

          el-form-item(label='Giá tiền').mt1
            .pt1
              vue-slider(v-model="form.price" width="100%" :height="height" :dotSize="dotSize" :min="min" :max="max" disabled=false show=true tooltip="hover" formatter="{value}.000.000VND" :bgStyle="bgStyle" :tooltipStyle="tooltipStyle" :processStyle="processStyle" :tooltipDir="tooltipDir")
          el-form-item(label='Thời gian').mt1
            .pt1
              vue-slider(v-model="form.totalTime" width="100%" :height="height" :dotSize="dotSize" :min="min1" :max="max1" disabled=false show=true tooltip="hover" :formatter="formatter" :bgStyle="bgStyle" :tooltipStyle="tooltipStyle" :processStyle="processStyle" :tooltipDir="tooltipDir" :step="step")
      .f1.pl2
        v-btn(color="primary" @click="_onFind") Tìm kiếm
    .design.f6.columnStart.p3
      .design.row
        .design.columnStart
          img.tour-image.sd(src="http://saigontourist.net/uploads/destination/TrongNuoc/mienbac/buckwheat-flower-season-hagiang.jpg")
        .design.column
          .design.f1.row
            .design.f1.columnStart.p2
              .s5 DU LỊCH HÀ GIANG - ĐỒNG VĂN - MÃ PÍ LÈNG - HÀ NỘI [MÙA TAM GIÁC MẠCH]
              .s3 TP. HỒ CHÍ MINH - ĐÔNG BẮC
              .s3 - Thăm Thôn Tha (hoặc thôn Hạ Thành), tìm hiểu phong tục tập quán và...
              .s3 Thời gian : 5 ngày 4 đêm
              .s3 Phương tiện : Hàng không Vietjet
            .design.p2.button
              .s4.text.cw.pb2 Giá từ
              .s5.text.b.cw.pb1 7,429,000
              .button.p1
                .s4.text.cw Chi tiết
          .design
            .design.rowStart.p1.buttonHeight
              .design.labelBox.pl4.pr4.buttonHeight.mr1
                .s4.text
                  i.fa.fa-calendar.mr1(aria-hidden="true")
                  span 27/10/2017
              .design.labelBox.pl4.pr4.buttonHeight.mr1
                .s4.text
                  i.fa.fa-money.mr1(aria-hidden="true")
                  span 3,259,000
              .p2.button.buttonHeight.mr1
                .s4.pl4.pr4 Mua tour
    .design.row.columnOnNarrow.center.fw
  .design.row
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
import vueSlider from 'vue-slider-component';
import store from '../../store';

export default {
  components: { Navigator, HomeBody, Info, _Footer: Footer, 'vue-slider': vueSlider },
  directives: {
    swipe: swipeDirective
  },
  data() {
    return{
      optionDepartures:[],
      optionJourneys:[],
      optionTourTypes:[],
      optionTransports:[],
      optionSaleOffs: [ {
        value: 1,
        label: 'Có khuyễn mãi'
      }, {
        value: 0,
        label: 'Không có khuyễn mãi'
      },{
        value: ' ',
        label: 'Tất cả'
      }],
      bgStyle:{
        "backgroundColor": "#fff",
        "boxShadow": "inset 0.5px 0.5px 3px 1px rgba(0,0,0,.36)"
      },
      tooltipStyle: {
        "backgroundColor": "#666",
        "borderColor": "#666"
      },
      processStyle: {
        "backgroundColor": "#999"
      },
      min: 1,
      max: 30,
      min1: 1,
      max1: 10,
      step: 0.5,
      height: 8,
      dotSize: 16,
      tooltipDir: [
        "bottom",
        "bottom"
      ],
      form: {
        departureId: "",
        tourTypeId: "",
        journeyId: "",
        transportId: "",
        saleOff: "",
        price:[
          0,
          20
        ],
        totalTime:[
          0,
          10
        ],
        page: 1
      },
      tours: [],

    }
  },
  mounted(){
    this.$store.dispatch({
      type: 'fetchDepartures',
    }).then((res) => {
      this.optionDepartures = res.content.map(item=>(
        ({value:item.id, label: item.name})
      ))
      this.optionDepartures.push({value:' ', label:'Tất cả'})
    }, (err) => {
      console.log(err);
    });

    this.$store.dispatch({
      type: 'fetchTourTypes',
    }).then((res) => {
      this.optionTourTypes = res.content.map(item=>(
        ({value:item.id, label: item.name})
      ))
      this.optionTourTypes.push({value:' ', label:'Tất cả'})
    }, (err) => {
      console.log(err);
    });

    this.$store.dispatch({
      type: 'fetchJourneys',
    }).then((res) => {
      this.optionJourneys = res.content.map(item=>(
        ({value:item.id, label: item.name})
      ))
      this.optionJourneys.push({value:' ', label:'Tất cả'})
    }, (err) => {
      console.log(err);
    });

    this.$store.dispatch({
      type: 'fetchTransports',
    }).then((res) => {
      this.optionTransports = res.content.map(item=>(
        ({value:item.id, label: item.name})
      ))
      this.optionTransports.push({value:' ', label:'Tất cả'})
//      console.log(this.optionTransports)
    }, (err) => {
      console.log(err);
    });
  },
  async beforeRouteEnter (to, from, next) {
    const tours = await store.dispatch({
      type: 'fetchTours',
    }).then((res) => {
      console.log(res)
    }, (err) => {
      console.log(err);
    });
    console.log(tours)
    await next(vm=>{
      vm.tours = tours
      vm.form.page = 1
    });

  },
  methods:{
    _onFind(){
      const form =  {...this.form}
      form.price =form.price.map(price=>price * 1000000)
      this.$store.dispatch({
        type: 'fetchTours',
        query:{
          ...form
        }
      }).then((res) => {
        console.log(res)
      }, (err) => {
        console.log(err);
      });
    },
    formatter(value){
      let str = ''
      str += `${Math.floor(value)} ngày`
      if(value % 2 === 1)  str += ' 1 đêm'
      return str
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
