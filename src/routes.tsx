import { BrowserRouter, Route, Routes } from "react-router"
import React from "react"
import Welcome from "./pages/Welcome.tsx"
import FanPage from './pages/FanPage.tsx'


export default function MainRoutes() {
    return (
        <BrowserRouter>
            <Routes>
                <Route Component={Welcome} index path="/"/>
                <Route Component={FanPage} path="/fan-page"/>
            </Routes>
        </BrowserRouter>
    )

}

