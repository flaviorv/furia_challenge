import React, { useEffect, useState, useRef } from 'react';
import { getInfoFromToken } from '../utils.ts';
import ChatBox from '../components/ChatBox.tsx';
import Feed from '../components/Feed.tsx';
import './FanPage.css';
import furiaIcon from '../assets/images/furia_icon.png';
import adidasImg from '../assets/images/logoAdidas.png';
import futureIsBlackImg from '../assets/images/logoFutureIsBlack.png';
import Campaign from '../components/Campaign.tsx';

enum Pages {
  FanPage,
  Campaign,
}

export default function FanPage() {
  const user = getInfoFromToken();
  const [isOpen, setIsOpen] = useState(false);
  const [page, setPage] = useState(Pages.FanPage);
  const menuRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const handleClickOutside = (event: MouseEvent) => {
      if (menuRef.current && !menuRef.current.contains(event.target as Node)) {
        setIsOpen(false);
      }
    };
    document.addEventListener('mousedown', handleClickOutside);
    return () => document.removeEventListener('mousedown', handleClickOutside);
  }, []);

  const navRef = useRef<HTMLDivElement>(null);
  const feedRef = useRef<HTMLDivElement>(null);
  const chatRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    if (navRef.current && chatRef.current && feedRef.current) {
      const navHeight = navRef.current.offsetHeight;
      const availableHeight = window.innerHeight - navHeight;

      feedRef.current.style.marginTop = `${navHeight}px`;
      feedRef.current.style.height = `${availableHeight}px`;

      chatRef.current.style.top = `${navHeight}px`;
      chatRef.current.style.height = `${availableHeight}px`;
    }
  }, [page]);

  return (
    <div id="fan-page">
      <nav id="fan-page-nav" ref={navRef}>
        <section className="navbar-left">
          <img id="fan-page-furia-icon" src={furiaIcon} alt="Ícone da Fúria na barra de navegação" />
          <div className="fan-page-dropdown" ref={menuRef}>
            <span className="fan-page-username" id="username" onClick={() => setIsOpen(!isOpen)}>
              {user?.username}
              {isOpen ? null : '▾'}
            </span>
            {isOpen && (
              <div className="fan-page-dropdown-menu">
                <button className="fan-page-dropdown-button" onClick={() => alert('Ir para configurações')}>
                  Configurações
                </button>
                <button className="fan-page-dropdown-button" onClick={() => alert('Sair do sistema')}>
                  Sair
                </button>
              </div>
            )}
          </div>
        </section>
        <section className="navbar-right">
          <a onClick={() => setPage(Pages.FanPage)} target="_blank" rel="noopener noreferrer">
            👤👥 Comunidade
          </a>
          <a onClick={() => setPage(Pages.Campaign)} target="_blank" rel="noopener noreferrer">
            🏆 Campanhas
          </a>
          <a href="https://www.furia.gg/" target="_blank" rel="noopener noreferrer">
            👕 Loja Oficial
          </a>
          <img className="footer-img fan-page-nav-img" src={futureIsBlackImg} alt="Logo Future is Black" />
          <img className="footer-img fan-page-nav-img" src={adidasImg} alt="Logo Adidas" />
        </section>
      </nav>
      {page === Pages.FanPage ? (
        <>
          <section id="fan-page-feed" ref={feedRef}>
            <Feed />
          </section>
          <section id="fan-page-chat-box" ref={chatRef}>
            <ChatBox />
          </section>
        </>
      ) : (
        <div id="fan-page-campaign">
          <Campaign />
        </div>
      )}
    </div>
  );
}
