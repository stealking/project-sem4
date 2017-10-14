/* eslint-disable */
import request from 'superagent';
// import store from '../index'
// import config from '../../config'

const state = {
  tours: [],
};

const getters = {
  tours: state => ({
    tours: state.tours,
  }),
};


const actions = {
  fetchTours({ commit }, { query }) {
    let queryStr = ''
    Object.keys(query).forEach(function(key,index) {
      if(typeof query[key] === "number" || query[key].indexOf(" ") === -1 && query[key].length > 0)queryStr+= `&${key}=${query[key]}`
    });
    console.log(queryStr);
    return new Promise((resolve, reject) => {
      request
        .get(`http://localhost:8080/custom-api/tours?page=1&paging=100&sort=desc&column=id${queryStr}`)
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
