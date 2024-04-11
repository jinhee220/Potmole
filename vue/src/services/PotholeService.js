import axios from 'axios';

export default {

  addPothole(pothole) {
    return axios.post('/reportPothole', pothole)
  },
  getPotholeList() {
    return axios.get('/getAllPotholes')
  },
  updatePothole(pothole) {
    return axios.put('/updatePothole/', pothole);
  }

}
