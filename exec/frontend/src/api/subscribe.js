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

/*구독하기*/
function subscribe(subscribeId) {
    return http.post(`subscribe/${subscribeId}`);
}

/*구독취소*/
function subscribeCancel(subscribeId) {
    return http.delete(`subscribe/${subscribeId}`);
}

/*좋아요, 싫어요 */
function checkLike(postId, like) {
    return http.post(`post/like?postId=${postId}&like=${like}`);
}

export {
    getPostList,
    getPostDetail,
    getCheckSubscribe,
    getMySubscribe,
    getSubscribeMessage,
    subscribe,
    subscribeCancel,
    checkLike
}