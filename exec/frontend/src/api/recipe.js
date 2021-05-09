import http from './axios.js';

/* RecipeList */
/* 메뉴를 통해서 전체 목록 출력 */
function getRecipeListByMenu(page, size) {
    return http.get(`recipes/${page}/${size}`)
}

/* 재료를 클릭해서 목록 출력 */
function getRecipeListByIngredients(id, page, size) {
    return http.get(`recipes/ingredients/${id}/${page}/${size}`)
}

/* RecipeDetail */
/* 조회수 UP */
function viewsUp(recipe_id) {
    return http.get(`recipes/views/${recipe_id}`);
}

/* 레시피목록을 클릭하면 디테일페이지로 이동하여 데이터출력 */
function getRecipeDetail(id) {
    return http.get(`recipes/detail/${id}`);
}

/* */
function createRecipe(formData) {
    return http.post(`post/upload/`, formData);
}

export {
    getRecipeListByMenu,
    getRecipeListByIngredients,
    viewsUp,
    getRecipeDetail,
    createRecipe
}