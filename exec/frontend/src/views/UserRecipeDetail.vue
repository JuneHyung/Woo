<template>
    <v-container>
        <div>
            <v-row>
                <p style="font-size: 24px; line-height: 24px; margin-left: 15px !important">
                    {{ post.title }}
                </p>
            </v-row>
        </div>
        <div>
            <p>요리이름 : {{ post.title }}</p>
            <p>업로드 날짜 : {{ post.date }}</p>
            <p>작성자 : {{ post.user_name }}</p>
        </div>
        <div v-for="(img, index) in post.imageStrArr" :key="index">
            <img :src="`data:image/jpg;base64,${img}`" alt="Customer_image" />
        </div>
    </v-container>
</template>

<script>
import http from '@/api/axios.js';

export default {
    name: 'UserRecipeDetail',
    data: () => ({
        post: '',
    }),
    created() {
        this.post_id = this.$route.params.id;

        http.get(`post/detail/${this.post_id}`)
            .then((response) => {
                if (response.data.message == 'success') {
                    console.log(response);
                    this.post = response.data.post;
                    this.id = this.post.id;
                } else {
                    alert('조회실패');
                }
            })
            .catch(() => {});
    },
    methods: {
        init() {
            http.get(`post/detail/${this.post_id}`)
                .then((response) => {
                    if (response.data.message == 'success') {
                        this.post = response.data.post;
                        this.id = this.post.id;
                    } else {
                        alert('정보 조회 실패');
                    }
                })
                .catch(() => {});
        },
    },
};
</script>

<style scoped></style>
