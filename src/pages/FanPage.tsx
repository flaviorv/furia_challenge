import React, { useEffect, useRef, useState } from 'react';
import { getInfoFromToken } from '../utils.ts';
import ChatBox from '../components/ChatBox.tsx';
import './FanPage.css';
import furiaIcon from '../assets/images/furia_icon.png';

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

  return (
    <div id="fan-page">
      <nav id="fan-page-nav">
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

      <div id="fan-page-content">
        <div id="fan-page-feed">
          <nav id="fan-page-nav">...</nav>
          <h1 id="fan-page-title">Feed</h1>

          <div className="feed-item">
            <div className="feed-description">
              <h3>Notícia ou conteúdo 1</h3>
              <p>Texto descritivo sobre o evento ou informação.</p>
            </div>
            <img className="feed-image" src="https://via.placeholder.com/150" alt="imagem do conteúdo" />
          </div>

          <div className="feed-item">
            <div className="feed-description">
              <h3>Notícia 2</h3>
              <p>Mais um texto explicando o conteúdo.</p>
            </div>
            <img className="feed-image" src="https://via.placeholder.com/150" alt="imagem do conteúdo" />
          </div>
        </div>

        <section id="fan-page-chat-box">
          <ChatBox />
        </section>
      </div>
    </div>
  );
}
