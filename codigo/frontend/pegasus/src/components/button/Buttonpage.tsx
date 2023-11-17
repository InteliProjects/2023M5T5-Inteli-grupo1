import { Link } from "react-router-dom";

export default function Buttonpage() {
  return (
    <>
      <Link to="/projects">
        <button className="box-border mt-12 w-[250px] font-bold font-montserrat text-[20px] text-white inline-flex h-[60px] items-center justify-center rounded-[20px] bg-[#080654] px-[15px] leading-none">
          Start Now!!
        </button>
      </Link>
    </>
  );
}
