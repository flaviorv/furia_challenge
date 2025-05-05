import React, { useState } from 'react';
import { useForm, SubmitHandler } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';
import './ResAuthForm.css';
import axios from 'axios';

type Inputs = {
  username: string;
  password: string;
};

export default function LoginForm() {
  const [isVisible, setVisibility] = useState(false);
  const navigate = useNavigate();

  async function authenticate(userData: Inputs) {
    try {
      const response = await axios.post('http://localhost:8080/login', userData);
      const token = response.data;
      localStorage.setItem('furia-jwt', token);
      navigate('/fan-page');
    } catch (error) {
      setVisibility(true);
    }
  }

  const { register, handleSubmit } = useForm<Inputs>();
  const onSubmit: SubmitHandler<Inputs> = (data) => authenticate(data);

  return (
    <form className="res-auth-form" onSubmit={handleSubmit(onSubmit)}>
      <div className="res-auth-div">
        <h3 className="res-auth-title">Acesso</h3>
        <input className="res-auth-input" type="text" placeholder="Username" {...register('username')} />
        <input className="res-auth-input" type="password" placeholder="Password" {...register('password')} />
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
