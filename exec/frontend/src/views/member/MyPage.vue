<template>
    <v-container>
        <v-row>
            <div class="myPageImg">
                <img :src="myPage" alt="마이페이지" />
            </div>
            <p class="titleText myPageTitle">{{ user.nick }}님의 정보</p>
            <v-spacer></v-spacer>
        </v-row>
        <div class="mt-5">
            <v-row>
                <v-spacer></v-spacer>
                <button class="modifyBtn" @click="ModifyUser">회원정보 수정</button>
                <button class="deleteUserBtn" @click="deleteUser">탈퇴하기</button>
                <v-spacer></v-spacer>
            </v-row>
        </div>
        <EditDialog :dialogEdit="dialogEdit" :user="user" @closeEdit="closeEdit"></EditDialog>
        <DeleteDialog
            :dialogDelete="dialogDelete"
            :user="user"
            @closeDelete="closeDelete"
        ></DeleteDialog>

        <div class="mt-5 pt-1">
            <p class="font-20">ㅇ<span class="titleText myRecipeTitle"> 내가 올린 레시피</span></p>
        </div>
        <v-row>
            <div
                v-for="(post, index) in myPostList"
                :key="index"
                class="mx-1 my-1"
                style="width: 150px"
            >
                <div class="myRecipeImg">
                    <img
                        @click="moveRecipeDetail(post.id)"
                        :src="`data:image/jpg;base64,${post.imageStrArr[0]}`"
                        alt="Recipeimage"
                    />
                </div>
                <p class="shorthand myRecipeText">
                    {{ post.title }}
                </p>
            </div>
        </v-row>
    </v-container>
</template>

<script>
import swal from 'sweetalert';
// import http from '@/api/axios.js';
import { getMyInfo, getMyList } from '@/api/user.js';
import { moveUserRecipeDetail } from '@/api/move.js';
import DeleteDialog from '../../components/MyPageDialog/DeleteDialog.vue';
import EditDialog from '../../components/MyPageDialog/EditDialog.vue';

export default {
    name: 'MyPage',
    components: {
        DeleteDialog,
        EditDialog,
    },
    data() {
        return {
            myPage: require('@/assets/images/header/MyPage.png'),
            dialogEdit: false,
            dialogDelete: false,
            subscribeDialog: false,
            user_id: '',
            user: {},
            myPostList: [],
            page: 0,
            size: 6,
            isLoading: true,
        };
    },
    created() {
        this.getMyInformation();
        this.getMyList();
        window.addEventListener('scroll', this.scroll);
    },
    methods: {
        scroll() {
            let scrolledToBottom =
                document.documentElement.scrollTop + window.innerHeight ===
                document.documentElement.offsetHeight;

            if (scrolledToBottom && this.isLoading) {
                setTimeout(this.getMyList, 500);
            }
        },
        deleteUser() {
            this.dialogDelete = true;
        },
        closeDelete() {
            this.dialogDelete = false;
        },
        ModifyUser() {
            this.dialogEdit = true;
        },
        closeEdit() {
            this.dialogEdit = false;
        },
        moveRecipeDetail(post_id) {
            moveUserRecipeDetail(post_id);
        },
        getMyInformation() {
            getMyInfo()
                .then((response) => {
                    if (response.data.message == 'success') {
                        this.user = response.data.user;
                        this.id = this.user.id;
                    } else {
                        swal('조회 실패!', {
                            icon: 'error',
                        });
                    }
                })
                .catch((error) => {
                    console.log(error);
                });
        },
        getMyList() {
            getMyList(this.page, this.size)
                .then((response) => {
                    let postList = response.data.myPostList;
                    if (postList.length < this.size) {
                        this.isLoading = false;
                        postList.forEach((el) => {
                            this.myPostList.push(el);
                        });
                    } else {
                        this.isLoading = true;
                        postList.forEach((el) => {
                            this.myPostList.push(el);
                        });
                        this.page++;
                    }
                })
                .catch((error) => {
                    console.log(error);
                });
        },
    },
};
</script>

<style scoped>
@import './../../assets/css/mypage.css';
</style>
