import React from 'react';
import './NotFound.css'; 

const NotFound = () => {
  return (
    <div className="not-found-container">
      <h1 className="not-found-heading">404 - Página não encontrada</h1>
      <p className="not-found-message">
        Desculpe, a página que você está procurando não foi encontrada.
      </p>
    </div>
  );
}

export default NotFound;