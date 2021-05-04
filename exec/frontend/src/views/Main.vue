<template>
    <v-container>
        <div>
            <div>
                <p class="mainText">내 냉장고 목록</p>
            </div>
            <v-slide-group center-active show-arrows>
                <v-slide-item v-for="(fridge, index) in fridgeList" :key="index">
                    <div>
                        <v-card class="ma-2" height="150" width="100" style="position: relative">
                            <img
                                :src="fridgeImg[fridge.type]"
                                alt="냉장고"
                                style="width: 100%; height: 100%"
                                @click="goRefManage(fridge.id)"
                            />
                            <p
                                style="
                                    width: 20px;
                                    height: 20px;
                                    position: absolute;
                                    top: 0;
                                    right: 0;
                                    border: 1px solid #f00;
                                    color: #f00;
                                    text-align: center;
                                "
                                @click="deleteRefrigerator(fridge.id)"
                            >
                                X
                            </p>
                        </v-card>
                        <p style="text-align: center">{{ fridge.name }}</p>
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
import http from '../api/axios.js';
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
            this.$router.push({ name: 'RefManage', params: { rid: rid } });
        },
        goRefAdd() {
            this.$router.push({ name: 'RefAdd' });
        },
        getMyRefrigerator() {
            http.get(`fridge/list`)
                .then((response) => {
                    this.fridgeList = response.data.fridgeList;
                })
                .catch(() => {
                    alert('받기실패');
                });
        },
        deleteRefrigerator(fridgeid) {
            http.delete(`fridge/list/${fridgeid}`)
                .then(() => {
                    alert('삭제 성공');
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
    font-size: 24px;
}
</style>
