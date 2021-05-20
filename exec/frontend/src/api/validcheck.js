// var RE2 = require("re2");

// 비밀번호 체크 정규식
var checkPwd = new RegExp(/^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.\-_*])([a-zA-Z0-9!@#$%^&+=*.\-_]){9,15}$/);

// 이메일 체크 정규식
var checkEmail = new RegExp(/[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/,'i');

function checkPasswordValid(password) {
    return checkPwd.test(password);
}

function checkEmailValid(email) {
    return checkEmail.test(email);
}

export { checkPasswordValid, checkEmailValid}