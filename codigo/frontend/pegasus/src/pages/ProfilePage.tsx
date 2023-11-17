import little from "../assets/img/littlestick1.jpg";
import fundo from "../assets/img/imagemfundo.jpeg";
import TextField from "@mui/material/TextField";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import axios from "axios";
import { useForm } from "react-hook-form";
import { useEffect, useState } from "react";
import api from "../api/api";
import { ToastContainer, toast } from "react-toastify";

// definindo as propriedades do componente
interface FormCreateAccountProps {
  user_id: number;
  name: string;
  username: string;
  email: string;
  password?: string | null;
}

interface dataProps {
  name: string;
  username: string;
  email: string;
  password?: string | null;
}

// definindo o schema de validação do formulário
const createUserFormSchema = z.object({
  name: z
    .string()
    .min(1, "O nome deve ter no mínimo 4 caracteres.")
    .nonempty("O nome é obrigatório.")
    .transform((name) => {
      return name
        .trim()
        .split(" ")
        .map((word) => {
          return word[0].toLocaleUpperCase().concat(word.substring(1));
        })
        .join(" ");
    }),
  username: z
    .string()
    .min(1, "O username deve ter no mínimo 4 caracteres.")
    .nonempty("O username é obrigatório."),
  email: z
    .string()
    .nonempty("O email é obrigatório.")
    .email("Formato de email invalido.")
    .toLowerCase()
    .refine((email) => {
      return email.endsWith("@sou.inteli.edu.br");
    }, "O email deve ser institucional."),
});

// definindo o tipo do formulário
type CreateUserForm = z.infer<typeof createUserFormSchema>;

export default function ProfilePage() {
  const user_id = Number(localStorage.getItem("userId"));
  const [output, setOutput] = useState("");
  const [userData, setUserData] = useState<FormCreateAccountProps | null>(null);
  const [name, setName] = useState<string>("");
  const [username, setUsername] = useState<string>("");
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>(null);
  const [usernameBD, setUsernameBD] = useState<string>("");
  const [emailBD, setEmailDB] = useState<string>("");

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<CreateUserForm>({
    resolver: zodResolver(createUserFormSchema),
  });

  function verifyUsername(){
    if (usernameBD != username){
      return username;
    } else {
      return null;
    }
  }

  function verifyEmail(){
    if (emailBD != email){
      return email;
    } else {
      return null;
    }
  }

  async function updateDataById(
    updatedData: Omit<FormCreateAccountProps, "user_id">
  ) {
    try {
      // Adicione o user_id ao objeto antes de enviar a atualização
      await api.patch(`/user/${user_id}`, 
        {
          name: name,
          username: verifyUsername(),
          email: verifyEmail(),
          password: password,
        }
      );
      // console.log(dataWithUserId)
      console.log({
        name: name,
        username: verifyUsername(),
        email: verifyEmail(),
        password: password,
      });
      console.log(emailBD);
      console.log(usernameBD);
      toast.success("Dados atualizados com sucesso!");
      fetchUserDataById();
    } catch (error) {
      console.log({ ...updatedData, user_id });
      console.error("Erro ao atualizar dados do usuário:", error);
      toast.error("Erro ao atualizar dados do usuário!, Username ou Email já cadastrado.");
    }
  }

  // ...

  const onSubmit = (data: Omit<CreateUserForm, "user_id">) => {
    updateDataById(data);
  };

  async function fetchUserDataById() {
    try {
      const response = await api.get(`/user/${user_id}`);
      const userData = response.data;
      setUserData(userData);
      setName(userData.name);
      setUsername(userData.username);
      setEmail(userData.email);
      setPassword(userData.password);
      setEmailDB(userData.email);
      setUsernameBD(userData.username);

    } catch (error) {
      console.error("Erro ao buscar dados do usuário:", error);
    }
  }

  useEffect(() => {
    fetchUserDataById();
  }, []);

  return (
    <div className="flex justify-center h-[88vh] ">
      <div className="ml-10 mt-12 pt-6 w-[1100px] h-[500px] bg-white rounded-xl  shadow-md shadow-black flex flex-col items-center relative">
        <img
          className="rounded-xl ml-24 mt-16 w-32  inline-flex items-center justify-center outline-none shadow-xl"
          aria-label="User Menu"
          src={little}
          alt="Usuario"
          style={{
            position: "absolute",
            left: 0,
            width: "100%", // Add this line to make the image fit within the container
            height: "auto", // Automatically adjust the height to maintain aspect ratio
          }}
        />
        <div
          className="w-[1000px] h-[200px] rounded-xl shadow-xl"
          style={{
            background: `url(${fundo})`,
            backgroundSize: "cover",
            backgroundPosition: "center",
          }}
        ></div>
        <div className=" w-1/2">
          <h1 className="font-wans mt-2   text-start text-[25px] text-[#3e3e3e]">
            {name}
          </h1>
        </div>
        <form onSubmit={handleSubmit(onSubmit)}>
          <div className="flex flex-col items-center">
            <div className="flex flex-row justify-around gap-10 translate-y-20 w-11/12">
              <TextField
                id="name"
                label="Name"
                onChange={(event) => setName(event.target.value)}
                value={name} // Defina o valor inicial com base no estado local
              />
              <TextField
                id="username"
                label="Username"
                onChange={(event) => setUsername(event.target.value)}
                value={username} // Defina o valor inicial com base no estado local
              />
              <TextField
                id="email"
                label="Email"
                onChange={(event) => setEmail(event.target.value)}
                value={email} // Defina o valor inicial com base no estado local
              />
              <TextField
                type="password"
                id="password"
                label="Password"
                onChange={(event) => setPassword(event.target.value)}
                value={password} // Defina o valor inicial com base no estado local
              />
            </div>
          </div>
          <div className="w-full flex justify-center items-center translate-y-[84px]">
            <button
              type="submit"
              onClick={() =>
                updateDataById({ name, username, email, password })
              }
              className="box-border  mt-4 w-[220px] font-bold font-montserrat text-[18px] text-white inline-flex h-[54px] items-center justify-center rounded-[4px] bg-[#373668] px-[15px] leading-none"
            >
              Save
            </button>
          </div>
        </form>
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
