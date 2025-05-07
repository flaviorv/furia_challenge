import React, { useState } from 'react';
import { useForm, SubmitHandler } from 'react-hook-form';
import './ResAuthForm.css';
import axios from 'axios';
import { ComponentType } from '../utils.ts';

type Inputs = {
  username: string;
  email: string;
  password: string;
  passwordConfirmation: string;
};

type Prop = {
  changeComponent: (component: ComponentType) => void;
};

export default function SignupForm({ changeComponent }: Prop) {
  const [isVisible, setVisibility] = useState(false);

  async function createNewUser(userData: Inputs) {
    try {
      await console.log(userData);
      await axios.post('http://localhost:8080/register', userData);
      changeComponent(ComponentType.Login);
    } catch (error) {
      console.log(error);
      setVisibility(true);
    }
  }

  const { register, handleSubmit } = useForm<Inputs>();
  const onSubmit: SubmitHandler<Inputs> = (data) => createNewUser(data);

  return (
    <form className="res-auth-form" onSubmit={handleSubmit(onSubmit)}>
      <div className="res-auth-div">
        <h3 className="res-auth-title">Nova Conta</h3>
        <input className="res-auth-input" type="text" placeholder="Username" {...register('username')} />
        <input className="res-auth-input" type="email" placeholder="Email" {...register('email')} />
        <input className="res-auth-input" type="password" placeholder="Password" {...register('password')} />
        <input className="res-auth-input" type="password" placeholder="Confirme o password" {...register('passwordConfirmation')} />
        <input className="res-auth-submit" type="submit" value={'ENVIAR'} />
        <b
          className="res-auth-error"
          style={{
            color: 'red',
            visibility: isVisible ? 'visible' : 'collapse',
          }}
        >
          Dados inválidos
        </b>
      </div>
    </form>
  );
}
