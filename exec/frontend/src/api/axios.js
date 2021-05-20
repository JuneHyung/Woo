import axios from 'axios';
// axios 객체 생성
export default axios.create({
    
    baseURL: 'https://k4d109.p.ssafy.io/api/', // baseUrl
    headers: {
        'Content-type': 'application/json',
    },
});