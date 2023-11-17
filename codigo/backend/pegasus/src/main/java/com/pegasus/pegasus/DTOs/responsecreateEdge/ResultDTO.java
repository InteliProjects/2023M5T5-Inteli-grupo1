// Define o pacote onde a classe ResultDTO está localizada
package com.pegasus.pegasus.DTOs.responsecreateEdge;

// Importa classes necessárias
import java.util.List;

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
public class ResultDTO {
    // Declaração do campo "records" que armazena uma lista de objetos do tipo RecordDTO
    private List<RecordDTO> records; // Lista de registros retornados pela consulta
    
    // Declaração do campo "summary" que armazena um objeto do tipo ResultSummaryDTO (resumo da consulta)
    private ResultSummaryDTO summary; // Resumo da consulta (opcional)
    
    // Método toString() sobrescrito para fornecer uma representação de string dos objetos da classe
    @Override
    public String toString() {
        return "ResultDTO [records=" + records + ", summary=" + summary + "]";
    }
}
