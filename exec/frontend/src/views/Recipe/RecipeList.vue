<template>
    <v-container>
        <v-row style="margin-bottom: 20px !important">
            <div style="width: 24px; height: 40px">
                <img
                    src="../../assets/images/header/sharing.png"
                    alt="레시피"
                    style="width: 100%; height: 70%"
                />
            </div>
            <p
                style="
                    font-size: 28px;
                    line-height: 24px;
                    margin-left: 15px !important;
                    text-decoration: underline;
                    text-decoration-style: wavy;
                    text-underline-position: under;
                "
            >
                레시피
            </p>
            <v-spacer></v-spacer>
        </v-row>
        <v-spacer></v-spacer>
        <v-row>
            <div
                v-for="(list, index) in listItem"
                :key="index"
                @click="goRecipeDetail(list.id)"
                style="width: 160px; height: 215px; margin: 0 3.5px"
            >
                <v-spacer></v-spacer>
                <img :src="list.thumbnail" alt="이미지" style="width: 100%; height: 125px" />
                <div
                    style="
                        background-color: black;
                        padding: 5px 15px;

                        font-family: 'MaruBuri-Regular', sans-serif !important;
                    "
                    class="infobox"
                >
                    <p style="font-size: 16px; font-weight: 900" class="shorthand">
                        {{ list.name }}
                    </p>
                    <div style="border: 1px solid white; height: 1px"></div>
                    <div style="font-size: 12px; font-weight: 900">
                        <v-row>
                            <p>Main : {{ list.category }}</p>
                            <v-spacer></v-spacer>
                            <span style="color: #fff"
                                ><v-icon style="color: #fff; font-size: 14px">mdi-eye</v-icon>
                                {{ list.views }}</span
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
.infobox p {
    color: #fff;
    font-family: 'MaruBuri-Regular', sans-serif !important;
}
</style>
