package com.pegasus.pegasus.DTOs;

// Importações das anotações do Lombok para geração automática de construtores, getters e setters.
// import jakarta.persistence.Embeddable;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
// A anotação @Embeddable indica que esta classe é incorporável em entidades
// JPA.
@Embeddable
public class DataInfo {

    // Declaração de atributos da classe.

    // Representa um rótulo (label) associado a algum dado.
    private String label;
}
