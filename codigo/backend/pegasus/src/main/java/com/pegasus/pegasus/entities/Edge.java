package com.pegasus.pegasus.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Edge {
    @Id
    private String id;
    private Long idProject;
    private String source;
    private String sourceHandle;
    private String target;
    private String targetHandle;
    private boolean bidirecional;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    // Construtor padrão da classe Edge
    public Edge() {
    }

    // Construtor com argumentos para inicializar os atributos da classe Edge
    public Edge(String id, String source, String sourceHandle, String target, String targetHandle, boolean bidirecional, Project project, Long idProject) {
        this.id = id;
        this.source = source;
        this.sourceHandle = sourceHandle;
        this.target = target;
        this.targetHandle = targetHandle;
        this.bidirecional = bidirecional;
        this.project = project;
        this.idProject = idProject;
    }

    // Métodos getter para os atributos da classe Edge

    public String getId() {
        return this.id;
    }

    public String getSource() {
        return this.source;
    }

    public String getSourceHandle() {
        return this.sourceHandle;
    }

    public String getTarget() {
        return this.target;
    }

    public String getTargetHandle() {
        return this.targetHandle;
    }

    public boolean getBidirecional() {
        return this.bidirecional;
    }

    public Project getProject() {
        return this.project;
    }

    public Long getIdProject() {
        return this.idProject;
    }

    // Métodos setter para os atributos da classe Edge

    public void setId(String id) {
        this.id = id;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setSourceHandle(String sourceHandle) {
        this.sourceHandle = sourceHandle;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setTargetHandle(String targetHandle) {
        this.targetHandle = targetHandle;
    }

    public void setBidirecional(boolean bidirecional) {
        this.bidirecional = bidirecional;
    }
    public void setProject(Project project) {
        this.project = project;
    }

    public void setIdProject(Long idProject) {
        this.idProject = idProject;
    }

    // Método toString para representação de string da classe Edge

    @Override
    public String toString() {
        return "Edge{" +
                "id='" + id + '\'' +
                ", source='" + source + '\'' +
                ", sourceHandle='" + sourceHandle + '\'' +
                ", target='" + target + '\'' +
                ", targetHandle='" + targetHandle + '\'' +
                ", bidirecional=" + bidirecional +
                ", project=" + project +
                ", idProject='" + idProject + '\'' +
                '}';
    }
}
