import Home from '@/components/App/Home';
import Account from '@/components/App/Account';
import Tour from '@/components/App/Tour';
import Tours from '@/components/App/Tours';
import TourDetail from '@/components/App/TourDetail';

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
