import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import Header from '../../components/common/Header';
import { logout } from '../../modules/auth';

const HeaderContainer = () => {
  const { auth } = useSelector(({ auth }) => ({ auth: auth.auth }));
  // const dispatch = useDispatch();
  // const onLogout = () => {
  //   dispatch(logout());
  // };
  // return <Header auth={auth} onLogout={onLogout} />;
  return <Header auth={auth} />;
};

export default HeaderContainer;