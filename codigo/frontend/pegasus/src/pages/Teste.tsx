import { useState } from "react";
import api from "../api/api";

export default function Teste() {
    const [loading, setLoading] = useState(false);

  const fetchData = async () => {
    setLoading(true);
    try {
      const response = await api.post("/graph/export", {
        allPaths: [1, 2, 3],
        baseFilename: "arquivo"
      }, {
        responseType: 'blob', // Indicamos que a resposta é um blob
      });

      const blob = new Blob([response.data], { type: 'application/octet-stream' });
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement("a");
      a.style.display = "none";
      a.href = url;
      a.download = "arquivo.xlsx"; // Nome do arquivo
      document.body.appendChild(a);
      a.click();
      window.URL.revokeObjectURL(url);
    } catch (error) {
      console.error("Erro na requisição:", error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      {loading ? (
        <p>Carregando...</p>
      ) : (
        <button onClick={fetchData}>Baixar Arquivo Excel</button>
      )}
    </div>
  );
}
