import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  vuetify,
  async beforeCreate() {
    let token = sessionStorage.getItem('X-AUTH-TOKEN');
    if (store.state.isLogin == false && token) {
      await store.dispatch('GET_USER_INFO', token);
    }
  },
  render: (h) => h(App),
}).$mount("#app");
