import { BrowserRouter, Route, Routes } from "react-router"
import React from "react"
import CreateUser from "./pages/Welcome.tsx"


export default function MainRoutes() {
    return (
        <BrowserRouter>
            <Routes>
                <Route Component={CreateUser} index path="/"/>
            </Routes>
        </BrowserRouter>
    )

}

