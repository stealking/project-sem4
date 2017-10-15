<template lang="pug">
v-app#inspire
  div.icon-color.card__text.blue.lighten-1(xs12) Tour đã đặt
  v-container(grid-list-md)
    v-layout(row wrap)
      v-flex.container.item-listtour.mb-4(xs12 v-for="item in toursRegistered.content" :key="item.id")
        v-flex.tour-name.mb-1(xs12)
          h6(v-if="item.saleOff === true") {{item.departure.name}} {{ item.journey.name}} ({{ item.totalTime}} N)   
          h6(v-else) {{item.departure.name}} - {{item.journey.name}} ({{ item.totalTime}} N) (tour tiết kiệm)
        v-container(grid-list-md)
          v-layout(row wrap)
            v-flex.pic(xs12 sm12 md3 lg2)
              a(title="item.journey.name")
                img(:src="getImage()" alt='image' width="90%")
            v-flex.info-tour(xs12 sm12 md9 lg10 style="font-size: 14px")
              v-container(grid-list-md)
                v-layout(row wrap)
                  v-flex(xs12 lg9)
                    v-container(grid-list-md)
                      v-layout(row wrap)
                        v-flex(xs12 lg6)
                          v-icon.mr-2 fa-clock-o 
                          span.orange--text Thời gian: 
                          span {{ item.totalTime }} ngày
                        v-flex(xs12 lg6)  
                          v-icon.mr-2 fa-car
                          span.orange--text Phương tiện: 
                          span {{ item.transport.name}}
                        v-flex(xs12 lg6)  
                          v-icon.mr-2 fa-map-marker
                          span.orange--text Điểm khởi hành: 
                          span {{ item.departure.name}}
                        v-flex(xs12 lg6)  
                          v-icon.mr-2 fa-map-marker
                          span.orange--text Điểm đến: 
                          span {{ item.journey.name}}
                      
                  v-flex.text-xs-center(xs12 lg3 style="border-left: ridge 2px")
                    h6.orange--text {{item.price}} đ
                    v-btn.white--text.orange.darken-1 Chi tiết
              
</template>
<script>

import service from '../Admin/services';
import * as moment from 'moment';

export default {
  
  name: 'Account',
  data() {
    return {
      alert: false,
      pathImage: 'http://localhost:8080/upload/',
      toursRegistered: '',
    }
  },
  watch: {
    value(v) {
      this.fileName = v;
    }
  },
  mounted() {
    moment.locale('vi');
    service.getToursByTourRegister().then((response) => {
      this.toursRegistered = response;
    });
  },
  computed: {
    avatarSize() {
      return `130px`;
    }
  },
  methods: {
    getImage() {
      this.toursRegistered.image= this.toursRegistered.image || '';
      return this.toursRegistered.image == '' ? '/static/no-image.png' : `http://localhost:8080/upload/${this.toursRegistered.image}`;
    },
    convertToDateTime(date) {
      let dateString = moment(date).format('DD-MM-YYYY');
      if (dateString === 'Invalid date') {
        dateString = '';
      }
      return dateString;
    },
    
  }
};
</script>

<style scoped lang="stylus">
.icon-color 
  color: white
.align--center
  text-align: center
.item-listtour
  background: #f4f4f4
.pic a img
  height 110px
.info-tour i
  text-align: center
  width: 26px
  display: inline-block
  vertical-align: top
@media screen and (max-width: 768px)
  .pic a img
    height 270px
@media screen and (min-width: 1024px)
  .input-file-margin
    margin-top: -200px
</style>
