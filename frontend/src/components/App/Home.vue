<template lang="pug">
doctype html
div.contain(style="max-width: 100%")
  Navigator
  main
    section
      el-carousel.carousel-layout(:interval='3000', arrow='never' indicator-position='none' height="100vh")
        el-carousel-item(v-for='item in items', :key='item.src')
          img.carousel-img(:src="item.src")
    section.wrapper
      v-container(grid-list-xl)
        v-tabs.tabs-layout(dark, v-model='active'   :scrollable="false")
          v-tabs-bar.tabs-bar-layout
            v-tabs-item(v-for='tab in tabs' :key='tab' :href="'#' + tab" ripple)
              | {{tab}}
            v-tabs-slider.orange
          v-tabs-items
            v-tabs-content.tab-content-wrapper(v-for='tab in tabs', :key='tab', :id='tab')
              v-layout(row wrap align-center light)
                v-flex(xs12 sm3 light)
                  v-layout.select-layout
                    v-flex(xs2)
                      v-icon.white--text people
                    v-flex(xs10)
                      v-select(dark v-bind:items='states', v-model='e1', label='Loại tour', single-line, auto, hide-details)
                v-flex(xs12 sm3 light)
                  v-layout.select-layout
                    v-flex(xs2)
                      v-icon.white--text fa-map-marker
                    v-flex(xs10)
                      v-select(dark v-bind:items='states', v-model='e1', label='Điểm đến', single-line, auto, hide-details)
                v-flex(xs12 sm3 light)
                  v-layout.select-layout
                    v-flex(xs2)
                      v-icon.white--text fa-calendar
                    v-flex(xs10)
                      v-select(dark v-bind:items='states', v-model='e1', label='Thời gian', single-line, auto, hide-details)
                v-flex(xs12 sm3 light)
                  v-btn.select-layout.search-button.white--text.blue.darken-1(large) Tìm kiếm

    HomeBody
  _Footer
</template>
<script>
import _ from 'lodash';
import Navigator from './Navigator';
import HomeBody from './Home.Body';
import Info from '../_reused/Info.vue';
import Footer from '../_reused/Footer.vue';

export default {
  name: 'hello',
  components: { Navigator, HomeBody, Info, _Footer: Footer },
  data() {
    return {
      e1: null,
      title: 'Your Logo',
      items: [
        {
          src: '/static/1.jpg',
        },
        {
          src: '/static/1.jpg',
        },
        {
          src: '/static/1.jpg',
        },
        {
          src: '/static/1.jpg',
        },
      ],
      states: [
        'Alabama', 'Alaska', 'American Samoa', 'Arizona',
      ],
      tabs: ['TÌM TOUR', 'TÌM KHÁCH SẠN', 'TÌM VÉ'],
      active: null,
      text: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
    };
  },
  mounted() {
    this.$nextTick(() => {
      window.addEventListener('resize', this.getWindowWidthDebounce);
      this.getWindowWidthDebounce();
    });
  },
  methods: {
    getWindowWidthDebounce: _.debounce(function getWindowWidth(event) {
      this.windowWidth = document.documentElement.clientWidth;
    }, 300),
  },
  beforeDestroy() {
    window.removeEventListener('resize', this.getWindowWidthDebounce);
  },
};
</script>

<style scoped lang="stylus">
/*#carousel-view*/
  /*height 100vh*/
  /*.fade*/
    /*&-enter-active, &-leave-active, &-leave-to*/
      /*transition: .3s ease-out*/
      /*position: absolute*/
      /*top: 0*/
      /*left: 0*/

    /*&-enter, &-leave, &-leave-to*/
      /*opacity: 0*/
.carousel-layout
  height 100vh
  z-index 0!important
.before-enter {
  opacity: 0;
}
.enter {
  transition: opacity 2s ease;
}
.wrapper
  width 100%
  position absolute
  top 35%
  background-color rgba(0,0,0,0)
  @media(max-width: 960px)
    top 25%
@media(max-width: 600px)
  .flex
    padding-top 0px!important
    padding-bottom 0px!important
i.icon
  font-size 30px!important
  padding-top 18px!important
.select-layout
  margin: 0px!important;
  /*@media(min-width: 960px)*/
    /*.flex*/
      /*padding 0px!important*/
.search-button
  margin-top 20px!important
  margin-bottom 10px!important
  width 100%
.tabs__bar
  background-color rgba(0,0,0,0.3)
.tab-content-wrapper
  background-color rgba(0,0,0,0.3)
.tabs__wrapper
  overflow-x inherit!important
.tabs__container
  margin-bottom 0px!important
.carousel-img
  filter brightness(90%)
  height 100%
  @media(min-width 1024px)
    width 100%
.tabs-layout
  border-radius 5px
.tabs-bar-layout
  border-top-right-radius 5px
  border-top-left-radius 5px

@media only screen and (min-width: 600px)
  .container
    max-width 100%
</style>
