package com.pegasus.pegasus.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
// A classe NodeDTO representa informações sobre um nó.
public class NodeDTO {

  // Declaração de atributos da classe.

  // Representa o ID do nó.
  private Long id;

  // Representa o nome do nó.
  private String name;

  // Representa o ID do projeto ao qual o nó está associado.
  private Long idProject;
}
