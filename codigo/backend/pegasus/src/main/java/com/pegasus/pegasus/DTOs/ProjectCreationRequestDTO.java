package com.pegasus.pegasus.DTOs;

import com.pegasus.pegasus.entities.Project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// A classe ProjectCreationRequestDTO é usada para representar uma solicitação de criação de projeto.
public class ProjectCreationRequestDTO {

  // Declaração de atributos da classe.

  // Representa o ID do usuário que está criando o projeto.
  private Long idUser;

  // Representa os detalhes do projeto que está sendo criado.
  private Project project;
}
