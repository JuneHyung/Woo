import Vue from "vue";
import Vuex from "vuex";
import { findById } from '@/api/user.js';
// import jwt_decode from 'jwt-decode';


Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        isLogin: false,
        
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
                    if (response.data.message === 'success') { // succeess가 돌아오면
                        console.log('가져오기 성공');
                    } else { // 아니면 유저정보가없음.
                        console.log('유저 정보 없음!!');
                    }
                },
                (error) => {
                    console.log(error);
                }
            );
        },
    LOGOUT({ commit }) {
        commit('logout');
        localStorage.removeItem('X-AUTH-TOKEN');
    }
},
modules: {},
});
