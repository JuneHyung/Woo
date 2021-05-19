<template>
    <v-dialog v-model="dialogDelete" style="width: 300px; height: 150px; margin: 0 auto">
        <v-card>
            <v-card-title style="margin: 0 auto">{{ user.nick }}님 탈퇴하실껀가요?</v-card-title>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn @click="closeDialogDelete">취소하기</v-btn>
                <v-btn @click="deleteUser"> 탈퇴하기 </v-btn><v-spacer></v-spacer>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
import swal from 'sweetalert';
import { deleteUser } from '@/api/user.js';
import { moveStart } from '@/api/move.js';

export default {
    name: 'DeleteDialog',
    props: {
        dialogDelete: Boolean,
        user: Object,
    },
    methods: {
        closeDialogDelete() {
            this.dialogDelete = false;
            this.$emit('closeDelete');
        },
        deleteUser() {
            // 회원 탈퇴 메소드.
            deleteUser()
                .then(() => {
                    swal('삭제 성공했습니다!', {
                        icon: 'success',
                    });
                    this.$store
                        .dispatch('LOGOUT')
                        .then(() => {
                            moveStart(); // 탈퇴 후 시작페이지로 이동
                        })
                        .catch((error) => {
                            swal(error, {
                                icon: 'error',
                            });
                        });
                    this.closeDialogDelete(); // dialog를 닫음
                })
                .catch(() => {
                    swal('삭제에 실패했습니다', {
                        icon: 'error',
                    });
                });
        },
    },
};
</script>
