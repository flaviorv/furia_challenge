import React, { useEffect, useState, useRef } from 'react';
import { getInfoFromToken } from '../utils.ts';
import ChatBox from '../components/ChatBox.tsx';
import furiaIcon from '../assets/images/furia_icon.png';
import Feed from '../components/Feed.tsx';
import './FanPage.css';

export default function FanPage() {
  const user = getInfoFromToken();
  const [isOpen, setIsOpen] = useState(false);

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
  }, []);

  return (
    <div id="fan-page">
      <nav id="fan-page-nav" ref={navRef}>
        <img id="fan-page-furia-icon" src={furiaIcon} alt="Ícone da Fúria na barra de navegação" />
        <div className="fan-page-dropdown" ref={menuRef}>
          <span className="fan-page-username" onClick={() => setIsOpen(!isOpen)}>
            {user?.username} ▾
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
      </nav>
      <section id="fan-page-feed" ref={feedRef}>
        <Feed />
      </section>
      <section id="fan-page-chat-box" ref={chatRef}>
        <ChatBox />
      </section>
    </div>
  );
}
