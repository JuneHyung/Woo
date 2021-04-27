<template>
    <v-container class="addBox">
        <p>냉장고 추가</p>
        <div class="col-5">
            <v-select :items="selectItem" label="선택해 주세여" dense solo></v-select>
        </div>
        <div>
            <p>단문형(인기순)</p>
            <v-slide-group center-active show-arrows>
                <v-slide-item v-for="(dan, index) in danItem" :key="index">
                    <v-card class="ma-2" height="150" width="100">
                        <img :src="dan" alt="냉장고이미지" style="width: 100%; height: 100%" />
                    </v-card>
                </v-slide-item>
            </v-slide-group>
        </div>
        <div class="centerContent">
            <p>양문형 (인기순)</p>
            <v-slide-group center-active show-arrows>
                <v-slide-item v-for="(yang, index) in yangItem" :key="index">
                    <v-card class="ma-2" height="150" width="100">
                        <img :src="yang" alt="냉장고이미지" style="width: 100%; height: 100%" />
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
