import { BrowserRouter, Route, Routes } from "react-router"
import React from "react"
import Welcome from "./pages/Welcome.tsx"


export default function MainRoutes() {
    return (
        <BrowserRouter>
            <Routes>
                <Route Component={Welcome} index path="/"/>
            </Routes>
        </BrowserRouter>
    )

}

