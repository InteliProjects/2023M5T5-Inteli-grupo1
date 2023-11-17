import { Link } from "react-router-dom";
import Logo from "../assets/img/Logoblack.svg";
import FormCreateAccount from "../components/Form/FormCreateAccount";
import imagePegasus from "../assets/img/imagePegasus.jpg";
import { ToastContainer, toast } from "react-toastify";

function createAccount() {
  toast.success("Conta criada com sucesso!");
}

function errorCreateAccount() {
  toast.error("Erro ao criar conta!, tente mudar seu username ou email.");
}

// Página de registro
export default function RegisterPage() {
  return (
    <>
      <div className="h-screen flex">
        <div className="w-1/2  flex flex-col items-center">
          <div className=" w-full mt-3 ml-6">
            <Link to="/" className=" items-center gap-4">
              <img src={Logo} alt="Logo pegasus" className="h-10"/>
            </Link>
          </div> 
          <div className="flex flex-col h-[550px] translate-y-10 text-center justify-center">
            <div>
              <h1 className="font-wans text-[60px]">Sign Up</h1>
              <Link to="/login">
                <p className="font-montserrat text-[15px] mb-6">Already have an account? Sign in.</p>
              </Link>
            </div>
            <div>
              <FormCreateAccount errorCreateAccount={errorCreateAccount} createAccount={createAccount}/>
            </div>
          </div>
        </div>
        <div className="w-[1px] h-full bg-[#E6E6E6]"></div>
        <div className="w-1/2">
          <img src={imagePegasus} alt="Logo pegasus" className="h-full" />
        </div>
      </div>
      <ToastContainer
        position="top-center"
        autoClose={1250}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick={true}
        rtl={false}
        pauseOnFocusLoss={false}
        draggable={true}
        pauseOnHover={false}
        theme="light"
        className={"text-center"}
      />
    </>
  );
}
