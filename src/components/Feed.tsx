import React, { useRef, useEffect } from 'react';
import './Feed.css';
import img4 from '../assets/images/wallpaper3.png';
import img1 from '../assets/images/logoCSGO.png';
import img2 from '../assets/images/wallpaper2.png';
import img3 from '../assets/images/logoLOL.png';

export default function Feed() {
  const titleRef = useRef<HTMLHeadingElement>(null);
  const contentRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    if (titleRef.current && contentRef.current) {
      const titleHeight = titleRef.current.offsetHeight;
      const availableHeight = window.innerHeight - titleHeight;
      contentRef.current.style.maxHeight = `${availableHeight - 100}px`;
    }
  }, []);

  return (
    <div id="feed">
      <h1 id="feed-title" ref={titleRef}>
        Feed
      </h1>
      <div id="feed-content" ref={contentRef}>
        <div className="feed-item">
          <img className="feed-image" src={img1} alt="imagem do conteúdo" />
          <div className="feed-description">
            <h3>Notícia ou conteúdo 1</h3>
            <p>Texto descritivo sobre o evento ou informação.</p>
          </div>
        </div>

        <div className="feed-item">
          <img className="feed-image" src={img2} alt="imagem do conteúdo" />
          <div className="feed-description">
            <h3>Notícia ou conteúdo 2</h3>
            <p>Texto descritivo sobre o evento ou informação.</p>
          </div>
        </div>

        <div className="feed-item">
          <img className="feed-image" src={img3} alt="imagem do conteúdo" />
          <div className="feed-description">
            <h3>Notícia ou conteúdo 3</h3>
            <p>Texto descritivo sobre o evento ou informação.</p>
          </div>
        </div>

        <div className="feed-item">
          <div className="feed-description">
            <img className="feed-image" src={img4} alt="imagem do conteúdo" />
            <h3>Notícia 4</h3>
            <p>Texto descritivo sobre o evento ou informação.</p>
          </div>
        </div>
      </div>
    </div>
  );
}
