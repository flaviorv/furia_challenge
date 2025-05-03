import React from 'react'
import name from '../assets/images/name.png'
import homeIcon from '../assets/images/home_icon.png'
import "./Navbar.css"


export enum PageType { 
    Welcome = "Welcome",
    Login = "Login",
    Signup = "Signup"
}

type NavbarProps = {
    changePage: (pageType: PageType) => void, currentPage: PageType
}

export default function Navbar({changePage, currentPage}: NavbarProps) {

    return (
        <nav id='navbar-component'>
            <div id='navbar-left' onClick={() => changePage(PageType.Welcome)}>
                <img id='navbar-home-icon' src={homeIcon} alt="Ícone da home page"/>
            </div>
            <div id='navbar-middle'>
                <img id='navbar-furia-img' src={name} alt="Imagem com nome da Fúria"/>
            </div>
            <div id='navbar-right'>
                {currentPage !== PageType.Signup ?  <p onClick={() => changePage(PageType.Signup)}>Registrar</p> : undefined}
                {currentPage !== PageType.Login ? <p onClick={() => changePage(PageType.Login)} >Entrar</p> : undefined }
                <a href='https://www.furia.gg/' target='_blank' rel="noopener noreferrer">Loja Oficial</a>
            </div>
        </nav>
    )
}