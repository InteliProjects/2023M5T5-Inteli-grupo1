package com.pegasus.pegasus.entities;

import java.util.Map;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@RelationshipProperties
public class Tubulacao {
  // Atributo ID da Tubulacao
  @Id
  @GeneratedValue
  private Long id;

  // Atributo comprimento da Tubulacao
  private int comprimento;

  // Atributo noDestino da Tubulacao
  @TargetNode
  private No noDestino;
}
