
import Admin from '@/components/Admin/index';
import User from '@/components/Admin/User';
import Tour from '@/components/Admin/Tour/Tour';
import NewUser from '@/components/Admin/User/NewUser';
import Edit from '@/components/Admin/User/Edit';
import EditTour from '@/components/Admin/Tour/EditTour';
import NewTour from '@/components/Admin/Tour/NewTour';
import EditTourDetail from '@/components/Admin/Tour-Details/EditTourDetail';
import NewTourDetail from '@/components/Admin/Tour-Details/NewTourDetail';
import Voucher from '@/components/Admin/Voucher/Voucher';
import EditVoucher from '@/components/Admin/Voucher/EditVoucher';
import NewVoucher from '@/components/Admin/Voucher/NewVoucher';
import Pages from '@/components/Admin/Login/index';
import Register from '@/components/Admin/Login/register';
import Login from '@/components/Admin/Login/login';
import Profile from '@/components/Admin/User/Profile';
import ChangePassword from '@/components/Admin/User/ChangePassword';
import auth from '../../auth';
// import router from '../../router';

function guardRoute(to, from, next) {
  auth.checkAuth();
  if (!auth.user.authenticated) {
    next({
      path: '/login',
      query: { redirect: to.path },
    });
  } else {
    next();
  }
}
export default [
  {
    path: '/admin',
    component: Admin,
    beforeEnter: guardRoute,
  },
  {
    path: '/pages',
    name: 'Pages',
    component: Pages,
    beforeEnter: (to, from, next) => {
      auth.checkAuth();
      if (auth.user.authenticated) {
        next({
          path: '/admin',
          query: { redirect: to.path },
        });
      } else {
        next();
      }
    },
    children: [
      {
        path: '/pages/register',
        name: 'Register',
        component: Register,
      },
      {
        path: '/pages/login',
        name: 'Login',
        component: Login,
      },
    ],
  },
  {
    path: '/admin',
    name: 'Admin',
    component: Admin,
    beforeEnter: guardRoute,
    children: [
      {
        path: '/admin/user',
        name: 'User',
        component: User,
      },
      {
        path: '/admin/newuser',
        name: 'NewUser',
        component: NewUser,
      },
      {
        path: '/admin/edituser/:id',
        name: 'Edit',
        component: Edit,
      },
      {
        path: '/admin/profile',
        name: 'Profile',
        component: Profile,
      },
      {
        path: '/admin/changepassword',
        name: 'ChangePassword',
        component: ChangePassword,
      },
      {
        path: '/admin/tour',
        name: 'Tour',
        component: Tour,
      },
      {
        path: '/admin/edittour/:id',
        name: 'EditTour',
        component: EditTour,
      },
      {
        path: '/admin/newtour',
        name: 'NewTour',
        component: NewTour,
      },
      {
        path: '/admin/edit-tour-detail/:id/:tourId',
        name: 'EditTourDetail',
        component: EditTourDetail, 
      },
      {
        path: '/admin/new-tour-detail/:tour_id',
        name: 'NewTourDetail',
        component: NewTourDetail,
      },
      {
        path: '/admin/voucher',
        name: 'Voucher',
        component: Voucher,
      },
      {
        path: '/admin/edit-voucher/:id',
        name: 'EditVoucher',
        component: EditVoucher,
      },
      {
        path: '/admin/new-voucher/',
        name: 'NewVoucher',
        component: NewVoucher,
      },
    ],
  },
];
