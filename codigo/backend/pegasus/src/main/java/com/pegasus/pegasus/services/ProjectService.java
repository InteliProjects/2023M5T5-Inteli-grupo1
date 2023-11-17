package com.pegasus.pegasus.services;

import java.util.List;
import java.util.logging.Logger;

//importando ferramentas
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pegasus.pegasus.DTOs.ProjectDTO;
import com.pegasus.pegasus.DTOs.ProjectInfo;
import com.pegasus.pegasus.entities.Edge;
import com.pegasus.pegasus.entities.Project;
import com.pegasus.pegasus.entities.Node.Node;
import com.pegasus.pegasus.repositories.ProjectRepository;
import javax.persistence.EntityNotFoundException;

@Service
public class ProjectService {
  @Autowired
  private ProjectRepository projectRepository;

  private static final Logger LOGGER = java.util.logging.Logger.getLogger(ProjectService.class.getName());

  //encontrando projeto pelo id do projeto
  public Project findProjectById(Long id) {
    try {
      Project existingProject = projectRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Project not found with id : " + id));

      Project projectDTO = new Project();
      projectDTO.setId(existingProject.getId());
      projectDTO.setName(existingProject.getName());
      projectDTO.setDescription(existingProject.getDescription());
      projectDTO.setStartDate(existingProject.getStartDate());
      projectDTO.setEndDate(existingProject.getEndDate());
      projectDTO.setIdUser(existingProject.getIdUser());
      projectDTO.setNodes(convertJsonToNodesList(existingProject.getNodesJson()));
      projectDTO.setEdges(convertJsonToEdgeList(existingProject.getEdgesJson()));
      projectDTO.setNodesJson(existingProject.getNodesJson());
      projectDTO.setEdgesJson(existingProject.getEdgesJson());


      return projectDTO;
    } catch (Exception e) {
        throw new RuntimeException("Project not found with id : " + id + ", erro: " + e.getMessage() + "", e);
    }
  }

  public List<ProjectInfo> findAllProjectsByUserId(Long idUser) {
    try {
      List<ProjectInfo> existingProject = projectRepository.findAllProjectsByUserId(idUser);
      return existingProject;
    } catch (Exception e) {
      throw new RuntimeException("Project not found with userId : " + idUser + ", erro: " + e.getMessage() + "", e);
    }
  }

  //criando projeto e relacionando ao usuario
  public ProjectDTO createProjectAndAssignToUser(Project project) {
    try {
      LOGGER.info("Iniciando criação de projeto");

      Project newProject = new Project();
      newProject.setName(project.getName());
      newProject.setDescription(project.getDescription());
      newProject.setStartDate(project.getStartDate());
      newProject.setEndDate(project.getEndDate());
      newProject.setIdUser(project.getIdUser());
      newProject.setNodesJson(convertNodesListToJson(project.getNodes()));
      newProject.setEdgesJson(convertEdgeListToJson(project.getEdges()));

      LOGGER.info("Conteúdo do nodesJson: " + newProject.getNodesJson() + "");

      //usa a repository pra salvar no banco
      Project createdProject = projectRepository.save(newProject);

      LOGGER.info("Projeto criado com sucesso" + createdProject + "");

      //usa o DTO para transitar entre as camadas e mandar para o frontend
      ProjectDTO createdProjectDTO = new ProjectDTO();
      createdProjectDTO.setId(createdProject.getId());
      createdProjectDTO.setName(createdProject.getName());
      createdProjectDTO.setDescription(createdProject.getDescription());
      createdProjectDTO.setStartDate(createdProject.getStartDate());
      createdProjectDTO.setEndDate(createdProject.getEndDate());
      createdProjectDTO.setIdUser(createdProject.getIdUser());
      createdProjectDTO.setNodes(convertJsonToNodesList(createdProject.getNodesJson()));
      createdProjectDTO.setEdges(convertJsonToEdgeList(createdProject.getEdgesJson()));
      createdProjectDTO.setNodesJson(createdProject.getNodesJson());
      createdProjectDTO.setEdgesJson(createdProject.getEdgesJson());


      return createdProjectDTO;
    } catch (Exception e) {
      throw new RuntimeException("Não foi possível criar o projeto, erro: " + e.getMessage() + "");
    }
  }
  
  // atualizando projeto pelo id do projeto
  public Project updateProject(Long id, Project project) {
    try {
      Project existingProject = projectRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Project not found with id : " + id));
   
      if (project.getName() != null) {
        existingProject.setName(project.getName());
      }

      if (project.getDescription() != null) {
        existingProject.setDescription(project.getDescription());
      }

      if (project.getStartDate() != null) {
        existingProject.setStartDate(project.getStartDate());
      }

      if (project.getEndDate() != null) {
        existingProject.setEndDate(project.getEndDate());
      }

      if (project.getNodes() != null) {
        existingProject.setNodesJson(convertNodesListToJson(project.getNodes()));
      }

      if (project.getEdges() != null) {
        existingProject.setEdgesJson(convertEdgeListToJson(project.getEdges()));
      }



      //usa a repository pra salvar no banco
      Project updatedProject = projectRepository.save(existingProject);

      //usa o DTO para transitar entre as camadas e mandar para o frontend
      ProjectDTO updatedProjectDTO = new ProjectDTO();
      updatedProjectDTO.setName(updatedProject.getName());
      updatedProjectDTO.setDescription(updatedProject.getDescription());
      updatedProjectDTO.setEndDate(updatedProject.getEndDate());
      updatedProjectDTO.setNodes(convertJsonToNodesList(updatedProject.getNodesJson()));
      updatedProjectDTO.setEdges(convertJsonToEdgeList(updatedProject.getEdgesJson()));
      updatedProjectDTO.setNodesJson(updatedProject.getNodesJson());
      updatedProjectDTO.setEdgesJson(updatedProject.getEdgesJson());

      return project;
    } catch (Exception ex) {
      throw new RuntimeException("Error updating project: " + ex.getMessage(), ex);
    }
  }

  //deletando usuario pelo id
  public ProjectDTO deleteProject(Long id) {
    try {
      Project existingProject = projectRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Project not found with id : " + id));

      ProjectDTO projectDTO = new ProjectDTO();
      projectDTO.setName(existingProject.getName());
      projectDTO.setDescription(existingProject.getDescription());
      projectDTO.setEndDate(existingProject.getEndDate());

      projectRepository.delete(existingProject);

      return projectDTO;
    } catch (Exception ex) {
      throw new RuntimeException("Error deleting project: " + ex.getMessage(), ex);
    }
  }

  private String convertNodesListToJson(List<Node> nodes) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
      return objectMapper.writeValueAsString(nodes);
    } catch (JsonProcessingException e) {
        throw new RuntimeException("Não foi possível converter a lista de nós para JSON, erro: " + e.getMessage() + "");
    }
  }

  private List<Node> convertJsonToNodesList(String nodesJson) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
      return objectMapper.readValue(nodesJson, List.class);
    } catch (JsonProcessingException e) {
        throw new RuntimeException("Não foi possível converter a lista de nós para JSON, erro: " + e.getMessage() + "");
    }
  }

  private String convertEdgeListToJson(List<Edge> edges) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
      return objectMapper.writeValueAsString(edges);
    } catch (JsonProcessingException e) {
        throw new RuntimeException("Não foi possível converter a lista de nós para JSON, erro: " + e.getMessage() + "");
    }
  }

  private List<Edge> convertJsonToEdgeList(String edgesJson) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
      return objectMapper.readValue(edgesJson, List.class);
    } catch (JsonProcessingException e) {
        throw new RuntimeException("Não foi possível converter a lista de nós para JSON, erro: " + e.getMessage() + "");
    }
  }

  



  // public List<Project> getAllProjects(Long userId) {
  //   List<Project> existingProject = projectRepository.findProjectsByUserId(userId);
    
  //   if (existingProject.isEmpty()) {
  //     throw new EntityNotFoundException("Project not found with this project userId : " + userId);
  //   }
  //   return existingProject;
  // }
}
