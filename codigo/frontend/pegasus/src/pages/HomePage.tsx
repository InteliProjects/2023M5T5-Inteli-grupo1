import Buttonpage from "../components/button/Buttonpage";
import PegasusHome from "../assets/img/PegasusHome.svg";

// Componente que renderiza a página inicial
export default function HomePage(){    
    return(
        <>
            <div className="flex w-full h-[78vh] bg-white">
                <div className="w-1/2 h-full flex flex-col justify-center pl-10">
                    <div className="align-items">
                        <h1 className="text-[44px] font-bold font-montserrat">Expand your knowledge</h1>
                        <h1 className="text-[44px] font-bold font-montserrat">be a <b className="text-[#207DA4] font-bold font-montserrat">PEGASUS!!</b></h1>
                    </div>
                    
                    <p className="text-[#727070] text-[20px] font-bold font-montserrat">
                        Our design simplifies the creation of complex graphs
                        with properties, making the representation of relationships more accessible.
                        In addition, it provides the efficiency to
                        find the best path between two points within these
                        structure, optimizing navigation and decision-making.</p>
                    <Buttonpage></Buttonpage>
                </div>
                <div className="w-1/2 h-full">
                    <img src={PegasusHome} alt="Logo pegasus" className="pt-24 pl-40" />
                </div>
            </div>

        </>
    )
}