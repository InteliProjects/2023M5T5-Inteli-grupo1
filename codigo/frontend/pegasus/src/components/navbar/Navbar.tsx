import { Link } from "react-router-dom";

import Logo from "../../assets/img/Logo.svg";
import UserMenu from "./UserMenu";

// Componente que renderiza a navbar
export default function Navbar() {
  return (
    <nav className="w-full h-20 bg-navbar flex items-center justify-center">
      <div className="w-4/5 flex items-center justify-between">
        <div className="flex items-center">
          <Link to="/" className="flex items-center gap-4">
            <img src={Logo} alt="Logo pegasus" className="w-12 h-12"/>
            <h1 className="font-bold font-space text-white text-4xl">pegasus</h1>
          </Link>
        </div>
        <div className="flex justify-center items-center gap-8">
          <ul className="flex font-normal font-inter text-base text-white gap-8">
            <li>
              <Link to="/">Home</Link>
            </li>
            <li>
              <Link to="/projects">Projects</Link>
            </li>
          </ul>
          <div>
            <UserMenu />
          </div>
        </div>
      </div>
    </nav>
  );
}
