import React, {useEffect} from 'react'
import { getInfoFromToken } from '../utils.ts';
import axios from 'axios'
// import {useNavigate} from 'react-router-dom'

export default function Chat() {
    // const navigate = useNavigate()

    async function getChatMessages() {    
        try {
            const token: string | null = localStorage.getItem("furia-jwt")
            const response = await axios.get("http://localhost:8080/chat", {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            })
            console.log(response)
        } catch (error) {
            console.log(error.status, error.code)
            // navigate("/")
        }
        
        
    }

    const user = getInfoFromToken();

    useEffect(() => {
        getChatMessages()
    },[])

    return (
        <div id="furia-chat-room">
            <h1>CHAT ROOM</h1>
            <h2>{user?.username}</h2>
            <h2>{user?.role}</h2>
        </div>
        
    )
}