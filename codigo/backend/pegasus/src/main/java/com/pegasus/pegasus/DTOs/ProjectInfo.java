package com.pegasus.pegasus.DTOs;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
// A anotação @Embeddable indica que esta classe é incorporável em entidades JPA.
@Embeddable
public class ProjectInfo {

    // Declaração de atributos da classe.

    // Representa o ID do projeto.
    private Long id;

    // Representa o nome do projeto.
    private String name;

    // Representa a descrição do projeto.
    private String description;
}
