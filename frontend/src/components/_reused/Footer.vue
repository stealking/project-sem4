<template lang="pug">
doctype html
div
  section(v-vpshow).company
    v-container(grid-list-xl)
      v-layout(row wrap justify-center)
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
import Info from '../_reused/Info.vue';

export default {
};
</script>

<style scoped lang="stylus">
.company
  border-top solid 1px #ff7609

</style>
