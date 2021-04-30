import Vue from "vue";
import VueRouter from "vue-router";
import Main from "@/views/Main.vue";
import Start from "@/views/Start.vue";
import MainHeader from "../components/commons/MainHeader.vue"
import SubHeader from "../components/commons/SubHeader.vue"

import SignUp from "../views/member/SignUp.vue";
import Login from "../views/member/Login.vue";

import ShareRecipe from "../views/ShareRecipe.vue";
import Subscribe from "../views/Subscribe.vue";
import FoodDetail from "../views/FoodDetail.vue";


import RefAdd from "../views/Refrigerator/RefAdd.vue";
import RefManage from "../views/Refrigerator/RefManage.vue";

import RecipeList from "../views/Recipe/RecipeList.vue";
import RecipeDetail from "../views/Recipe/RecipeDetail.vue";
import RecipeCreate from "../views/Recipe/RecipeCreate.vue";
Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Start",
    components: { default: Start, header: SubHeader },
  },
  {
    path: "/main",
    name: "Main",
    components: { default: Main, header: MainHeader },
  },
  {
    path: "/signup",
    name: "SignUp",
    components: { default: SignUp, header: SubHeader },
  },
  {
    path: "/login",
    name: "Login",
    components: { default: Login, header: SubHeader },
  },
  {
    path: "/sharerecipe",
    name: "ShareRecipe",
    components: { default: ShareRecipe, header: MainHeader },
  },
  {
    path: "/subscribe",
    name: "Subscribe",
    components: { default: Subscribe, header: MainHeader },
  },
  {
    path: "/refadd",
    name: "RefAdd",
    components: { default: RefAdd, header: MainHeader },
  },
  {
    path: "/refmanage/:rid",
    name: "RefManage",
    components: { default: RefManage, header: MainHeader },
  },
  {
    path: "/recipeList",
    name: "RecipeList",
    components: { default: RecipeList, header: MainHeader },
  },
  {
    path: "/recipeDetail/:recipe_id",
    name: "RecipeDetail",
    components: { default: RecipeDetail, header: MainHeader },
  },
  {
    path: "/recipeCreate",
    name: "RecipeCreate",
    components: { default: RecipeCreate, header: MainHeader },
  },
  {
    path: "/FoodDetail",
    name: "FoodDetail",
    components: { default: FoodDetail, header: MainHeader },
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;