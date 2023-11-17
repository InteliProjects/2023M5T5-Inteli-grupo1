package com.pegasus.pegasus.DTOs;

import com.pegasus.pegasus.entities.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// A classe UserDTO é usada para representar informações relacionadas a um usuário.
public class UserDTO {

  // Declaração de atributos da classe.

  // Representa o ID do usuário.
  private Long id;

  // Representa o nome do usuário.
  private String name;

  // Representa o nome de usuário (username) do usuário.
  private String username;

  // Representa a senha do usuário.
  private String password;

  // Representa o endereço de email do usuário.
  private String email;

  // Representa o papel (role) do usuário.
  private UserRole role;
}
