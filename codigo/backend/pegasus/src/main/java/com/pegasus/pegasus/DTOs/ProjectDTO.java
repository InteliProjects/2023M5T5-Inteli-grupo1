  package com.pegasus.pegasus.DTOs;

  import java.util.List;

  import com.pegasus.pegasus.entities.Edge;
  import com.pegasus.pegasus.entities.Node.Node;

  import lombok.AllArgsConstructor;
  import lombok.Getter;
  import lombok.NoArgsConstructor;
  import lombok.Setter;

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
 
// A classe ProjectDTO é usada para representar informações relacionadas a um projeto.
public class ProjectDTO {

  // Declaração de atributos da classe.

  // Representa o ID do projeto.
  private Long id;

  // Representa o nome do projeto.
  private String name;

  // Representa a descrição do projeto.
  private String description;

  // Representa a data de início do projeto.
  private String startDate;

  // Representa a data de término do projeto.
  private String endDate;

  // Representa o ID do usuário associado ao projeto.
  private Long idUser;

  // Representa uma lista de nós (nodes) associados ao projeto.
  private List<Node> nodes;

  // Representa uma lista de arestas (edges) associadas ao projeto.
  private List<Edge> edges;

  // Representa uma representação em formato JSON dos nós do projeto.
  private String nodesJson;

  // Representa uma representação em formato JSON das arestas do projeto.
  private String edgesJson;
  }
