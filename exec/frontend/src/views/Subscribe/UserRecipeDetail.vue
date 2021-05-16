<template>
    <v-container>
        <div v-if="loadingDialog">
            <img :src="loadingIcon" alt="로딩 ui" />
        </div>
        <div v-else>
            <v-row>
                <div class="userIconBox">
                    <img :src="meal" alt="요리이미지" class="mealImg" />
                </div>
                <p class="font-24 ml-4">
                    {{ post.title }}
                </p>
            </v-row>
            <div>
                <p class="font-18">요리이름 : {{ post.title }}</p>
                <p class="font-18">업로드 날짜 : {{ post.date }}</p>
                <v-row class="font-18">
                    <p class="font-18">작성자 : {{ post.writer }}</p>
                    <v-spacer></v-spacer>
                    <img
                        :src="subscribeY"
                        alt="구독하기"
                        v-if="!subscribeFlag"
                        class="subscribeUserBtn"
                        @click="startSubscribe()"
                    />
                    <img
                        :src="subscribeN"
                        alt="구독취소"
                        v-else
                        @click="endSubscribe()"
                        class="subscribeUserBtn"
                    />
                </v-row>
                <v-row>
                    <v-spacer></v-spacer>
                    <p class="mr-2" @click="checkGood()">
                        <v-icon>mdi-thumb-up</v-icon>
                        <span class="likeNum">{{ post.good }}</span>
                    </p>
                    <p @click="checkHate()">
                        <v-icon>mdi-thumb-down</v-icon>
                        <span class="hateNum">{{ post.hate }}</span>
                    </p>
                </v-row>
            </div>
            <p class="font-24 mt-5">ㅇ요리 방법</p>

            <div class="recipeThumbnailBox">
                <img :src="`data:image/jpg;base64,${imageUrl}`" alt="시작이미지" />
            </div>
            <template>
                <v-sheet class="mx-auto recipeSlideBox">
                    <v-slide-group center-active show-arrows>
                        <v-slide-item v-for="(img, index) in post.imageStrArr" :key="index">
                            <v-row>
                                <v-card
                                    class="ma-2 thumbnail"
                                    height="100"
                                    width="120"
                                    @click="changeImage(img)"
                                >
                                    <img :src="`data:image/jpg;base64,${img}`" alt="시작이미지" />
                                </v-card>
                            </v-row>
                        </v-slide-item>
                    </v-slide-group>
                </v-sheet>
            </template>
        </div>
    </v-container>
</template>

<script>
// import http from '@/api/axios.js';
import {
    getPostDetail,
    getCheckSubscribe,
    subscribe,
    subscribeCancel,
    checkLike,
} from '@/api/subscribe.js';
import { moveUserRecipeDetail } from '@/api/move.js';
import swal from 'sweetalert';

export default {
    name: 'UserRecipeDetail',
    data() {
        return {
            post: [],
            meal: require('@/assets/images/요리.png'),
            post_id: Number,
            post_user_id: Number,
            imageUrl: '',
            userList: [],
            subscribeFlag: false,
            subscribeY: require('@/assets/images/subscribe.png'),
            subscribeN: require('@/assets/images/subcancle.png'),
            loadingDialog: false,
            loadingIcon: require('@/assets/images/loading.gif'),
        };
    },
    created() {
        this.post_id = this.$route.params.post_id;
        this.loadingDialog = true;
        setTimeout(this.toggleLoading, 2000);
    },
    mounted() {
        this.getDetailPost();
    },
    methods: {
        getDetailPost() {
            getPostDetail(this.post_id)
                .then((response) => {
                    if (response.data.message == 'success') {
                        this.post = response.data.post;
                        this.imageUrl = response.data.post.imageStrArr[0];
                        this.post_user_id = response.data.post.userId;
                        this.checkSubscribe();
                    } else {
                        swal('정보 조회 실패!', {
                            icon: 'error',
                        });
                    }
                })
                .catch(() => {});
        },
        changeImage(url) {
            this.imageUrl = url;
        },
        checkSubscribe() {
            getCheckSubscribe().then((response) => {
                this.userList = response.data.userlist;
                this.userList.forEach((el) => {
                    if (el.id == this.post_user_id) {
                        this.subscribeFlag = true;
                    } else {
                        this.subscribeFlag = false;
                    }
                });
            });
        },
        startSubscribe() {
            subscribe(this.post_user_id)
                .then(() => {
                    this.subscribeFlag = true;
                })
                .catch((error) => console.log(error));
        },
        endSubscribe() {
            subscribeCancel(this.post_user_id)
                .then(() => {
                    this.subscribeFlag = false;
                })
                .catch((error) => console.log(error));
        },
        toggleLoading() {
            this.loadingDialog = false;
        },
        checkGood() {
            checkLike(this.post_id, 'good')
                .then((response) => {
                    if (response.data.message == 'success') {
                        moveUserRecipeDetail(this.post_id);
                    }
                })
                .catch((error) => console.log(error));
        },
        checkHate() {
            checkLike(this.post_id, 'hate')
                .then((response) => {
                    if (response.data.message == 'success') {
                        moveUserRecipeDetail(this.post_id);
                    }
                })
                .catch((error) => console.log(error));
        },
    },
};
</script>

<style scoped>
@import './../../assets/css/userRecipeDetail.css';
.mealImg {
    width: 100%;
    height: 70%;
}
</style>
