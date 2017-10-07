import Vue from 'vue';
import Vuex from 'vuex';
import tourDetail from './tourDetail'
Vue.use(Vuex);
//
export default new Vuex.Store({
  modules: {
    tourDetail
  },
});
