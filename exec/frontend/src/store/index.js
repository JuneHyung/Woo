import Vue from "vue";
import Vuex from "vuex";
import { findById } from '@/api/user.js';
// import jwt_decode from 'jwt-decode';


Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        isLogin: false,
        
    },
    getters: {
        getIsLogin(state) {
            
            return state.isLogin;
        }
    },
    mutations: {
        
    setIsLogined(state, isLogin) {
        state.isLogin = isLogin;
    },
    
    logout(state) {
        state.isLogin = false;
    }
    },
    actions: {
        async GET_USER_INFO() {
            await findById( // user.js에 있는 findByid
                (response) => {
                    if (response.data.message !== 'success') { 
                        console.log('유저 정보 없음!!');
                        
                    } 
                },
                // (error) => {
                //     console.log(error);
                // }
            );
        },
    LOGOUT({ commit }) {
        commit('logout');
        localStorage.removeItem('X-AUTH-TOKEN');
    }
},
modules: {},
});
