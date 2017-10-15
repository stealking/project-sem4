import Home from '@/components/App/Home';
import Account from '@/components/App/Account';
import AccountInfo from '@/components/App/Account.Info';
import AccountChangePassword from '@/components/App/Account.ChangePassword';
import ToursRegistered from '@/components/App/Account.ToursRegistered';
import Tour from '@/components/App/Tour';
import Tours from '@/components/App/Tours';
import TourDetail from '@/components/App/TourDetail';

import auth from '../../auth/index'

function guardRoute(to, from, next) {
  auth.checkAuth();
  if (!auth.user.authenticated) {
    next({
      path: '/pages/login',
      query: { redirect: to.path },
    });
  } else {
    next();
  }
}
export default [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/account',
    name: 'Account',
    component: Account,
    beforeEnter: guardRoute,
    children: [
      {
        path: '/account/info',
        name: 'AccountInfo',
        component: AccountInfo,
        beforeEnter: guardRoute,
      },
      {
        path: '/account/change-password',
        name: 'AccountChangePassword',
        component: AccountChangePassword,
        beforeEnter: guardRoute,
      },
      {
        path: '/account/tours-registered',
        name: 'ToursRegistered',
        component: ToursRegistered,
      }
    ]
  }, {
    path: '/tours',
    component: Tours,
  },{
    path: '/tours/:tourId',
    component: Tour,
  },{
    path: '/tours/:tourId/tour-details/:tourDetailId',
    component: TourDetail,
  },
];
