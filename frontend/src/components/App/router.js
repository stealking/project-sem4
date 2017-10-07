import Home from '@/components/App/Home';
import Tour from '@/components/App/Tour';

export default [
  {
    path: '/',
    component: Home,
    // redirect: 'tour'
  },
  {
    path: '/tour',
    component: Tour,
  },
];
