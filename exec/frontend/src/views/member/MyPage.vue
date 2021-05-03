<template>
    <v-container>
        <v-row style="margin: auto 0">
            <img :src="myPage" alt="마이페이지" class="myPageImg" />
            <p class="myPageTitle" style="margin-left: 10px !important">My Page</p>
            <v-spacer></v-spacer>
        </v-row>
        <div>
            <p style="font-size: 18px">닉네임</p>
        </div>
        <div style="padding-top: 5px; margin-bottom: 10px">
            <p style="height: 100px; font-size: 18px">내가 올린 레시피</p>
        </div>
        <div>
            <v-row>
                <v-spacer></v-spacer>
                <v-dialog v-model="modifyDialog" transition="dialog-top-transition" max-width="600">
                    <template v-slot:activator="{ on, attrs }">
                        <button class="modifyBtn" v-bind="attrs" v-on="on">회원정보 수정</button>
                    </template>
                    <template v-slot:default="dialog">
                        <v-card>
                            <v-toolbar style="color: white; background-color: #494949">
                                <p style="font-size: 18px">회원 정보 수정</p>
                            </v-toolbar>
                            <v-card-text>
                                <v-row>
                                    <v-text-field
                                        label="닉네임"
                                        placeholder="변경을 희망하는 닉네임을 알려주세요!"
                                        type="string"
                                    ></v-text-field>
                                    <button @click="doubleCheck">중복 확인</button>
                                </v-row>
                                <v-text-field
                                    label="ID (Email)"
                                    placeholder="ex)ssafy@naver.com"
                                    type="string"
                                ></v-text-field>
                                <v-text-field
                                    label="PW (대소문자 특수문자 조합 8자 이상)"
                                    placeholder="비밀번호를 입력해주세요!"
                                    type="string"
                                >
                                    ></v-text-field
                                >
                            </v-card-text>
                            <v-card-actions class="justify-end">
                                <v-btn text @click="dialog.value = false">수정 취소</v-btn>
                                <v-btn text @click="[finishModify(), (dialog.value = false)]"
                                    >수정 완료</v-btn
                                >
                            </v-card-actions>
                        </v-card>
                    </template>
                </v-dialog>
                <v-dialog
                    v-model="deleteDialog"
                    style="width: 300px; height: 150px; margin: 0 auto"
                >
                    <template v-slot:activator="{ on, attrs }">
                        <div class="deleteBtn" v-bind="attrs" v-on="on">회원탈퇴</div>
                    </template>
                    <v-card>
                        <v-card-title style="margin: 0 auto">정말 탈퇴하실껀가요?</v-card-title>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn @click="deleteDialog = false"> 탈퇴하기 </v-btn
                            ><v-spacer></v-spacer>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
            </v-row>
        </div>
    </v-container>
</template>

<script>
import swal from 'sweetalert';

export default {
    name: 'MyPage',
    data() {
        return {
            myPage: require('@/assets/images/header/MyPage.png'),
            modifyDialog: false,
            deleteDialog: false,
            subscribeDialog: false,
        };
    },
    methods: {
        doubleCheck() {
            swal('사용 가능한 닉네임입니다.', {
                icon: 'success',
            });
        },
        finishModify() {
            swal('회원정보가 수정되었습니다', {
                icon: 'success',
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
