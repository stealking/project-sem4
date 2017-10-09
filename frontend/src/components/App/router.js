import Home from '@/components/App/Home';
import Account from '@/components/App/Account';
import Tour from '@/components/App/Tour';
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
    path: '/tour',
    component: Tour,
  },{
    path: '/tour/:tourId',
    component: TourDetail,
  },
];
