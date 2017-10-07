import request from 'superagent';
// import config from '../../config'

const state = {
  tourDetail: {},
};
const getters = {
  tourDetail: state => () => state,
};
const mutations = {
  setTourDetail(state, { tourDetail }) {
    state = tourDetail;
  },
};


const actions = {
  fetchTourDetail({ commit }, {id}) {
    return new Promise((resolve, reject) => {
      request
        .get(`http://localhost:8080/custom-api/tourDetails/${id}`)
        .set('Authorization', `Bearer ${localStorage.getItem('token')}`)
        .then((res) => {
          resolve();
          console.log(res.body)
          commit({
            type: 'setTourDetail',
            tourDetail: res.body
          });
        }, (err) => {
          reject(err);
        });
    });
  },
};

export default {
  state,
  actions,
  getters,
  mutations,
};
