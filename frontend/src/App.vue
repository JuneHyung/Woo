<template>
    <v-app style="width: 360px; margin: 0 auto">
        <router-view name="header"></router-view>
        <router-view></router-view>
    </v-app>
</template>

<script>
export default {
    name: 'App',

    data: () => ({
        //
    }),
};
</script>
<style>
@import './assets/css/reset.css';
</style>
