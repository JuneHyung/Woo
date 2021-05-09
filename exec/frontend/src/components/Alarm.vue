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
// import { Kafka } from 'kafkajs';
export default {
    data() {
        return {
            alarm: require('@/assets/images/header/alarm.png'),
            messageList: [],
        };
    },
    created() {
        // setTimeout(this.getMessage(), 5000);
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
        // getMessage() {
        //     const kafka = new Kafka({
        //         clientId: 'my-app',
        //         ssl: true,
        //         brokers: ['k4d109.p.ssafy.io:9092'],
        //     });

        //     const consumer = kafka.consumer({ groupId: 'foo' });

        //     const run = async () => {
        //         await consumer.connect();
        //         await consumer.subscribe({ topic: 'exam', fromBeginning: true });
        //         await consumer.run({
        //             eachMessage: async ({ topic, partition, message }) => {
        //                 console.log(`${topic} ${partition} ${message.value}`);
        //             },
        //         });
        //     };

        //     run().catch((error) => alert(error));
        // },
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
    opacity: 0.88 !important;
    right: 25px;
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
