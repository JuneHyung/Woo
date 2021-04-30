<template>
    <v-container>
        <div class="signFormBox">
            <h1 class="signTitle">회 원 가 입</h1>
            <div class="pb-5">
                <img v-if="imageUrl == ''" :src="imageUrl" alt="" />
                <img v-else :src="imageUrl" class="profileImg" alt="profileImg" />
            </div>

            <v-row>
                <v-spacer></v-spacer>
                <p class="profileName">{{ imageName }}</p>

                <input ref="imageInput" type="file" hidden @change="onChangeImages" />
                <v-btn type="button" @click="onClickImageUpload">이미지 업로드</v-btn>
            </v-row>

            <v-text-field
                label="ID (Email)"
                placeholder="ex)ssafy@naver.com"
                type="string"
            ></v-text-field>
            <v-text-field
                label="PW (대소문자 특수문자 조합 8자 이상)"
                placeholder="ssafy123!"
                type="string"
            >
                ></v-text-field
            >
            <v-text-field
                label="PW 확인"
                placeholder="확인을 위해 한번 더 입력해 주세요."
                type="string"
            ></v-text-field>
            <v-text-field label="닉네임" placeholder="살림왕" type="string"></v-text-field>
            <v-row>
                <v-spacer></v-spacer>
                <button class="resetBtn">초기화</button>

                <button class="registBtn">등록</button>
            </v-row>
        </div>
    </v-container>
</template>
<script>
export default {
    data() {
        return {
            imageName: this.imageName,
            imageUrl: '',
        };
    },
    methods: {
        onClickImageUpload() {
            this.$refs.imageInput.click();
        },
        onChangeImages(e) {
            console.log(e.target.files);
            const file = e.target.files[0];
            // this.imageFile = file;
            this.imageUrl = URL.createObjectURL(file);
            this.imageName = file.name;
        },
    },
};
</script>
<style scoped>
.profileImg {
    width: 100%;
    height: 100%;
}
.profileName {
    line-height: 36px;
    border-bottom: 1px solid black;
    margin-right: 20px !important;
}
.signTitle {
    font-size: 24px;
    text-align: center;
}
.signFormBox {
    width: calc(100% - 100px);
    margin: 0 auto;
}
.resetBtn {
    width: 60px;
    height: 60px;
    text-align: center;
}
.registBtn {
    width: 60px;
    height: 60px;
    text-align: center;
}
</style>
