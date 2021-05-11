<template>
    <v-container>
        <div>
            <div>
                <p class="mainText">
                    ㅇ
                    <span
                        class="mainText"
                        style="
                            display: inline-block;
                            text-decoration: underline;
                            text-decoration-style: wavy;
                            text-underline-position: under;
                        "
                        >내 냉장고</span
                    >
                </p>
            </div>
            <v-slide-group
                center-active
                show-arrows
                style="
                    border-radius: 30px;
                    margin: 15px auto;
                    box-shadow: inset 0px 0px 5px 5px #ffecf2;
                "
            >
                <v-slide-item v-for="(fridge, index) in fridgeList" :key="index">
                    <div style="margin: 20px auto">
                        <p
                            class="shorthand"
                            style="
                                width: 100px;
                                text-align: center;
                                font-size: 22px;
                                margin: 0 auto !important;
                            "
                        >
                            {{ fridge.name }}
                        </p>
                        <v-card
                            class="mx-2"
                            height="130"
                            width="102"
                            style="border: 1px solid black"
                        >
                            <img
                                :src="fridgeImg[fridge.type]"
                                alt="냉장고"
                                style="
                                    width: 90px;
                                    height: 110px;
                                    padding-top: 14px;
                                    margin: 0 auto;
                                "
                                :type="fridge.type"
                                @click="goRefManage(fridge.id)"
                            />
                        </v-card>
                        <div style="margin-top: 10px">
                            <v-icon
                                style="
                                    width: 20px;
                                    height: 20px;
                                    color: #000;
                                    margin: 0 auto;
                                    cursor: pointer;
                                    display: flex;
                                    justify-content: center;
                                    align-items: center;
                                "
                                @click="deletseRefrigerator(fridge.id)"
                            >
                                mdi-delete-outline
                            </v-icon>
                        </div>
                    </div>
                </v-slide-item>
            </v-slide-group>

            <v-row style="border-top: 1px solid black; border-bottom: 1px solid black">
                <p style="font-family: 'twayair', sans-serif; font-size: 14px">
                    클릭시 냉장고 관리가 가능합니다
                </p>
                <v-spacer></v-spacer>
                <p
                    @click="goRefAdd()"
                    style="width: 40px; height: 40px; margin: 0px 10px 10px 10px !important"
                >
                    <img
                        :src="require('@/assets/images/refadd.png')"
                        style="width: 100%; height: 100%"
                    />
                </p>
            </v-row>
        </div>
        <div style="margin-top: 10px">
            <div>
                <p class="mainText">
                    ㅇ
                    <span
                        class="mainText"
                        style="
                            display: inline-block;
                            text-decoration: underline;
                            text-decoration-style: wavy;
                            text-underline-position: under;
                        "
                        >구독</span
                    >
                </p>
            </div>
            <div style="width: 330px; height: 200px">
                <v-slide-group
                    center-active
                    show-arrows
                    style="
                        border-radius: 30px;
                        margin: 15px auto;
                        box-shadow: inset 0px 0px 5px 5px #ffecf2;
                    "
                >
                    <v-slide-item v-for="(sub, index) in subscribeList" :key="index">
                        <div style="margin: 30px auto 20px">
                            <v-card
                                class="mx-2"
                                height="130"
                                width="101"
                                style="border: 1px solid black"
                                @click="goSubscribe()"
                            >
                                <img
                                    :src="`data:image/jpg;base64,${sub.imageStrArr[0]}`"
                                    alt="대표이미지"
                                    style="width: 100%; height: 80%"
                                />

                                <p
                                    class="shorthand"
                                    style="
                                        width: 100px;
                                        height: 20%;
                                        text-align: center;
                                        font-size: 22px;
                                        margin: 0 auto !important;
                                    "
                                >
                                    {{ sub.title }}
                                </p>
                            </v-card>

                            <p
                                style="
                                    font-size: 16px;
                                    height: 16x;
                                    line-height: 16px;
                                    padding-left: 10px !important;
                                "
                            >
                                작성자 : {{ sub.user_name }}
                            </p>

                            <p
                                style="
                                    text-align: right;
                                    font-size: 16px;
                                    height: 16x;
                                    line-height: 16px;
                                    padding-right: 20px !important;
                                "
                            >
                                <v-icon style="font-size: 14px">mdi-eye</v-icon> :
                                {{ sub.visit }}
                            </p>
                        </div>
                    </v-slide-item>
                </v-slide-group>
            </div>
        </div>
    </v-container>
</template>

<script>
import { getMyFridge, deleteMyFridge } from '../api/refrigerator.js';
import { moveRefAdd, moveRefManage, moveSubscribe } from '@/api/move.js';
import { getMySubscribe } from '@/api/subscribe.js';
import jwt_decode from 'jwt-decode';
export default {
    name: 'Main',
    data() {
        return {
            id: '',
            fridgeList: [],
            fridgeImg: {
                44: require('@/assets/images/refrigerator/ref_44.png'),
                55: require('@/assets/images/refrigerator/ref_55.png'),
                66: require('@/assets/images/refrigerator/ref_66.png'),
                4444: require('@/assets/images/refrigerator/ref_4444.png'),
                5555: require('@/assets/images/refrigerator/ref_5555.png'),
            },
            subscribeList: [],
            page: 0,
            size: 6,
        };
    },
    created() {
        let token = localStorage.getItem('X-AUTH-TOKEN');
        let decode = jwt_decode(token); // 가져온 token을 decode함.
        this.id = decode.sub;
        this.getMyRefrigerator();
        this.getSubscribeInfo();
    },
    methods: {
        goRefManage(rid) {
            // this.$router.push({ name: 'RefManage', params: { rid: rid } });
            moveRefManage(rid);
        },
        goRefAdd() {
            moveRefAdd();
        },
        goSubscribe() {
            moveSubscribe();
        },
        getMyRefrigerator() {
            getMyFridge()
                .then((response) => {
                    this.fridgeList = response.data.fridgeList;
                })
                .catch(() => {
                    alert('받기실패');
                });
        },
        deleteRefrigerator(fridgeid) {
            deleteMyFridge(fridgeid)
                .then(() => {
                    alert('삭제 성공');
                    location.href = '/main';
                })
                .catch(() => {
                    alert('삭제 실패');
                });
        },
        getSubscribeInfo() {
            getMySubscribe(this.page, this.size, this.id)
                .then((response) => {
                    this.subscribeList = response.data.post;
                })
                .catch((error) => console.log(error));
        },
    },
};
</script>

<style scoped>
.myRef {
    height: 250px;
    margin-bottom: 20px;
}
.mainText {
    font-size: 28px;
}
.wavedivider {
    width: 100px;
    height: 3px;
}
@font-face {
    font-family: 'twayair';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_tway@1.0/twayair.woff')
        format('woff');
    font-weight: normal;
    font-style: normal;
    color: black;
}
</style>
