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
                @click="goRecipeDetail(list.id)"
                style="cursor: pointer"
            >
                <img :src="list.thumbnail" alt="이미지" style="width: 60px; height: 60px" />
                <div style="margin-left: 10px">
                    <p>{{ list.name }}</p>
                    <p>category : {{ list.category }} | {{ list.subcategory }}</p>
                    <p>{{ list.views }}</p>
                </div>
            </v-row>
        </div>
    </v-container>
</template>
<script>
import http from '../../api/axios.js';
export default {
    data() {
        return {
            listItem: [
                {
                    category: '',
                    id: '',
                    name: '',
                    subcategory: '',
                    url: '',
                    views: '',
                    thumbnail: '',
                },
            ],
            ingredient_id: 0,
        };
    },
    created() {
        this.getRecipeList();
    },
    methods: {
        goRecipeCreate() {
            this.$router.push({ name: 'RecipeCreate' });
        },
        goRecipeDetail(recipe_id) {
            this.$router.push({ name: 'RecipeDetail', params: { recipe_id: recipe_id } });
        },
        getRecipeList() {
            if (this.ingredient_id == 0) {
                http.get(`recipes`)
                    .then(({ data }) => {
                        this.ingredient_id = this.$route.params.ingredient_id;
                        this.listItem = data.recipelist;
                        for (var i = 0; i < this.listItem.length; i++) {
                            let temp = this.listItem[i].url;
                            let urlId = temp.substr(17);
                            let thumbnail = `http://img.youtube.com/vi/${urlId}/0.jpg`;
                            this.listItem[i].thumbnail = thumbnail;
                            console.log(this.listItem[i].thumbnail);
                        }
                    })
                    .catch((error) => console.log(error));
            } else {
                http.get(`detail/${this.ingredient_id}`)
                    .then(({ data }) => {
                        this.listItem = data.recipelist;
                        for (var i = 0; i < this.listItem.length; i++) {
                            let temp = this.listItem[i].url;
                            let urlId = temp.substr(17);
                            let thumbnail = `http://img.youtube.com/vi/${urlId}/0.jpg`;
                            this.listItem[i].thumbnail = thumbnail;
                            console.log(this.listItem[i].thumbnail);
                        }
                    })
                    .catch((error) => console.log(error));
            }
        },
    },
};
</script>
