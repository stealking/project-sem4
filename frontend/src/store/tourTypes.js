/* eslint-disable */
import request from 'superagent';
// import store from '../index'
// import config from '../../config'

const state = {
  tourTypes: [],
};

const getters = {
  tourTypes: state => ({
    tourTypes: state.tourTypes,
  }),
};


const actions = {
  fetchTourTypes({ commit }, { from, to }) {
    return new Promise((resolve, reject) => {
      request
        .get(`http://localhost:8080/custom-api/tourTypes?page=1&paging=100&sort=desc&column=id`)
        .set('Authorization', `Bearer ${localStorage.getItem('token')}`)
        .then((res) => {
          resolve(res.body);
        }, (err) => {
          reject(err);
        });
    });
  },
};
export default {
  state,
  getters,
  actions,
};
