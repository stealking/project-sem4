/* eslint-disable */
import request from 'superagent';
// import store from '../index'
// import config from '../../config'

const state = {
  departures: [],
};

const getters = {
  depatures: state => ({
    departures: state.departures,
  }),
};


const actions = {
  requestWaterHeight({ commit }, { from, to }) {
    return new Promise((resolve, reject) => {
      request
        .get(`http://localhost:8080/custom-api/`)
        .set('Authorization', `Bearer ${localStorage.getItem('token')}`)
        .then((res) => {
          resolve(res.body.reverse());
        }, (err) => {
          reject(err);
        });
    });
  },
  requestWaterHeightLatest({ commit }, {paging}) {
    return new Promise((resolve, reject) => {
      request
        .get(`http://localhost:8080/custom-api/stations/1/stationHistoryWaterHeights?page=1&paging=${paging}&sort=desc&column=updatedOn`)
        .set('Authorization', `Bearer ${localStorage.getItem('token')}`)
        .then((res) => {
          resolve(res.body.reverse());
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
