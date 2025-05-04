import { BrowserRouter, Route, Routes } from "react-router"
import React from "react"
import Welcome from "./pages/Welcome.tsx"
import Chat from './pages/Chat.tsx'


export default function MainRoutes() {
    return (
        <BrowserRouter>
            <Routes>
                <Route Component={Welcome} index path="/"/>
                <Route Component={Chat} path="/chat"/>
            </Routes>
        </BrowserRouter>
    )

}

