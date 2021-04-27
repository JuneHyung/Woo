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
                <div class="addBtn">+ 재료 추가</div>
                <div class="minusBtn">- 재료 빼기</div>
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
export default {
    data() {
        return {
            garbage: ['사과', '바나나', '메론', '포도'],
        };
    },
};
</script>
<style scoped></style>
