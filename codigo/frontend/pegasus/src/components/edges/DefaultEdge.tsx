import { useLocation } from "react-router-dom";
import { EdgeProps, getSmoothStepPath } from "reactflow";

// Componente que renderiza a aresta default
export default function DefaultEdge({
  id,
  sourceX,
  sourceY,
  targetX,
  targetY,
  sourcePosition,
  targetPosition,
  style = {},
  data = {},
}: EdgeProps) {
  // pegando o id do projeto através da url para adicionar como atributo da aresta
  const idUserProject = useLocation().pathname.split("/")[2];

  data.idProject = idUserProject;
  const [edgePath] = getSmoothStepPath({
    sourceX,
    sourceY,
    sourcePosition,
    targetX,
    targetY,
    targetPosition,
  });

  return (
    <>
      <svg style={{ overflow: "visible", position: "absolute", cursor: "pointer", zIndex: 1 }}>
        <defs>
          <marker
            id={`arrow-${id}`}
            markerWidth="10"
            markerHeight="10"
            refX="9"
            refY="3"
            orient="auto"
            markerUnits="strokeWidth"
          >
            <path d="M0,0 L0,6 L9,3 z" fill="#999" />
          </marker>
        </defs>
      </svg>
      <path
        id={id}
        style={style}
        className="react-flow__edge-path stroke-[3px] stroke-zinc-300"
        d={edgePath}
        markerEnd={`url(#arrow-${id})`}
      />
    </>
  );
}
