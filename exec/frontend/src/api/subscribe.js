import http from './axios.js';

/* 유저 글 목록 가져오기 */
function getPostList(page, size){
    return http.get(`post/list/${page}/${size}`);
}

/* 상세 내용 */
function getPostDetail(id) {
    return http.get(`post/detail/${id}`);
}

/*구독 확인 */
function getCheckSubscribe() {
    return http.get(`subscribe`);
}

/*쓴글 확인*/
function getMySubscribe(page, size, id) {
    return http.get(`post/subscriberContents/${page}/${size}/${id}`);
}

/*메시지 받기 */
function getSubscribeMessage() {
    return http.get(`/kafka`);
}
export { getPostList, getPostDetail, getCheckSubscribe,getMySubscribe,getSubscribeMessage}