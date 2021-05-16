<template>
    <v-container>
        <div class="startImage">
            <img :src="main" alt="메인이미지" />
        </div>
        <div class="startText">
            <p>냉장고 관리의 현명한 선택</p>
        </div>
        <div class="mainText">
            <p>
                <span class="font-24">우</span>리집 <span class="font-24">냉</span>장고를
                <span class="font-24">부</span>탁해
            </p>
        </div>
        <div class="mainText">
            <p>지금 바로 경험해보세요!</p>
        </div>

        <div class="btnBox">
            <v-row>
                <v-spacer></v-spacer>
                <img :src="signup" alt="" class="startIcon" @click="moveSignUp()" />
                <v-spacer></v-spacer>
                <img :src="login" alt="로그인  " class="startIcon" @click="moveLogin()" />
                <v-spacer></v-spacer>
            </v-row>
        </div>
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
@import './../assets/css/start.css';
</style>
