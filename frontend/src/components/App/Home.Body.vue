<template lang="pug">
doctype html
div
  section(v-vpshow)
    .design.p1
      .text.bold.s7 Tour giảm giá sốc
      .design.row.fw
        .design.center.p1
          Info(image="https://media.timeout.com/images/101602611/image.jpg")
        .design.center.p1
          Info(image="http://www.bakermckenzie.com/-/media/images/locations/vietnam1.jpg")
        .design.center.p1
          Info(image="https://www.i-to-i.com/wp-content/uploads/2016/03/vietnam-header.jpg")
      .design
         .text.bold.s4.more Xem thêm...
    .design.p1
      .text.bold.s7 Tour hot
      .design.row.fw
        .design.center.p1
          Info(image="https://www.adb.org/sites/default/files/styles/content_media/public/content-media/11576-ha-noi-viet-nam.jpg?itok=Oras3xig")
        .design.center.p1
          Info(image="http://www.hitt-initiative.org/wp/wp-content/uploads/2011/03/vietnam_head_large.jpg")
        .design.center.p1
          Info(image="https://media.timeout.com/images/101602611/image.jpg")
      .design
         .text.bold.s4.more Xem thêm...
    .design.p1
      .text.bold.s7 Tour giảm giá sốc
      .design.row.fw
        .design.center.p1
          Info(image="https://media.timeout.com/images/101602611/image.jpg")
        .design.center.p1
          Info(image="https://media.timeout.com/images/101602611/image.jpg")
        .design.center.p1
          Info
      .design
         .text.bold.s4.more Xem thêm...


</template>
<script>
import _ from 'lodash';
import Navigator from './Navigator';
import Info from '../_reused/Info.vue';

export default {
  name: 'hello',
  components: { Navigator, Info },
  data() {
    return {
    };
  },
  mounted() {

  },
  methods: {
    onclick(){
      alert(1212412)
    }
  },
  beforeDestroy() {
  },
};
</script>

<style scoped lang="stylus">
#carousel-view
  height 100vh
  .fade
    &-enter-active, &-leave-active, &-leave-to
      transition: .3s ease-out
      position: absolute
      top: 0
      left: 0

    &-enter, &-leave, &-leave-to
      opacity: 0
.before-enter {
  opacity: 0;
}

.enter {
  transition: opacity 2s ease;
}
.wrapper
  width 100%
.flex
  padding-top 0px!important
  padding-bottom 0px!important
i.icon
  font-size 30px!important
  padding-top 18px!important
.select-layout
  margin: 0px!important;
  .flex
    padding 0px!important
.search-button
  margin-top 20px!important
  margin-bottom 10px!important
  width 100%
.more:hover
  color red

</style>
