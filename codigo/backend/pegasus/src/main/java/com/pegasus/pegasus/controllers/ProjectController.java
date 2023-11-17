package com.pegasus.pegasus.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pegasus.pegasus.DTOs.ProjectCreationRequestDTO;
import com.pegasus.pegasus.DTOs.ProjectDTO;
import com.pegasus.pegasus.entities.Edge;
import com.pegasus.pegasus.entities.EdgeEntity;
import com.pegasus.pegasus.entities.No;
import com.pegasus.pegasus.entities.Project;
import com.pegasus.pegasus.entities.Node.Node;
import com.pegasus.pegasus.services.EdgeService;
import com.pegasus.pegasus.services.NodeService;
import com.pegasus.pegasus.services.ProjectService;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value = "/project")
@CrossOrigin(origins = "http://localhost:5173")
public class ProjectController {
  @Autowired
  private ProjectService projectService;

  @Autowired
  private NodeService nodeService;

  @Autowired
  private EdgeService edgeService;

  // Achar projeto pelo ido
  @GetMapping("/{id}")
  public ResponseEntity<Project> findProjectById(@PathVariable Long id) {
    try {
      Project response = projectService.findProjectById(id);
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (EntityNotFoundException ex) {
      return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
    } catch (Exception ex) {
      return new ResponseEntity<Project>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/user/{idUser}")
  public ResponseEntity<?> findAllProjectsByUserId(@PathVariable Long idUser) {
    try {
      return ResponseEntity.ok().body(projectService.findAllProjectsByUserId(idUser));
    } catch (Exception e) {
      String errorMessage = e.getMessage();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
  }

  // Criar projeto
  @PostMapping("/create-and-assign")
  public ResponseEntity<?> createAndAssignProject(@RequestBody ProjectCreationRequestDTO request) {
    try {
      ProjectDTO response = projectService.createProjectAndAssignToUser(request.getProject());
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      String errorMessage = e.getMessage();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
  }

  // Atualizar projeto
  @PatchMapping("/{id}")
  public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project projectDTO) {
    try {
      Project response = projectService.updateProject(id, projectDTO);
      
      List<Node> nodeList = projectDTO.getNodes();

      List<No> noList = new ArrayList<>();
      for (Node node : nodeList) {
        No no = new No();
        no.setNome(node.getData().getLabel());
        no.setIdReactFlow(node.getId());
        no.setIdProject(id);

        noList.add(no);
      }

      List<Edge> edgeList = projectDTO.getEdges();

      List<EdgeEntity> edgeEntityList = new ArrayList<>();
      for (Edge edge : edgeList) {
        EdgeEntity edgeEntity = new EdgeEntity();
        edgeEntity.setIdSourceReactFlow(edge.getSource());
        edgeEntity.setIdTargetReactFlow(edge.getTarget());
        edgeEntity.setBidirectional(edge.getBidirecional());
        edgeEntity.setIdReactFlow(edge.getId());
        edgeEntity.setIdProject(id);

        edgeEntityList.add(edgeEntity);
      }

      nodeService.createNodes(noList);
      
      edgeService.createEdges(edgeEntityList);

      System.out.println(noList);
      System.out.println(edgeEntityList);
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (EntityNotFoundException ex) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception ex) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }  
  }

  // Deletar projeto
  @DeleteMapping("/{id}")
  public ResponseEntity<ProjectDTO> deleteProject(@PathVariable Long id) {
    try {
      ProjectDTO response = projectService.deleteProject(id);
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (EntityNotFoundException ex) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception ex) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  } 
}
