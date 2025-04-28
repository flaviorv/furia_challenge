import React from 'react'
import name from '../images/name.png'
import "./Navbar.css"

export default function Navbar() {    
    return (
        <div id='navbar-component'>
            <img id='navbar-furia-img' src={name} alt="Barra de menu com o nome da fúria"/>
            <h1 id='navbar-club-text'>Club</h1>
        </div>
    )
}