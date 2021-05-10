<template>
    <v-container>
        <v-row>
            <div style="width: 24px; height: 40px">
                <img :src="subscribe" alt="구독 이미지" class="subscribeImg" />
            </div>
            <p
                style="
                    font-size: 28px;
                    line-height: 22px;
                    text-decoration: underline;
                    text-decoration-style: wavy;
                    text-underline-position: under;
                    margin-left: 10px !important;
                "
            >
                {{ title }}
            </p>
        </v-row>
        <v-row style="margin-bottom: 20px !important">
            <v-spacer></v-spacer>
            <button
                style="
                    font-size: 18px;
                    border: 1px solid #d9418d;
                    border-radius: 10px;
                    padding: 5px;
                    color: #d9418d;
                    margin: 0 10px;
                "
                @click="changeSubscribePage()"
            >
                {{ btnTitle }}
            </button>

            <button
                style="
                    font-size: 18px;
                    border: 1px solid #d9418d;
                    border-radius: 10px;
                    padding: 5px;
                    color: #d9418d;
                    margin: 0;
                "
                @click="goRecipeCreate()"
            >
                레시피 등록
            </button>
        </v-row>
        <v-row>
            <div
                v-for="(post, index) in postList"
                :key="index"
                style="width: 165px; margin: 0 auto; margin-bottom: 20px"
            >
                <img
                    @click="moveRecipeDetail(post.id)"
                    :src="`data:image/jpg;base64,${post.imageStrArr[0]}`"
                    alt="Customer_image"
                    style="width: 160px; height: 160px"
                />
                <p class="shorthand" style="font-size: 20px; padding-left: 8px !important">
                    요리이름 : {{ post.title }}
                </p>
                <p style="font-size: 20px; padding-left: 8px !important">
                    작성자 : {{ post.user_name }}
                </p>
            </div>
            <v-spacer></v-spacer>
        </v-row>
    </v-container>
</template>
<script>
// import http from '@/api/axios.js';
import { getPostList } from '@/api/subscribe.js';
import { moveUserRecipeDetail, moveRecipeCreate } from '@/api/move.js';
export default {
    name: 'Subscribe',
    data() {
        return {
            subscribe: require('@/assets/images/header/subscribe.png'),
            subscribeflag: false,
            title: '전체 레시피',
            btnTitle: '구독자 레시피',
            postList: [],
            page: 0,
            size: 6,
            imgUrl: '',
        };
    },
    // mounted() {
    //     http.get(`post/list/0/${this.size}`)
    //         .then((response) => {
    //             if (response.data.message == 'success') {
    //                 this.postList = response.data.postList;
    //             } else {
    //                 alert('정보 조회 실패');
    //             }
    //         })
    //         .catch(() => {});
    // },
    created() {
        this.append_list();
        window.addEventListener('scroll', this.scroll);
    },
    methods: {
        scroll() {
            let scrolledToBottom =
                document.documentElement.scrollTop + window.innerHeight ===
                document.documentElement.offsetHeight;

            if (scrolledToBottom) {
                setTimeout(this.append_list, 500);
            }
        },
        append_list() {
            getPostList(this.page, this.size)
                .then((response) => {
                    var list = response.data.postList;
                    for (var i = 0; i < list.length; i++) this.postList.push(list[i]);

                    this.page++;
                })
                .catch(() => alert('!!!!!!!!!!!!!!!'));
        },
        moveRecipeDetail(post_id) {
            // this.$router.push({ name: 'UserRecipeDetail', params: { id: post_id } });
            moveUserRecipeDetail(post_id);
        },
        goRecipeCreate() {
            // this.$router.push({ name: 'RecipeCreate' });
            moveRecipeCreate();
        },
        changeSubscribePage() {
            this.subscribeflag = !this.subscribeflag;
            if (this.subscribeflag == false) {
                this.title = '전체 레시피';
                this.btnTitle = '구독자 레시피';
            } else {
                this.title = '구독자 레시피';
                this.btnTitle = '전체 레시피';
            }
        },
    },
};
</script>

<style scoped>
.subscribeImg {
    width: 100%;
    height: 70%;
}
.foodImg {
    width: 100%;
    height: 100px;
}
</style>
