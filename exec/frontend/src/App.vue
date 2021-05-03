<template>
    <v-app style="width: 360px; margin: 0 auto; border: 1px solid black">
        <router-view name="header"></router-view>
        <router-view></router-view>
        <alarm></alarm>
    </v-app>
</template>

<script>
import Alarm from './components/Alarm';
export default {
    name: 'App',
    components: { Alarm },
    data: () => ({
        //
    }),
};
</script>
<style>
@import './assets/css/reset.css';
</style>
