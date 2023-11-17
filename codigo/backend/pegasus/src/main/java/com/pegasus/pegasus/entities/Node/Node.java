package com.pegasus.pegasus.entities.Node;


import com.pegasus.pegasus.DTOs.DataInfo;
import com.pegasus.pegasus.entities.Project;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
// A classe Node representa uma entidade de nó em algum sistema.
@Entity
public class Node {

    // Declaração de atributos da classe.

    // Representa o ID do nó.
    @Id
    private String id;

    // Representa o tipo do nó.
    private String type;

    // Representa o ID do projeto ao qual o nó está associado.
    private Long idProject;

    // Representa a posição do nó (possivelmente um objeto da classe Position).
    private Position position;

    // Representa informações de dados relacionadas ao nó (possivelmente um objeto da classe DataInfo).
    private DataInfo data;

    // Representa o projeto ao qual o nó pertence (muitos nós podem estar associados a um projeto).
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Override
    public String toString() {
        return "Node [id=" + id + ", name=" + type + "]";
    }
}
