import { createStore as _createStore } from 'vuex';
import axios from 'axios';
//not sure if this is allowed
import PotholeService from "../services/PotholeService.js";

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      potholeList: [],
      pothole: {
        potHoleId: 0,
				userId: 0,
        streetAddress: "",
				longitude: null,
				latitude: null,
				currentStatus: "reported",
				reportedDate: new Date().toDateString(),
				inspectedDate: "Not Inspected Yet",
				repairedDate: "Not Repaired Yet",
				severity: ""
			},
    },
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      },
      // UPDATE_STREET_ADDRESS(state, newStreetAddress){
      
      //   state.pothole.streetAddress = newStreetAddress;

      // },
      UPDATE_POTHOLE_LIST(state){
        PotholeService.getPotholeList()
        .then((response) => { 
            state.potholeList =  response.data;
        });

      //   try{
      //     const response = await PotholeService.getPotholeList();
      //     this.state.potholeList = response;
      //   }
      //   catch(error){
      //     console.error('Error fetching data')
      //   }
      }
    },
  });
  return store;
}
