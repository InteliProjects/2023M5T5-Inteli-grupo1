package com.pegasus.pegasus.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
// A classe EdgeDTO representa informações sobre uma aresta.
public class EdgeDTO {

  // Declaração de atributos da classe.

  // Representa o ID da aresta.
  private Long id;

  // Representa o nó de origem (source) da aresta.
  private String source;

  // Representa o nó de destino (target) da aresta.
  private String target;

  // Representa se a aresta é bidirecional (true) ou não (false).
  private boolean bidirecional;

  // Representa o ID do projeto ao qual a aresta está associada.
  private Long idProject;
}
