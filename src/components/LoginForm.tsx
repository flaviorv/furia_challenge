import React from 'react'
import {useForm, SubmitHandler} from 'react-hook-form'
import './ResAuthForm.css'

type Inputs = {
    userName: string,
    password: string,
}


export default function LoginForm() {


    const {
        register,
        handleSubmit
    } = useForm<Inputs>()
    const onSubmit: SubmitHandler<Inputs> = (data) => console.log(data)

    return (
        <form className="res-auth-form" onSubmit={handleSubmit(onSubmit)}>
            <div className="res-auth-div">
                <h3 className="res-auth-title">Login</h3>
                <input className="res-auth-input" type="text" placeholder="Username" {...register("userName")}/> 
                <input className="res-auth-input" type="password" placeholder="Password" {...register("password")}/>
                <input className="res-auth-submit" type="submit" value={"ENVIAR"}/>
            </div>
        </form>
    )
}