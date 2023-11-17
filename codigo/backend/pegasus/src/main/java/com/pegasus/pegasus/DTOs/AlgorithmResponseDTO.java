package com.pegasus.pegasus.DTOs;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// A classe AlgorithmResponseDTO é usada para representar uma resposta de algoritmo.
public class AlgorithmResponseDTO {

    // Declaração de atributos da classe.

    // Representa a lista de valores longos na resposta do algoritmo.
    private List<Long> retorno;
}