<template>
    <v-container>
        <div class="loginFormBox">
            <h1 class="loginTitle">로 그 인</h1>
            <v-text-field
                label="ID (Email)"
                placeholder="ex)ssafy@naver.com"
                type="string"
            ></v-text-field>
            <v-text-field
                label="PW (대소문자 특수문자 조합 8자 이상)"
                placeholder="ssafy123!"
                type="string"
            ></v-text-field>
            <v-row>
                <v-spacer></v-spacer>
                <button class="resetBtn">초기화</button>

                <button class="loginBtn">로그인</button>
            </v-row>
        </div>
    </v-container>
</template>
<style scoped>
.loginFormBox {
    width: calc(100% - 100px);
    margin: 0 auto;
}
.loginTitle {
    font-size: 24px;
    text-align: center;
}
.resetBtn {
    width: 60px;
    height: 60px;
    text-align: center;
}
.loginBtn {
    width: 60px;
    height: 60px;
    text-align: center;
}
</style>
