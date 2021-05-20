<template>
    <v-container>
        <div class="signFormBox">
            <h1 class="signTitle">회 원 가 입</h1>
            <v-row>
                <v-text-field
                    label="ID (Email)"
                    placeholder="ex)ssafy@naver.com"
                    type="string"
                    v-model="user.email"
                ></v-text-field>
                <v-spacer></v-spacer>
                <div class="duplicateCheckBox">
                    <v-btn @click="checkId()">ID 중복확인</v-btn>
                </div>
            </v-row>
            <v-text-field
                label="PW (대소문자 특수문자 숫자 조합 8자 이상)"
                placeholder="ssafy123!"
                type="password"
                v-model="user.pwd"
            >
            </v-text-field>
            <v-text-field
                label="PW 확인"
                placeholder="확인을 위해 한번 더 입력해 주세요."
                type="password"
                v-model="pwdchecked"
            >
            </v-text-field>

            <v-row>
                <v-text-field
                    label="닉네임"
                    placeholder="살림왕"
                    type="string"
                    v-model="user.nick"
                ></v-text-field>
                <v-spacer></v-spacer>
                <div class="duplicateCheckBox">
                    <v-btn @click="checkNick">닉네임 중복확인</v-btn>
                </div>
            </v-row>

            <v-row>
                <v-spacer></v-spacer>
                <button class="resetBtn" @click="reset">초기화</button>

                <button class="signupBtn" @click="signUp">등록</button>
            </v-row>
        </div>
    </v-container>
</template>
<script>
// import http from '@/api/axios.js';
import { joinUser, checkId, checkNickName } from '@/api/user.js';
import { moveStart } from '@/api/move.js';
import swal from 'sweetalert';
// import safe from 'safe-regex';
import { checkPasswordValid, checkEmailValid } from '@/api/validcheck.js';

export default {
    data() {
        return {
            user: {
                email: '',
                pwd: '',
                nick: '',
            },
            pwdchecked: '',
            idChecked: false,
            nickChecked: false,
        };
    },
    methods: {
        onClickImageUpload() {
            this.$refs.imageInput.click();
        },
        onChangeImages(e) {
            const file = e.target.files[0];
            this.imageUrl = URL.createObjectURL(file);
            this.imageName = file.name;
        },
        validPassword(password) {
            // 비밀번호 체크
            return checkPasswordValid(password);
        },
        validEmail(email) {
            // email체크
            return checkEmailValid(email);
        },
        signUp() {
            // 빈 값 체크 후  회원가입.
            if (this.user.email == '') {
                swal('아이디를 입력해주세요!', {
                    icon: 'error',
                });
            } else if (!this.idChecked) {
                swal('아이디 중복 검사를 진행해주세요!', {
                    icon: 'error',
                });
            } else if (!this.validPassword(this.user.pwd)) {
                swal('비밀번호를 숫자와 문자, 특수문자 포함 형태의 8자리로 입력해주세요!', {
                    icon: 'error',
                });
            } else if (this.user.pwd == '') {
                swal('비밀번호를 입력해주세요!', {
                    icon: 'error',
                });
            } else if (this.user.pw != this.pwdChecked) {
                swal('비밀번호가 일치하지 않습니다!', {
                    icon: 'error',
                });
            } else if (this.pwdChecked == '') {
                swal('비밀번호를 확인해주세요!', {
                    icon: 'error',
                });
            } else if (this.user.nick == '') {
                swal('사용하실 닉네임을 입력해주세요!', {
                    icon: 'error',
                });
            } else if (!this.nickChecked) {
                swal('닉네임 중복 검사를 진행해주세요!', {
                    icon: 'error',
                });
            } else {
                joinUser(this.user)
                    .then(() => {
                        swal('회원가입이 완료되었습니다', {
                            icon: 'success',
                            closeOnClickOutside: false, //알람창을 제외하고 다른 곳 클릭시 넘어가지 않음
                        }).then(() => moveStart());
                    })
                    .catch(() => {
                        swal('회원가입 실패', {
                            icon: ' error',
                        });
                    });
            }
        },
        checkId() {
            if (this.user.email == '') {
                swal('이메일을 입력해주세요!', {
                    icon: 'warning',
                });
            } else if (!this.validEmail(this.user.email)) {
                // 이메일 형식이 아닐 시
                swal('이메일 양식을 맞춰주세요!', {
                    icon: 'error',
                });
            } else {
                // 중복체크시 id를 가지고 체크.
                checkId(this.user.email)
                    .then((response) => {
                        if (response.data.message == 'fail') {
                            // fail이 오면 이미 존재하는 아이디.
                            swal('이미 존재하는 아이디입니다!!', {
                                icon: 'warning',
                            });
                            this.isChecked = false;
                        } else if (response.data.message == 'success') {
                            // success면 사용가능한 아이디.
                            swal('사용가능한 닉네임입니다!!', {
                                icon: 'success',
                            });
                            this.idChecked = true;
                        }
                    })
                    .catch();
            }
        },
        checkNick() {
            if (this.user.nick == '') {
                swal('닉네임을 입력해주세요!', {
                    icon: 'warning',
                });
            } else {
                checkNickName(this.user.nick)
                    .then((response) => {
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
            }
        },
        reset() {
            this.user.email = '';
            this.user.pwd = '';
            this.pwdchecked = '';
            this.user.nick = '';
        },
    },
};
</script>
<style scoped>
@import './../../assets/css/signup.css';
</style>
