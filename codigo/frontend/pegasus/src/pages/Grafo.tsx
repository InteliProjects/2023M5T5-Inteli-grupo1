// Importando componentes e libs necessárias
import ReactFlow, {
  Background,
  Connection,
  ConnectionMode,
  Controls,
  MarkerType,
  addEdge,
  useEdgesState,
  useNodesState,
} from "reactflow";
import "reactflow/dist/style.css";
import { zinc } from "tailwindcss/colors";
import { useCallback, useEffect, useState } from "react";
import DefaultEdge from "../components/edges/DefaultEdge";
import ToolbarComponent from "../components/toolbar/ToolbarComponent";
import Worth from "../components/nodes/Worth";
import GreenBeer from "../components/nodes/GreenBeer";
import Filtration from "../components/nodes/Filtration";
import Mixproof from "../components/nodes/Mixproof";
import Solenoide from "../components/nodes/Solenoide";
import Tank from "../components/nodes/Tank";
import api from "../api/api";
import NavToolbarComponent from "../components/toolbar/NavToolbarComponent";
import { useLocation } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import Algorithm from "../components/algorithm/Algorithm";


interface NodeData {
  label: string;
  type: string;
}

// Definindo os tipos de nós e arestas
const NODE_TYPES = {
  Worth: Worth,
  GreenBeer: GreenBeer,
  Filtration: Filtration,
  Mixproof: Mixproof,
  Solenoide: Solenoide,
  Tank: Tank,
};

// Definindo os tipos de arestas
const EDGE_TYPES = {
  default: DefaultEdge,
  highligthed: DefaultEdge,
};

// Componente que renderiza a página de grafo
function Grafo() {
  const idProject = useLocation().pathname.split("/")[2]; // id do projeto através da url
  const [name, setName] = useState(""); // nome do projeto
  const [description, setDescription] = useState(""); // descrição do projeto
  const [nodes, setNodes, onNodesChange] = useNodesState([]); // nós
  const [edges, setEdges, onEdgesChange] = useEdgesState([]); // arestas
  const [paths, setPaths] = useState<any[]>([]);

  async function handlePathsChange(newPaths: any[]) {
    setPaths(newPaths);
    // alert("Esses são os caminhos: " + paths)
    console.log(paths);

     // Substitua pelos IDs dos nós desejados
    updateEdgeTypes(paths, edges);
  }

  function updateEdgeTypes(nodeIdsToUpdate: string[], edges: any): void {
    // Copie as arestas existentes e atualize o tipo das arestas para os nós desejados
    const updatedEdges = edges.map((edge: any) => {
      if (
        nodeIdsToUpdate.includes(edge.source) &&
        nodeIdsToUpdate.includes(edge.target)
      ) {
        return {
          ...edge,
          animated: true, // Marque a aresta como animada, se necessário
          style: { stroke: "red" }, // Defina o estilo da aresta animada
        };
      }
      return edge;
    });

    // Atualize o estado das arestas com as novas arestas
    setEdges(updatedEdges);
  }


  // Função que adiciona uma aresta
  const onConnect = useCallback((connection: Connection) => {
    return setEdges((edges) =>
      addEdge(
        {
          ...connection,
          idProject: idProject,
          type: "default",
          bidirectional: false,
          // markerEnd: {
          //   type: MarkerType.ArrowClosed
          // }
        },
        edges
      )
    );
  }, []);

  // Funções que adicionam nós de cada tipo
  function addNode(data: NodeData) {
    setNodes((nodes) => [
      ...nodes,
      {
        id: crypto.randomUUID(),
        type: data.type,
        position: { x: 750, y: 350 },
        idProject: idProject,
        data: { label: data.label },
      },
    ]);
  }

  // Função que atualiza o nome do projeto
  function handleNameChange(newName: string) {
    setName(newName);
  }

  // Função que atualiza o projeto
  async function updateProject() {
    await api.patch(`/project/${idProject}`, {
      name: name,
      description: description,
      endDate: new Date(),
      nodes: nodes,
      edges: edges,
    })
      .then((response) => {
        console.log(response.data);
        toast.success("Projeto atualizado com sucesso!");
      })
      .catch((error) => console.log(error))
      .finally(() => {
        console.log("Project updated");
      });
  }

  // Função que carrega o projeto
  async function loadProjects() {
    await api.get(`/project/${idProject}`)
      .then((response) => {
        console.log(response.data);
        setName(response.data.name);
        setDescription(response.data.description);
        if (response.data.nodes != null) {
          setNodes(response.data.nodes);
        }
        if (response.data.edges != null) {
          setEdges(response.data.edges);
        }
      })
      .catch((error) => {
        console.log(error);
      });
  }

  useEffect(() => {
    loadProjects();
  }, []);

  useEffect(() => {
    console.log(nodes);
    console.log(edges);
  }, [nodes, edges]);

  return (

    <div style={{ display: "flex" }}>
      <div className="w-full h-[89vh]" style={{ width: "98vw", height: "98vh" }}>
        <ReactFlow
          nodeTypes={NODE_TYPES}
          edgeTypes={EDGE_TYPES}
          nodes={nodes}
          edges={edges}
          onConnect={onConnect}
          onEdgesChange={onEdgesChange}
          onNodesChange={onNodesChange}
          connectionMode={ConnectionMode.Loose}
          defaultViewport={{ x: 50, y: 50, zoom: 0.8 }}
          minZoom={0.2}
        >

          <Background color={zinc[200]} gap={12} size={2} />

          <Controls />
        </ReactFlow >
        <NavToolbarComponent name={name} onNameChange={handleNameChange} />

        <ToolbarComponent
          onNodeDataChange={addNode}
          updateProject={updateProject}
        />
        <ToastContainer
          position="top-right"
          autoClose={2000}
          hideProgressBar={false}
          newestOnTop={false}
          closeOnClick={true}
          rtl={false}
          pauseOnFocusLoss={false}
          draggable={true}
          pauseOnHover={false}
          theme="light"
        />
      </div>
      <div style={{ flex: 1 }}>
        <div style={{ display: "flex", justifyContent: "flex-end", alignItems: "center" }}>
          <Algorithm onPathsChange={handlePathsChange} />
        </div>
      </div>
    </div >
  );
}

export default Grafo;
