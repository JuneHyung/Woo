<template>
    <v-container>
        <header>
            <v-row>
                <div @click="goStart" class="logo"><img :src="logo" alt="로고" /></div>
                <v-spacer></v-spacer>
                <li @click="goShareRecipt" class="menuList">
                    <img :src="recipe" alt="레시피 이미지" class="menuImg" />
                    <p>레시피</p>
                </li>
                <li @click="goSubscribe" class="menuList">
                    <img :src="subscribe" alt="구독 이미지" class="menuImg" />
                    <p>구독</p>
                </li>
                <li class="menuList">
                    <img :src="logout" alt="로그아웃 이미지" class="menuImg" />
                    <p>로그아웃</p>
                </li>
                <li class="menuList">
                    <img :src="alarm" alt="알림 이미지" class="menuImg" />
                </li>
            </v-row>
        </header>
    </v-container>
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
            alarm: require('@/assets/images/header/alarm.png'),
        };
    },
    methods: {
        goStart() {
            this.$router.push({ name: 'Start' });
        },
        goShareRecipt() {
            this.$router.push({ name: 'ShareRecipe' });
        },
        goSubscribe() {
            this.$router.push({ name: 'Subscribe' });
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
