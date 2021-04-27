<template>
    <v-container>
        <v-row>
            <div style="width: 24px; height: 24px">
                <img
                    src="../../assets/images/header/sharing.png"
                    alt="레시피"
                    style="width: 100%; height: 100%"
                />
            </div>
            <p style="font-size: 24px; line-height: 24px; margin-left: 15px !important">
                레시피 목록
            </p>
            <v-spacer></v-spacer>
            <div class="addBtn" @click="goRecipeCreate()">레시피 등록</div>
        </v-row>

        <div>
            <v-row
                v-for="(list, index) in listItem"
                :key="index"
                class="my-5"
                @click="goRecipeDetail()"
                style="cursor: pointer"
            >
                <img :src="list.img" alt="이미지" style="width: 60px; height: 60px" />
                <div style="margin-left: 10px">
                    <p>{{ list.title }}</p>
                    <p>내용 : {{ list.description }}</p>
                    <p>{{ list.writeDate }}</p>
                </div>
            </v-row>
        </div>
    </v-container>
</template>
<script>
export default {
    data() {
        return {
            listItem: [
                {
                    title: '리스트 1번',
                    description: '1번 요리 맛나다 맛나.',
                    img: require('@/assets/images/ingredients/apple.png'),
                    writeDate: '2021-04-29',
                },
                {
                    title: '리스트 2번',
                    description: '2번 요리 맛나다 맛나.',
                    img: require('@/assets/images/ingredients/banana.png'),
                    writeDate: '2021-04-28',
                },
                {
                    title: '리스트 3번',
                    description: '3번 요리 맛나다 맛나.',
                    img: require('@/assets/images/ingredients/beaf.png'),
                    writeDate: '2021-04-27',
                },
                {
                    title: '리스트 4번',
                    description: '4번 요리 맛나다 맛나.',
                    img: require('@/assets/images/ingredients/chicken.jpg'),
                    writeDate: '2021-04-26',
                },
                {
                    title: '리스트 5번',
                    description: '5번 요리 맛나다 맛나.',
                    img: require('@/assets/images/ingredients/lemon.png'),
                    writeDate: '2021-04-25',
                },
                {
                    title: '리스트 6번',
                    description: '6번 요리 맛나다 맛나.',
                    img: require('@/assets/images/ingredients/plum.png'),
                    writeDate: '2021-04-24',
                },
            ],
        };
    },
    methods: {
        goRecipeCreate() {
            this.$router.push({ name: 'RecipeCreate' });
        },
        goRecipeDetail() {
            this.$router.push({ name: 'RecipeDetail' });
        },
    },
};
</script>
