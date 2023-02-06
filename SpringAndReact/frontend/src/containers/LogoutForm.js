import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { changeField, initializeForm, logout } from '../modules/auth';
import AuthForm from '../components/auth/AuthForm';
import { useNavigate } from '../../node_modules/react-router-dom/dist/index';

const LogoutForm = () => {
  const dispatch = useDispatch();

  //컴포넌트가 처음 렌더링될 때 form 을 초기화함
  useEffect(() => {
    dispatch(initializeForm('logout'));
  }, [dispatch]);

  const { form, auth, authError, isRegister, isLogin } = useSelector(({ auth }) => ({
    form: auth.logout,
    auth: auth.auth,
    authError: auth.authError,
    isRegister: auth.isRegister,
    isLogin: auth.isLogin
  }));
  const navigate = useNavigate();


  // 인풋 변경 이벤트 핸들러
  const onChange = (e) => {
    const { value, name } = e.target;
    dispatch(
      changeField({
        form: 'logout',
        key: name,
        value,
      }),
    );
    // console.log(form, name, value)
  };

  // 폼 등록 이벤트 핸들러
  const onSubmit = (e) => {
    e.preventDefault();
    // 230124 siwon
    const { username } = form;
    if (username === '') {
      window.alert('아이디를 입력하세요.');
      return;
    }
    dispatch(logout({ username }));
  };

  // 로그인 성공/실패 처리
  useEffect(() => {
    if (authError) {
      window.alert('아이디/비밀번호를 확인해주세요.');
      console.log('오류 발생');
      console.log(authError);
      return;
    }
    if (auth && !isLogin) {
      window.alert('로그아웃 성공!');
      console.log('로그아웃 성공');
      console.log(auth);
      navigate("/login");
    }
  }, [auth, authError, isLogin]);

  return (
    <AuthForm
      type="logout"
      form={form}
      onChange={onChange}
      onSubmit={onSubmit}
    />
  );
};

export default LogoutForm;