<template>
    <header>
        <v-row class="headerBox">
            <div @click="goMain" class="logo">
                <img :src="logo" alt="로고" />
            </div>
            <v-spacer></v-spacer>
            <div class="menuBox">
                <v-row class="menu">
                    <div class="menuList" @click="toggleMenu">
                        <v-icon class="openImg">mdi-menu</v-icon>
                    </div>

                    <div @click="goMyPage" class="menuList">
                        <img :src="MyPage" alt="마이페이지" class="menuImg" />
                        <p>내 정보</p>
                    </div>

                    <div @click="goShareRecipt" class="menuList">
                        <img :src="recipe" alt="레시피 이미지" class="menuImg" />
                        <p>추천 레시피</p>
                    </div>
                    <div @click="goSubscribe" class="menuList">
                        <img :src="subscribe" alt="구독 이미지" class="menuImg" />
                        <p>공유 레시피</p>
                    </div>
                    <div @click="clickLogout" class="menuList">
                        <img :src="logout" alt="로그아웃 이미지" class="menuImg" />
                        <p>로그아웃</p>
                    </div>
                </v-row>
            </div>
        </v-row>
        <alarm></alarm>
    </header>
</template>
<script>
import { moveStart, moveMain, moveRecipeList, moveSubscribe, moveMyPage } from '@/api/move.js';
import Alarm from '@/components/Alarm.vue';
import swal from 'sweetalert';
export default {
    name: 'MainHeader',
    data() {
        return {
            logo: require('@/assets/images/header/logo.png'),
            recipe: require('@/assets/images/header/sharing.png'),
            subscribe: require('@/assets/images/header/subscribe.png'),
            logout: require('@/assets/images/header/logout.png'),
            MyPage: require('@/assets/images/header/MyPage.png'),
            alertDialog: false,
            alarmDialog: false,
        };
    },
    components: { Alarm },
    methods: {
        goMain() {
            // 메인으로 이동
            moveMain();
        },
        goShareRecipt() {
            // 레시피 목록으로 이동
            moveRecipeList(0);
        },
        goSubscribe() {
            // 구독 페이지로 이동
            moveSubscribe(0);
        },

        goMyPage() {
            // 마이 페이지로 이동
            moveMyPage();
        },
        toggleMenu() {
            // 메뉴 열고 닫는 메소드
            // openMenu라는 클래스를 toggle하면서 메뉴 열고 닫음.
            const menu = document.querySelector('.menu');
            menu.classList.toggle('openMenu');
        },
        clickLogout() {
            // 로그아웃
            this.$store
                .dispatch('LOGOUT')
                .then(() => {
                    moveStart(); // 로그아웃 후 시작페이지로 이동
                })
                .catch((error) => {
                    swal(error, {
                        icon: 'error',
                    });
                });
        },
    },
};
</script>
<style scoped>
@import './../../assets/css/header.css';

.menu {
    right: -224px;
}
.menuBox {
    width: calc(100% - 80px);
}
.menuList {
    width: 20%;
    text-align: center;
}
</style>
