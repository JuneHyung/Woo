<template>
    <v-container>
        <div>
            <div>
                <p class="font-28">
                    ㅇ
                    <span class="titleText">내 냉장고</span>
                </p>
            </div>
            <v-slide-group
                v-if="fridgeList.length != 0"
                center-active
                show-arrows
                class="refListBox"
            >
                <v-slide-item v-for="(fridge, index) in fridgeList" :key="index">
                    <div class="refListCard">
                        <p class="shorthand">
                            {{ fridge.name }}
                        </p>
                        <v-card class="mx-2 refList">
                            <img
                                :src="fridgeImg[fridge.type]"
                                alt="냉장고"
                                class="refImg"
                                @click="goRefManage(fridge.id, fridge.type)"
                            />
                        </v-card>
                        <div class="deleteBtnBox">
                            <div class="deleteBtnInnerBox">
                                <v-icon class="deleteBtn" @click="deleteRefrigerator(fridge.id)">
                                    mdi-delete-outline
                                </v-icon>
                            </div>
                        </div>
                    </div>
                </v-slide-item>
            </v-slide-group>

            <div v-else class="refListBox noList">
                <div class="logoIconBox">
                    <img :src="logoIcon" alt="냉장고아이콘" />
                </div>
                <div class="mb-5">
                    <p class="font-24">
                        여기에 내 냉장고가 표시됩니다.<br />
                        <span class="font-20">냉장고 관리를 위해 냉장고를 추가해 주세요.</span>
                    </p>
                </div>
            </div>

            <v-row class="explainBox">
                <p>클릭시 냉장고 관리가 가능합니다</p>
                <v-spacer></v-spacer>
                <div @click="goRefAdd()" class="refAddBtn">
                    <img :src="require('@/assets/images/refadd.png')" alt="냉장고추가버튼" />
                </div>
            </v-row>
        </div>
        <div class="mt-3">
            <div>
                <p class="font-28">
                    ㅇ
                    <span class="titleText">구독</span>
                </p>
            </div>
            <div>
                <v-slide-group
                    center-active
                    show-arrows
                    class="recipeListBox"
                    v-if="subscribeList.length != 0"
                >
                    <v-slide-item v-for="(sub, index) in subscribeList" :key="index">
                        <div class="recipeListCard">
                            <v-card class="mx-2 recipeList" @click="goSubscribe()">
                                <img
                                    :src="`data:image/jpg;base64,${sub.imageStrArr[0]}`"
                                    alt="대표이미지"
                                    style="width: 100%; height: 80%"
                                />
                                <p class="shorthand">
                                    {{ sub.title }}
                                </p>
                            </v-card>
                            <div class="recipeInfoBox">
                                <p class="writerBox shorthand">작성자 : {{ sub.writer }}</p>

                                <p class="viewBox">
                                    <v-icon class="font-14">mdi-eye</v-icon> :
                                    {{ sub.visit }}
                                </p>
                            </div>
                        </div>
                    </v-slide-item>
                </v-slide-group>
                <div v-else class="recipeListBox noList">
                    <div class="logoIconBox">
                        <img :src="recipeIcon" alt="레시피아이콘" />
                    </div>
                    <div class="mb-5">
                        <p class="font-24">
                            여기에 구독자 컨텐츠가 표시됩니다.<br />
                            <span class="font-20"
                                >즐겨찾는 분을 구독하여 최신 레시피를 받아 보세요.</span
                            >
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </v-container>
</template>

<script>
import { getMyFridge, deleteMyFridge } from '../api/refrigerator.js';
import { moveRefAdd, moveRefManage, moveSubscribe } from '@/api/move.js';
import { getCheckSubscribe, getMySubscribe } from '@/api/subscribe.js';
import jwt_decode from 'jwt-decode';
import swal from 'sweetalert';

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
            userList: [],
            page: 0,
            size: 6,
            recipeIcon: require('@/assets/images/header/sharing.png'),
            logoIcon: require('@/assets/images/header/logo.png'),
        };
    },
    created() {
        let token = sessionStorage.getItem('X-AUTH-TOKEN');
        let decode = jwt_decode(token); // 가져온 token을 decode함.
        this.id = decode.sub;
        this.getMyRefrigerator();
        this.getSubscribeInfo();
    },
    methods: {
        goRefManage(rid) {
            moveRefManage(rid);
        },
        goRefAdd() {
            moveRefAdd();
        },
        goSubscribe() {
            moveSubscribe(0);
        },
        getMyRefrigerator() {
            getMyFridge()
                .then((response) => {
                    this.fridgeList = response.data.fridgeList;
                })
                .catch(() => {
                    swal('받기 실패!', {
                        icon: 'error',
                    });
                });
        },
        deleteRefrigerator(fridgeid) {
            deleteMyFridge(fridgeid)
                .then(() => {
                    swal('삭제 성공!', {
                        icon: 'success',
                    });
                    location.href = '/main';
                })
                .catch(() => {
                    swal('삭제 실패!', {
                        icon: 'error',
                    });
                });
        },
        getSubscribeInfo() {
            getCheckSubscribe()
                .then((response) => {
                    this.userList.splice(0);
                    this.userList = response.data.userlist;
                    this.subscribeList.splice(0);
                    this.userList.forEach((el) => {
                        getMySubscribe(this.page, this.size, el.id)
                            .then((response) => {
                                this.subscribeList = response.data.post;
                            })
                            .catch((error) => {
                                console.log(error);
                            });
                    });
                })
                .catch((error) => console.log(error));
        },
    },
};
</script>

<style scoped>
@import './../assets/css/main.css';
</style>
