import axios from 'axios';

// Cria uma instância do axios com a URL base do backend (localhost:8080)
const api = axios.create({
  baseURL: 'http://localhost:8080',
});

export default api;