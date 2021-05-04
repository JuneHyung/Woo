<template>
    <v-container>
        <div class="loginFormBox">
            <h1 class="loginTitle">로 그 인</h1>
            <v-text-field
                label="ID (Email)"
                placeholder="ex)ssafy@naver.com"
                type="string"
                v-model="user.id"
            ></v-text-field>
            <v-text-field
                label="PW (대소문자 특수문자 조합 8자 이상)"
                placeholder="ssafy123!"
                type="password"
                v-model="user.password"
            ></v-text-field>
            <v-row>
                <v-spacer></v-spacer>
                <button class="resetBtn">초기화</button>

                <button class="loginBtn" @click="confirm()">로그인</button>
            </v-row>
        </div>
    </v-container>
</template>
<script>
import { login } from '@/api/user.js';
import http from '../../api/axios.js';
export default {
    data() {
        return {
            user: {
                id: '',
                password: '',
            },
        };
    },
    methods: {
        confirm() {
            localStorage.setItem('X-AUTH-TOKEN', '');
            login(
                this.user,
                (response) => {
                    if (response.data.message === 'success') {
                        let token = response.data['X-AUTH-TOKEN'];
                        this.$store.commit('setIsLogined', true);
                        localStorage.setItem('X-AUTH-TOKEN', token);
                        console.log(localStorage.getItem('X-AUTH-TOKEN'));
                        http.defaults.headers['X-AUTH-TOKEN'] = window.localStorage.getItem(
                            'X-AUTH-TOKEN'
                        );

                        this.$router.push({ name: 'Main' });
                    } else {
                        this.isLoginError = true;
                        if (this.user.id == '') {
                            alert('아이디 입력 해라!');
                        } else if (this.user.pw == '') {
                            alert('비번 입력 해라!');
                        } else {
                            alert('아이디 또는 비번이 일치하지않아유~');
                        }
                    }
                },
                (error) => {
                    console.log(error);
                }
            );
        },
    },
};
</script>
<style scoped>
.loginFormBox {
    width: calc(100% - 100px);
    margin: 0 auto;
}
.loginTitle {
    font-size: 24px;
    text-align: center;
}
.resetBtn {
    width: 60px;
    height: 60px;
    text-align: center;
}
.loginBtn {
    width: 60px;
    height: 60px;
    text-align: center;
}
</style>
