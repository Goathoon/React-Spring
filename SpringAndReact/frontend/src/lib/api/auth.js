import client from './client';

//로그인
export const login = ({ username, password }) =>
    client.post('/api/auth/login', { username, password });
//회원 가입
export const register = ({ username, password }) =>
    client.post('/api/auth/register', { username, password });


//로그인 상태 확인
<<<<<<< HEAD
export const check = () => client.get('/api/auth/register'); //register로 다시 get
=======
export const check = () => client.get('/api/auth/check');
>>>>>>> 217c858a9a151d4a69cf6b268c6f82fb054cbe6a

