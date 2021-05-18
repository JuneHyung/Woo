import http from './axios.js';

function login(userinput, success, fail) {
    
    const user = {
        email: userinput.id,
        pwd: userinput.password,
    }
    http.post('user/login', JSON.stringify(user)).then(success).catch(fail);
    
    }

    async function findById( success, fail) { 
        http.defaults.headers['X-AUTH-TOKEN'] = window.sessionStorage.getItem('X-AUTH-TOKEN');
        await http
            .get(`/user/info`)
            .then(success)
            .catch(fail);
}
/* 회원가입 */
function joinUser(user) {
    return http.post(`user/join`, user);
}
/* 중복 id체크 */
function checkId(email) {
    return http.get(`user/idcheck/${email}`)
}

/* 중복 닉네임체크 */
function checkNickName(nickname) {
    return http.get(`user/nickcheck/${nickname}`)
}

/* 내 정보 얻기 */
function getMyInfo() {
    return http.get(`user/info/`);
}

/* 비밀번호 변경 */
function changePassword(email, newPwd, nickname) {
    return http.put(`user/modify`, { email: email, pwd: newPwd, nick: nickname });
}
/* 내 정보 수정 */
function updateUser(id, email, pwd, nickname) {
    return http.put(`user/modify`, { id: id, email: email, pwd: pwd, nick: nickname });
}
/* 탈퇴 하기 */
function deleteUser() {
    return http.delete(`user/delete`);
}

/* 구독리스트 가져오기 */
function getMyList(page, size) {
    return http.get(`post/mylist/${page}/${size}`);
}
export {
    login,
    findById,
    joinUser,
    checkId,
    checkNickName,
    getMyInfo,
    changePassword,
    updateUser,
    deleteUser,
    getMyList,
}