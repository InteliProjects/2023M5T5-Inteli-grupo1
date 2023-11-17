import * as Dialog from "@radix-ui/react-dialog";
import { Link } from "react-router-dom";
import api from "../../api/api";
import { useState } from "react";
import { AiOutlinePlusCircle } from "react-icons/ai";

// definindo as propriedades do componente
interface DialogDemoProps {
  AddProjeto: string;
}

// componente que renderiza o modal de adicionar projeto
export default function DialogDemo({ AddProjeto }: DialogDemoProps) {
  const [isLoading, setIsLoading] = useState(false); // variável que define se o botão está carregando ou não
  const [name, setName] = useState(""); // nome do projeto
  const [description, setDescription] = useState(""); // descrição do projeto
  const user_id = localStorage.getItem("userId"); // id do usuário

  // função que adiciona um projeto
  async function addProject() {
    await api.post("/project/create-and-assign", {
        project: {
          name: name,
          description: description,
          idUser: user_id,
          startDate: new Date(),
        }
      })
      .then((response) => {
        console.log(response.data)
        console.log(response.data.id)
        window.location.href = "/projects/" + response.data.id
      }
      )
      .catch((error) => console.log(error))
      .finally(() => {
        setIsLoading(false);
        console.log("Project created");
      }
      );
  }

  return (
    <Dialog.Root>
      <Dialog.Trigger asChild>
        <button className="w-44 h-44 flex flex-col bg-white m-6 text-[#080654] justify-center items-center text-center text-2xl border-2 border-black rounded-2xl">
          <AiOutlinePlusCircle className="text-[#080654] text-6xl" />
          {AddProjeto}
        </button>
      </Dialog.Trigger>
      <Dialog.Portal>
        <Dialog.Overlay className="bg-blackA9 data-[state=open]:animate-overlayShow fixed inset-0" />
        <Dialog.Content className="data-[state=open]:animate-contentShow fixed top-[50%] left-[50%] max-h-[85vh] w-[90vw] max-w-[450px] translate-x-[-50%] translate-y-[-50%] rounded-[6px] bg-white p-[25px] shadow-[hsl(206_22%_7%_/_35%)_0px_10px_38px_-10px,_hsl(206_22%_7%_/_20%)_0px_10px_20px_-15px] focus:outline-none">
          <Dialog.Title className="text-mauve12 m-0 text-[17px] font-medium">
            Add project
          </Dialog.Title>
          <Dialog.Description className="text-mauve11 mt-[10px] mb-5 text-[15px] leading-normal"></Dialog.Description>
          <fieldset className="mb-[15px] flex items-center gap-5">
            <label
              className="text-violet11 w-[90px] text-right text-[15px]"
              htmlFor="name"
            >
              Name:
            </label>
            <input
              className="text-violet11 shadow-violet7 focus:shadow-violet8 inline-flex h-[35px] w-full flex-1 items-center justify-center rounded-[4px] px-[10px] text-[15px] leading-none shadow-[0_0_0_1px] outline-none focus:shadow-[0_0_0_2px]"
              id="name"
              value={name}
              onChange={(e) => setName(e.target.value)}
            />
          </fieldset>
          <fieldset className="mb-[15px] flex items-center gap-5">
            <label
              className="text-violet11 w-[90px] text-right text-[15px]"
              htmlFor="username"
            >
              Description:
            </label>
            <input
              className="text-violet11 shadow-violet7 focus:shadow-violet8 inline-flex h-[35px] w-full flex-1 items-center justify-center rounded-[4px] px-[10px] text-[15px] leading-none shadow-[0_0_0_1px] outline-none focus:shadow-[0_0_0_2px]"
              id="description"
              value={description}
              onChange={(e) => setDescription(e.target.value)}
            />
          </fieldset>
          <div className="mt-[25px] flex justify-end">
            <Dialog.Close asChild>
              <Link to={"/projects"}>
                <button 
                  className="bg-green4 text-green11 hover:bg-green5 focus:shadow-green7 inline-flex h-[35px] items-center justify-center rounded-[4px] px-[15px] font-medium leading-none focus:shadow-[0_0_0_2px] focus:outline-none"
                  onClick={addProject}
                  disabled={isLoading}
                >
                  {isLoading ? "Loading..." : "Add"}
                </button>
              </Link>
            </Dialog.Close>
          </div>
          <Dialog.Close asChild>
            <button
              className="text-violet11 hover:bg-violet4 focus:shadow-violet7 absolute top-[10px] right-[10px] inline-flex h-[25px] w-[25px] appearance-none items-center justify-center rounded-full focus:shadow-[0_0_0_2px] focus:outline-none"
              aria-label="Close"
            ></button>
          </Dialog.Close>
        </Dialog.Content>
      </Dialog.Portal>
    </Dialog.Root>
  );
}
