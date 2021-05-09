<template>
    <v-container>
        <header>
            <v-row>
                <div @click="goStart" class="logo"><img :src="logo" alt="로고" /></div>
                <v-spacer></v-spacer>
                <div @click="goSignUp" class="menuList">
                    <img :src="signup" alt="회원가입 이미지" class="menuImg" />
                    <p>회원가입</p>
                </div>
                <div @click="goLogin" class="menuList">
                    <img :src="login" alt="로그인 이미지" class="menuImg" />
                    <p>로그인</p>
                </div>
            </v-row>
        </header>
    </v-container>
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
    },
};
</script>
<style scoped>
header {
    padding-bottom: 5px;
    border-bottom: 1px solid #333;
}
.logo {
    width: 60px;
    height: 60px;
}
.logo img {
    width: 100%;
    height: 100%;
    margin: 0 auto;
}
.menuList {
    width: 50px;
    height: 60px;
    line-height: 15px;
    text-align: center;
    margin: 0 5px;
}

.menuImg {
    width: 36px;
    height: 50%;
    margin: 10px auto 5px;
}
li p {
    font-size: 8px;
    height: 25%;
}
</style>
