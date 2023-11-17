import React from 'react'
import { SearchResult } from './SearchResult';

// COMPONENTE QUE RETORNA A LISTA DE RESULTADOS DOS NÓS PESQUISADOS

interface SearchResultsListProps {
    results: React.Dispatch<React.SetStateAction<any[]>>; // Tipo de setResults
    onResultClick: (result: any) => void;
}

export const SearchResultsList = ({ results, onResultClick }) => {
    return (
        <div className='w-full bg-white flex flex-col shadow-md rounded-lg mt-4 max-h-300px overflow-y-scroll h-32'>
            {
                results.map((result, id) => {
                    return <SearchResult result={result.nome} key={id} onClick={() => onResultClick(result)} />
                })
            }
        </div>
    )
}
