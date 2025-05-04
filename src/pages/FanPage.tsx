import React from 'react'
import { getInfoFromToken } from '../utils.ts';
import ChatBox from '../components/ChatBox.tsx'

export default function FanPage() {

    const user = getInfoFromToken();

    return (
        <div id="furia-chat-room">
            <h1>Fan Page</h1>
            <h2>{user?.username}</h2>
            <h2>{user?.role}</h2>
            <ChatBox />
        </div>
        
    )
}