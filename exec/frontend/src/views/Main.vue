<template>
    <v-container>
        <div>
            <div>
                <p class="mainText" style="font-family: 'BinggraeSamanco-Bold', sans-serif">
                    ㅇ
                    <span
                        class="mainText"
                        style="
                            font-family: 'BinggraeSamanco-Bold', sans-serif;
                            display: inline-block;
                            padding-right: 20px;
                            text-decoration: underline;
                            text-decoration-style: wavy;
                            text-underline-position: under;
                        "
                        >냉장고 목록</span
                    >
                </p>
                <div class="wavedivider"></div>
                <div class="divider"></div>
            </div>
            <v-slide-group center-active show-arrows>
                <v-slide-item v-for="(fridge, index) in fridgeList" :key="index">
                    <div style="margin: 20px auto">
                        <p
                            class="shorthand"
                            style="text-align: center; font-size: 14px; text-overflow: hidden"
                        >
                            {{ fridge.name }}
                        </p>
                        <v-card class="ma-2" height="130" width="102">
                            <img
                                :src="fridgeImg[fridge.type]"
                                alt="냉장고"
                                style="
                                    width: 90px;
                                    height: 110px;
                                    padding-top: 14px;
                                    margin: 0 auto;
                                "
                                @click="goRefManage(fridge.id)"
                            />
                        </v-card>
                        <div>
                            <v-icon
                                style="
                                    width: 20px;
                                    height: 20px;
                                    color: #f00;
                                    margin: 0 auto;
                                    pointer: cursor;
                                    display: flex;
                                    justify-content: center;
                                    align-items: center;
                                "
                                @click="deletseRefrigerator(fridge.id)"
                            >
                                mdi-delete-forever-outline
                            </v-icon>
                        </div>
                    </div>
                </v-slide-item>
            </v-slide-group>

            <v-row style="border-top: 1px solid black; border-bottom: 1px solid black">
                <p>클릭시 냉장고 관리가 가능합니다</p>
                <v-spacer></v-spacer>
                <span @click="goRefAdd()" class="addBtn">냉장고 추가</span>
            </v-row>
        </div>
        <div style="margin-top: 10px">
            <div>
                <p class="mainText">구독</p>
            </div>
            <div style="width: 330px; height: 200px"></div>
            <div>
                <hr />
                <p>클릭시 구독한 작성자의 상세 내용에 대해 확인할 수 있습니다</p>
                <div>
                    <hr />
                </div>
            </div>
        </div>
    </v-container>
</template>

<script>
import { getMyFridge, deleteMyFridge } from '../api/refrigerator.js';
import { moveRefAdd, moveRefManage } from '@/api/move.js';
export default {
    name: 'Main',
    data() {
        return {
            id: 6,
            fridgeList: [],
            fridgeImg: {
                22: require('@/assets/images/refrigerator/ref_44.png'),
                33: require('@/assets/images/refrigerator/ref_55.png'),
                44: require('@/assets/images/refrigerator/ref_66.png'),
                55: require('@/assets/images/refrigerator/ref_4444.png'),
                66: require('@/assets/images/refrigerator/ref_5555.png'),
                1: require('@/assets/images/refrigerator/ref_4444.png'),
            },
        };
    },
    created() {
        this.getMyRefrigerator();
    },
    methods: {
        goRefManage(rid) {
            // this.$router.push({ name: 'RefManage', params: { rid: rid } });
            moveRefManage(rid);
        },
        goRefAdd() {
            // this.$router.push({ name: 'RefAdd' });
            moveRefAdd();
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
    font-family: 'BinggraeSamanco-Bold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-10@1.0/BinggraeSamanco-Bold.woff')
        format('woff');
    font-weight: normal;
    font-style: normal;
}
</style>
