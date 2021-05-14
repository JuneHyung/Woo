<template>
    <v-container>
        <v-row style="margin: auto 0">
            <img :src="myPage" alt="마이페이지" class="myPageImg" />
            <p
                class="myPageTitle"
                style="
                    margin-left: 10px !important;
                    font-size: 28px;
                    text-decoration: underline;
                    text-decoration-style: wavy;
                    text-underline-position: under;
                "
            >
                {{ user.nick }}님의 정보
            </p>
            <v-spacer></v-spacer>
        </v-row>
        <div style="margin-top: 20px">
            <v-row>
                <v-spacer></v-spacer>
                <button
                    style="
                        margin-right: 20px;
                        font-size: 18px;
                        border: 1px solid #d9418d;
                        border-radius: 10px;
                        padding: 5px;
                        color: #d9418d;
                    "
                    @click="ModifyUser"
                >
                    회원정보 수정
                </button>

                <button
                    @click="deleteUser"
                    style="
                        font-size: 18px;
                        border: 1px solid #d9418d;
                        border-radius: 10px;
                        padding: 5px;
                        color: #d9418d;
                    "
                >
                    탈퇴하기
                </button>
                <v-spacer></v-spacer>
            </v-row>
        </div>
        <EditDialog :dialogEdit="dialogEdit" :user="user" @closeEdit="closeEdit"></EditDialog>
        <DeleteDialog
            :dialogDelete="dialogDelete"
            :user="user"
            @closeDelete="closeDelete"
        ></DeleteDialog>

        <div style="padding-top: 5px; margin-top: 20px">
            <p
                style="
                    font-size: 28px;
                    text-decoration: underline;
                    text-decoration-style: wavy;
                    text-underline-position: under;
                    margin-bottom: 20px !important;
                "
            >
                ㅇ내가 올린 레시피
            </p>
        </div>
        <v-row>
            <div v-for="(post, index) in myPostList" :key="index" style="margin: 5px auto">
                <img
                    @click="moveRecipeDetail(post.id)"
                    :src="`data:image/jpg;base64,${post.imageStrArr[0]}`"
                    alt="Recipeimage"
                    style="width: 150px; height: 160px"
                />
                <p class="shorthand" style="width: 150px; text-align: center; font-size: 18px">
                    {{ post.title }}
                </p>
            </div>
        </v-row>
    </v-container>
</template>

<script>
// import swal from 'sweetalert';
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
                        alert('조회실패');
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
.myPageTitle {
    font-size: 24px;
    line-height: 24px;
    height: 32px;
}
.myPageImg {
    width: 30px;
    height: 100%;
    margin: auto 1px;
    padding-bottom: 10px;
}
.modifyBtn {
    width: 60px;
    height: 60px;
    text-align: center;
    padding-right: 15px;
}

.deleteBtn {
    width: 60px;
    height: 60px;
    text-align: center;
    padding-top: 20px;
}
</style>
