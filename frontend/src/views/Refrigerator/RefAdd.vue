<template>
    <v-container class="addBox">
        <p>냉장고 추가</p>
        <v-text-field label="냉장고이름" type="text" v-model="fridge.name"></v-text-field>
        <div class="col-5">
            <v-select :items="selectItem" label="선택해 주세여" dense solo></v-select>
        </div>
        <div>
            <p>단문형(인기순)</p>
            <v-slide-group center-active show-arrows>
                <v-slide-item v-for="(dan, index) in danItem" :key="index">
                    <v-card class="ma-2" height="150" width="100" @click="setDanFridgeType(index)">
                        <img :src="dan" alt="냉장고이미지" style="width: 100%; height: 100%" />
                    </v-card>
                </v-slide-item>
            </v-slide-group>
        </div>
        <div class="centerContent">
            <p>양문형 (인기순)</p>
            <v-slide-group center-active show-arrows>
                <v-slide-item v-for="(yang, index) in yangItem" :key="index">
                    <v-card
                        class="ma-2"
                        height="150"
                        width="100"
                        v-model="fridge.index"
                        @click="setYangFridgeType(index)"
                    >
                        <img :src="yang" alt="냉장고이미지" style="width: 100%; height: 100%" />
                    </v-card>
                </v-slide-item>
            </v-slide-group>
        </div>
        <v-row>
            <v-spacer></v-spacer>
            <div class="addBtn" @click="RegisterFridge()">등록</div>
        </v-row>
    </v-container>
</template>
<script>
import http from '../../api/axios.js';
export default {
    data() {
        return {
            fridge: {
                name: '',
                type: 0,
                user: {
                    id: 6,
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
            http.post(`fridge/create`, this.fridge)
                .then(() => {
                    alert('추가성공');
                })
                .catch(() => {
                    alert('통신실패');
                });
        },
        setDanFridgeType(index) {
            switch (index) {
                case 0:
                    this.fridge.type = 22;
                    break;
                case 1:
                    this.fridge.type = 33;
                    break;
                case 2:
                    this.fridge.type = 44;
                    break;
            }
        },
        setYangFridgeType(index) {
            switch (index) {
                case 0:
                    this.fridge.type = 55;
                    break;
                case 1:
                    this.fridge.type = 66;
                    break;
                case 2:
                    this.fridge.type = 1;
                    break;
            }
        },
    },
};
</script>
<style scoped>
.addBox img {
    cursor: pointer;
}
.selectBox {
    height: 30px;
}
</style>
