import { Route, Routes } from "react-router"
import React from "react"

import CreateUser from "./pages/CreateUser.tsx"
import Login from "./pages/Login.tsx"
import SignUp from "./pages/Signup.tsx"

export default function MainRoutes() {
    return (
        <Routes>
            <Route Component={CreateUser} index path="/"/>
            <Route Component={Login} path="/login"/>
            <Route Component={SignUp} path="/signup"/>
        </Routes>
    )

}

