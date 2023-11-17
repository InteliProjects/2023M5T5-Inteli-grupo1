import { Link } from "react-router-dom";

import TextField from "../components/textfield/TextField";
import Logo from "../assets/img/Logoblack.svg";
import imagePegasus from "../assets/img/imagePegasus.jpg";
import { ToastContainer, toast } from "react-toastify";

function loginUserToast() {
  toast.success("Usuário Entrou com sucesso!");
}

function errorLoginUserToast() {
  toast.error("Usuário não cadastrado ou username/senha incorreta!");
}

// Componente que renderiza a página de login
export default function LoginPage() {
  return (
    <>
      <div className="h-screen flex">
        <div className="w-1/2  flex flex-col items-center">
          <div className=" w-full mt-3 ml-6">
            <Link to="/" className=" items-center gap-4">
              <img src={Logo} alt="Logo pegasus" className="h-10" />
            </Link>
          </div> 
          <div className="flex flex-col h-[650px] text-center justify-center">
            <div>
              <h1 className="font-wans text-[60px]">Login</h1>
              <Link to="/register">
                <p className="font-montserrat text-[15px] mb-6">Don't have an account? Create one.</p>
              </Link>
            </div>
            <div>
              <TextField loginUserToast={loginUserToast} errorLoginUserToast={errorLoginUserToast}/>
            </div>
          </div>
        </div>
        <div className="w-[1px] h-full bg-[#E6E6E6]"></div>
        <div className="w-1/2">
          <img src={imagePegasus} alt="Logo pegasus" className="h-full" />
        </div>
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
    </>
  );
}
