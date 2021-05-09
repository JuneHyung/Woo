// import Vue from "vue";
// import VueRouter from "vue-router";
// Vue.use(VueRouter);
import ROUTER from "@/router"
function moveStart() {
    return ROUTER.push({ name: 'Start' });
}

function moveLogin() {
    return ROUTER.push({ name: 'Login' });
}

function moveSignUp(){
    return ROUTER.push({ name: 'SignUp' });
}


function moveMain() {
    return ROUTER.push({ name: 'Main' });
}

function moveRecipeList(num) {
    return ROUTER.push({ name: 'RecipeList', params: { ingredient_id: num } });
}

function moveSubscribe() {
    return ROUTER.push({ name: 'Subscribe' });
}

function moveMyPage() {
    return ROUTER.push({ name: 'MyPage' });
}

function moveRecipeCreate() {
    return ROUTER.push({ name: 'RecipeCreate' });
}

function moveRecipeDetail(id) {
    return ROUTER.push({ name: 'RecipeDetail', params: {recipe_id: id} });
}

function moveRefManage(rid) {
    return ROUTER.push({ name: 'RefManage', params: { rid: rid } });
}

function moveRefAdd() {
    return ROUTER.push({ name: 'RefAdd' });
}

function moveUserRecipeDetail(post_id) {
    return ROUTER.push({ name: 'UserRecipeDetail', params: { id: post_id } });
}


export {
    moveLogin,
    moveSignUp,
    moveStart,
    moveMain,
    moveRecipeList,
    moveSubscribe,
    moveMyPage,
    moveRecipeCreate,
    moveRecipeDetail,
    moveRefManage,
    moveRefAdd,
    moveUserRecipeDetail,
    
}