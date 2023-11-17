// Importações de bibliotecas necessárias
package com.pegasus.pegasus.entities;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Declaração da classe EdgeEntity como uma entidade de relacionamento no Neo4j
@RelationshipProperties
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EdgeEntity {

    // Declaração de variáveis ​​de instância e seus tipos
    
    // Anotação @Id indica que o campo 'id' é a chave primária da entidade
    @Id
    @GeneratedValue
    private Long id;

    private String idSourceReactFlow; // Representa o nó de origem da aresta

    private String idTargetReactFlow; // Representa o nó de destino da aresta

    private boolean bidirectional; // Indica se a aresta é bidirecional ou não

    private String idReactFlow; // Representa o id da aresta no ReactFlow

    private Long idProject; // Representa o id do projeto ao qual a aresta pertence

    private String source;

    private String target;

    // Método toString para representar a entidade como uma String

    public String toString() {
        return "EdgeEntity [source=" + idSourceReactFlow + ", target=" + idTargetReactFlow + ", bidirectional=" + bidirectional + "]";
    }
}
