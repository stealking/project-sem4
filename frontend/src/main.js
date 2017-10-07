// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';

import BootstrapVue from 'bootstrap-vue';
import 'element-ui/lib/theme-default/index.css';
import 'vuetify/dist/vuetify.min.css';
import ElementUI from 'element-ui';
import VueMaterial from 'vuetify';
import locale from 'element-ui/lib/locale/lang/en';
import App from './App';
import router from './router';
import addDirectives from './directives';
import store from './store';
// import('./assets/css/style.css');

// import('../node_modules/font-awesome/css/font-awesome.min.css');
// import('bootstrap/dist/css/bootstrap.css');
// import('bootstrap-vue/dist/bootstrap-vue.css');
addDirectives(Vue);
Vue.use(ElementUI, { locale });
// Vue.use(DataTables);
Vue.use(VueMaterial);
Vue.use(BootstrapVue);
// Vue.use(VueQuillEditor);
Vue.config.productionTip = false;
// Vue.material.registerTheme('default', {
//   primary: 'white',
//   accent: 'blue',
//   warn: 'red',
//   background: 'white',
// });
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App },
});
