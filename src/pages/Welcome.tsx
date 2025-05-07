import React, { useState, useEffect } from 'react';
import './Welcome.css';
import { useNavigate } from 'react-router-dom';
import wallpaper from '../assets/images/wallpaper1.png';
import Navbar from '../components/Navbar.tsx';
import Footer from '../components/Footer.tsx';
import SignupForm from '../components/SignupForm.tsx';
import LoginForm from '../components/LoginForm.tsx';
import { ComponentType, getInfoFromToken } from '../utils.ts';
import Home from '../components/Home.tsx';

export default function Welcome() {
  const [component, setComponent] = useState(ComponentType.Welcome);
  const user = getInfoFromToken();
  const navigate = useNavigate();

  useEffect(() => {
    if (user != null) {
      return navigate('/fan-page');
    }
  }, []);

  return (
    <div id="welcome-page">
      <Navbar changeComponent={setComponent} currentComponent={component} />
      <img id="welcom-img-background" src={wallpaper} alt="Imagem de fundo com arte da Fúria" />
      <div id="welcome-content">
        {component === ComponentType.Signup ? <SignupForm changeComponent={setComponent} /> : null}
        {component === ComponentType.Login ? <LoginForm /> : null}
        {component === ComponentType.Welcome ? <Home /> : null}
      </div>
      <Footer />
    </div>
  );
}
