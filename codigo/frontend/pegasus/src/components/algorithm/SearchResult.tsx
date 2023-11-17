import React from 'react'

// COMPONENTE QUE RETORNA O RESULTADO PRA LISTA DE RESULTADOS

interface SearchResultsListProps {
    result: React.Dispatch<React.SetStateAction<any[]>>; // Tipo de setResults
    onClick: () => void;
}

export const SearchResult = ({ result, onClick }) => {
    return (
        <div className='p-2 px-4 hover:bg-gray-300'
            onClick={onClick}>
            {result}
        </div>
    )
}
