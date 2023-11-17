import * as Tabs from '@radix-ui/react-tabs';
import SearchApp from './SearchApp';
import { useState } from 'react';
import SelectBar from './selectBar';
import '@radix-ui/themes/styles.css';
import { TextField } from '@radix-ui/themes'
import { MagnifyingGlassIcon } from '@radix-ui/react-icons'

interface SideBarProps {
    onPathsChange: (newPaths: any[]) => void;
}

// Componente que renderiza a sidebar com as tabs
// Componente que renderiza a barra de seleção para calcular os caminhos e outras informações do diagrama - é uma collapsed side bar
export default function SideBar({ onPathsChange }: SideBarProps) {
    const [paths, setPaths] = useState<any[]>([]);

    async function handlePathsChange(newPaths: any[]) {
        setPaths(newPaths);
        onPathsChange(newPaths);
    };


    return (
        <div>
            <Tabs.Root
                className="flex flex-col h-screen w-[400px] shadow-[0_2px_10px] shadow-blackA4"
                defaultValue="tab1"
            >
                <Tabs.List className="shrink-0 flex border-b border-mauve6" aria-label="Manage your account">
                    <Tabs.Trigger
                        className="bg-white px-5 h-[45px] flex-1 flex items-center justify-center text-[15px] leading-none text-mauve11 select-none first:rounded-tl-md last:rounded-tr-md hover:text-blue-600 data-[state=active]:text-blue-600 data-[state=active]:shadow-[inset_0_-1px_0_0,0_1px_0_0] data-[state=active]:shadow-current data-[state=active]:focus:relative data-[state=active]:focus:shadow-[0_0_0_2px] data-[state=active]: outline-none cursor-default"
                        value="tab1"
                    >
                        Routes
                    </Tabs.Trigger>
                    <Tabs.Trigger
                        className="bg-white px-5 h-[45px] flex-1 flex items-center justify-center text-[15px] leading-none text-mauve11 select-none first:rounded-tl-md last:rounded-tr-md hover:text-blue-600 data-[state=active]:text-blue-600data-[state=active]:shadow-[inset_0_-1px_0_0,0_1px_0_0] data-[state=active]:shadow-current data-[state=active]:focus:relative data-[state=active]:focus:shadow-[0_0_0_2px] data-[state=active]: outline-none cursor-default"
                        value="tab2"
                    >
                        Export
                    </Tabs.Trigger>
                    <Tabs.Trigger
                        className="bg-white px-5 h-[45px] flex-1 flex items-center justify-center text-[15px] leading-none text-mauve11 select-none first:rounded-tl-md last:rounded-tr-md hover:text-blue-600 data-[state=active]:text-blue-600data-[state=active]:shadow-[inset_0_-1px_0_0,0_1px_0_0] data-[state=active]:shadow-current data-[state=active]:focus:relative data-[state=active]:focus:shadow-[0_0_0_2px] data-[state=active]: outline-none cursor-default"
                        value="tab3"
                    >
                        Info
                    </Tabs.Trigger>
                </Tabs.List>
                <Tabs.Content
                    className="grow p-5 bg-white rounded-b-md outline-none focus:shadow-[0_0_0_2px] focus:shadow-black"
                    value="tab1"
                >
                    <SearchApp onPathsChange={handlePathsChange}></SearchApp>

                </Tabs.Content>
                <Tabs.Content
                    className="grow p-5 bg-white rounded-b-md outline-none focus:shadow-[0_0_0_2px] focus:shadow-black"
                    value="tab2"
                >
                    <div className="flex flex-col items-center mt-5">
                        <button className="rounded px-[15px] text-[15px] leading-none font-medium h-[35px] bg-green4 text-green11 hover:bg-green5 focus:shadow-[0_0_0_2px] focus:shadow-green7 outline-none cursor-default mb-2">
                            PDF
                        </button>
                        <button className="rounded px-[15px] text-[15px] leading-none font-medium h-[35px] bg-green4 text-green11 hover:bg-green5 focus:shadow-[0_0_0_2px] focus:shadow-green7 outline-none cursor-default">
                            PNG
                        </button>
                    </div>
                </Tabs.Content>
                <Tabs.Content
                    className="grow p-5 bg-white rounded-b-md outline-none focus:shadow-[0_0_0_2px] focus:shadow-black"
                    value="tab3"
                >
                    <div className="flex flex-col items-center mt-5">
                        <p className="mb-5 text-mauve11 text-[15px] leading-normal">
                            Info related to the diagrams created.
                        </p>
                    </div>
                </Tabs.Content>
            </Tabs.Root>
        </div >
    );
}