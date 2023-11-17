package com.pegasus.pegasus.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.pegasus.pegasus.entities.No;
import com.pegasus.pegasus.entities.NodeEntity;

// A anotação @Repository indica que esta interface é um repositório gerenciado pelo Spring.
@Repository
public interface NoRepository extends Neo4jRepository<No, Long> {

    // Método para encontrar um nó por nome
    No findByNome(String nome);

    @Query("MATCH (n:No) RETURN n")
    List<No> findAll();

    @Query("MATCH (No) WHERE ID(No) = $id RETURN No;")
    Optional<No> findById(Long id);

    List<No> findByIdProject(Long idProject);

    @Query("MATCH (n:No {idReactFlow: $idReactFlow}) RETURN n")
    No findByIdReactFlow(String idReactFlow);

    @Query("MATCH (n:No) WHERE n.idProject = $idProject DETACH DELETE n")
    void deleteByIdProject(Long idProject);

    @Query("MATCH (n:No {nome: $nodeName})-[Tubulação]->(vizinhos:No) RETURN vizinhos")
    List<No> findNeighborsByName(String nodeName);
}
