<template>
    <div @click="openAlarmList">
        <div class="alarmImg">
            <img :src="alarmN" alt="알림 이미지" v-if="mdialog" />
            <img :src="alarmY" alt="알림 이미지" v-else />
        </div>
        <div class="alarm">
            <v-card>
                <div class="alarmText">
                    <v-spacer></v-spacer>
                    알림
                    <v-spacer></v-spacer>
                </div>

                <div
                    v-for="(message, index) in messageList"
                    :key="index"
                    class="alarmInfo"
                    @click="goSubscribe()"
                >
                    <p class="font-16 pl-2">{{ message.name }}님이 새 글을 등록하였습니다.</p>
                </div>
            </v-card>
        </div>
    </div>
</template>

<script>
import { getSubscribeMessage } from '@/api/subscribe.js';
import { moveSubscribe } from '@/api/move.js';
export default {
    data() {
        return {
            alarmY: require('@/assets/images/header/alarmY.png'),
            alarmN: require('@/assets/images/header/alarmN.png'),
            messageList: [],
            beforeSize: 0,
            token: '',
            mdialog: true,
            count: 0,
        };
    },
    mounted() {
        this.getInfo(); // 메세지 정보를 가져옴.
        this.getMessage(); // 메세지를 3초마다 받는 메소드
    },

    methods: {
        openAlarmList() {
            this.mdialog = true;
            var alarm = document.querySelector('.alarm');
            alarm.classList.toggle('alarmOpen');
        },
        getMessage() {
            let token = sessionStorage.getItem('X-AUTH-TOKEN'); // 토큰이 있는지 확인 후
            if (token) {
                // 토큰이 있으면
                setInterval(this.getInfo, 3000); // 3초마다 한번 씩 메세지 정보를 가져옴.
            }
        },
        getInfo() {
            getSubscribeMessage()
                .then((response) => {
                    let before = this.messageList.length; // 이전 상태의 메세지 리스트 길이.
                    let temp = response.data.messageList; // 받아온 메세지를 잠깐 저장해둘 temp 변수
                    if (before <= this.beforeSize) {
                        // 마지막에 받은 길이보다 before가 작거나 같을 때
                        let after = response.data.messageList.length; // 받아온 메세지의 길이.
                        if (after < 10 && this.beforeSize < after) {
                            // 처음 받은 경우 after가 10보다 작음.
                            this.messageList.splice(0); // 메세지 목록을 비우고,
                            for (var i = 0; i < after; i++) {
                                // 받은 메세지 길이만큼 메세지 리스트에 추가함.
                                this.messageList.push(temp[i]);
                            }
                            this.beforeSize = after; // after를 저장해 두고 다음에 비교.
                            if (this.count == 1) this.mdialog = false; // 처음 받는 데이터가 아니면 dialog 읽음표시.
                        } else if (after >= 10 && this.beforeSize < after) {
                            // 10개보다 많으면
                            this.messageList.splice(0);
                            for (i = 0; i < 10; i++) {
                                // 10개만 ㄴ허음.
                                this.messageList.push(temp[i]);
                            }
                            this.beforeSize = after;
                            if (this.count == 1) this.mdialog = false;
                        }
                        if (this.count == 0) this.count++; // 처음 동작한거라면 count를 1증가시킴.
                    }
                })
                .catch((error) => {
                    console.log(error);
                });
        },
        goSubscribe() {
            moveSubscribe(1);
        },
    },
};
</script>

<style scoped>
@import './../assets/css/alarm.css';
</style>
