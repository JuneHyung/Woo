<template>
    <v-container>
        <div class="startImage">
            <img :src="main" alt="메인이미지" style="width: 100%; height: 100%" />
        </div>
        <div class="startText" style="margin-bottom: 20px">
            <p>냉장고 관리의 현명한 선택</p>
        </div>
        <div class="mainText" style="margin-bottom: 10px">
            <p style="display: inline-block; font-size: 24px">우</p>
            <p style="display: inline-block">리집</p>
            &nbsp;&nbsp;&nbsp;
            <p style="display: inline-block; font-size: 24px">냉</p>
            <p style="display: inline-block">장고를</p>
            &nbsp;&nbsp;&nbsp;
            <p style="display: inline-block; font-size: 24px">부</p>
            <p style="display: inline-block">탁해</p>
        </div>
        <div class="mainText" style="margin-bottom: 10px">
            <p>지금 바로 경험해보세요!</p>
        </div>
        <hr />
        <div style="margin-top: 10px; margin-bottom: 10px">
            <v-row>
                <v-spacer></v-spacer>
                <img :src="signup" alt="" class="startIcon" @click="moveSignUp()" />
                <v-spacer></v-spacer>
                <img :src="login" alt="로그인  " class="startIcon" @click="moveLogin()" />
                <v-spacer></v-spacer>
            </v-row>
        </div>
        <hr />
    </v-container>
</template>

<script>
import { moveLogin, moveSignUp } from '../api/move.js';
export default {
    name: 'Main',
    data() {
        return {
            signup: require('@/assets/images/header/sign-up.png'),
            login: require('@/assets/images/header/login.png'),
            main: require('@/assets/images/mainIntro.png'),
        };
    },
    methods: {
        moveLogin() {
            moveLogin();
        },
        moveSignUp() {
            moveSignUp();
        },
    },
};
</script>

<style scoped>
.mainText {
    text-align: center;
}
.startText {
    text-align: center;
}
.startImage {
    margin: 0 auto;
    width: 300px;
    height: 280px;
}
.startIcon {
    width: 50px;
    height: 50px;
}
</style>
