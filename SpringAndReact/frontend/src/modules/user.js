//해당 모듈 기능은 사용하지 않아보겠습니다.
import { createAction, handleActions } from 'redux-actions';
import { takeLatest } from 'redux-saga/effects';
import * as authAPI from '../lib/api/auth';
import createRequestSaga, {
  createRequestActionTypes
} from '../lib/createRequestSaga';

const TEMP_SET_USER = 'user/TEMP_SET_USER'; // 새로고침 이후 임시 로그인 처리
// 회원 정보 확인
const [CHECK, CHECK_SUCCESS, CHECK_FAILURE] = createRequestActionTypes(
  'user/CHECK'
);

export const tempSetUser = createAction(TEMP_SET_USER, user => user);
export const check = createAction(CHECK, (username => ({
  username
})));

// const checkSaga = createRequestSaga(CHECK, authAPI.check);
// export function* userSaga() {
//   yield takeLatest(CHECK, checkSaga);
// }

const initialState = {
  user: null,
  checkError: null
};

const user = handleActions(
  {
    [TEMP_SET_USER]: (state, { payload: user }) => ({
      ...state,
      user
    }),
    [CHECK_SUCCESS]: (state, { payload: user }) => ({
      ...state,
      user,
      checkError: null
    }),
    [CHECK_FAILURE]: (state, { payload: error }) => ({
      ...state,
      user: null,
      checkError: error
    })
  },
  initialState
);

export default user;