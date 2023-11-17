// Define o pacote onde a classe ResultSummaryDTO está localizada
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
public class ResultSummaryDTO {
  // Declaração do campo "query" que armazena um objeto do tipo QueryDTO
  private QueryDTO query;
  
  // Declaração do campo "counters" que armazena um objeto do tipo QueryStatisticsDTO (contadores)
  private QueryStatisticsDTO counters;
  
  // Declaração do campo "updateStatistics" que armazena um objeto do tipo QueryStatisticsDTO (estatísticas de atualização)
  private QueryStatisticsDTO updateStatistics;
  
  // Declaração do campo "server" que armazena um objeto do tipo ServerInfoDTO (informações do servidor)
  private ServerInfoDTO server;
  
  // Declaração do campo "resultConsumedAfter" que armazena um valor inteiro representando o resultado consumido após
  private int resultConsumedAfter;
  
  // Declaração do campo "resultAvailableAfter" que armazena um valor inteiro representando o resultado disponível após
  private int resultAvailableAfter;
  
  // Declaração do campo "database" que armazena um objeto do tipo DatabaseInfoDTO (informações do banco de dados)
  private DatabaseInfoDTO database;
  
  // Método toString() sobrescrito para fornecer uma representação de string personalizada dos objetos da classe
  @Override
  public String toString() {
    return "ResultSummaryDTO [query=" + query + ", counters=" + counters + ", updateStatistics=" + updateStatistics
        + ", server=" + server + ", resultConsumedAfter=" + resultConsumedAfter + ", resultAvailableAfter="
        + resultAvailableAfter + ", database=" + database + "]";
  }
}
