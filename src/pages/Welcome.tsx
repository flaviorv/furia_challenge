import React, { useState } from 'react';
import './Welcome.css';
import wallpaper from '../assets/images/wallpaper1.png';
import Navbar from '../components/Navbar.tsx';
import Footer from '../components/Footer.tsx';
import SignupForm from '../components/SignupForm.tsx';
import LoginForm from '../components/LoginForm.tsx';
import { ComponentType } from '../utils.ts';

export default function Welcome() {
  const [component, setComponent] = useState(ComponentType.Welcome);

  return (
    <div id="welcome-page">
      <Navbar changeComponent={setComponent} currentComponent={component} />
      <img id="welcom-img-background" src={wallpaper} alt="Imagem de fundo com arte da Fúria" />
      <div id="welcome-content">
        {component === ComponentType.Signup ? <SignupForm changeComponent={setComponent} /> : null}
        {component === ComponentType.Login ? <LoginForm /> : null}
      </div>
      <Footer />
    </div>
  );
}
