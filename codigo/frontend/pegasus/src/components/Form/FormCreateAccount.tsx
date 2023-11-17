import * as Form from "@radix-ui/react-form";
import { useForm } from "react-hook-form";
import { useState } from "react";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import api from "../../api/api";
import { toast, ToastContainer } from "react-toastify";


interface FormProps {
  createAccount: () => void;
  errorCreateAccount: () => void;
}
// definindo as propriedades do componente
interface FormCreateAccountProps {
  name: string;
  username: string;
  email: string;
  password: string;
}

interface LoginUserProps {
  username: string;
  password: string;
}

// definindo o schema de validação do formulário
const createUserFormSchema = z.object({
  name: z.string()
    .min(1, 'O nome deve ter no mínimo 4 caracteres.')
    .nonempty('O nome é obrigatório.')
    .transform(name => {
      return name.trim().split(' ').map(word => {
        return word[0].toLocaleUpperCase().concat(word.substring(1))
      }).join(' ')
  }),
  username: z.string()
    .min(1, 'O username deve ter no mínimo 4 caracteres.')
    .nonempty('O username é obrigatório.'),
  email: z.string()
    .nonempty('O email é obrigatório.')
    .email("Formato de email invalido.")
    .toLowerCase()
    .refine(email => {
      return email.endsWith("@sou.inteli.edu.br")
    }, 'O email deve ser institucional.'),
    
  password: z.string()
  .min(8, 'A senha deve ter no mínimo 8 caracteres.')
  .nonempty('A senha é obrigatória.'),
})

// definindo o tipo do formulário
type CreateUserForm = z.infer<typeof createUserFormSchema>

// componente que renderiza o formulário de cadastro
export default function FormCreateAccount({createAccount, errorCreateAccount}: FormProps) {
  const { handleSubmit, register, formState: { errors } } = useForm<CreateUserForm>({
    mode: "all",
    reValidateMode: "onChange",
    resolver: zodResolver(createUserFormSchema)
  });

  // variável que define o output do formulário
  const [output, setOutput] = useState('');
  
  async function loginUser(loginData: LoginUserProps) {
    await api.post("/authenticate", loginData, {headers: {"Content-Type": "application/json"}})
    .then(response => {
      localStorage.setItem("userId", String(response.data.userId));
      localStorage.setItem("JWT", String(response.data.token));
      window.location.href = "/";
    })
    .catch(error => {
      alert("Usuario não cadastrado ou senha incorreta!");
      console.log(error);
    })
  }

  // função que cria um usuário
  async function createUser(data: FormCreateAccountProps) {
    setOutput(JSON.stringify(data, null, 2));
    await api.post("/register", data, {headers: {"Content-Type": "application/json"}})
    .then(response => {
      console.log(response.data);
      createAccount();
      setTimeout(() => {
        loginUser({username: data.username, password: data.password})
      }, 2000)
      console.log("Cadastro realizado com sucesso!");
    })
    .catch(error => {
      errorCreateAccount();
      console.log(error);
    })
  }

  return (
   <>
     <div className="translate-y-2">
      <Form.Root  
        onSubmit={handleSubmit(createUser)} 
        className="w-[450px] h-full"
        >
        <div className="flex justify-between ">
          <div className="-translate-y-[10px] w-[110px] justify-center items-center mr-8 ">
            <Form.Field name="name">
              <div className="flex  flex-col  ml-4 w-52 items-baseline justify-between">
                {errors.name && <p className="text-red-500 text-[12px]">{errors.name.message}</p> || <p className="text-transparent cursor-default select-none text-[12px]">|<br /> |</p>}
                <Form.Label className="text-[18px] font-medium leading-[35px] text-black font-montserrat">
                  Name *
                </Form.Label>
              </div>              
              <Form.Control asChild>
                <div className="flex flex-col">
                  <input
                    className="border-[1px] border-gray-600 p-4 w-52 bg-[#f1f1f1] text-[15px] inline-flex h-[50px] appearance-none items-center justify-center rounded-[4px] px-[10px] "
                    type="text"
                    placeholder="Ex: John Doe"
                    {...register("name")}
                    
                  />        
                </div>        
              </Form.Control>
            </Form.Field>
          </div>

          <div className="-translate-y-[10px] justify-center  items-center flex flex-col">
            <Form.Field name="email">
              <div className="flex flex-col w-52 items-baseline justify-between">
                {errors.username && <p className="text-red-500 text-[12px]">{errors.username.message}</p> || <p className="text-transparent cursor-default select-none text-[12px]">|<br /> |</p>}
                <Form.Label className="text-[18px] font-medium leading-[35px] text-black font-montserrat">
                  Username *
                </Form.Label>
              </div>
              <Form.Control asChild>
                <input
                  className="border-[1px] border-gray-600 p-4 w-52 bg-[#f1f1f1] text-[15px] inline-flex h-[50px] appearance-none items-center justify-center rounded-[4px] px-[10px] "
                  type="text"
                  placeholder="Ex: johndoe"
                  {...register("username")}
                  
                />
              </Form.Control>
            </Form.Field>
          </div>
        </div>

        <Form.Field
          className="grid mb-[10px] justify-center items-center"
          name="email"
        >
          <div className="flex flex-col w-56 items-baseline justify-between">
            {errors.email && <p className="text-red-500 text-[12px]">{errors.email.message}</p> || <p className="text-transparent cursor-default select-none text-[12px]">|</p>}
            <Form.Label className="text-[18px] font-medium leading-[35px] text-black font-montserrat">
              Email *
            </Form.Label>
          </div>
          <Form.Control asChild>
            <input
              className="border-[1px] border-gray-600 p-4 w-[450px] bg-[#f1f1f1] text-[15px] inline-flex h-[50px] appearance-none items-center justify-center rounded-[4px] px-[10px] "
              type="email"
              placeholder="Ex: john@example.com"
              {...register("email")}
              
            />
          </Form.Control>
        </Form.Field>

        <Form.Field
          className="grid mb-[10px] justify-center items-center"
          name="password"
        >
          <div className="flex flex-col w-56 items-baseline justify-between">
            {errors.password && <p className="text-red-500 text-[12px]">{errors.password.message}</p> || <p className="text-transparent cursor-default select-none text-[12px]">|</p>}
            <Form.Label className="text-[18px] font-medium leading-[35px] text-black font-montserrat">
              Password *
            </Form.Label>
          </div>
          <Form.Control asChild>
            <input
              className="border-[1px] border-gray-600 w-[450px] bg-[#f1f1f1] text-[15px]  inline-flex h-[50px] appearance-none items-center justify-center rounded-[4px] px-[10px]"
              type="password"
              placeholder="************"
              {...register("password")}
            />
          </Form.Control>
        </Form.Field>
        <div>
          <Form.Submit asChild>
            <button type="submit" 
              onSubmit={()=>{
                alert("Cadastro realizado com sucesso!")
                console.log(output)
              }} 
              className="box-border mt-4 w-[200px] font-bold font-montserrat text-[16px] text-white inline-flex h-[44px] items-center justify-center rounded-[4px] bg-[#373668] px-[15px] leading-none">
              Create Account
            </button>
          </Form.Submit>

        </div>
      </Form.Root>
    </div>
   </>
  );
}
