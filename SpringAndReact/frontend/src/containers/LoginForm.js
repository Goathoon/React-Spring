import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { changeField, initializeForm, login } from '../modules/auth';
import AuthForm from '../components/auth/AuthForm';
import { useNavigate } from '../../node_modules/react-router-dom/dist/index';

const LoginForm = () => {
  const dispatch = useDispatch();

  //컴포넌트가 처음 렌더링될 때 form 을 초기화함
  useEffect(() => {
    dispatch(initializeForm('login'));
  }, [dispatch]);

  const { form, auth, authError, isRegister, isLogin } = useSelector(({ auth }) => ({
    form: auth.login,
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
        form: 'login',
        key: name,
        value,
      }),
    );
  };

  // 폼 등록 이벤트 핸들러
  const onSubmit = (e) => {
    e.preventDefault();
    //구현 예정
    // 230124 siwon
    const { username, password } = form;
    if (username === '') {
      window.alert('아이디를 입력하세요.');
      return;
    }
    if (password === '') {
      window.alert('패스워드를 입력하세요.');
      return;
    }
    dispatch(login({ username, password }));
  };

  // 로그인 성공/실패 처리
  useEffect(() => {
    if (authError) {
      window.alert('아이디/비밀번호를 확인해주세요.');
      console.log('오류 발생');
      console.log(authError);
      return;
    }
    if (auth && isLogin) {
      window.alert('로그인 성공!');
      console.log('로그인 성공');
      console.log(auth);
      navigate("/write");
    }
  }, [auth, authError, isLogin]);

  return (
    <AuthForm
      type="login"
      form={form}
      onChange={onChange}
      onSubmit={onSubmit}
    />
  );
};

export default LoginForm;