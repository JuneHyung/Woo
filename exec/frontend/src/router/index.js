import Vue from "vue";
import VueRouter from "vue-router";

import Main from "@/views/Main.vue";
import Start from "@/views/Start.vue";
import MainHeader from "../components/commons/MainHeader.vue"
import SubHeader from "../components/commons/SubHeader.vue"

import SignUp from "../views/member/SignUp.vue";
import Login from "../views/member/Login.vue";
import MyPage from "../views/member/MyPage.vue";

import ShareRecipe from "../views/ShareRecipe.vue";
import Subscribe from "../views/Subscribe.vue";
import FoodDetail from "../views/FoodDetail.vue";


import RefAdd from "../views/Refrigerator/RefAdd.vue";
import RefManage from "../views/Refrigerator/RefManage.vue";

import RecipeList from "../views/Recipe/RecipeList.vue";
import RecipeDetail from "../views/Recipe/RecipeDetail.vue";
import RecipeCreate from "../views/Recipe/RecipeCreate.vue";

/** 같은 페이지에서 같은 페이지로 $router.push 한 오률를 처리함 (ex : 홈페이지에서 홈 로고를 클릭한 경우) */
const originalPush = VueRouter.prototype.push;
  VueRouter.prototype.push = function push(location){
  return originalPush.call(this, location).catch(()=>{
  return window.location.reload()
  })
};

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
    path: "/MyPage",
    name: "MyPage",
    components: { default: MyPage, header: MainHeader },
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
    path: "/recipeList/:ingredient_id",
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
  {
    path: "/MyPage",
    name: "MyPage",
    components: { default: MyPage, header: MainHeader },
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;