// import Vue from 'vue';
import axios from 'axios';
import router from '../router';

// const API_URL = 'http://localhost:8080/';
// const LOGIN_URL = 'http://localhost:8080/api/auth';

export default {
  user: {
    authenticated: false,
  },

  login(creds, redirect, header) {
    // const cred = JSON.parse(creds);
    // console.log(creds);
    return axios({
      method: 'post',
      url: 'http://localhost:8080/auth',
      data: {
        username: creds.username,
        password: creds.password,
      },
      headers: {
        Authorization: header,
      },
    })
    .then((response) => {
      localStorage.setItem('access_token', response.data.token);
      this.user.authenticated = true;
      // rediect to specified route
      if (redirect) {
        router.push({ name: redirect });
      }
      return response;
    }).catch(e => e);
  },
  logout() {
    localStorage.removeItem('access_token');
    this.user.authenticated = false;
    router.push({ name: 'Login' });
  },
  checkAuth() {
    const jwt = localStorage.getItem('access_token');

    if (jwt) {
      this.user.authenticated = true;
    } else {
      this.user.authenticated = false;
    }
  },

  setAuthHeader(request) {
    request.headers.set('Authorization', `Bearer ${localStorage.getItem('access_token')}`);
  },

  // The object to be passed as a header for authenticated requests
  getAuthHeader() {
    return {
      Authorization: `Bearer ${localStorage.getItem('access_token')}`,
    };
  },
};

