<template>
    <header>
        <v-row class="headerBox">
            <div @click="goStart" class="logo"><img :src="logo" alt="로고" /></div>
            <v-spacer></v-spacer>
            <div class="menuBox">
                <v-row class="menu">
                    <div class="menuList" @click="toggleMenu">
                        <v-icon
                            style="
                                width: 60px;
                                line-height: 60px;
                                text-align: center;
                                margin: 10px auto;
                            "
                            class="openImg"
                            >mdi-arrow-collapse-left</v-icon
                        >
                    </div>

                    <div @click="goMyPage" class="menuList">
                        <div class="menuBtn">
                            <img :src="MyPage" alt="마이페이지" class="menuImg" />
                        </div>
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
                    <div class="menuList">
                        <img :src="logout" alt="로그아웃 이미지" class="menuImg" />
                        <p>로그아웃</p>
                    </div>
                </v-row>
            </div>
        </v-row>
    </header>
</template>
<script>
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
    methods: {
        goStart() {
            this.$router.push({ name: 'Start' });
        },
        goShareRecipt() {
            this.$router.push({ name: 'RecipeList', params: { ingredient_id: 0 } });
        },
        goSubscribe() {
            this.$router.push({ name: 'Subscribe' });
        },

        goMyPage() {
            this.$router.push({ name: 'MyPage' });
        },
        toggleMenu() {
            console.log('찍');
            const menu = document.querySelector('.menu');
            console.log(menu);
            menu.classList.toggle('openMenu');
        },
    },
};
</script>
<style scoped>
header {
    height: 80px;
    border-bottom: 1px solid #333;
    box-sizing: content-box;
}
.logo {
    width: 60px;
    height: 60px;
    margin: 10px auto;
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
    right: -220px;
    padding: 0 !important;
    overflow: hidden;
}
.menuBox {
    width: calc(100% - 80px);
    position: absolute;
    top: 0;
    right: 0;
    height: 80px;
    overflow: hidden;
}
.menuList {
    width: 20%;
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
