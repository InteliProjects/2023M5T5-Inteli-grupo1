package com.pegasus.pegasus.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pegasus.pegasus.DTOs.BidirectionalDTO;
// Importando bibliotecas necessárias
import com.pegasus.pegasus.DTOs.responsecreateEdge.EdgeQuery;
import com.pegasus.pegasus.DTOs.responsecreateEdge.ResultDTO;
import com.pegasus.pegasus.DTOs.responsecreateEdge.TubulacaoDTO;
import com.pegasus.pegasus.entities.EdgeEntity;
import com.pegasus.pegasus.entities.No;
import com.pegasus.pegasus.entities.Tubulacao;
import com.pegasus.pegasus.repositories.EdgeRepository;
import com.pegasus.pegasus.repositories.NoRepository;

@Service
public class EdgeService {

    // Injeção de dependência do repositório de arestas
    @Autowired
    private EdgeRepository edgeRepository;

    @Autowired
    private NoRepository noRepository;

    // Método para criar uma relação entre nós (aresta)
    public BidirectionalDTO createEdge(EdgeEntity edge) {

        List<EdgeEntity> tubulacaoDTOs = new ArrayList<>();
        
        EdgeEntity edgeEntity1 = new EdgeEntity();
        edgeEntity1.setIdReactFlow(edge.getIdReactFlow());
        edgeEntity1.setIdSourceReactFlow(edge.getIdSourceReactFlow());
        edgeEntity1.setIdTargetReactFlow(edge.getIdTargetReactFlow());
        edgeEntity1.setBidirectional(edge.isBidirectional());
        edgeEntity1.setIdProject(edge.getIdProject());
        edgeEntity1.setSource(edge.getSource());
        edgeEntity1.setTarget(edge.getTarget());

        tubulacaoDTOs.add(edgeEntity1);
    
        edgeRepository.createEdge(edge.getIdSourceReactFlow(), edge.getIdTargetReactFlow(), edge.getIdReactFlow(), edge.getId(), edge.isBidirectional());
        
        // Verifica se a aresta é bidirecional
        if (edge.isBidirectional()) {
            EdgeEntity edgeEntity2 = new EdgeEntity();
            edgeEntity2.setIdReactFlow(edge.getIdReactFlow());
            edgeEntity2.setIdSourceReactFlow(edge.getIdTargetReactFlow());
            edgeEntity2.setIdTargetReactFlow(edge.getIdSourceReactFlow());
            edgeEntity2.setBidirectional(edge.isBidirectional());
            edgeEntity2.setIdProject(edge.getIdProject());
            edgeEntity2.setSource(edge.getTarget());
            edgeEntity2.setTarget(edge.getSource());

            edgeRepository.createEdge(edge.getIdTargetReactFlow(), edge.getIdSourceReactFlow(), edge.getIdReactFlow(), edge.getId(), edge.isBidirectional());
        
            tubulacaoDTOs.add(edgeEntity2);
        }

    return new BidirectionalDTO(tubulacaoDTOs);
}

    // Método para criar arestas em massa
    public List<BidirectionalDTO> createEdges(List<EdgeEntity> edgeList) {
        List<BidirectionalDTO> responseList = new ArrayList<>();
    
        for (EdgeEntity edge : edgeList) {
            BidirectionalDTO bidirectionalDTO = createEdge(edge);
            responseList.add(bidirectionalDTO);
        }
    
        return responseList;
    }


    // Método para obter uma relação entre nós (aresta)
    public ResultDTO getEdge(EdgeQuery edge) {
        try {
            // Chama o método do repositório para obter uma aresta com base nas informações
            // fornecidas
            ResultDTO response = edgeRepository.getEdge(edge.getSource(), edge.getTarget());
            return response;
        } catch (Exception e) {
            // Em caso de erro, lança uma exceção personalizada
            throw new RuntimeException("Não foi possível obter a aresta, erro: " + e.getMessage() + "");
        }
    }

    // Método para excluir uma relação entre nós (aresta)
    public ResultDTO deleteEdge(EdgeQuery edge) {
        try {
            // Chama o método do repositório para excluir uma aresta com base nas
            // informações fornecidas
            ResultDTO response = edgeRepository.deleteEdge(edge.getSource(), edge.getTarget());
            return response;
        } catch (Exception e) {
            // Em caso de erro, lança uma exceção personalizada
            throw new RuntimeException("Não foi possível excluir a aresta, erro: " + e.getMessage() + "");
        }
    }
}
