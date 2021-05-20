import http from './axios.js';

/*로그인 후 내 냉장고 목록 받기*/
function getMyFridge() {
    return http.get(`fridge/list`);
}

/*내 냉장고 목록에서 냉장고 삭제 */
function deleteMyFridge(fridgeid) {
    return http.delete(`fridge/list/${fridgeid}`)
}

/*내 냉장고 추가 */
function addMyFridge(fridge) {
    return http.post(`fridge/create`, fridge)
}

/*냉장고 관리 */
/* 카테고리 얻기 */
function getCategory() {
    return http.get(`fridge/categoryList`);
}
/* category가 all일때, 모든 재료얻기 */
function getAllIngredients() {
    return http.get(`fridge/ingredientsDetailList`);
}

/* category가 선택되면, 재료얻기 */
function getSomeIngredients(category) {
    return http.get(`fridge/categoryByingredients/${category}`);
}

/*냉장고 안에 있는 재료들 가져오기. */
function getIngredients(ref_id) {
    return http.get(`fridge/ingredients/${ref_id}`);
}

/* 냉장고에 재료를 추가하기 */
function addIngredients(addItem) {
    return http.post(`fridge/addIngredients`, addItem);
}

/* 냉장고에 재료 삭제하기 */
function deleteIngredients(id) {
    return http.delete(`fridge/delIngredients/${id}`);
}

/* 냉장고에 있는 재료 이동시키기 */
function moveIngredients(moveItem) {
    return http.put(`fridge/moveIngredients`, moveItem);
}

/* 냉장고 정보가져오기 */
function getFridgeDetail(rid) {
    return http.get(`fridge/detail/${rid}`);
}
export {
    getMyFridge,
    deleteMyFridge,
    addMyFridge,
    getCategory,
    getAllIngredients,
    getSomeIngredients,
    getIngredients,
    addIngredients,
    deleteIngredients,
    moveIngredients,
    getFridgeDetail
}