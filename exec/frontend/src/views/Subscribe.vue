<template>
    <v-container>
        <v-row>
            <div style="width: 24px; height: 40px">
                <img :src="subscribe" alt="구독 이미지" class="subscribeImg" />
            </div>
            <p style="font-size: 24px; line-height: 24px; margin-left: 15px !important">
                {{ title }}
            </p>
        </v-row>
        <div style="margin-left: 260px; text-align: center">
            <button style="margin-bottom: 5px" @click="changeSubscribePage()">
                {{ btnTitle }}
            </button>
            <v-spacer></v-spacer>
            <button style="margin-bottom: 5px" @click="goRecipeCreate()">레시피 등록</button>
        </div>
        <v-row>
            <div
                v-for="(post, index) in postList"
                :key="index"
                style="width: 165px; height: 200px; margin: 0 auto"
            >
                <p>요리이름 : {{ post.title }}</p>
                <p>작성자 : {{ post.user_name }}</p>
                <img
                    @click="moveRecipeDetail(post.id)"
                    :src="`data:image/jpg;base64,${post.imageStrArr[0]}`"
                    alt="Customer_image"
                    style="width: 160px; height: 160px"
                />
            </div>
            <v-spacer></v-spacer>
        </v-row>
    </v-container>
</template>
<script>
import http from '@/api/axios.js';

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
            http.get(`post/list/${this.page}/${this.size}`)
                .then((response) => {
                    var list = response.data.postList;
                    for (var i = 0; i < list.length; i++) this.postList.push(list[i]);

                    this.page++;
                })
                .catch(() => alert('!!!!!!!!!!!!!!!'));
        },
        moveRecipeDetail(post_id) {
            this.$router.push({ name: 'UserRecipeDetail', params: { id: post_id } });
        },
        goRecipeCreate() {
            this.$router.push({ name: 'RecipeCreate' });
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
