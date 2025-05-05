import React, {useEffect, useState, useRef} from 'react'
import axios from 'axios'
import { useForm, SubmitHandler } from 'react-hook-form'
import './ChatBox.css'
import { getInfoFromToken } from '../utils.ts'

type Message = {
    message: string
}

type ReceivedMessage = {
    id: string
    timestamp: string,
    sender: string,
    message: string
}

export default function ChatBox() {

    async function getChatMessages() {    
        try {
            const token: string | null = localStorage.getItem("furia-jwt")
            const response = await axios.get("http://localhost:8080/chat", {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            })
            const newMessages = response.data
            setAllMessages(prevMessages => {
                if (prevMessages.length === newMessages.length) {
                    const lastPrev = prevMessages[prevMessages.length - 1]
                    const lastNew = newMessages[newMessages.length - 1]
                    
                    if (lastPrev?.id === lastNew?.id) {
                        return prevMessages
                    }
                }
                console.log("chamou")
                return newMessages
            })
        } catch (error) {
            console.log(error.status, error.code)
        }
    }

    async function sendMessage(message: Message) {    
        try {
            const token: string | null = localStorage.getItem("furia-jwt")
            await axios.post("http://localhost:8080/chat", message, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            })
        } catch (error) {
            console.log(error.status, error.code)
        }
    }

    function getTime(timestamp: string): string {
        const date = new Date(timestamp)
        const time = date.getHours().toString()+":"+date.getMinutes().toString()
        return time
    }

    function isMe(sender: string): boolean {
        return (userInfo?.username === sender || (sender === "FURIA" && userInfo?.role === "ROLE_ADMIN"))
    }

    const [allMessages, setAllMessages] = useState<ReceivedMessage[]>([]);
    const userInfo = getInfoFromToken()
    const chatRef = useRef<HTMLDivElement>(null)
    const [isAtBottom, setIsAtBottom] = useState(true)
    const [newMessagesWarning, setNewMessagesWarning] = useState(false)

    const handleScroll = () => {
        const element = chatRef.current
        if (!element) return
      
        const threshold = 10
        const atBottom = element.scrollHeight - element.scrollTop - element.clientHeight < threshold
        setIsAtBottom(atBottom)
        if (atBottom) {
            setNewMessagesWarning(false)
        }
    }

    useEffect(() => {
        getChatMessages()
        const interval = setInterval(() => {
            getChatMessages()    
        }, 5000);

        return () => clearInterval(interval)
    },[])

    useEffect(() => {
        if (isAtBottom) {
            chatRef.current?.scrollTo({
              top: chatRef.current.scrollHeight,
              behavior: "smooth"
            })
        }
    }, [allMessages, isAtBottom])

    useEffect(()=> {
        if (!isAtBottom && !isMe(allMessages[allMessages.length-1].sender)){
            setNewMessagesWarning(true)
        }
    },[allMessages])

    const {
        register,
        handleSubmit,
        reset
    } = useForm<Message>() 
    const onSubmit: SubmitHandler<Message> = (message) => {
        sendMessage(message)
        reset()
    }

    return (
        <div id="furia-chat-box">
            <h1 id="furia-chat-title">Chat Room</h1>
            <h3 id="furia-chat-warning">Somente as últimas 200 mensagens são persistidas</h3>
            <section id="furia-chat-section" ref={chatRef} onScroll={handleScroll}>
                {allMessages.map((message) => {
                    const senderType = isMe(message.sender) ? "me" : "other"
                    return (
                        <article className="furia-chat-article" id={"furia-chat-article-"+senderType} key={message.id}>
                            <p id="chat-message-sender" className="furia-chat-message">{message.sender}</p>
                            <p id="chat-message-message" className="furia-chat-message">{message.message}</p>    
                            <p id="chat-message-timestamp" className="furia-chat-message">{getTime(message.timestamp)}</p>
                        </article>
                    )
                })}        
            </section>
            <p id="furia-chat-new-messages-warning" style={{visibility: newMessagesWarning ? "visible" : "collapse"}}>↓ Novas Mensagens ↓</p>
            <form id="furia-chat-form" onSubmit={handleSubmit(onSubmit)}>
                <input id="furia-chat-input" type="text" placeholder="Escrever mensagem" {...register("message")}/>
                <input id="furia-chat-submit" type="submit" value="➤" />
            </form>
        </div>
    )
}