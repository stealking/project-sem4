<template lang="pug">
doctype html
div
  section(v-vpshow)
    v-layout.my-5(column wrap align-center)
      v-flex.my-3(xs12 sm4)
        .text-xs-center
          h2.headline The best way to start developing
          span.subheading
            | Cras facilisis mi vitae nunc
      v-flex(xs12)
        v-container(grid-list-xl)
          v-layout(row wrap align-center)
            v-flex(xs12 md4)
              v-card.elevation-0.transparent
                v-card-text.text-xs-center
                  v-icon.blue--text.text--lighten-2(x-large) color_lens
                v-card-title.layout.justify-center(primary-title)
                  .headline.text-xs-center Material Design
                v-card-text
                  | Cras facilisis mi vitae nunc lobortis pharetra. Nulla volutpat tincidunt ornare.
                  | Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.
                  | Nullam in aliquet odio. Aliquam eu est vitae tellus bibendum tincidunt. Suspendisse potenti.
            v-flex(xs12 md4)
              v-card.elevation-0.transparent
                v-card-text.text-xs-center
                  v-icon.blue--text.text--lighten-2(x-large) flash_on
                v-card-title.layout.justify-center(primary-title)
                  .headline Fast development
                v-card-text
                  | Cras facilisis mi vitae nunc lobortis pharetra. Nulla volutpat tincidunt ornare.
                  | Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.
                  | Nullam in aliquet odio. Aliquam eu est vitae tellus bibendum tincidunt. Suspendisse potenti.

            v-flex(xs12 md4)
              v-card.elevation-0.transparent
                v-card-text.text-xs-center
                  v-icon.blue--text.text--lighten-2(x-large) build
                v-card-title.layout.justify-center(primary-title)
                  .headline.text-xs-center Completely Open Sourced
                v-card-text
                  | Cras facilisis mi vitae nunc lobortis pharetra. Nulla volutpat tincidunt ornare.
                  | Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.
                  | Nullam in aliquet odio. Aliquam eu est vitae tellus bibendum tincidunt. Suspendisse potenti.

  section(v-vpshow)
    v-parallax(src='', height='380')
      v-layout(column align-center justify-center)
        .headline.white--text.mb-3 Web development has never been easier
        em Kick-start your application today
        v-btn.blue.lighten-2.mt-5(dark large href='/pre-made-themes')
          | Get Started

  section(v-vpshow)
    v-container(grid-list-xl)
      v-layout.my-5(row wrap justify-center)
        v-flex(xs12 sm4)
          v-card.elevation-0.transparent
            v-card-title.layout.justify-center(primary-title)
              .headline Company info
            v-card-text
              | Cras facilisis mi vitae nunc lobortis pharetra. Nulla volutpat tincidunt ornare.
              | Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.
              | Nullam in aliquet odio. Aliquam eu est vitae tellus bibendum tincidunt. Suspendisse potenti.
        v-flex(xs12 sm4 offset-sm1)
          v-card.elevation-0.transparent
            v-card-title.layout.justify-center(primary-title)
              .headline Contact us
            v-card-text
              | Cras facilisis mi vitae nunc lobortis pharetra. Nulla volutpat tincidunt ornare.
            v-list.transparent
              v-list-tile
                v-list-tile-action
                  v-icon.blue--text.text--lighten-2 phone
                v-list-tile-content
                  v-list-tile-title 777-867-5309
              v-list-tile
                v-list-tile-action
                  v-icon.blue--text.text--lighten-2 place
                v-list-tile-content
                  v-list-tile-title Chicago, US
              v-list-tile
                v-list-tile-action
                  v-icon.blue--text.text--lighten-2 email
                v-list-tile-content
                  v-list-tile-title john@vuetifyjs.com
  |
  v-footer.blue.darken-2(v-vpshow)
    v-layout(row wrap align-center)
      v-flex(xs12)
        .white--text.ml-3
          | Made with
          v-icon.red--text favorite
          |             by
          a.white--text(href='https://vuetifyjs.com', target='_blank') Vuetify
          |             and
          a.white--text(href='mailto:costa.huang@outlook.com') Costa Huang
</template>
<script>
import _ from 'lodash';
import Navigator from './Navigator';

export default {
  name: 'hello',
  components: { Navigator },
  data() {
    return {
    };
  },
  mounted() {
  },
  methods: {
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
</style>
