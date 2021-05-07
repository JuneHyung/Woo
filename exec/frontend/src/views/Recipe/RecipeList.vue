<template>
    <v-container>
        <v-row>
            <div style="width: 24px; height: 40px">
                <img
                    src="../../assets/images/header/sharing.png"
                    alt="레시피"
                    style="width: 100%; height: 70%"
                />
            </div>
            <p style="font-size: 24px; line-height: 24px; margin-left: 15px !important">
                레시피 목록
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

                        font-family: 'MaruBuri-Regular' !important;
                    "
                    class="infobox"
                >
                    <p style="font-size: 16px; font-weight: 900" class="shorthand">
                        {{ list.name }}
                    </p>
                    <div style="border: 1px solid white; height: 1px"></div>
                    <div style="font-size: 12px; font-weight: 900">
                        <p>Main : {{ list.category }}</p>
                        <v-row>
                            <p>Sub : {{ list.subcategory }}</p>
                            <v-spacer></v-spacer>
                            <p>조회수 : {{ list.views }}</p>
                        </v-row>
                    </div>
                </div>
            </div>
        </v-row>
        <v-spacer></v-spacer>
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
            page: 0,
            size: 6,
        };
    },

    created() {
        this.ingredient_id = this.$route.params.ingredient_id;
        this.getRecipeList();
    },
    watch: {
        $route() {
            console.log('확인!');
            this.ingredient_id = this.$route.params.ingredient_id;
            this.getRecipeList();
        },
    },
    methods: {
        goRecipeCreate() {
            this.$router.push({ name: 'RecipeCreate' });
        },
        goRecipeDetail(recipe_id) {
            this.upViews(recipe_id);
            this.$router.push({ name: 'RecipeDetail', params: { recipe_id: recipe_id } });
        },
        upViews(recipe_id) {
            http.get(`recipes/views/${recipe_id}`)
                .then(({ data }) => {
                    console.log(data);
                })
                .catch((error) => console.log(error));
        },
        getRecipeList() {
            if (this.ingredient_id == 0) {
                console.log('다시왔니?');
                http.get(`recipes/${this.page}/${this.size}`)
                    .then(({ data }) => {
                        this.ingredient_id = this.$route.params.ingredient_id;
                        this.listItem = data.recipelist;
                        for (var i = 0; i < this.listItem.length; i++) {
                            let temp = this.listItem[i].url;
                            let urlId = temp.substr(17);
                            let thumbnail = `http://img.youtube.com/vi/${urlId}/maxresdefault.jpg`;
                            this.listItem[i].thumbnail = thumbnail;
                            console.log(this.listItem[i].thumbnail);
                        }
                    })
                    .catch((error) => console.log(error));
            } else {
                console.log(`${this.ingredient_id}`);
                http.get(`recipes/ingredients/${this.ingredient_id}/${this.page}/${this.size}`)
                    .then(({ data }) => {
                        console.log('여기?');
                        this.listItem = data.recipelist;
                        for (var i = 0; i < this.listItem.length; i++) {
                            let temp = this.listItem[i].url;
                            let urlId = temp.substr(17);
                            let thumbnail = `http://img.youtube.com/vi/${urlId}/maxresdefault.jpg`;
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

<style scoped>
.infobox p {
    color: #fff;
    font-family: 'MaruBuri-Regular' !important;
}
</style>
