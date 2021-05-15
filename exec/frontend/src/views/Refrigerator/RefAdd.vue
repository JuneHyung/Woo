<template>
    <v-container class="addBox">
        <p class="titleText mb-3">냉장고 추가</p>
        <v-text-field label="냉장고이름" type="text" v-model="fridge.name"></v-text-field>
        <div>
            <p class="font-18">단문형(인기순)</p>
            <v-slide-group center-active show-arrows>
                <v-slide-item v-for="(dan, index) in danItem" :key="index">
                    <v-card
                        class="ma-2 refDCard"
                        height="150"
                        width="100"
                        @click="setDanFridgeType(index)"
                    >
                        <img :src="dan" alt="냉장고이미지" />
                    </v-card>
                </v-slide-item>
            </v-slide-group>
        </div>
        <div class="centerContent">
            <p class="font-18">양문형 (인기순)</p>
            <v-slide-group center-active show-arrows>
                <v-slide-item v-for="(yang, index) in yangItem" :key="index">
                    <v-card
                        class="ma-2 refYCard"
                        height="150"
                        width="100"
                        v-model="fridge.index"
                        @click="setYangFridgeType(index)"
                    >
                        <img :src="yang" alt="냉장고이미지" />
                    </v-card>
                </v-slide-item>
            </v-slide-group>
        </div>
        <v-row>
            <v-spacer></v-spacer>
            <div class="registBtn" @click="RegisterFridge()">등록</div>
        </v-row>
    </v-container>
</template>
<script>
import { addMyFridge } from '../../api/refrigerator';
import { moveMain } from '@/api/move.js';
// import http from '../../api/axios.js';
import jwt_decode from 'jwt-decode';
import swal from 'sweetalert';
export default {
    data() {
        return {
            fridge: {
                name: '',
                type: 0,
                user: {
                    id: 0,
                },
            },
            selectItem: ['전체', '단문형', '양문형'],
            danItem: [
                require('@/assets/images/refrigerator/ref_44.png'),
                require('@/assets/images/refrigerator/ref_55.png'),
                require('@/assets/images/refrigerator/ref_66.png'),
            ],
            yangItem: [
                require('@/assets/images/refrigerator/ref_4444.png'),
                require('@/assets/images/refrigerator/ref_5555.png'),
                require('@/assets/images/refrigerator/ref_4444.png'),
            ],
        };
    },
    methods: {
        RegisterFridge() {
            let token = localStorage.getItem('X-AUTH-TOKEN');
            let decode = jwt_decode(token); // 가져온 token을 decode함.
            this.fridge.user.id = decode.sub;
            addMyFridge(this.fridge)
                .then(() => {
                    swal('추가 성공!', {
                        icon: 'success',
                    });
                    moveMain();
                })
                .catch(() => {
                    swal('추가 실패!', {
                        icon: 'error',
                    });
                });
        },
        setDanFridgeType(index) {
            let refDCard = document.querySelectorAll('.refDCard');
            let refYCard = document.querySelectorAll('.refYCard');
            refDCard.forEach((refDCard) => refDCard.classList.remove('checked'));
            refYCard.forEach((refYCard) => refYCard.classList.remove('checked'));
            refDCard[index].classList.toggle('checked');

            switch (index) {
                case 0:
                    this.fridge.type = 44;
                    break;
                case 1:
                    this.fridge.type = 55;
                    break;
                case 2:
                    this.fridge.type = 66;
                    break;
            }
        },
        setYangFridgeType(index) {
            let refDCard = document.querySelectorAll('.refDCard');
            let refYCard = document.querySelectorAll('.refYCard');
            refDCard.forEach((refDCard) => refDCard.classList.remove('checked'));
            refYCard.forEach((refYCard) => refYCard.classList.remove('checked'));
            refYCard[index].classList.toggle('checked');
            switch (index) {
                case 0:
                    this.fridge.type = 4444;
                    break;
                case 1:
                    this.fridge.type = 5555;
                    break;
                case 2:
                    this.fridge.type = 4444;
                    break;
            }
        },
    },
};
</script>
<style scoped>
@import './../../assets/css/refAdd.css';
</style>
