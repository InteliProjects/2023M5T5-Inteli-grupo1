package com.pegasus.pegasus.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pegasus.pegasus.DTOs.BidirectionalDTO;
import com.pegasus.pegasus.DTOs.responsecreateEdge.ResultDTO;
import com.pegasus.pegasus.entities.EdgeEntity;

@Repository("edgeRepository")
public interface EdgeRepository extends Neo4jRepository<EdgeEntity, Long> {

    // Método para buscar uma aresta entre dois nós com base em seus nomes
    @Query("MATCH (source:NodeEntity {name: $source})-[r]-(target:NodeEntity {name: $target}) RETURN {" +
                     "  records: collect({keys: [r], _fields: [r]})" +
                     "} AS result")
    ResultDTO getEdge(@Param("source") String source, @Param("target") String target);

    // Método para criar uma aresta entre dois nós com base em seus ids do reactFlow
    @Query("MATCH (source:No {idReactFlow: $idSourceReactFlow}) " +
                     "MATCH (target:No {idReactFlow: $idTargetReactFlow}) " +
                     "CREATE (source)-[r:Tubulacao {idReactFlow: $idReactFlow, idProject: $idProject, bidirectional: $bidirectional}]->(target)")
    EdgeEntity createEdge(@Param("idSourceReactFlow") String idSourceReactFlow, @Param("idTargetReactFlow") String idTargetReactFlow,
                         @Param("idReactFlow") String idReactFlow, @Param("idProject") Long idProject,
                         @Param("bidirectional") boolean bidirectional);

    // Método para excluir uma aresta entre dois nós com base em seus nomes
    @Query("MATCH (source:No {name: $idSourceReactFlow})-[r]-(target:No {name: $idTargetReactFlow}) DETACH DELETE r RETURN {} AS result")
    ResultDTO deleteEdge(@Param("source") String source, @Param("target") String target);

    // Não há método para atualizar a aresta, pois não estamos realizando atualizações na aresta.
}
