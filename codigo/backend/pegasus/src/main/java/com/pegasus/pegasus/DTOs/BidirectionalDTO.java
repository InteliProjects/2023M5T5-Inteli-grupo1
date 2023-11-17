// Pacote onde esta classe DTO está localizada
package com.pegasus.pegasus.DTOs;


// Importações necessárias
import java.util.List;
import com.pegasus.pegasus.DTOs.responsecreateEdge.TubulacaoDTO;
import com.pegasus.pegasus.entities.EdgeEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Lombok annotations para gerar construtores, getters e setters automaticamente
@AllArgsConstructor // Gera um construtor com todos os argumentos
@NoArgsConstructor  // Gera um construtor vazio
@Getter // Gera automaticamente os métodos getter
@Setter // Gera automaticamente os métodos setter
public class BidirectionalDTO {

    private List<EdgeEntity> tubulacaoDTOs; // Lista de objetos TubulacaoDTO

}
