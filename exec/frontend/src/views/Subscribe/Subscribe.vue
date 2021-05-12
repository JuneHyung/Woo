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
                style="width: 155px; margin: 10px auto"
            >
                <img
                    @click="moveRecipeDetail(post.id)"
                    :src="`data:image/jpg;base64,${post.imageStrArr[0]}`"
                    alt="Customer_image"
                    style="width: 100%; height: 160px; margin: 0 auto; border: 1px solid black"
                />
                <div style="border: 1px solid black">
                    <p class="shorthand" style="font-size: 20px; padding-left: 8px !important">
                        요리이름 : {{ post.title }}
                    </p>
                    <p style="font-size: 20px; padding-left: 8px !important">
                        작성자 : {{ post.user_name }}
                    </p>
                </div>
            </div>
        </v-row>
    </v-container>
</template>
<script>
// import http from '@/api/axios.js';
// getMySubscribe
import { getPostList, getCheckSubscribe, getMySubscribe } from '@/api/subscribe.js';
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
            temp: [],
            userList: [{ id: '' }],
            page: 0,
            size: 6,
            imgUrl: '',
        };
    },
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
                this.page = 0;
                this.append_list();
            } else {
                this.title = '구독자 레시피';
                this.btnTitle = '전체 레시피';
                this.page = 0;

                getCheckSubscribe()
                    .then((response) => {
                        this.userList.splice(0);
                        this.userList = response.data.userlist;
                        console.log('옴?');
                        this.postList.splice(0);
                        this.userList.forEach((el) => {
                            getMySubscribe(this.page, this.size, el.id)
                                .then((response) => {
                                    this.postList = response.data.post;
                                })
                                .catch((error) => {
                                    console.log(error);
                                });
                        });
                    })
                    .catch((error) => console.log(error));
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
