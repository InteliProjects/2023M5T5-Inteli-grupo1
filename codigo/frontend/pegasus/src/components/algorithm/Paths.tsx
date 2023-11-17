import React, { useState } from 'react';
import api from '../../api/api';
import Button from '@mui/material/Button';
import { PlayIcon, DownloadIcon } from '@radix-ui/react-icons';
import Box from '@mui/material/Box';
import LinearProgress from '@mui/material/LinearProgress';

import { PathsResults } from './PathsResults';

interface PathsProps {
    selectedResult: any;
    selectedResult2: any;
    onPathsChange: (paths: any[]) => void; // Função para passar os dados
}

export const Paths: React.FC<PathsProps> = ({ selectedResult, selectedResult2, onPathsChange }) => {
    const [paths, setPaths] = useState<any[]>([]);
    const [loading, setLoading] = useState<boolean>(false);

    async function downloadPath(path: any, baseFilename: string) {
        setLoading(true);
        try {
            const response = await api.post("/graph/export", {
                allPaths: path,
                baseFilename: "oioi"
            }, {
                responseType: 'blob', // Indicamos que a resposta é um blob
            });

            const blob = new Blob([response.data], { type: 'application/octet-stream' });
            const url = window.URL.createObjectURL(blob);
            const a = document.createElement("a");
            a.style.display = "none";
            a.href = url;
            a.download = baseFilename; // Nome do arquivo
            document.body.appendChild(a);
            a.click();
            window.URL.revokeObjectURL(url);
        } catch (error) {
            console.error("Erro na requisição:", error);
        } finally {
            setLoading(false);
        }
    }

    async function getPaths() {
        try {
            if (!selectedResult || !selectedResult2 || !selectedResult.id || !selectedResult2.id) {
                console.error('selectedResult or selectedResult2 is null or does not have an id');
                return;
            }
            const requestBody = {
                originId: selectedResult.id,
                destinyId: selectedResult2.id,
            };

            setLoading(true); // Inicie o estado de loading antes da requisição

            const response = await api.post(`/graph/node/algorithm`, requestBody);

            console.log(response);
            console.log(requestBody);
            console.log(selectedResult.id);
            console.log(selectedResult2.id);
            setPaths(response.data);
            // onPathsChange(response.data);


        } catch (error) {
            console.log(error);
        } finally {
            setLoading(false); // Desative o estado de loading quando a requisição terminar
        }
    }

    async function playPath(index: number) {
        // Verifique se o índice está dentro dos limites do array paths
        if (index >= 0 && index < paths.length) {
            const selectedPath = paths[index];
            onPathsChange(selectedPath);

            alert(selectedPath);
        }
    }

    return (
        <div>
            <Button className="mx-auto flex flex-col items-center w-[200px] pt-0.5" variant="contained" onClick={getPaths}>
                Calculate paths
            </Button>

            {loading ? (
                <div className='pt-2.5'>
                    <Box sx={{ width: '100%' }}>
                        <LinearProgress />
                    </Box>
                </div>
            ) : (
                <div className='pt-2.5'>
                    {paths.map((result, index) => (
                        <div key={index} className="mb-2 flex items-center">
                            <div className="mr-2">{`Caminho ${index + 1}`}</div>
                            <Button
                                variant="contained"
                                color="primary"
                                startIcon={<PlayIcon />}
                                className="mr-2"
                                onClick={() => {
                                    playPath(index); // Chame a função playPath com o índice do caminho selecionado
                                }}
                            >
                                Play
                            </Button>
                            <Button
                                variant="contained"
                                color="secondary"
                                startIcon={<DownloadIcon />}
                                onClick={() => {
                                    // Lógica para iniciar o download do caminho
                                    downloadPath(result, `caminho_${index + 1}.xlsx`);
                                }}
                            >
                                Download
                            </Button>
                        </div>
                    ))}
                </div>
            )}

        </div>
    );
};
