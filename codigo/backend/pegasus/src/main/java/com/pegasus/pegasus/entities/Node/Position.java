package com.pegasus.pegasus.entities.Node;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Position {
  // Declaração de atributos da classe.

    // Representa a coordenada x da posição.
    private int x;

    // Representa a coordenada y da posição.
    private int y;
}
