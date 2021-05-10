<template>
    <v-app style="width: 360px; margin: 0 auto">
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
};
</script>
<style>
@import './assets/css/reset.css';
</style>
