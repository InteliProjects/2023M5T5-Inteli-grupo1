import React, { useState } from 'react'
import { FaSearch } from "react-icons/fa";

// COMPONENTE QUE FAZ A REQUISIÇÃO DO BANCO DE DADOS PARA CALCULAR OS CAMINHOS


interface SearchBarProps {
    setResults: React.Dispatch<React.SetStateAction<any[]>>; // Tipo de setResults
    onFocus: () => void; // Adicionando a propriedade onFocus
    onBlur: () => void; // Adicionando a propriedade onFocus
}

export const SearchBar: React.FC<SearchBarProps> = ({ setResults, onFocus, onBlur }) => {
    const [input, setInput] = useState("")

    const fetchData = (value: any) => {
        fetch("http://localhost:8080/graph/node/all-nodes")
            .then((response) => response.json())
            .then(json => {
                const results = json.filter((user: any) => {
                    return user && user.nome && user.nome.toLowerCase().includes(value)
                })
                console.log(results);
                setResults(results);
            });
    }


    const handleChange = (value: any) => {
        setInput(value);
        fetchData(value);
    }

    return (
        // input-wrapper
        <div className='bg-white w-full rounded-lg h-[2.5rem] p-2.5 flex items-center shadow-md'>
            {/* search-icon */}
            <FaSearch className='fill-blue-900' />
            <input
                className="bg-transparent border-none h-full text-s p-2  flex-grow focus:outline-none"
                placeholder="Type to search..."
                value={input}
                onChange={(e) => handleChange(e.target.value)}
                onFocus={onFocus}
                onBlur={onBlur}
            />
        </div>
    )
}
