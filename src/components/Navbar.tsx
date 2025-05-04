import React from 'react'
import name from '../assets/images/name.png'
import homeIcon from '../assets/images/home_icon.png'
import "./Navbar.css"
import { ComponentType } from '../utils.ts'

type NavbarProps = {
    changeComponent: (component: ComponentType) => void, currentComponent: ComponentType
}

export default function Navbar({changeComponent, currentComponent}: NavbarProps) {

    return (
        <nav id='navbar-component'>
            <div id='navbar-left' onClick={() => changeComponent(ComponentType.Welcome)}>
                <img id='navbar-home-icon' src={homeIcon} alt="Ícone da home page"/>
            </div>
            <div id='navbar-middle'>
                <img id='navbar-furia-img' src={name} alt="Imagem com nome da Fúria"/>
            </div>
            <div id='navbar-right'>
                {currentComponent !== ComponentType.Signup ?  <p onClick={() => changeComponent(ComponentType.Signup)}>Registrar</p> : undefined}
                {currentComponent !== ComponentType.Login ? <p onClick={() => changeComponent(ComponentType.Login)} >Entrar</p> : undefined }
                <a href='https://www.furia.gg/' target='_blank' rel="noopener noreferrer">Loja Oficial</a>
            </div>
        </nav>
    )
}