package com.pegasus.pegasus.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// A classe GraphAlgorithmDTO é usada para representar informações relacionadas a um algoritmo de gráfico ou rede.
public class GraphAlgorithmDTO {

    // Declaração de atributos da classe.

    // Representa o ID de origem (ou origem) no contexto do algoritmo.
    private long originId;

    // Representa o ID de destino (ou destino) no contexto do algoritmo.
    private long destinyId;
}