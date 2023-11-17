package com.pegasus.pegasus.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Node
public class No {

    @Id // Marca o campo 'id' como a chave primária
    @GeneratedValue // Gera automaticamente valores para o campo 'id'
    private Long id; // O identificador único do nó

    private String nome; // O nome do nó

    private String idReactFlow; // O identificador único do nó no ReactFlow

    private Long idProject; // O identificador único do projeto ao qual o nó pertence

    // Relacionamento com a classe "Tubulacao" através do tipo "Tubulacao"
    @Relationship(type="Tubulacao")
    private List<Tubulacao> tubulacoesList = new ArrayList<>(); // Lista de tubulações conectadas a este nó

    @Override
    public String toString() {
        return "No [id=" + id + ", idProject=" + idProject + ", idReactFlow=" + idReactFlow + ", nome=" + nome + "]";
    }
}
