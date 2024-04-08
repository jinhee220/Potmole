import axios from 'axios';

export default {

  addPothole(pothole) {
    return axios.post('/reportPothole', pothole)
  },
  getPotholeList() {
    return axios.get('/getAllPotholes')
  },

}
