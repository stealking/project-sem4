/* eslint-disable */
import request from 'superagent';
// import store from '../index'
// import config from '../../config'

const state = {
  departures: [],
};

const getters = {
  departures: state => ({
    departures: state.departures,
  }),
};


const actions = {
  fetchDepartures({ commit }, { from, to }) {
    return new Promise((resolve, reject) => {
      request
        .get(`http://localhost:8080/custom-api/departures?page=1&paging=100&sort=desc&column=id`)
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
