import { Link } from 'react-router-dom';
import DialogDemo from '../components/dialog/DialogDemo';
import { useEffect, useState } from 'react';
import api from '../api/api';
import trash from '../../public/trash.svg';
import { ToastContainer, toast } from 'react-toastify';

/**
 * Esta classe representa a interface de visualização de projetos de um usuário.
 * Ela exibe uma lista de projetos com seus nomes e descrições, permitindo ao
 * usuário clicar em "Come in!" para acessar os detalhes do projeto. Além disso,
 * cada projeto agora possui um ícone "trash" que funciona como um botão de
 * exclusão, permitindo ao usuário remover projetos diretamente da lista. A
 * classe também oferece a funcionalidade de adicionar novos projetos por meio de
 * um card de criação de projeto. Usamos axios para fazer solicitações e receber e com isso
 * conseguimos pegar os dados do banco de dados.
 */


interface ProjectProps {
  id: number;
  name: string;
  description: string;
}

function Project() {
  const [projects, setProjects] = useState([]);
  const user_id = String(localStorage.getItem('userId'));

  async function getProjects() {
    try {
      const response = await api.get(`/project/user/${user_id}`);
      setProjects(response.data);
      console.log(user_id)
    } catch (error) {
      console.log(error);
    }
  }

  async function deleteProject(id) {
    try {
      await api.delete(`/project/${id}`);
      // Remova o projeto da lista local
      setProjects((prevProjects) => prevProjects.filter((projeto) => projeto.id !== id));
      toast.success("Projeto deletado com sucesso!");
      console.log("Project deleted successfully");
    } catch (error) {
      console.error("Error deleting project:", error);
    }
  }

  useEffect(() => {
    if(user_id === 'null'){
      window.location.href = '/login';
    }
    console.log(user_id)
  });

  useEffect(() => {
    getProjects();
  }, []);

  return (
    <div className="flex justify-center items-center min-h-[89vh] bg-gray-300">
      <div className="flex flex-wrap max-w-full justify-center items-start mt-5">
        {projects.map((projeto: ProjectProps) => (
          <div
            className="w-72 h-72 rounded-2xl bg-white break-words p-5 m-6 flex justify-center items-center text-center text-black text-2xl border-2 border-black"
            key={projeto.id}
          >
            <div className="w-72 h-72 flex flex-col justify-center mt-4">
              <div>{projeto.name}</div>
              <div className="w-full h-1 bg-black"></div>
              <div className="text-sm pt-4 text-left">
                <p className="max-h-[100px] overflow-hidden">{projeto.description}</p>
              </div>
              <div className="flex mt-auto mb-6">
                <Link to={'/projects/' + projeto.id} key={projeto.id}>
                  <button className="box-border w-[170px] font-bold font-montserrat text-[20px] text-white inline-flex h-[60px] items-center justify-center rounded-[20px] bg-[#080654] px-[15px] leading-none">
                    Come in!
                  </button>
                </Link>
                <div>
                  {/* Transforme a imagem "trash" em um botão */}
                  <button onClick={() => deleteProject(projeto.id)} className="w-8  mt-2 ml-8">
                    <img src={trash} alt="Delete" />
                  </button>
                </div>
              </div>
            </div>
          </div>
        ))}
        <DialogDemo AddProjeto="Add Project" />
      </div>
      <ToastContainer
        position="top-right"
        autoClose={2000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick={true}
        rtl={false}
        pauseOnFocusLoss={false}
        draggable={true}
        pauseOnHover={false}
        theme="light"
      />
    </div>
  );
}

export default Project;
