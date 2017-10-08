import Home from '@/components/App/Home';
import Tour from '@/components/App/Tour';
import Tours from '@/components/App/Tours';
import TourDetail from '@/components/App/TourDetail';

export default [
  {
    path: '/',
    name: Home,
    component: Home,
    // redirect: 'tour'
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
