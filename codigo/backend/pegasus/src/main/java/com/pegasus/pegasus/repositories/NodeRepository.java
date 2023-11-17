package com.pegasus.pegasus.repositories;

// Importações necessárias
import java.util.List;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.pegasus.pegasus.entities.NodeEntity;

// A anotação @Repository indica que esta interface é um repositório gerenciado pelo Spring.
@Repository("nodeRepository")
public interface NodeRepository extends Neo4jRepository<NodeEntity, Long> {

    // Método para encontrar nós vizinhos com base no nó fornecido
    @Query("MATCH (n:NodeEntity {name: $nodeName})-[Tubulação]->(vizinhos:NodeEntity) RETURN vizinhos")
    List<NodeEntity> findNeighborsByName(String nodeName);
}
