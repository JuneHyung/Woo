<template>
    <v-container>
        <v-row class="mb-5">
            <div class="recipeIconBox">
                <img src="../../assets/images/header/sharing.png" alt="레시피" />
            </div>
            <p class="titleText ml-2">레시피</p>
            <v-spacer></v-spacer>
        </v-row>
        <v-spacer></v-spacer>
        <v-row>
            <div
                v-for="(list, index) in listItem"
                :key="index"
                @click="goRecipeDetail(list.id)"
                class="videoThumbnailBox mx-1 my-1"
            >
                <v-spacer></v-spacer>
                <img :src="list.thumbnail" alt="이미지" />
                <div class="cookInfobox px-2 py-1" style="background-color: #000">
                    <p class="shorthand">
                        {{ list.name }}
                    </p>
                    <div class="cookInfoDivider my-1"></div>
                    <div class="cookInfoBottomBox">
                        <v-row>
                            <p>Main : {{ list.category }}</p>
                            <v-spacer></v-spacer>
                            <span class="ml-1"
                                ><v-icon class="viewIcon">mdi-eye</v-icon> {{ list.views }}</span
                            >
                        </v-row>
                        <p>Sub : {{ list.subcategory }}</p>
                        <p></p>
                    </div>
                </div>
            </div>
        </v-row>
        <v-spacer></v-spacer>
    </v-container>
</template>
<script>
// import http from '../../api/axios.js';
import { getRecipeListByMenu, getRecipeListByIngredients, viewsUp } from '../../api/recipe.js';
import { moveRecipeCreate, moveRecipeDetail } from '@/api/move.js';
export default {
    data() {
        return {
            listItem: [],
            ingredient_id: 0,
            page: 0,
            size: 6,
            isLoading: true,
        };
    },

    created() {
        this.ingredient_id = this.$route.params.ingredient_id;
        this.getRecipeList();
        window.addEventListener('scroll', this.scroll);
    },
    watch: {
        $route() {
            this.ingredient_id = this.$route.params.ingredient_id;
            // this.isLoading = true;
            // this.getRecipeList();
        },
    },
    methods: {
        goRecipeCreate() {
            moveRecipeCreate();
        },
        goRecipeDetail(recipe_id) {
            this.upViews(recipe_id);
            moveRecipeDetail(recipe_id);
        },
        scroll() {
            let scrolledToBottom =
                document.documentElement.scrollTop + window.innerHeight ===
                document.documentElement.offsetHeight;

            if (scrolledToBottom && this.isLoading) {
                setTimeout(this.getRecipeList, 500);
            }
        },
        upViews(recipe_id) {
            viewsUp(recipe_id)
                .then(() => {})
                .catch((error) => console.log(error));
        },
        getRecipeList() {
            if (this.ingredient_id == 0) {
                getRecipeListByMenu(this.page, this.size)
                    .then(({ data }) => {
                        this.ingredient_id = this.$route.params.ingredient_id;
                        let recipeList = data.recipelist;
                        if (recipeList.length < this.size) {
                            this.isLoading = false;
                            recipeList.forEach((el) => {
                                this.listItem.push(el);
                            });
                            for (var i = 0; i < this.listItem.length; i++) {
                                let temp = this.listItem[i].url;
                                let urlId = temp.substr(17);
                                let thumbnail = `http://img.youtube.com/vi/${urlId}/maxresdefault.jpg`;
                                this.listItem[i].thumbnail = thumbnail;
                            }
                        } else {
                            this.isLoading = true;
                            recipeList.forEach((el) => {
                                this.listItem.push(el);
                            });
                            for (i = 0; i < this.listItem.length; i++) {
                                let temp = this.listItem[i].url;
                                let urlId = temp.substr(17);
                                let thumbnail = `http://img.youtube.com/vi/${urlId}/maxresdefault.jpg`;
                                this.listItem[i].thumbnail = thumbnail;
                            }

                            this.page++;
                        }
                    })
                    .catch((error) => console.log(error));
            } else {
                getRecipeListByIngredients(this.ingredient_id, this.page, this.size)
                    .then(({ data }) => {
                        this.ingredient_id = this.$route.params.ingredient_id;
                        let recipeList = data.recipelist;
                        if (recipeList.length < this.size) {
                            this.isLoading = false;
                            recipeList.forEach((el) => {
                                this.listItem.push(el);
                            });
                            for (var i = 0; i < this.listItem.length; i++) {
                                let temp = this.listItem[i].url;
                                let urlId = temp.substr(17);
                                let thumbnail = `http://img.youtube.com/vi/${urlId}/maxresdefault.jpg`;
                                this.listItem[i].thumbnail = thumbnail;
                            }
                        } else {
                            this.isLoading = true;
                            recipeList.forEach((el) => {
                                this.listItem.push(el);
                            });
                            for (i = 0; i < this.listItem.length; i++) {
                                let temp = this.listItem[i].url;
                                let urlId = temp.substr(17);
                                let thumbnail = `http://img.youtube.com/vi/${urlId}/maxresdefault.jpg`;
                                this.listItem[i].thumbnail = thumbnail;
                            }

                            this.page++;
                        }
                    })
                    .catch((error) => console.log(error));
            }
        },
    },
};
</script>

<style scoped>
@import './../../assets/css/recipeList.css';
</style>
