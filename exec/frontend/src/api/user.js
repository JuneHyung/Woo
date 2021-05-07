import http from './axios.js';

function login(userinput, success, fail) {
    
    const user = {
        email: userinput.id,
        pwd: userinput.password,
    }
    http.post('user/login', JSON.stringify(user)).then(success).catch(fail);
    
    };

    async function findById( success, fail) { 
        http.defaults.headers['X-AUTH-TOKEN'] = window.localStorage.getItem('X-AUTH-TOKEN');
        console.log(`${http.defaults.headers['X-AUTH-TOKEN']}`);
        await http
            .get(`/user/info`)
            .then(success)
            .catch(fail);
    }
export { login, findById }