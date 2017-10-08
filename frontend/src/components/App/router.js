import Home from '@/components/App/Home';
import Tour from '@/components/App/Tour';
import TourDetail from '@/components/App/TourDetail';

export default [
  {
    path: '/',
    name: Home,
    component: Home,
    // redirect: 'tour'
  }, {
    path: '/tour',
    component: Tour,
  },{
    path: '/tour/:tourId',
    component: TourDetail,
  },
];
