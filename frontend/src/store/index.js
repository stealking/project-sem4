import Vue from 'vue';
import Vuex from 'vuex';
import tourDetail from './tourDetail'
import departures from './departures'
import tourTypes from './tourTypes'
import journeys from './journeys'
import transports from './transports'
import tours from './tours'
Vue.use(Vuex);
//
export default new Vuex.Store({
  modules: {
    tourDetail,
    departures,
    tourTypes,
    journeys,
    transports,
    tours,
  },
});
