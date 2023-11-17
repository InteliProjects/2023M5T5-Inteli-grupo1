import SideBar from '../components/algorithm/sidebar'
import img from '../assets/img/grafo.png'
import { useState } from 'react'
import { ChevronLast, ChevronFirst } from 'lucide-react';

{/* Componente que renderiza a página de algoritmo */ }

export default function Algorithm() {
    const [sidebarVisible, setSidebarVisible] = useState(false);

    const toggleSidebar = () => {
        setSidebarVisible(!sidebarVisible);
    };

    return (
        <div className="flex h-[79vh]">
            <div className="">
                <img src={img} alt="" />
            </div>

            <button onClick={toggleSidebar} className="p-1.5 rounded-lg bg-gray-50 hover:bg-gray-100"
            >
                {sidebarVisible ? <ChevronLast /> : <ChevronFirst />}
            </button>

            {sidebarVisible && (
                <div className="">
                    <SideBar />
                    {/* <Sidebar>
                    <SidebarItem icon={<LayoutDashboard size={20} />} text="Dashboard" alert />
                </Sidebar> */}
                </div>
            )}
        </div>
    )

}