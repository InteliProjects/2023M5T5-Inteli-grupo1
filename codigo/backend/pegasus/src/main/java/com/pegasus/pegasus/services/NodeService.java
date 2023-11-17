package com.pegasus.pegasus.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.Record;
import org.neo4j.driver.Value;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.Values;
import org.neo4j.driver.internal.value.NodeValue;
import org.neo4j.driver.Value;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import com.pegasus.pegasus.DTOs.AlgorithmResponseDTO;
import com.pegasus.pegasus.DTOs.GraphAlgorithmDTO;
import com.pegasus.pegasus.DTOs.NodeDTO;
import com.pegasus.pegasus.entities.No;
import com.pegasus.pegasus.entities.NodeEntity;
import com.pegasus.pegasus.repositories.NoRepository;
import com.pegasus.pegasus.repositories.NodeRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class NodeService {

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private NoRepository noRepository;

    @Autowired
    private Driver neo4jDriver; // Injeta o driver do Neo4j
    // Criar nós

    public List<No> createNodes(List<No> nodes) {
        try {
            deleteByIdProject(nodes.get(0).getIdProject());
            List<No> createdNodes = noRepository.saveAll(nodes);
            return createdNodes;
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível criar os nós, erro: " + e.getMessage());
        }
    }

    public No getNoById(Long id) {
        try {
            return noRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Node not found with id : " + id));
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível criar o nó, erro: " + e.getMessage() + "");
        }
    }

    public List<No> getNoByIdProject(Long idProject) {
        try {
            return noRepository.findByIdProject(idProject);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível criar o nó, erro: " + e.getMessage() + "");
        }
    }

    public void deleteByIdProject(Long idProject) {
        try {
            noRepository.deleteByIdProject(idProject);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível criar o nó, erro: " + e.getMessage() + "");
        }
    }

    // Retornar nó pelo id
    public NodeEntity getNodeById(Long id) {
        try {
            return nodeRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Node not found with id : " + id));
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível criar o nó, erro: " + e.getMessage() + "");
        }
    }

    // Retornar todos os nós
    public List<No> getAllNodes() {
        try {
            return noRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível criar o nó, erro: " + e.getMessage() + "");
        }
    }

    public List<No> getNeighbor(String nodeName) {
        try {
            List<No> response = noRepository.findNeighborsByName(nodeName);
            // System.out.println("Nodeservice: " + response);
            return response;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter os nós vizinhos: " + e.getMessage());
        }
    }

    // public List<NodeDTO> getNode() {
    // // Converter de NodeDTO para NodeEntity
    // List<NodeEntity> nodes = nodeRepository.findAllNodes();
    // return nodes.stream().map(NodeDTO::new).collect(Collectors.toList());
    // }

    // Atualizar nó
    public NodeEntity updateNode(Long id, NodeEntity nodeDTO) {
        try {
            NodeEntity node = nodeRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Node not found with id : " + id));

            node.setName(nodeDTO.getName());

            return nodeRepository.save(node);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível criar o nó, erro: " + e.getMessage() + "");
        }
    }

    // Deletar nó
    public void deleteNode(Long id) {
        try {
            nodeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Node not found with id : " + id);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível criar o nó, erro: " + e.getMessage() + "");
        }
    }
}
