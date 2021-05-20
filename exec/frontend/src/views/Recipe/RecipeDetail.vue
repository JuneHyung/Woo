<template>
    <v-container>
        <div v-if="loadingDialog">
            <img :src="loadingIcon" alt="로딩 ui" />
        </div>
        <div v-else>
            <h1 class="font-32">ㅇ{{ item.name }}</h1>
            <div class="videoBox">
                <iframe
                    id="mainVideo"
                    width="100%"
                    height="300"
                    :src="videoUrl"
                    title="YouTube video player"
                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                    allowfullscreen
                ></iframe>
            </div>
            <div>
                <v-row class="videoInfoTopBox">
                    <p class="font-20">이름 : {{ item.name }}</p>
                    <v-spacer></v-spacer>
                    <p class="font-20">조회수 : {{ item.views }}</p>
                </v-row>
                <v-row class="mx-5">
                    <p class="font-20">분류 : {{ item.category }} | {{ item.subcategory }}</p>
                </v-row>
                <v-row class="mx-5">
                    <span class="font-20 mr-2">주 재료 : </span>
                    <p v-for="(main, index) in mainList" :key="index" class="font-20 mr-2">
                        {{ main }}
                    </p>
                </v-row>
                <v-row class="mx-5">
                    <span class="font-20 mr-2">부 재료 : </span>
                    <p v-for="(sub, index) in subList" :key="index" class="font-20 mr-2">
                        {{ sub }}
                    </p>
                </v-row>
            </div>
        </div>
    </v-container>
</template>

<script>
import { getRecipeDetail, getRecipeMain, getRecipeSub } from '../../api/recipe.js';
export default {
    data() {
        return {
            recipe_id: 0,
            item: {},
            videoUrl: '',
            mainList: [],
            subList: [],
            loadingDialog: false,
            loadingIcon: require('@/assets/images/loading.gif'),
        };
    },
    created() {
        this.recipe_id = this.$route.params.recipe_id;
        this.loadingDialog = true;
        setTimeout(this.toggleLoading, 2000);
    },
    mounted() {
        this.getRecipeDetail();
        this.getMainList();
        this.getSubList();
    },
    methods: {
        changeImage(thumbnail) {
            this.url = thumbnail;
        },
        getRecipeDetail() {
            getRecipeDetail(this.recipe_id)
                .then(({ data }) => {
                    this.item = data.recipe;
                    // 유튜브 url에서 아이디부분을 자름.
                    let temp = this.item.url.substr(17);
                    // 아래 형식으로 url을 써야 video가 나와 데이터 가공.
                    this.videoUrl = 'https://www.youtube.com/embed/' + temp;
                })
                .catch((error) => console.log(error));
        },
        getMainList() {
            let id = this.$route.params.recipe_id;

            getRecipeMain(id)
                .then((response) => {
                    this.mainList = response.data.recipeMain;
                })
                .catch((error) => console.log(error));
        },
        getSubList() {
            getRecipeSub(this.$route.params.recipe_id)
                .then((response) => {
                    this.subList = response.data.recipeSub;
                })
                .catch((error) => console.log(error));
        },
        toggleLoading() {
            this.loadingDialog = false;
        },
    },
};
</script>
<style scoped>
@import './../../assets/css/recipeDetail.css';
</style>
