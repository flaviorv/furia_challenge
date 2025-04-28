import { useForm, SubmitHandler } from "react-hook-form"
import React, {useEffect} from "react"
import "./CreateUser.css"
import wallpaper from '../images/wallpaper1.png'
import Navbar from "../components/Navbar.tsx"

type Inputs = {
    userName: string,
    email: string,
    password: string,
    passwordConfirmation: string
}


export default function CreateUser() {
    useEffect(()=>{
        document.title = "Fúria - Cadastro"
    },[])

    const {
        register,
        handleSubmit
    } = useForm<Inputs>()
    const onSubmit: SubmitHandler<Inputs> = (data) => console.log(data)

    return (
        <div className="signup-page">
            <Navbar/>
            <img id="signup-img-background" src={wallpaper} alt="Imagem de fundo com arte da Fúria" />
            
            <form id="signup-form" onSubmit={handleSubmit(onSubmit)}>
                <div id="signup-form-div">
                    <h3 className="signup-title">Nova Conta</h3>
                    <input className="signup-input" type="text" placeholder="Username" {...register("userName")}/> 
                    <input className="signup-input" type="email" placeholder="Email" {...register("email")}/> 
                    <input className="signup-input" type="password" placeholder="Password" {...register("password")}/>
                    <input className="signup-input" type="password" placeholder="Confirme o password" {...register("passwordConfirmation")}/>
                    <input className="signup-submit" type="submit" value={"ENVIAR"}/>
                </div>
            </form>
            
        </div>
        
    )
}