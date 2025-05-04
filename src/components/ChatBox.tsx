import React, {useEffect, useState} from 'react'
import axios from 'axios'
import { useForm, SubmitHandler } from 'react-hook-form'

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

    const [allMessages, setAllMessages] = useState(Array<ReceivedMessage>)

    useEffect(() => {
        const interval = setInterval(() => {
            getChatMessages()    
        }, 5000);

        return () => clearInterval(interval)
    },[])

    async function getChatMessages() {    
        try {
            const token: string | null = localStorage.getItem("furia-jwt")
            const response = await axios.get("http://localhost:8080/chat", {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            })
            console.log(response.data)
            setAllMessages(response.data)
        } catch (error) {
            console.log(error.status, error.code)
        }
    }

    async function sendMessage(message: Message) {    
        try {
            const token: string | null = localStorage.getItem("furia-jwt")
            const response = await axios.post("http://localhost:8080/chat", message, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            })
            console.log(response.data)
        } catch (error) {
            console.log(error.status, error.code)
        }
    }

    function getTime(timestamp: string): string {
        const date = new Date(timestamp)
        console.log(date.getHours(), date.getMinutes())
        const time = date.getHours().toString()+":"+date.getMinutes().toString()
        return time
    }

    const {
        register,
        handleSubmit
    } = useForm<Message>()
    const onSubmit: SubmitHandler<Message> = (message) => sendMessage(message)

    return (
        <div id="furia-chat-box">
            <h1 id="furia-chat-title">Chat Box</h1>
            <section id="furia-chat-section">
                {allMessages.map((message) => (
                    <article key={message.id}>
                        <p>{getTime(message.timestamp)}</p>
                        <p>{message.sender}</p>
                        <p>{message.message}</p>
                    </article>
                ))}
            </section>
            <form id="furia-chat-form" onSubmit={handleSubmit(onSubmit)}>
                <input id="furia-chat-input" type="text" placeholder="Enviar mensagem" {...register("message")}/>
                <input id="furia-chat-submit" type="submit" value="Enviar" />
            </form>
        </div>
    )
}