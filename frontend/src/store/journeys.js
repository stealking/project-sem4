/* eslint-disable */
import request from 'superagent';
// import store from '../index'
// import config from '../../config'

const state = {
  journeys: [],
};

const getters = {
  journeys: state => ({
    journeys: state.journeys,
  }),
};


const actions = {
  fetchJourneys({ commit }, { from, to }) {
    return new Promise((resolve, reject) => {
      request
        .get(`http://localhost:8080/custom-api/journeys?page=1&paging=100&sort=desc&column=id`)
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
