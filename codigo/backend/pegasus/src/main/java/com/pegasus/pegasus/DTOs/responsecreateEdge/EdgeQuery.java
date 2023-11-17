// Define o pacote onde a classe EdgeQuery está localizada
package com.pegasus.pegasus.DTOs.responsecreateEdge;

// Importa as anotações do projeto Lombok
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// A anotação @AllArgsConstructor gera um construtor com todos os campos da classe
@AllArgsConstructor
// A anotação @NoArgsConstructor gera um construtor sem argumentos (construtor padrão)
@NoArgsConstructor
// A anotação @Getter gera automaticamente métodos getters para todos os campos da classe
@Getter
// A anotação @Setter gera automaticamente métodos setters para todos os campos da classe
@Setter
public class EdgeQuery {
  // Declaração do campo "source" que armazena a fonte da aresta
  private String source;
  
  // Declaração do campo "target" que armazena o alvo da aresta
  private String target;
}
