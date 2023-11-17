import React, { useState } from 'react'
import { SearchBar } from './SearchBar'
import { SearchResultsList } from './SearchResultsList';
import { Paths } from './Paths';

// COMPONENTE DA BARRA DE PESQUISA PARA CALCULAR OS CAMINHOS DOS NÓS

const SearchApp: React.FC<{ onPathsChange: (newPaths: any[]) => void }> = ({ onPathsChange }) => {
    const [results, setResults] = useState<any[]>([]);
    const [selectedResult, setSelectedResult] = useState<any | null>(null);

    const [results2, setResults2] = useState<any[]>([]);
    const [selectedResult2, setSelectedResult2] = useState<any | null>(null);

    const [isSearchBarFocused, setIsSearchBarFocused] = useState(false); // Novo estado para controlar o foco da SearchBar
    const [isSearchBarFocused2, setIsSearchBarFocused2] = useState(false); // Novo estado para controlar o foco da SearchBar

    const [paths, setPaths] = useState<any[]>([]);


    const handleResultClick = (result: any) => {
        setSelectedResult(result);
    };
    const handleResultClick2 = (result: any) => {
        setSelectedResult2(result);
    };

    const handleSearchBarFocus = () => {
        // Quando o input recebe foco, definimos isSearchBarFocused como true
        setIsSearchBarFocused(true);
        console.log("SearchBar on focus");
    }

    const handleSearchBarBlurred = () => {
        setTimeout(() => {
            setIsSearchBarFocused(false);
        }, 150);
        console.log("SearchBar blurred");
    }

    const handleSearchBarFocus2 = () => {
        // Quando o input recebe foco, definimos isSearchBarFocused como true
        setIsSearchBarFocused2(true);
        console.log("SearchBar on focus");
    }

    const handleSearchBarBlurred2 = () => {
        setTimeout(() => {
            setIsSearchBarFocused2(false);
        }, 150);
        console.log("SearchBar blurred");
    }

    // Função para receber os dados de paths do componente Paths
    async function handlePathsChange(newPaths: any[]) {
        setPaths(newPaths);
        onPathsChange(newPaths);
    };

    return (
        // classe do app
        <div className=''>
            {/* search-bar container  */}
            <div className='mx-auto flex flex-col min-w-[250px]'>
                <h1 className='text-base'>Choose an origin: </h1>
                <SearchBar setResults={setResults} onFocus={handleSearchBarFocus} onBlur={handleSearchBarBlurred} />
                {isSearchBarFocused && results.length > 0 && (
                    // Renderize a SearchResultsList aqui com base em isSearchBarFocused
                    <SearchResultsList results={results} onResultClick={handleResultClick} />
                )}
            </div>

            {selectedResult && (
                <div className='mt-4'>
                    <h2 className='text-sm'>Origin node: {selectedResult.nome}</h2>
                    {/* Renderize outras informações do resultado, se necessário */}
                </div>
            )}

            <div className='mx-auto flex flex-col min-w-[250px] pt-2.5'>
                <h1 className='text-base'>Choose a destiny: </h1>
                <SearchBar setResults={setResults2} onFocus={handleSearchBarFocus2} onBlur={handleSearchBarBlurred2} />
                {isSearchBarFocused2 && results2.length > 0 &&
                    (<SearchResultsList results={results2} onResultClick={handleResultClick2} />
                    )}
            </div>

            {selectedResult2 && (
                <div className='mt-4'>
                    <h2 className='text-sm'>Destiny node: {selectedResult2.nome}</h2>
                </div>
            )}

            <div className='pt-5'>
                <Paths selectedResult={selectedResult} selectedResult2={selectedResult2} onPathsChange={handlePathsChange}></Paths>
            </div>



        </div>
    )
}

export default SearchApp