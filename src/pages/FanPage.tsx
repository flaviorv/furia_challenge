import React, {useEffect, useRef} from 'react'
import { getInfoFromToken } from '../utils.ts';
import ChatBox from '../components/ChatBox.tsx'
import './FanPage.css'
import furiaIcon from '../assets/images/furia_icon.png'

export default function FanPage() {

    const user = getInfoFromToken();
    const pageRef = useRef<HTMLDivElement>(null)

    const scrollPage = () => {
        const randomNum = Math.random() * pageRef.current.scrollHeight
        pageRef.current?.scrollTo({
            top: randomNum,
            behavior: "smooth"
        })
        console.log(randomNum)
    }

    useEffect(() => {
        const interval = setInterval(() => {
            scrollPage()
        }, 5000)
        return () => clearInterval(interval)
    },[])

    return (
        <div id="fan-page" ref={pageRef}>
            <section id="fan-page-feed">
                <nav id="fan-page-nav">
                    <img id="fan-page-furia-icon" src={furiaIcon} alt="Ícone da Fúria na barra de navegação"/>
                    <h2>{user?.username}</h2>
                </nav>
                
                <h1 id="fan-page-title">Feed</h1>
                
            </section>
            <section id="fan-page-chat-box">
                <ChatBox />
            </section>
        </div>
    )
}