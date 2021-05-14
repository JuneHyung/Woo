<template>
    <v-app style="width: 360px; margin: 0 auto">
        <router-view name="header"></router-view>

        <transition name="component-fade" mode="out-in">
            <router-view></router-view>
        </transition>
        <!-- <alarm></alarm> -->
    </v-app>
</template>

<script>
// import Alarm from './components/Alarm';

export default {
    name: 'App',
    // components: { Alarm },
    data() {
        return {
            loadingIcon: require('@/assets/images/loading.gif'),
            loadingDialog: false,
            token: '',
        };
    },
};
</script>
<style>
@import './assets/css/reset.css';

.component-fade-enter-active,
.component-fade-leave-active {
    transition: opacity 0.3s ease;
}
.component-fade-enter, .component-fade-leave-to
/* .component-fade-leave-active below version 2.1.8 */ {
    opacity: 0;
}
</style>
