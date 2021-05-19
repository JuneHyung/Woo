<template>
    <v-container>
        <v-row>
            <div class="subscribeIconBox">
                <img :src="subscribe" alt="구독 이미지" />
            </div>
            <p class="titleText subscribeTitle">
                {{ title }}
            </p>
        </v-row>
        <v-row class="my-4">
            <v-spacer></v-spacer>
            <button class="toggleBtn" @click="changeSubscribePage()">
                {{ btnTitle }}
            </button>

            <button class="recipeCreateBtn" @click="goRecipeCreate()">레시피 등록</button>
        </v-row>
        <v-row>
            <div
                v-for="(post, index) in postList"
                :key="index"
                class="my-2 mx-auto"
                style="width: 155px; border: 1px solid black"
            >
                <div class="postImgBox">
                    <img
                        @click="moveRecipeDetail(post.id)"
                        :src="`data:image/jpg;base64,${post.imageStrArr[0]}`"
                        alt="Customer_image"
                    />
                </div>
                <div>
                    <p class="shorthand font-20 pl-2">요리이름 : {{ post.title }}</p>
                    <p class="font-20 pl-2">작성자 : {{ post.writer }}</p>
                </div>
            </div>
        </v-row>
    </v-container>
</template>
<script>
import { getPostList, getCheckSubscribe, getMySubscribe } from '@/api/subscribe.js';
import { moveUserRecipeDetail, moveRecipeCreate } from '@/api/move.js';
import swal from 'sweetalert';
export default {
    name: 'Subscribe',
    data() {
        return {
            subscribe: require('@/assets/images/header/subscribe.png'),
            subscribeflag: false,
            title: '전체 레시피',
            btnTitle: '구독자 레시피',
            postList: [],
            temp: [],
            userList: [{ id: '' }],
            page: 0,
            size: 6,
            imgUrl: '',
            isLoading: true,
            snum: 0,
        };
    },
    created() {
        this.snum = this.$route.params.snum;
        if (this.snum == 1) {
            this.changeSubscribePage();
        } else {
            this.append_list();
        }
        window.addEventListener('scroll', this.scroll);
    },
    methods: {
        scroll() {
            let scrolledToBottom =
                document.documentElement.scrollTop + window.innerHeight ===
                document.documentElement.offsetHeight;
            // 전체 목록을 보고있는경우
            if (scrolledToBottom && this.isLoading && !this.subscribeflag) {
                setTimeout(this.append_list, 500);
            }
            // 구독 목록을 보고 있는 경우
            if (scrolledToBottom && this.isLoading && this.subscribeflag) {
                setTimeout(this.getMySubscribeList, 500);
            }
        },
        append_list() {
            getPostList(this.page, this.size)
                .then((response) => {
                    var list = response.data.postList;
                    if (list < this.size) {
                        for (var i = 0; i < list.length; i++) this.postList.push(list[i]);
                    } else {
                        for (i = 0; i < list.length; i++) this.postList.push(list[i]);
                        this.page++;
                    }
                })
                .catch((error) => {
                    swal(error, {
                        icon: 'error',
                    });
                });
        },
        moveRecipeDetail(post_id) {
            moveUserRecipeDetail(post_id);
        },
        goRecipeCreate() {
            moveRecipeCreate();
        },
        changeSubscribePage() {
            this.subscribeflag = !this.subscribeflag;
            if (this.subscribeflag == false) {
                this.title = '전체 레시피';
                this.btnTitle = '구독자 레시피';
                this.page = 0;
                this.postList.splice(0);
                this.append_list();
            } else {
                this.title = '구독자 레시피';
                this.btnTitle = '전체 레시피';
                this.page = 0;
                this.postList.splice(0);
                this.getMySubscribeList();
            }
        },
        getMySubscribeList() {
            getCheckSubscribe()
                .then((response) => {
                    this.userList = response.data.userlist;
                    this.userList.forEach((el) => {
                        getMySubscribe(this.page, this.size, el.id)
                            .then((response) => {
                                let tempList = response.data.post;
                                if (tempList.length < this.size) {
                                    this.isLoading = false;
                                    tempList.forEach((el) => {
                                        this.postList.push(el);
                                    });
                                } else {
                                    this.isLoading = true;
                                    tempList.forEach((el) => {
                                        this.postList.push(el);
                                    });
                                    this.page++;
                                }
                            })
                            .catch((error) => {
                                console.log(error);
                            });
                    });
                })
                .catch((error) => console.log(error));
        },
    },
};
</script>

<style scoped>
@import './../../assets/css/subscribe.css';
</style>
