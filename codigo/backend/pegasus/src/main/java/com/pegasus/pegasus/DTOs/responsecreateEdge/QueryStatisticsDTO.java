// Define o pacote onde a classe QueryStatisticsDTO está localizada
package com.pegasus.pegasus.DTOs.responsecreateEdge;

// Importa classes necessárias
import java.util.Map;

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
public class QueryStatisticsDTO {
  // Declaração do campo "stats" que armazena um mapa de estatísticas com chaves String e valores Integer
  private Map<String, Integer> stats;
  
  // Declaração do campo "systemUpdates" que armazena um valor inteiro para as atualizações do sistema
  private int systemUpdates;
}
