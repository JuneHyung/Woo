<template>
    <v-container>
        <v-row>
            <div style="width: 24px; height: 40px">
                <img :src="meal" alt="요리이미지" class="mealImg" />
            </div>
            <p style="font-size: 24px; line-height: 24px; margin-left: 15px !important">
                {{ post.title }}
            </p>
        </v-row>
        <div>
            <p class="font-14">요리이름 : {{ post.title }}</p>
            <p class="font-14">업로드 날짜 : {{ post.date }}</p>
            <p class="font-14">작성자 : {{ post.user_name }}</p>
        </div>
        <p class="font-14">요리 방법</p>

        <div style="width: 100%; height: 230px">
            <img
                :src="`data:image/jpg;base64,${imageUrl}`"
                alt="시작이미지"
                style="width: 100%; height: 100%"
            />
        </div>
        <template>
            <v-sheet class="mx-auto">
                <v-slide-group center-active>
                    <v-slide-item v-for="(img, index) in post.imageStrArr" :key="index">
                        <v-row>
                            <v-card
                                class="ma-2 thumbnail"
                                height="200"
                                width="120"
                                @click="changeImage(img)"
                            >
                                <img
                                    :src="`data:image/jpg;base64,${img}`"
                                    alt="시작이미지"
                                    style="width: 100%; height: 100%"
                                />
                            </v-card>
                        </v-row>
                    </v-slide-item>
                </v-slide-group>
            </v-sheet>
        </template>
    </v-container>
</template>

<script>
// import http from '@/api/axios.js';
import { getPostDetail } from '@/api/subscribe.js';

export default {
    name: 'UserRecipeDetail',
    data() {
        return {
            post: '',
            meal: require('@/assets/images/요리.png'),
            post_id: '',
            imageUrl: '',
        };
    },
    created() {
        this.post_id = this.$route.params.id;
        this.getDetailPost();
    },
    methods: {
        getDetailPost() {
            getPostDetail(this.post_id)
                .then((response) => {
                    if (response.data.message == 'success') {
                        this.post = response.data.post;
                        this.imageUrl = response.data.post.imageStrArr[0];
                    } else {
                        alert('정보 조회 실패');
                    }
                })
                .catch(() => {});
        },
        changeImage(url) {
            this.imageUrl = url;
        },
    },
};
</script>

<style scoped>
.mealImg {
    width: 100%;
    height: 70%;
}
</style>
