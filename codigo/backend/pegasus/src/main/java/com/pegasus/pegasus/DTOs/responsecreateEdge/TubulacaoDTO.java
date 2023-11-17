package com.pegasus.pegasus.DTOs.responsecreateEdge;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
// A classe TubulacaoDTO é usada para representar informações sobre uma tubulação.
public class TubulacaoDTO {

    // Declaração de atributos da classe.

    // Representa o nome de origem da tubulação.
    private String idSourceReactFlow;

    // Representa o nome de destino da tubulação.
    private String idTargetReactFlow;

    private String idReactFlow;

    private Long idProject;

    private Boolean bidirectional;

    // Representa o comprimento da tubulação como um valor inteiro.
    private int comprimento;
}
