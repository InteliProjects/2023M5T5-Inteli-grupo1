package com.pegasus.pegasus.DTOs.responsecreateEdge;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
// Marca a classe como sendo um objeto de transferência de dados (DTO).
public class ServerInfoDTO {
  
  // Declaração de atributos da classe.

  // Representa o endereço do servidor.
  private String address;

  // Representa o agente do servidor.
  private String agent;

  // Representa a versão do protocolo do servidor como um número em ponto flutuante.
  private float protocolVersion;
}
