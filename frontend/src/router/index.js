import Vue from 'vue';
import Router from 'vue-router';
import PageNotFound from '@/components/PageNotFound/index';
import adminRouteConfig from '@/components/Admin/router';
import appRouteConfig from '@/components/App/router';
import VueResource from 'vue-resource';

Vue.use(VueResource);
Vue.use(Router);
//fasdsfdc
export default new Router({
  mode: 'history',
  routes: [
    {
      path: '*',
      component: PageNotFound,
    },
  ]
    .concat(adminRouteConfig)
    .concat(appRouteConfig),
});
