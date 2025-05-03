import React from 'react'
import './Footer.css'
import adidasImg from '../assets/images/logoAdidas.png'
import futureIsBlackImg from '../assets/images/logoFutureIsBlack.png'
import soccerImg from '../assets/images/logoSoccer.png'
import lolImg from '../assets/images/logoLOL.png'
import csgoImg from '../assets/images/logoCSGO.png'
import rsImg from '../assets/images/logoRS.png'
import rlImg from '../assets/images/logoRL.png'
import alImg from '../assets/images/logoAL.png'
import pubgImg from '../assets/images/logoPUBG.png'
import valorantImg from '../assets/images/logoValorant.png'



export default function Footer() {
    return (
        <div id='footer-component'>
            <img className='footer-img' src={alImg} alt='Logo Apex Legends'/>
            <img className='footer-img' src={pubgImg} alt='Logo PUBG'/>
            <img className='footer-img' src={valorantImg} alt='Logo Valorant'/>
            <img className='footer-img' src={rsImg} alt='Logo Rainbow Six' />
            <img className='footer-img' src={rlImg} alt='Logo Rocket League'/>
            <img className='footer-img' src={lolImg} alt='Logo League of Legends'/>
            <img className='footer-img' src={csgoImg} alt='Logo CS GO'/>
            <img className='footer-img' src={soccerImg} alt="Logo Kings League"/>
            <img className='footer-img' src={adidasImg} alt='Logo Adidas'/>
            <img className='footer-img' src={futureIsBlackImg} alt='Logo Future is Black'/>
        </div>
    )
}