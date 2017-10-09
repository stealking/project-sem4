<template lang="pug">
div.contain
  Navigator
  div(class="el-row")
    div(class="el-col el-col-24 el-col-xs-24 el-col-sm-6")
      div(class="side-nav")
        ul
          li(class="nav-item")
            a(href="#") Account Info
  _Footer
</template>
<script>
import Navigator from './Navigator';
import Footer from '../_reused/Footer.vue';

export default {
  name: 'Account',
  components: { Navigator, _Footer: Footer },
  data() {
    return {
    }
  }
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
