import { BrowserRouter, Routes, Route } from "react-router-dom";
import Layout from "./Layout";
import Algorithm from "../pages/Algorithm";
import Grafo from "../pages/Grafo";
import LoginPage from "../pages/LoginPage";
import RegisterPage from "../pages/RegisterPage";
import HomePage from "../pages/HomePage";
import Projects from "../pages/Projects";
import Teste from "../pages/Teste";
import ProfilePage from "../pages/ProfilePage";
import NotFound from "../pages/NotFound";

export default function AppRoutes() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route path="/" element={<HomePage />} />
          <Route path="/about" element={<h1>about</h1>} />
          <Route path="/teste" element={<Teste />} />
          <Route path="/projects" element={<Projects />} />
          <Route path="/projects/:id" element={<Grafo />} />
          <Route path="/algorithm" element={<Algorithm />} />
          <Route path="/profile" element={<ProfilePage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/unautorized" element={<NotFound />} />
          <Route path="*" element={<NotFound />} />
  
      </Route>
      </Routes>
    </BrowserRouter>
  );
}