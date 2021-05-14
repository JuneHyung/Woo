<template>
    <v-container>
        <div v-if="loadingDialog">
            <img :src="loadingIcon" alt="로딩 ui" />
        </div>
        <div v-else>
            <h1 style="font-size: 32px">ㅇ{{ item.name }}</h1>
            <div style="width: 100%; height: 300px; margin: 20px auto">
                <iframe
                    id="mainVideo"
                    width="100%"
                    height="300"
                    :src="videoUrl"
                    title="YouTube video player"
                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                    allowfullscreen
                    style="border-radius: 20px; border: 1px solid black"
                ></iframe>
            </div>
            <div>
                <v-row style="margin: 0 20px !important; border-bottom: 3px solid #ffecf2">
                    <p class="font-20">이름 : {{ item.name }}</p>
                    <v-spacer></v-spacer>
                    <p class="font-20">조회수 : {{ item.views }}</p>
                </v-row>
                <v-row style="margin: 0 20px !important">
                    <p class="font-20">분류 : {{ item.category }} | {{ item.subcategory }}</p>
                </v-row>
                <v-row style="margin: 0 20px !important">
                    <span style="font-size: 20px; margin-right: 10px">주 재료 : </span>
                    <p
                        v-for="(main, index) in mainList"
                        :key="index"
                        style="margin-right: 10px !important; font-size: 20px"
                    >
                        {{ main }}
                    </p>
                </v-row>
                <v-row style="margin: 0 20px !important">
                    <span style="font-size: 20px; margin-right: 10px">부 재료 : </span>
                    <p
                        v-for="(sub, index) in subList"
                        :key="index"
                        style="margin-right: 10px !important; font-size: 20px"
                    >
                        {{ sub }}
                    </p>
                </v-row>
            </div>
        </div>
    </v-container>
</template>

<script>
// import http from '../../api/axios.js';
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
                    let temp = this.item.url.substr(17);

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
