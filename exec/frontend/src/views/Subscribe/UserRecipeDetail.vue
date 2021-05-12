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
            <p class="font-18">요리이름 : {{ post.title }}</p>
            <p class="font-18">업로드 날짜 : {{ post.date }}</p>
            <v-row class="font-18">
                <p class="font-18">작성자 : {{ post.user_name }}</p>
                <v-spacer></v-spacer>
                <img
                    :src="subscribeY"
                    alt="구독하기"
                    v-if="!subscribeFlag"
                    style="width: 100px; height: 60px; margin-left: 20px !important; display: block"
                    @click="startSubscribe()"
                />
                <img
                    :src="subscribeN"
                    alt="구독취소"
                    v-else
                    @click="endSubscribe()"
                    style="width: 100px; height: 60px; margin-left: 20px !important; display: block"
                />
            </v-row>
        </div>
        <p class="font-24" style="margin-top: 20px !important">ㅇ요리 방법</p>

        <div style="width: calc(100% - 20px); height: 230px; margin: 0 auto">
            <img
                :src="`data:image/jpg;base64,${imageUrl}`"
                alt="시작이미지"
                style="width: 100%; height: 100%"
            />
        </div>
        <template>
            <v-sheet class="mx-auto" style="width: calc(100% - 20px); margin: 0 auto">
                <v-slide-group center-active show-arrows>
                    <v-slide-item v-for="(img, index) in post.imageStrArr" :key="index">
                        <v-row>
                            <v-card
                                class="ma-2 thumbnail"
                                height="100"
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
import { getPostDetail, getCheckSubscribe, subscribe, subscribeCancel } from '@/api/subscribe.js';

export default {
    name: 'UserRecipeDetail',
    data() {
        return {
            post: [],
            meal: require('@/assets/images/요리.png'),
            post_id: '',
            post_user_id: Number,
            imageUrl: '',
            userList: [],
            subscribeFlag: false,
            subscribeY: require('@/assets/images/subscribe.png'),
            subscribeN: require('@/assets/images/subcancle.png'),
        };
    },
    created() {
        this.post_id = this.$route.params.post_id;
        this.getDetailPost();
    },
    methods: {
        getDetailPost() {
            getPostDetail(this.post_id)
                .then((response) => {
                    if (response.data.message == 'success') {
                        this.post = response.data.post;
                        this.imageUrl = response.data.post.imageStrArr[0];
                        this.post_user_id = response.data.post.user_id;
                        this.checkSubscribe();
                    } else {
                        alert('정보 조회 실패');
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
                    console.log(`${el.id} , ${this.post_user_id}`);
                    if (el.id == this.post.user_id) {
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
                    alert('확인');
                })
                .catch((error) => console.log(error));
        },
        endSubscribe() {
            console.log(`id : ${this.post_user_id}`);
            subscribeCancel(this.post_user_id)
                .then(() => {
                    this.subscribeFlag = false;
                    alert('확인');
                })
                .catch((error) => console.log(error));
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
