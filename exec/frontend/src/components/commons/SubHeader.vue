<template>
    <header>
        <v-row class="headerBox">
            <div @click="goStart" class="logo">
                <img :src="logo" alt="로고" />
            </div>
            <v-spacer></v-spacer>
            <div class="menuBox">
                <v-row class="menu">
                    <div class="menuList" @click="toggleMenu">
                        <v-icon class="openImg">mdi-menu</v-icon>
                    </div>

                    <div @click="goSignUp" class="menuList">
                        <img :src="signup" alt="회원가입 이미지" class="menuImg" />
                        <p>회원가입</p>
                    </div>
                    <div class="menuList" @click="goLogin()">
                        <img :src="login" alt="로그인 이미지" class="menuImg" />
                        <p>로그인</p>
                    </div>
                </v-row>
            </div>
        </v-row>
    </header>
</template>

<script>
import { moveStart, moveLogin, moveSignUp } from '@/api/move.js';
export default {
    name: 'SubHeader',

    data() {
        return {
            logo: require('@/assets/images/header/logo.png'),
            signup: require('@/assets/images/header/sign-up.png'),
            login: require('@/assets/images/header/login.png'),
        };
    },
    methods: {
        goStart() {
            moveStart();
        },
        goSignUp() {
            moveSignUp();
        },
        goLogin() {
            moveLogin();
        },
        toggleMenu() {
            const menu = document.querySelector('.menu');
            menu.classList.toggle('openMenu');
        },
    },
};
</script>
<style scoped>
@import './../../assets/css/header.css';
</style>
