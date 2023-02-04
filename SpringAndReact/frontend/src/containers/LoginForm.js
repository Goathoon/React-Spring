import React, { useEffect, useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { changeField, initializeForm, login } from '../modules/auth';
import AuthForm from '../components/auth/AuthForm';
// import { check } from '../../modules/user';
import { useNavigate } from 'react-router-dom';

const LoginForm = () => {
  const navigate = useNavigate();
  const [error, setError] = useState(null);
  const dispatch = useDispatch();
  const { form, auth, authError} = useSelector(({ auth }) => ({
    form: auth.login,
    auth: auth.auth,
    authError: auth.authError,
    // user: user.user,
  }));
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
    const { username, password } = form;
    dispatch(login({ username, password })); 
  };

  // 컴포넌트가 처음 렌더링 될 때 form 을 초기화함
  useEffect(() => {
    dispatch(initializeForm('login')); //initializeform이 작동을 하나?
  }, [dispatch]);

  useEffect(() => {
    if (authError) {
      console.log('오류 발생');
      console.log(authError);
      setError('로그인 실패');
      return;
    }
    if (auth) {
      window.alert('로그인 성공!');
      console.log('로그인 성공');
      navigate('/');
      console.log(form);
      try {
        localStorage.setItem('auth', JSON.stringify(auth));
      } catch (e) {
        console.log('localStorage is not working');
      }
      // dispatch(check());
    }
  }, [auth, authError, dispatch]);

  // useEffect(() => {
  //   if (user) {
  //     navigate('/');
  //     try {
  //       localStorage.setItem('user', JSON.stringify(user));
  //     } catch (e) {
  //       console.log('localStorage is not working');
  //     }
  //   }
  // }, [navigate, user]);

  return (
    <AuthForm
      type="login"
      form={form}
      onChange={onChange}
      onSubmit={onSubmit}
      error={error}
    />
  );
};

export default LoginForm;