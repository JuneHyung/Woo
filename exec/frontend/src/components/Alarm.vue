<template>
    <div class="alramMenu" @click="openAlarmList">
        <div class="alarmImg">
            <img :src="alarmN" alt="알림 이미지" v-if="messageDialog" />
            <img :src="alarmY" alt="알림 이미지" v-else />
        </div>
        <div class="alarm">
            <v-card>
                <div
                    style="
                        height: 80%;
                        width: 100%;
                        font-size: 22px;
                        text-align: center;
                        border-bottom: 2px solid #ffecf2;
                    "
                    dark
                >
                    <v-spacer></v-spacer>
                    알림
                    <v-spacer></v-spacer>
                </div>

                <div
                    v-for="(message, index) in messageList"
                    :key="index"
                    style="border-bottom: 2px solid #ffecf2; box-sizing: border-box"
                >
                    <p style="font-size: 14px; padding-left: 15px !important">
                        {{ message.name }}님이 새 글을 등록하였습니다.
                    </p>
                </div>
            </v-card>
        </div>
    </div>
</template>

<script>
import { getSubscribeMessage } from '@/api/subscribe.js';
export default {
    data() {
        return {
            alarmY: require('@/assets/images/header/alarmY.png'),
            alarmN: require('@/assets/images/header/alarmN.png'),
            messageList: [],
            beforeSize: 0,
            token: '',
            messageDialog: false,
        };
    },
    created() {
        this.getInfo();
        this.getMessage();
    },
    watch: {
        messageDialog() {
            this.messageDialog;
            console.log(this.messageDialog);
        },
    },
    methods: {
        openAlarmList() {
            this.messageDialog = true;
            var alarm = document.querySelector('.alarm');
            alarm.classList.toggle('alarmOpen');
        },
        getMessage() {
            let token = localStorage.getItem('X-AUTH-TOKEN');
            if (token) {
                setInterval(this.getInfo, 3000);
            }
        },
        getInfo() {
            getSubscribeMessage()
                .then((response) => {
                    let before = this.messageList.length;
                    let temp = response.data.messageList;

                    if (before <= this.beforeSize) {
                        let after = response.data.messageList.length;

                        if (this.beforeSize < after) {
                            this.messageList.splice(0);
                            for (var i = 0; i < 10; i++) {
                                this.messageList.push(temp[i]);
                            }
                            this.beforeSize = after;
                            this.messageDialog = false;
                        }
                    }
                })
                .catch((error) => {
                    console.log(error);
                });
        },
    },
};
</script>

<style scoped>
.alarmImg {
    position: fixed;
    right: 5px;
    bottom: 5px;
    width: 36px;
    height: 36px;
    margin: 22px;
}
.alarm {
    position: fixed;
    bottom: 70px;
    opacity: 0.95 !important;
    right: 25px;
    width: 180px;
    display: none;
    transition: all 0.4s;
    border: solid #ffecf2 3px;
    z-index: 999;
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
