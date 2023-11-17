import SideBar from './sidebar'
import img from "../../assets/img/grafo.png"
import { useState } from 'react'
import { ChevronLast, ChevronFirst } from 'lucide-react';

{/* Componente que renderiza a página de algoritmo */ }

interface AlgorithmProps {
    onPathsChange: (newPaths: any[]) => void;
}

export default function Algorithm({ onPathsChange }: AlgorithmProps) {
    const [sidebarVisible, setSidebarVisible] = useState(false);

    const [paths, setPaths] = useState<any[]>([]);

    async function handlePathsChange(newPaths: any[]) {
        setPaths(newPaths);
        onPathsChange(newPaths);
    };



    const toggleSidebar = () => {
        setSidebarVisible(!sidebarVisible);
    };

    return (
        <div className="flex h-[79vh]">

            <button onClick={toggleSidebar} className="p-1.5 rounded-lg bg-gray-50 hover:bg-gray-100"
            >
                {sidebarVisible ? <ChevronLast /> : <ChevronFirst />}
            </button>

            {sidebarVisible && (
                <div className="">
                    <SideBar onPathsChange={handlePathsChange} />
                </div>
            )}
        </div>
    )

}