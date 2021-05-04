<template>
    <div class="alramMenu" @click="openAlarmList">
        <div class="alarmImg">
            <img :src="alarm" alt="알림 이미지" />
        </div>
        <div class="alarm">
            <v-card>
                <v-card-title
                    style="height: 80%; width: 100%; font-size: 18px; text-align: center"
                    dark
                    ><v-spacer></v-spacer>
                    알림
                    <v-spacer></v-spacer>
                </v-card-title>

                <v-card-text>
                    <button style="word-break: break-all; padding-bottom: 7px">
                        - 구독하신 00님의 새 영상
                    </button>
                    <button style="word-break: break-all; padding-bottom: 7px">
                        - 구독하신 00님의 새 영상
                    </button>
                    <button style="word-break: break-all; padding-bottom: 7px">
                        - 구독하신 00님의 새로운 레시피
                    </button>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn>전부 읽음 표시</v-btn>
                    <v-spacer></v-spacer>
                </v-card-actions>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn>창 닫기</v-btn>
                    <v-spacer></v-spacer>
                </v-card-actions>
            </v-card>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            alarm: require('@/assets/images/header/alarm.png'),
        };
    },
    methods: {
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
.alarmImg {
    position: absolute;
    right: 0px;
    bottom: 15px;
    width: 36px;
    height: 36px;
    margin: 22px;
}
.alarm {
    position: absolute;
    bottom: 78px;
    opacity: 0.88 !important;
    right: 0;
    width: 180px;
    display: none;
    transition: all 0.4s;
    border: solid #494949 3px;
}

.alarmImg img {
    display: block;
    width: 100%;
    height: 100%;
}
.menuImg {
    display: block;
    width: 100%;
    height: 100%;
}
.alarmOpen {
    display: block;
}
</style>
