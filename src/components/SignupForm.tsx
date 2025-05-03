import React from 'react'
import { useForm, SubmitHandler } from "react-hook-form"
import './ResAuthForm.css'

type Inputs = {
    userName: string,
    email: string,
    password: string,
    passwordConfirmation: string
}

export default function SignupForm() {
   
    const {
        register,
        handleSubmit
    } = useForm<Inputs>()
    const onSubmit: SubmitHandler<Inputs> = (data) => console.log(data)

    return (
        <form className="res-auth-form" onSubmit={handleSubmit(onSubmit)}>
            <div className="res-auth-div">
                <h3 className="res-auth-title">Nova Conta</h3>
                <input className="res-auth-input" type="text" placeholder="Username" {...register("userName")}/> 
                <input className="res-auth-input" type="email" placeholder="Email" {...register("email")}/> 
                <input className="res-auth-input" type="password" placeholder="Password" {...register("password")}/>
                <input className="res-auth-input" type="password" placeholder="Confirme o password" {...register("passwordConfirmation")}/>
                <input className="res-auth-submit" type="submit" value={"ENVIAR"}/>
            </div>
        </form>
    )

}
