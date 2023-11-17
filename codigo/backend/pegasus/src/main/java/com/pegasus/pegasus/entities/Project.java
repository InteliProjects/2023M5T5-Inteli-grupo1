package com.pegasus.pegasus.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pegasus.pegasus.entities.Node.Node;

@Entity
@Table(name = "tb_project")
public class Project implements Serializable{
  private static final long serialVersionUID = 1L;

  // Declaração das variaveis e seus tipos
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long idUser;
  private String name;
  private String description;
  private String startDate;
  private String endDate;
  
  @OneToMany(mappedBy = "project")
  private List<Node> nodes;

  @OneToMany(mappedBy = "project")
  private List<Edge> edges;

  @Column(columnDefinition = "TEXT")
  @Lob
  private String nodesJson;


  @Column(columnDefinition = "TEXT")
  @Lob
  private String edgesJson;

  // Constructor
  public Project() {
  }

  public Project(String name, String description, String startDate, String endDate, Long idUser, List<Node> nodes, String nodesJson, List<Edge> edges, String edgesJson) {
    this.name = name;
    this.description = description;
    this.startDate = startDate;
    this.endDate = endDate;
    this.idUser = idUser;
    this.nodes = nodes;
    this.edges = edges;
    this.nodesJson = nodesJson;
    this.edgesJson = edgesJson;
  }

  // Getters
  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getStartDate() {
    return startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public Long getIdUser() {
    return idUser;
  }

  public List<Node> getNodes() {
    return nodes;
  }

  public List<Edge> getEdges() {
    return edges;
  }

  public String getNodesJson() {
    return nodesJson;
  }

  public String getEdgesJson() {
    return edgesJson;
  }

  // Setters
  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public void setIdUser(Long idUser) {
    this.idUser = idUser;
  }

  public void setNodes(List<Node> nodes) {
    this.nodes = nodes;
  }

  public void setEdges(List<Edge> edges) {
    this.edges = edges;
  }

  public void setNodesJson(String nodesJson) {
    this.nodesJson = nodesJson;
  }

  public void setEdgesJson(String edgesJson) {
    this.edgesJson = edgesJson;
  }

  public String toString() {
    return "Project [id=" + id + ", name=" + name + ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + ", idUser=" + idUser + ", nodes=" + nodes + ", edges=" + edges + ", nodesJson=" + nodesJson + ", edgesJson=" + edgesJson + "]";
  }

}
