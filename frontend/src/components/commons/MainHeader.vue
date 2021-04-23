<template>
    <v-container>
        <header>
            <v-row>
                <div @click="goStart">로고</div>
                <v-spacer></v-spacer>
                <div @click="goShareRecipt">레시피 공유</div>
                <div @click="goSubscribe">구독</div>
                <div>로그아웃</div>
                <div>알림</div>
            </v-row>
        </header>
    </v-container>
</template>
<script>
export default {
    name: 'MainHeader',
    data() {
        return {};
    },
    methods: {
        goStart() {
            this.$router.push({ name: 'Start' });
        },
        goShareRecipt() {
            this.$router.push({ name: 'ShareRecipe' });
        },
        goSubscribe() {
            this.$router.push({ name: 'Subscribe' });
        },
    },
};
</script>
