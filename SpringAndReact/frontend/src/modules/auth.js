import { createAction, handleActions } from 'redux-actions';
import produce from 'immer';
import { takeLatest,call } from 'redux-saga/effects';
import createRequestSaga, {
  createRequestActionTypes
} from '../lib/createRequestSaga';
import * as authAPI from '../lib/api/auth';
import { useCallback } from 'react';

const CHANGE_FIELD = 'auth/CHANGE_FIELD';
const INITIALIZE_FORM = 'auth/INITIALIZE_FORM';

const [REGISTER, REGISTER_SUCCESS, REGISTER_FAILURE] = createRequestActionTypes(
  'auth/REGISTER'
);

const [LOGIN, LOGIN_SUCCESS, LOGIN_FAILURE] = createRequestActionTypes(
  'auth/LOGIN'
);
const LOGOUT = 'user/LOGOUT';
function *logoutSaga(){
  try{
    yield call(authAPI.logout); //logout API 호출
    localStorage.removeItem('user');
  }catch (e){
    console.log(e);
  }
}
export const changeField = createAction(
  CHANGE_FIELD,
  ({ form, key, value }) => ({
    form, // register , login
    key, // username, password, passwordConfirm
    value // 실제 바꾸려는 값
  })
);
export const initializeForm = createAction(INITIALIZE_FORM, form => form); // register / login
export const register = createAction(REGISTER, ({ username, password }) => ({
  username,
  password
}));
// username,password라는 payload가 담김 (회원가입하자마자)


export const login = createAction(LOGIN, ({ username, password }) => ({
  username,
  password
}));

export const logout = createAction(LOGOUT);
// saga 생성
const registerSaga = createRequestSaga(REGISTER, authAPI.register);

//앞에서 RequestActionType으로 REGISTER, REG성공, REG실패 모두 정의함.
//그 후에, REGISTER 상태 타입에 Action을 달아줌(아이디,password를 인자로),
//해당 REGISTER를 api로 실행하기 위해 requestsaga 를 만든 것임.
// REG action으로 api를 쏘고, 그 후에, saga를 통해 register_success 상태함수가 동작하면서 
// 스프링에서 받아온 member객체가 auth에 들어가게됨.
const loginSaga = createRequestSaga(LOGIN, authAPI.login);
export function* authSaga() {
  yield takeLatest(REGISTER, registerSaga);
  yield takeLatest(LOGIN, loginSaga);
  yield takeLatest(LOGOUT,logoutSaga);
}

const initialState = {
  register: {
    username: '',
    password: '',
    passwordConfirm: ''
  },
  login: {
    username: '',
    password: ''
  },
  auth: null,
  authError: null
};

const auth = handleActions(// createAction생성시, 인자가 존재하면 payload를 추가로 붙여줘야함.
  {
    [CHANGE_FIELD]: (state, { payload: { form, key, value } }) =>
      produce(state, draft => {
        draft[form][key] = value; // 예: state.register.username을 바꾼다
      }),
    [INITIALIZE_FORM]: (state, { payload: form }) => ({ //이 payload는 이미 createAction할 때 건내준 인자임.
      ...state,
      [form]: initialState[form],
      auth:null, //이렇게 정의해야 로그인화면으로 넘어가지 않는다!!
      authError: null // 폼 전환 시 회원 인증 에러 초기화
    }),
    [LOGOUT]: state=> ({
      ...state,
      user:null,
    }),
    // 회원가입 성공
    [REGISTER_SUCCESS]: (state, { payload: auth }) => ({ //saga에서 정의한 register임 따라서 payload는 saga내 비동기 함수 response.data로 들어감.(api 결과)
      // 따로 인자를 주지 않아도, saga에서 이미 정의가 되어진 것임.
      ...state,
      authError: null,
      auth //스프링에서 쏜 member객체가 auth로 들어감
    }),
    // 회원가입 실패
    [REGISTER_FAILURE]: (state, { payload: error }) => ({
      ...state,
      authError: error
    }),
    // 로그인 성공
    [LOGIN_SUCCESS]: (state, { payload: auth }) => ({
      ...state,
      authError: null,
      auth
    }),
    // 로그인 실패
    [LOGIN_FAILURE]: (state, { payload: error }) => ({
      ...state,
      authError: error
    })
  },
  initialState
);

export default auth;