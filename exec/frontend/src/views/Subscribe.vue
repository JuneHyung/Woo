<template>
    <v-container>
        <div style="padding-bottom: 15px">
            <v-row>
                <img :src="subscribe" alt="구독 이미지" class="subscribeImg" />
                <p class="subscribeTitle" style="margin-left: 10px !important">구독</p>
                <v-spacer></v-spacer>
                <button style="margin-bottom: 5px" @click="goRecipeCreate()">레시피 등록</button>
            </v-row>
        </div>
        <v-row>
            <div
                @click="moveRecipeDetail()"
                v-for="(food, index) in foods"
                :key="index"
                style="width: 165px; height: 200px; margin: 0 auto"
            >
                <!-- <router-link to="/Recipedetail"> -->
                <img
                    :src="food"
                    alt="된장찌개"
                    class="foodImg"
                    style="width: 160px; height: 160px; margin: 0 auto"
                />
                <p style="text-align: center">엄마의 손맛 구수한 된장찌개</p>
                <p style="text-align: center">작성자 : 민주</p>
                <!-- </router-link> -->
            </div>
        </v-row>
    </v-container>
</template>
<script>
export default {
    name: 'Subscribe',
    data() {
        return {
            subscribe: require('@/assets/images/header/subscribe.png'),
            foods: [
                require('@/assets/images/계란말이.jpg'),
                require('@/assets/images/된장찌개.jpg'),
                require('@/assets/images/참치김치찌개.jpg'),
                require('@/assets/images/투움바파스타.jpg'),
                require('@/assets/images/치즈밥.jpg'),
                require('@/assets/images/카레라이스.jpg'),
            ],
        };
    },
    methods: {
        moveRecipeDetail() {
            this.$router.push({ name: 'RecipeDetail' });
        },
        goRecipeCreate() {
            this.$router.push({ name: 'RecipeCreate' });
        },
    },
};
</script>

<style scoped>
.subscribeImg {
    width: 30px;
    height: 70%;
    margin: auto 1px;
}
.subscribeTitle {
    font-size: 24px;
    line-height: 24px;
}

.foodImg {
    width: 100%;
    height: 100px;
}
</style>
