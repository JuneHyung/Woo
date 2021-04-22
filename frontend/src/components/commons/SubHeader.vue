<template>
    <v-container>
        <header>
            <v-row>
                <div @click="goMain">로고</div>
                <v-spacer></v-spacer>
                <div @click="goSignUp">Sign Up</div>
                <div @click="goLogin">Login</div>
            </v-row>
        </header>
    </v-container>
</template>

<script>
export default {
    name: 'SubHeader',

    data() {
        return {};
    },
    methods: {
        goMain() {
            this.$router.push({ name: 'Main' });
        },
        goSignUp() {
            this.$router.push({ name: 'SignUp' });
        },
        goLogin() {
            this.$router.push({ name: 'Login' });
        },
    },
};
</script>
