import http from './axios.js';

/* 유저 글 목록 가져오기 */
function getPostList(page, size){
    return http.get(`post/list/${page}/${size}`);
}

/* 상세 내용 */
function getPostDetail(id) {
    return http.get(`post/detail/${id}`);
}
export { getPostList, getPostDetail}