<template>
    <v-dialog v-model="dialogEdit" transition="dialog-top-transition" max-width="600">
        <v-card>
            <v-toolbar style="color: white; background-color: #494949">
                <p style="font-size: 18px">회원 정보 수정</p>
            </v-toolbar>
            <v-card-text>
                <v-row>
                    <v-text-field
                        v-model="Modifyuser.nick"
                        label="닉네임"
                        placeholder="변경을 희망하는 닉네임을 알려주세요!"
                        type="string"
                    ></v-text-field>
                    <button @click="doubleCheck">중복 확인</button>
                </v-row>
                <v-text-field
                    v-model="Modifyuser.email"
                    label="ID (Email)"
                    placeholder="ex)ssafy@naver.com"
                    type="string"
                ></v-text-field>
                <v-row
                    ><v-text-field
                        v-model="pwdCheck"
                        label="PW (대소문자 특수문자 조합 8자 이상)"
                        placeholder="비밀번호를 입력해주세요!"
                        type="password"
                    >
                    </v-text-field>
                    <v-dialog v-model="PasswordDialog">
                        <template v-slot:activator="{ on, attrs }">
                            <v-btn v-bind="attrs" v-on="on">비밀번호 변경</v-btn>
                        </template>
                        <v-card>
                            <v-card-title class="headline grey lighten-2">
                                비밀번호 변경
                            </v-card-title>
                            <v-text-field v-model="pwd" label="기존 비밀번호 입력" type="password">
                            </v-text-field
                            ><v-text-field
                                v-model="newPwd"
                                label="새로운 비밀번호 입력(대소문자 특수조합 8자 이상)"
                                type="password"
                            >
                            </v-text-field
                            ><v-text-field
                                v-model="NewPwdCheck"
                                label="새로운 비밀번호 한번 더 입력"
                                type="password"
                            >
                            </v-text-field>
                            <v-card-actions>
                                <v-btn @click="closePasswordDialog">변경취소</v-btn>
                                <v-btn @click="ChangePwd">변경완료</v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-dialog>
                </v-row>
            </v-card-text>
            <v-card-actions class="justify-end">
                <v-btn text @click="CloseEditDialog">수정 취소</v-btn>
                <v-btn text @click="finishModify(user)">수정 완료</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
import swal from 'sweetalert';
import http from '@/api/axios.js';

export default {
    name: 'EditDialog',
    data() {
        return {
            Modifyuser: {
                id: '',
                email: '',
                pwd: '',
                nick: '',
            },
            pwdCheck: '',
            pwd: '',
            newPwd: '',
            NewPwdCheck: '',
            PasswordDialog: false,
        };
    },
    props: {
        dialogEdit: Boolean,
        user: Object,
    },
    watch: {
        user() {
            this.Modifyuser.id = this.user.id;
            this.Modifyuser.email = this.user.email;
            this.Modifyuser.pwd = this.user.pwd;
            this.Modifyuser.nick = this.user.nick;
        },
    },
    methods: {
        closePasswordDialog() {
            this.PasswordDialog = false;
        },
        ChangePwd() {
            if (this.pwd == '') {
                swal('기존 비밀번호를 입력해주세요!', { icon: 'error' });
            } else if (this.pwd != this.user.pwd) {
                swal('기존 비밀번호가 일치하지 않습니다!', { icno: 'error' });
            } else if (this.newPwd == '') {
                swal('새로운 비밀번호를 입력해주세요!', { icno: 'error' });
            } else if (this.NewPwdCheck == '') {
                swal('새로운 비밀번호를 확인해주세요!', { icno: 'error' });
            } else if (this.NewPwdCheck != this.newPwd) {
                swal('비밀번호가 일치하지 않습니다!', { icno: 'error' });
            } else {
                http.put(`user/modify`, {
                    email: this.Modifyuser.email,
                    pwd: this.newPwd,
                    nick: this.Modifyuser.nick,
                })
                    .then(() => {
                        swal('수정이 성공되었습니다', { icon: 'success' });
                        this.closePasswordDialog();
                    })
                    .catch(() => {
                        swal('실패', {
                            icon: 'error',
                        });
                    });
            }
        },
        CloseEditDialog() {
            this.dialogEdit = false;
            this.$emit('closeEdit');
        },
        finishModify() {
            if (this.Modifyuser.nick == '') {
                swal('변경을 희망하는 닉네임을 입력해주세요!', {
                    icon: 'error',
                });
            } else if (this.Modifyuser.email == '') {
                swal('이메일을 입력해주세요!', {
                    icon: 'error',
                });
            } else if (this.pwdCheck == '') {
                swal('비밀번호를 입력해주세요!', {
                    icon: 'error',
                });
            } else {
                http.put(`user/modify`, {
                    id: this.Modifyuser.id,
                    email: this.Modifyuser.email,
                    pwd: this.Modifyuser.pwd,
                    nick: this.Modifyuser.nick,
                })
                    .then(() => {
                        swal('수정이 성공되었습니다', { icon: 'success' });
                        this.CloseEditDialog();
                    })
                    .catch(() => {
                        swal('실패', {
                            icon: 'error',
                        });
                    });
            }
        },
        doubleCheck() {
            http.get(`user/nickcheck/${this.Modifyuser.nick}`)
                .then((response) => {
                    console.log(response);
                    if (response.data.message == 'fail') {
                        swal('이미 존재하는 닉네임입니다!!', {
                            icon: 'warning',
                        });
                        this.nickChecked = false;
                    } else if (response.data.message == 'success') {
                        swal('사용가능한 닉네임입니다!!', {
                            icon: 'success',
                        });
                        this.nickChecked = true;
                    }
                })
                .catch();
        },
    },
};
</script>
