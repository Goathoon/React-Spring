import React from 'react';
import AuthTemplate from '../components/auth/AuthTemplate';
import LogoutForm from '../containers/LogoutForm';
const LogoutPage = () => {
  return (
    <AuthTemplate>
      <LogoutForm />
    </AuthTemplate>
  )
};

export default LogoutPage;