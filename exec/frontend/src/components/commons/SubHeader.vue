<template>
    <header>
        <v-row class="headerBox">
            <div @click="goStart" class="logo">
                <img :src="logo" alt="로고" />
            </div>
            <v-spacer></v-spacer>
            <div class="menuBox" style="background-color: #ffecf2">
                <v-row class="menu">
                    <div class="menuList" @click="toggleMenu" style="background-color: #ffecf2">
                        <v-icon
                            style="
                                width: 60px;
                                line-height: 80px;
                                text-align: center;
                                border-radius: 0px 40px 40px 0px;
                            "
                            class="openImg"
                            >mdi-menu</v-icon
                        >
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
header {
    height: 80px;

    box-sizing: content-box;
}
.logo {
    width: 70px;
    height: 70px;
    margin: 0px auto;
    margin-left: 10px;
}
.logo img {
    width: 100%;
    height: 100%;
    margin: 0 auto;
}

.openMenu {
    transition: width 2s;
    right: 0 !important;
}
.openMenu .openImg {
    transition: all 0.7s;
    transform: rotate(180deg);
    background-color: #fff;
    height: 100%;
    margin: 0 !important;
}
.menu .openImg {
    transition: all 0.7s;
}
.headerBox {
    position: relative;
}
.menu {
    transition: all 0.7s;
    background-color: #fff;
    width: 100%;
    height: 80px;
    position: absolute;
    top: 0;
    right: -144px;
    padding: 0 !important;
    overflow: hidden;
}
.menuBox {
    width: calc(100% - 160px);
    position: absolute;
    top: 0;
    right: 0;
    height: 80px;
    overflow: hidden;
    border-radius: 40px 0px 0px 40px;
}
.menuList {
    width: 60px;
    height: 80px;
    text-align: center;
}

.menuList p {
    font-size: 0.5rem;
    line-height: 20px;
    text-align: center;
    height: 20px;
}

.menuImg {
    width: 36px;
    height: 36px;
    margin: 12px auto;
}
.openBtn {
    width: 80px;
    height: 80px;
    margin: 0 !important;
}
.closeBtn {
    margin: 25px;
}
</style>
