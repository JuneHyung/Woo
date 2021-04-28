<template>
    <v-container>
        <div>
            <p>자취방 냉장고</p>
            <div style="width: 100%; height: 200px">
                <img
                    :src="require('@/assets/images/refrigerator/ref_44.png')"
                    alt="냉장고 이미지"
                    style="width: 100%; height: 100%"
                />
            </div>
            <v-row>
                <v-spacer></v-spacer>
                <v-dialog v-model="addDialog">
                    <template v-slot:activator="{ on, attrs }">
                        <div class="addBtn" v-bind="attrs" v-on="on">+ 재료 추가</div>
                    </template>
                    <v-card>
                        <v-card-title>재료를 추가해주세요!</v-card-title>
                        <v-card-text>
                            재료추가가 들어갈 예정입니다. 재료추가가 들어갈 예정입니다. 재료추가가
                            들어갈 예정입니다. 재료추가가 들어갈 예정입니다.
                        </v-card-text>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="primary" text @click="addDialog = false">
                                추가완료
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
                <v-dialog v-model="minusDialog">
                    <template v-slot:activator="{ on, attrs }">
                        <div class="minusBtn" v-bind="attrs" v-on="on">- 재료 빼기</div>
                    </template>
                    <v-card>
                        <v-card-title>재료를 빼주세요!</v-card-title>
                        <v-card-text>
                            재료제거가 들어갈 예정입니다. 재료제거가 들어갈 예정입니다. 재료제거가
                            들어갈 예정입니다. 재료제거가 들어갈 예정입니다.
                        </v-card-text>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="primary" text @click="minusDialog = false">
                                빼기완료
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
            </v-row>
        </div>
        <div stlye="margin-top:50px;">
            <p>유통기한 1주일 미만</p>
            <v-slide-group center-active show-arrows>
                <v-slide-item v-for="(gar, index) in garbage" :key="index">
                    <v-card class="ma-1" height="120" width="80">
                        <img
                            :src="require('@/assets/images/ingredients/apple.png')"
                            alt="재료이미지"
                            style="width: 100%; height: 85%; padding: 5px"
                        />
                        <p style="text-align: center; height: 15%">{{ gar }}</p>
                    </v-card>
                </v-slide-item>
            </v-slide-group>
        </div>
    </v-container>
</template>
<script>
import http from '../../api/axios.js';
export default {
    data() {
        return {
            addDialog: false,
            minusDialog: false,
            ref_id: 0,
            garbage: ['사과', '바나나', '메론', '포도'],
            ingredients: [],
        };
    },

    created() {
        this.ref_id = this.$route.params.rid;
        this.getIngredients();
    },
    methods: {
        getIngredients() {
            // console.log('ref_id : ' + this.ref_id);
            http.get(`fridge/ingredients/${this.ref_id}`)
                .then((response) => {
                    console.log(response.data);
                    this.ingredients = response.data;
                    alert('정보받기 성공');
                })
                .catch(() => {
                    alert('정보받기 실패!');
                });
        },
    },
};
</script>
<style scoped></style>
