import { useForm, SubmitHandler } from "react-hook-form"
import React from "react"

type Inputs = {
    userName: string,
    email: string,
    password: string,
    passwordConfirmation: string
}

export default function CreateUser() {
    const {
        register,
        handleSubmit
    } = useForm<Inputs>()
    const onSubmit: SubmitHandler<Inputs> = (data) => console.log(data)

    return (
        <div>
            <h1>Nova Conta</h1>
            <form onSubmit={handleSubmit(onSubmit)}>
                <input placeholder="Username" {...register("userName")}/> 
                <input placeholder="Email" {...register("email")}/> 
                <input placeholder="Password" {...register("password")}/>
                <input placeholder="Confirme o password" {...register("passwordConfirmation")}/>
            </form>
        </div>
        
    )
}