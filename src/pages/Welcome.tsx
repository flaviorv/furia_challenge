import React, { useState } from "react"
import "./Welcome.css"
import wallpaper from '../images/wallpaper1.png'
import Navbar from "../components/Navbar.tsx"
import Footer from "../components/Footer.tsx"
import CreateUserForm from "../components/CreateUserForm.tsx"
import { PageType } from "../components/Navbar.tsx"

export default function Welcome() {
    
    const [page, setPage] = useState(PageType.Signup);

    return (
        <div id="welcome-page"> 
            <Navbar changePage={setPage} currentPage={page} />
            <img id="welcom-img-background" src={wallpaper} alt="Imagem de fundo com arte da Fúria" />
            <div id="welcome-content">
                {page === PageType.Signup ? <CreateUserForm /> : null}
            </div>
            <Footer/>
        </div>
    )
}