<template>
    <v-container>
        <v-row style="margin: auto 0">
            <img :src="myPage" alt="마이페이지" class="myPageImg" />
            <p class="myPageTitle" style="margin-left: 10px !important">
                {{ user.nick }}님의 정보 수정
            </p>
            <v-spacer></v-spacer>
        </v-row>
        <div>
            <v-row>
                <v-spacer></v-spacer>
                <button style="margin-right: 10px" @click="ModifyUser">회원정보 수정</button>

                <button @click="deleteUser">탈퇴하기</button>
            </v-row>
        </div>
        <EditDialog :dialogEdit="dialogEdit" :user="user" @closeEdit="closeEdit"></EditDialog>
        <DeleteDialog
            :dialogDelete="dialogDelete"
            :user="user"
            @closeDelete="closeDelete"
        ></DeleteDialog>
        <div>
            <p style="font-size: 18px"></p>
        </div>
        <div style="padding-top: 5px; margin-bottom: 10px">
            <p style="height: 100px; font-size: 18px">내가 올린 레시피</p>
        </div>
    </v-container>
</template>

<script>
// import swal from 'sweetalert';
import http from '@/api/axios.js';
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
            user: '',
        };
    },
    mounted() {
        http.get(`user/info/`)
            .then((response) => {
                if (response.data.message == 'success') {
                    console.log(response);
                    this.user = response.data.user;
                    this.id = this.user.id;
                } else {
                    alert('조회실패');
                }
            })
            .catch(() => {});
    },
    methods: {
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
