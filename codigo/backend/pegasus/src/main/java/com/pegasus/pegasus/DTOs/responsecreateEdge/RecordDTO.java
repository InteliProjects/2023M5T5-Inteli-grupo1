// Define o pacote onde a classe RecordDTO está localizada
package com.pegasus.pegasus.DTOs.responsecreateEdge;

// Importa classes necessárias
import java.util.List;
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
public class RecordDTO {
  // Declaração do campo "keys" que armazena uma lista de chaves (strings)
  private List<String> keys;
  
  // Declaração do campo "_fields" que armazena uma lista de objetos
  private List<Object> _fields;
  
  // Declaração do campo "_fieldLookup" que armazena um mapa de chaves de texto e valores inteiros
  private Map<String, Integer> _fieldLookup;
}
