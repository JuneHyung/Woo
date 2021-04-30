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
                <li class="menuList" @click="openAlarmList">
                    <img :src="alarm" alt="알림 이미지" class="menuImg" v-bind="attrs" v-on="on" />
                    <p>알람</p>
                    <div class="alarm">
                        <img :src="alarm" style="width: 25px; height: 25px; padding-bottom: 3px" />
                        <div style="text-align: left">
                            <button style="word-break: break-all; padding-bottom: 7px">
                                - 구독하신 00님의 새 영상
                            </button>
                            <button style="word-break: break-all; padding-bottom: 7px">
                                - 구독하신 00님의 새 영상
                            </button>
                            <button style="word-break: break-all; padding-bottom: 7px">
                                - 구독하신 00님의 새로운 레시피
                            </button>
                        </div>
                    </div>
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
            alertDialog: false,
        };
    },
    methods: {
        goStart() {
            this.$router.push({ name: 'Start' });
        },
        goShareRecipt() {
            this.$router.push({ name: 'RecipeList' });
        },
        goSubscribe() {
            this.$router.push({ name: 'Subscribe' });
        },
        openAlertDialog() {
            console.log('알람창 오픈');
        },
        openAlarmList() {
            this.alarmDialog = true;
            var alarm = document.querySelector('.alarm');
            alarm.classList.toggle('alarmOpen');
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
    position: relative;
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
.alarm {
    position: absolute;
    top: 68px;
    background-color: #929aab;
    opacity: 0.9 !important;
    right: 0;
    width: 180px;
    display: none;
    transition: all 0.4s;
    border: solid #494949 2px;
    color: white;
}
.alarmOpen {
    display: block;
}
</style>
