package com.pegasus.pegasus.entities;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class NodeEntity {
    // declaração das variaveis e seus tipos
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String idReactFlow;

    private Long idProject;

    // constructor
    public NodeEntity() {
    }

    public NodeEntity(NodeEntity other) {
        this.id = other.id;
        this.name = other.name;

        this.idReactFlow = other.idReactFlow;
        this.idProject = other.idProject;
    }

    public NodeEntity(Long id, String name, String idReactFlow, Long idProject) {
        this.id = id;
        this.name = name;
        this.idReactFlow = idReactFlow;
        this.idProject = idProject;
    }

    // getters
    public String getName() {
        return this.name;
    }

    public Long getId() {
        return this.id;
    }

    public String getIdReactFlow() {
        return this.idReactFlow;
    }

    public Long getIdProject() {
        return this.idProject;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdReactFlow(String idReactFlow) {
        this.idReactFlow = idReactFlow;
    }

    public void setIdProject(Long idProject) {
        this.idProject = idProject;
    }
}
