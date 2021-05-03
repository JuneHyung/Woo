<template>
    <v-container>
        <div style="padding-bottom: 15px">
            <v-row>
                <v-col>
                    <img :src="subscribe" alt="구독 이미지" class="subscribeImg" />
                    <p class="subscribeTitle" style="margin-left: 10px !important; height: 70%">
                        {{ title }}
                    </p>
                </v-col>

                <v-spacer></v-spacer>
                <div>
                    <button style="margin-bottom: 5px" @click="changeSubscribePage()">
                        {{ btnTitle }}
                    </button>
                    <v-spacer></v-spacer>
                    <button style="margin-bottom: 5px" @click="goRecipeCreate()">
                        레시피 등록
                    </button>
                </div>
            </v-row>
        </div>
        <v-row>
            <div
                @click="moveRecipeDetail()"
                v-for="(food, index) in foods"
                :key="index"
                style="width: 165px; height: 200px; margin: 0 auto"
            >
                <img
                    :src="food"
                    alt="된장찌개"
                    class="foodImg"
                    style="width: 160px; height: 160px; margin: 0 auto"
                />
                <p style="text-align: center">엄마의 손맛 구수한 된장찌개</p>
                <p style="text-align: center">작성자 : 민주</p>
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
            subscribeflag: false,
            title: '전체 레시피',
            btnTitle: '구독자 레시피',
        };
    },
    methods: {
        moveRecipeDetail() {
            this.$router.push({ name: 'RecipeDetail' });
        },
        goRecipeCreate() {
            this.$router.push({ name: 'RecipeCreate' });
        },
        changeSubscribePage() {
            this.subscribeflag = !this.subscribeflag;
            if (this.subscribeflag == false) {
                this.title = '전체 레시피';
                this.btnTitle = '구독자 레시피';
            } else {
                this.title = '구독자 레시피';
                this.btnTitle = '전체 레시피';
            }
        },
    },
};
</script>

<style scoped>
.subscribeImg {
    width: 30px;
    height: 70%;
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
