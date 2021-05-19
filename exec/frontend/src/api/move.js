import ROUTER from "@/router"

/* 시작 페이지로 이동 */
function moveStart() {
    return ROUTER.push({ name: 'Start' });
}

/* 로그인 페이지로 이동 */
function moveLogin() {
    return ROUTER.push({ name: 'Login' });
}

/* 회원가입 페이지로 이동 */
function moveSignUp(){
    return ROUTER.push({ name: 'SignUp' });
}

/* 메인 페이지로 이동 */
function moveMain() {
    return ROUTER.push({ name: 'Main' });
}

/* 레시피 목록 페이지으로 이동 */
function moveRecipeList(num) {
    return ROUTER.push({ name: 'RecipeList', params: { ingredient_id: num } });
}

/* 구독 페이지로 이동 */
function moveSubscribe(num) {
    return ROUTER.push({ name: 'Subscribe', params: {snum:num} });
}

/* 마이 페이지로 이동 */
function moveMyPage() {
    return ROUTER.push({ name: 'MyPage' });
}

/* 레시피 작성 페이지로 이동 */
function moveRecipeCreate() {
    return ROUTER.push({ name: 'RecipeCreate' });
}

/* 레시피 상세 페이지로 이동 */
function moveRecipeDetail(id) {
    return ROUTER.push({ name: 'RecipeDetail', params: {recipe_id: id} });
}

/* 냉장고 관리 페이지로 이동 */
function moveRefManage(rid, rtype) {
    return ROUTER.push({ name: 'RefManage', params: { rid: rid,rtype:rtype } });
}

/* 냉장고 추가 페이지로 이동 */
function moveRefAdd() {
    return ROUTER.push({ name: 'RefAdd' });
}

/* 사용자가 포스트한 레시피 상세 페이지로 이동 */
function moveUserRecipeDetail(post_id) {
    return ROUTER.push({ name: 'UserRecipeDetail', params: { post_id: post_id } });
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