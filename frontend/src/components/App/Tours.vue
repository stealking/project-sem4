<template lang="pug">
doctype html
.design
  Navigator
  .design.f1.space-top
    .rowStart.f1.pl1
      .s6.bold Tiêu chí lựa chọn
    .design.pl2.pr3
      el-form(ref='form', :model='form'  label-width="120px")
        el-form-item(label='Loại hình du lịch').mt1
          el-select(v-model='form.tourType', filterable='', placeholder='Select')
            el-option(v-for='item in optionTourTypes', :key='item.value.id', :label='item.label', :value='item.value')

        el-form-item(label='Phương tiện').mt1
          el-select(v-model='form.transport', filterable='', placeholder='Select')
            el-option(v-for='item in optionTransports', :key='item.value.id', :label='item.label', :value='item.value')

        el-form-item(label='Khuyến mãi').mt1
          el-select(v-model='form.time', filterable='', placeholder='Select')
            el-option(v-for='item in optionSaleOffs', :key='item.value.id', :label='item.label', :value='item.value')

        el-form-item(label='Nơi xuất phát').mt1
         el-select(v-model='form.departure', filterable='', placeholder='Select')
            el-option(v-for='item in optionDepartures', :key='item.value.id', :label='item.label', :value='item.value')

        el-form-item(label='Nơi xuất phát').mt1
         el-select(v-model='form.journey', filterable='', placeholder='Select')
            el-option(v-for='item in optionJourneys', :key='item.value.id', :label='item.label', :value='item.value')

        el-form-item(label='Giá tiền').mt1
          .pt1
            vue-slider(v-model="priceRange" width="100%" :height="height" :dotSize="dotSize" :min="min" :max="max" disabled=false show=true tooltip="always" formatter="{value}.000.000VND" :bgStyle="bgStyle" :tooltipStyle="tooltipStyle" :processStyle="processStyle" :tooltipDir="tooltipDir")

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
import vueSlider from 'vue-slider-component';

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
      priceRange:[
        0,
        20
      ],
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
      height: 8,
      dotSize: 16,
      tooltipDir: [
        "bottom",
        "bottom"
      ],
      form: {
        departure: {},
        tourType: {},
        journey: {},
        transport: {},
        time: {},
      }
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

  }
};
</script>

<style scoped lang="stylus">
.space-top
  margin-top 4rem
  @media(max-width: 600px)
    margin-top 3rem
</style>
