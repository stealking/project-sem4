import axios from 'axios';
// import auth from '../../auth';
// import router from '../../router';

const url = 'http://localhost:8080';

export default {
  changePage(page, type, paging, sort, column, field, value) {
    let link = '';
    if (column === undefined || column === null) column = '';
    if ((field !== undefined || field !== null || field !== '')) {
      link = `${url}/custom-api/${type}?${field}=${value}&page=${page}&paging=${paging}&sort=${sort}&column=${column}`;
    } else {
      link = `${url}/custom-api/${type}?page=${page}&paging=${paging}&sort=${sort}&column=${column}`;
    }
    console.log(link);
    return fetch(`${link}`, {
      method: 'get',
      headers: {
        'Content-type': 'application/x-www-form-urlencoded; charset=UTF-8',
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then((response) => {
      if (response.status !== 200) {
        console.log(`Looks like there was a problem. Status Code: ${response.status}`);
        throw response.status;
      }
      // Examine the text in the response
      return response.json();
    },
    ).catch((err) => {
      console.log('Fetch Error :-S', err);
    });
  },

  // user services
  findAllUser() {
    return fetch(`${url}/custom-api/users?page=1&paging=10&sort=asc&column=id`, {
      method: 'get',
      headers: {
        'Content-type': 'application/x-www-form-urlencoded; charset=UTF-8',
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then((response) => {
      if (response.status !== 200) {
        console.log(`Looks like there was a problem. Status Code: ${response.status}`);
        throw response.status;
      }
      // Examine the text in the response
      return response.json();
    },
    ).catch((err) => {
      console.log('Fetch Error :-S', err);
    });
  },

  getUserById(id) {
    return fetch(`${url}/custom-api/users/${id}`, {
      method: 'get',
      headers: {
        'Content-type': 'application/x-www-form-urlencoded; charset=UTF-8',
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then((response) => {
      if (response.status !== 200) {
        console.log(`Looks like there was a problem. Status Code: ${response.status}`);
        throw response.status;
      }
      return response.json();
    }).catch((err) => {
      console.log('Fetch Error :-S', err);
    });
  },

  getUserInfo() {
    return axios({
      method: 'get',
      url: `${url}/custom-api/user`,
      headers: {
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then((response) => {
      return response.data;
    });
  },

  updateUser(profileUser, image) {
    const user = JSON.stringify(profileUser);
    const formData = new FormData();
    formData.append('file', image);
    formData.append('content', user);
    return axios({
      method: 'patch',
      url: `${url}/custom-api/users`,
      data: formData,
      headers: {
        'content-type': 'multipart/form-data',
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then(response => response);
  },

  deleteUser(id) {
    return axios({
      method: 'delete',
      url: `${url}/custom-api/users/${id}`,
      headers: {
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then(response => response);
  },

  createUser(user) {
    const newUser = JSON.stringify(user);
    const formData = new FormData();
    formData.append('content', newUser);
    return axios({
      method: 'post',
      url: `${url}/custom-api/users`,
      data: formData,
      headers: {
        'content-type': 'multipart/form-data',
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then(response => response).catch((err) => {
      console.log(err.response);
    });;
  },

  register(user) {
    const newUser = JSON.stringify(user);
    const formData = new FormData();
    formData.append('content', newUser);
    return axios({
      method: 'post',
      url: `${url}/registration`,
      data: formData,
      headers: {
        'content-type': 'application/json',
      },
    }).then(response => response).catch((err) => {
      console.log(err);
      return (err.response);
    });
  },

  forgetPassword(email) {
    return axios({
      method: 'post',
      url: `${url}/forget-password?email=${email}`,
    }).then(response => response).catch((err) => {
      console.log(err);
      return (err.response);
    });
  },

  changePassword(passwordConfirm) {
    var content = JSON.stringify(passwordConfirm);
    return axios({
      method: 'patch',
      url: `${url}/custom-api/users/change-password`,
      data: content,
      headers: {
        'content-type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then((response) => {
      return response;
    }).catch((err) => {
      return err.response;
    });
  },

  resetPassword(user, token) {
    const content = JSON.stringify(user);
    const formData = new FormData();
    formData.append('content', content);
    return axios({
      method: 'patch',
      url: `${url}/custom-api/users/reset-password`,
      data: formData,
      headers: {
        'content-type': 'application/json',
        Authorization: `Bearer ${token}`,
      },
    }).then(response => response);
  },

  checkEmail(email) {
    return axios({
      method: 'post',
      url: `${url}/check-email`,
      data: { email },
    }).then((response) => {
      if (response.data === null || response.data === undefined) {
        return false;
      }
      return true;
    }).catch((err) => {
      console.log(err.response);
    });
  },
  checkUsername(username) {
    return axios({
      method: 'post',
      url: `${url}/check-username`,
      data: { username },
    }).then((response) => {
      if (response.data === null || response.data === undefined) {
        return false;
      }
      return true;
    }).catch((err) => {
      console.log(err.response);
    });
  },

  findByEmail(email) {
    return axios({
      method: 'get',
      url: `${url}/custom-api/users/findByEmail?email=${email}&page=1&paging=10&sort=asc&column=id`,
      headers: {
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then((response) => {
      if (response.status !== 200) {
        return false;
      }
      return response.data;
    });
  },

  findByUsername(username) {
    return axios({
      method: 'get',
      url: `${url}/custom-api/users/findByUsername?username=${username}&page=1&paging=10&sort=asc&column=id`,
      headers: {
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then((response) => {
      if (response.status !== 200) {
        return false;
      }
      return response.data;
    });
  },
  findByAddress(address) {
    return axios({
      method: 'get',
      url: `${url}/custom-api/users/findByAddress?address=${address}&page=1&paging=10&sort=asc&column=id`,
      headers: {
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then((response) => {
      if (response.status !== 200) {
        return false;
      }
      return response.data;
    });
  },
  findByPhone(phone) {
    return axios({
      method: 'get',
      url: `${url}/custom-api/users/findByPhone?phone=${phone}&page=1&paging=10&sort=asc&column=id`,
      headers: {
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then((response) => {
      if (response.status !== 200) {
        return false;
      }
      return response.data;
    });
  },
  //end user services

  //tour serivces
  getAllTour() {
    return fetch(`${url}/custom-api/tours?page=1&paging=10&sort=asc&column=id`, {
      method: 'get',
      headers: {
        'Content-type': 'application/x-www-form-urlencoded; charset=UTF-8',
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then((response) => {
      if (response.status !== 200) {
        console.log(`Looks like there was a problem. Status Code: ${response.status}`);
        throw response.status;
      }
      // Examine the text in the response
      return response.json();
    },
    ).catch((err) => {
      console.log('Fetch Error :-S', err);
    });
  },
  getTourById(id) {
    return fetch(`${url}/custom-api/tours/${id}`, {
      method: 'get',
      headers: {
        'Content-type': 'application/x-www-form-urlencoded; charset=UTF-8',
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then((response) => {
      if (response.status !== 200) {
        console.log(`Looks like there was a problem. Status Code: ${response.status}`);
        throw response.status;
      }
      return response.json();
    }).catch((err) => {
      console.log('Fetch Error :-S', err);
    });
  },

  updateTour(tour, image) {
    const newTour = JSON.stringify(tour);
    const formData = new FormData();
    formData.append('file', image);
    formData.append('content', newTour);
    return axios({
      method: 'patch',
      url: `${url}/custom-api/tours`,
      data: formData,
      headers: {
        'content-type': 'multipart/form-data',
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then(response => response);
  },

  createTour(tour, image) {
    const newTour = JSON.stringify(tour);
    const formData = new FormData();
    formData.append('content', newTour);
    formData.append('file', image);
    return axios({
      method: 'post',
      url: `${url}/custom-api/tours`,
      data: formData,
      headers: {
        'content-type': 'multipart/form-data',
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then(response => response);
  },

  deleteTour(id) {
    return axios({
      method: 'delete',
      url: `${url}/custom-api/tours/${id}`,
      headers: {
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then(response => response);
  },

  findTourByDepPointAndDes(depPoint, des, page, paging, sort, column) {
    return axios({
      method: 'get',
      url: `${url}/custom-api/tours/findTourByDepPointAndDes?from=${depPoint}&to=${des}&page=${page}&paging=${paging}&sort=${sort}&column=${column}`,
      headers: {
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then((response) => {
      if (response.status !== 200) {
        return false;
      }
      return response.data;
    });
  },
  findTour(dep, journey, tourTypeId, page, paging, sort, column) {
    column = column == null ? 'id' : column;
    let link = `${url}/custom-api/tours?page=${page}&paging=${paging}&sort=${sort}&column=${column}&departureName=${dep}&journeyName=${journey}`;
    if(tourTypeId !== '0') {
      link += `&tourTypeId=${tourTypeId}`;
    }
    return axios({
      method: 'get',
      url: `${link}`,
      headers: {
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then((response) => {
      if (response.status !== 200) {
        return false;
      }
      return response.data;
    });
  },

  getToursByTourRegister(){
    return axios({
      method: 'get',
      url: `${url}/custom-api/tours-by-tourRegisters?page=1&paging=1000&sort=desc&column=id`,
      headers: {
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then((response) => {
      return response.data;
    }).catch((err) => {
      console.log(err.response);
    });
  },
  //end tour services

  //tour detail services
  getTourDetailsById(id) {
    return fetch(`${url}/custom-api/tourDetails/${id}`, {
      method: 'get',
      headers: {
        'Content-type': 'application/x-www-form-urlencoded; charset=UTF-8',
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then((response) => {
      if (response.status !== 200) {
        console.log(`Looks like there was a problem. Status Code: ${response.status}`);
        throw response.status;
      }
      return response.json();
    }).catch((err) => {
      console.log('Fetch Error :-S', err);
    });
  },

  getTourDetailsByTourId(id) {
    return fetch(`${url}/custom-api/tourDetails-by-tours?tour_id=${id}`, {
      method: 'get',
      headers: {
        'Content-type': 'application/x-www-form-urlencoded; charset=UTF-8',
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then((response) => {
      if (response.status !== 200) {
        console.log(`Looks like there was a problem. Status Code: ${response.status}`);
        throw response.status;
      }
      return response.json();
    }).catch((err) => {
      console.log('Fetch Error :-S', err);
    });
  },
  createTourDetails(tourDetails) {
    const newTourDetail = JSON.stringify(tourDetails);
    return axios({
      method: 'post',
      url: `${url}/custom-api/tourDetails`,
      data: newTourDetail,
      headers: {
        'content-type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then(response => response);
  },
  updateTourDetails(tourDetails) {
    const newTour = JSON.stringify(tourDetails);
    return axios({
      method: 'patch',
      url: `${url}/custom-api/tourDetails`,
      data: newTour,
      headers: {
        'content-type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then(response => response);
  },
  deleteTourDetails(id) {
    return axios({
      method: 'delete',
      url: `${url}/custom-api/tourDetails/${id}`,
      headers: {
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then(response => response);
  },

  changePageTourDetails(page, tourid, paging, sort, column) {
    column = column || "";
    return fetch(`${url}/custom-api/tourDetails-by-tours?tour_id=${tourid}&page=${page}&paging=${paging}&sort=${sort}&column=${column}`, {
      method: 'get',
      headers: {
        'Content-type': 'application/x-www-form-urlencoded; charset=UTF-8',
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then((response) => {
      if (response.status !== 200) {
        console.log(`Looks like there was a problem. Status Code: ${response.status}`);
        throw response.status;
      }
      // Examine the text in the response
      return response.json();
    },
    ).catch((err) => {
      console.log('Fetch Error :-S', err);
    });
  },

  //end tour detail services

  //Voucher services
  getAllVoucher() {
    return fetch(`${url}/custom-api/vouchers?page=1&paging=10&sort=asc&column=id`, {
      method: 'get',
      headers: {
        'Content-type': 'application/x-www-form-urlencoded; charset=UTF-8',
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then((response) => {
      if (response.status !== 200) {
        console.log(`Looks like there was a problem. Status Code: ${response.status}`);
        throw response.status;
      }
      // Examine the text in the response
      return response.json();
    },
    ).catch((err) => {
      console.log('Fetch Error :-S', err);
    });
  },
  findAllVouchers() {
    return fetch(`${url}/custom-api/getAllVouchers`, {
      method: 'get',
      headers: {
        'Content-type': 'application/x-www-form-urlencoded; charset=UTF-8',
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then((response) => {
      if (response.status !== 200) {
        console.log(`Looks like there was a problem. Status Code: ${response.status}`);
        throw response.status;
      }
      // Examine the text in the response
      return response.json();
    },
    ).catch((err) => {
      console.log('Fetch Error :-S', err);
    });
  },
  getVoucherById(id) {
    return fetch(`${url}/custom-api/vouchers/${id}`, {
      method: 'get',
      headers: {
        'Content-type': 'application/x-www-form-urlencoded; charset=UTF-8',
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then((response) => {
      if (response.status !== 200) {
        console.log(`Looks like there was a problem. Status Code: ${response.status}`);
        throw response.status;
      }
      return response.json();
    }).catch((err) => {
      console.log('Fetch Error :-S', err);
    });
  },
  updateVoucher(voucher, image) {
    const newVoucher = JSON.stringify(voucher);
    const formData = new FormData();
    formData.append('file', image);
    formData.append('content', newVoucher);
    return axios({
      method: 'patch',
      url: `${url}/custom-api/vouchers`,
      data: formData,
      headers: {
        'content-type': 'multipart/form-data',
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then(response => response);
  },
  createVoucher(voucher, image) {
    const newVoucher = JSON.stringify(voucher);
    const formData = new FormData();
    formData.append('content', newVoucher);
    formData.append('file', image);
    return axios({
      method: 'post',
      url: `${url}/custom-api/vouchers`,
      data: formData,
      headers: {
        'content-type': 'multipart/form-data',
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then(response => response);
  },

  deleteVoucher(id) {
    return axios({
      method: 'delete',
      url: `${url}/custom-api/vouchers/${id}`,
      headers: {
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then(response => response);
  },
  searchVoucher(search) {
    return fetch(`${url}/custom-api/vouchers/findVoucher?search=${search}&page=1&paging=10&sort=asc&column=id`, {
      method: 'get',
      headers: {
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then((response) => {
      if (response.status !== 200) {
        console.log(`Looks like there was a problem. Status Code: ${response.status}`);
        throw response.status;
      }
      // Examine the text in the response
      return response.json();
    },
    ).catch((err) => {
      console.log('Fetch Error :-S', err);
    });
  },
  //End voucher services

  //transport serivces
  findAllTransport() {
    return fetch(`${url}/custom-api/get-all-transports`, {
      method: 'get',
      headers: {
        'Content-type': 'application/x-www-form-urlencoded; charset=UTF-8',
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then((response) => {
      if (response.status !== 200) {
        console.log(`Looks like there was a problem. Status Code: ${response.status}`);
        throw response.status;
      }
      // Examine the text in the response
      return response.json();
    },
    ).catch((err) => {
      console.log('Fetch Error :-S', err);
    });
  },
  //End transport services

  //Journey serivces
  findAllJourney() {
    return fetch(`${url}/custom-api/get-all-journeys`, {
      method: 'get',
      headers: {
        'Content-type': 'application/x-www-form-urlencoded; charset=UTF-8',
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then((response) => {
      if (response.status !== 200) {
        console.log(`Looks like there was a problem. Status Code: ${response.status}`);
        throw response.status;
      }
      // Examine the text in the response
      return response.json();
    },
    ).catch((err) => {
      console.log('Fetch Error :-S', err);
    });
  },
  //End Journey services

  //departures serivces
  findAllDeparture() {
    return fetch(`${url}/custom-api/get-all-departures`, {
      method: 'get',
      headers: {
        'Content-type': 'application/x-www-form-urlencoded; charset=UTF-8',
        Authorization: `Bearer ${localStorage.getItem('access_token')}`,
      },
    }).then((response) => {
      if (response.status !== 200) {
        console.log(`Looks like there was a problem. Status Code: ${response.status}`);
        throw response.status;
      }
      // Examine the text in the response
      return response.json();
    },
    ).catch((err) => {
      console.log('Fetch Error :-S', err);
    });
  },
  //End departures services

};
