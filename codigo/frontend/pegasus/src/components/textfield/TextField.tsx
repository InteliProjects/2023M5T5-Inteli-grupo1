import * as Form from "@radix-ui/react-form";
import { useForm } from "react-hook-form";
import { useEffect, useState } from "react";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { Link } from "react-router-dom";
import api from "../../api/api";
import { toast } from "react-toastify";

interface FormCreateAccountProps {
  username: string;
  password: string;
}

interface loginFormProps {
  loginUserToast: () => void;
  errorLoginUserToast: () => void;
}
// definindo o schema de validação do formulário
const loginUserFormSchema = z.object({
  username: z.string()
    .min(1, 'O username deve ter no mínimo 4 caracteres.')
    .nonempty('O username é obrigatório.'),
  password: z.string()
  .min(8, 'A senha deve ter no mínimo 8 caracteres.')
  .nonempty('A senha é obrigatória.'),
})

// definindo o tipo do formulário
type LoginUserForm = z.infer<typeof loginUserFormSchema>;

// componente que renderiza o formulário de cadastro


// componente que renderiza o formulário de cadastro
export default function TextField({loginUserToast, errorLoginUserToast}: loginFormProps) {

  const [output, setOutput] = useState('');
  const [userName, setUserName] = useState('');
  const [password, setPassword] = useState('');
  const { 
    register,
    handleSubmit,
    formState: { errors } 
  } = useForm<LoginUserForm>({
    resolver: zodResolver(loginUserFormSchema)
  });

// função que cria um usuário
  async function loginUser() {
    const data = {
      username: userName,
      password: password
    }
    setOutput(JSON.stringify(data, null, 2));
    await api.post("/authenticate", data, {headers: {"Content-Type": "application/json"}})
    .then(response => {
      loginUserToast();
      console.log(response.data);
      localStorage.setItem("userId", String(response.data.userId));
      localStorage.setItem("JWT", String(response.data.token));
      setTimeout(() => {
        window.location.href = "/";
      }, 2000);
    })
    .catch(error => {
      errorLoginUserToast();
      console.log(error);
    })
  }

  return (
    <div>
      <Form.Root
        className="w-[260px] h-full"
        onSubmit={handleSubmit(loginUser)}
      >
        <Form.Field className="grid mb-[10px] justify-center items-center" name="email">
          <div className="flex items-baseline justify-between">
            <Form.Label className="text-[18px] font-medium leading-[35px] text-black font-montserrat">
              Username *
            </Form.Label>
          </div>
          <Form.Control asChild>
            <input
              className="border-[1px] border-gray-600 p-4 w-[450px] bg-[#f1f1f1] text-[15px] inline-flex h-[50px] appearance-none items-center justify-center rounded-[4px] px-[10px] "
              type="email"
              placeholder="Ex: yagophellipe"
              value={userName}
              onChange={(event) => setUserName(event.target.value)}
              required
            />
          </Form.Control>
        </Form.Field>

        <Form.Field className="grid mb-[10px] justify-center items-center" name="password" >
          <div className="flex items-baseline justify-between">
            <Form.Label className="text-[18px] font-medium leading-[35px] text-black font-montserrat">
              Password *
            </Form.Label>
          </div>
          <Form.Control asChild>
            <input
              className="border-[1px] border-gray-600 w-[450px] bg-[#f1f1f1] text-[15px]  inline-flex h-[50px] appearance-none items-center justify-center rounded-[4px] px-[10px]"
              type="password"
              placeholder="************"
              value={password}
              onChange={(event) => setPassword(event.target.value)}
              required
            />
          </Form.Control>
        </Form.Field>
        <div>    
          <Form.Submit asChild>
              <button 
                onClick={() => loginUser()}
                type="button" className="box-border mt-4 w-[120px] font-bold font-montserrat text-[16px] text-white inline-flex h-[44px] items-center justify-center rounded-[4px] bg-[#373668] px-[15px] leading-none">
                Enter
              </button>
          </Form.Submit>
        </div>
      </Form.Root>
    </div>
  );
}
