<template>
    <v-container>
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
            <v-row>
                <p class="font-18">이름 : {{ item.name }}</p>
                <v-spacer></v-spacer>
                <p class="font-18">조회수 : {{ item.views }}</p>
            </v-row>
            <v-row>
                <p class="font-18">분류 : {{ item.category }} | {{ item.subcategory }}</p>
            </v-row>
        </div>
    </v-container>
</template>

<script>
// import http from '../../api/axios.js';
import { getRecipeDetail } from '../../api/recipe.js';
export default {
    data() {
        return {
            recipe_id: 0,
            item: {},
            videoUrl: '',
        };
    },
    mounted() {
        this.recipe_id = this.$route.params.recipe_id;

        this.getRecipeDetail();
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
    },
};
</script>
