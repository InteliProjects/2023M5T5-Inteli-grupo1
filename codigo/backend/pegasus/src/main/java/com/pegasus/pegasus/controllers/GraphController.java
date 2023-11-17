package com.pegasus.pegasus.controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pegasus.pegasus.DTOs.BidirectionalDTO;
import com.pegasus.pegasus.DTOs.ExportExcelDTO;
import com.pegasus.pegasus.DTOs.GraphAlgorithmDTO;
import com.pegasus.pegasus.DTOs.NodeDTO;
import com.pegasus.pegasus.DTOs.responsecreateEdge.EdgeQuery;
import com.pegasus.pegasus.DTOs.responsecreateEdge.ResultDTO;
import com.pegasus.pegasus.entities.EdgeEntity;
import com.pegasus.pegasus.entities.No;
import com.pegasus.pegasus.entities.NodeEntity;
import com.pegasus.pegasus.services.EdgeService;
import com.pegasus.pegasus.services.NodeService;
import com.pegasus.pegasus.services.GraphAlgorithmService;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value = "/graph")
@CrossOrigin(origins = "http://localhost:5173")
public class GraphController {
    @Autowired
    private EdgeService edgeService;

    @Autowired
    private NodeService nodeService;

    @Autowired
    private GraphAlgorithmService graphAlgorithmService;

    @PostMapping("/node")
    public ResponseEntity<List<No>> createNodesBatch(@RequestBody List<No> nodes) {
        try {
            // Tentativa de criar nós em massa com base na lista de nós fornecida no corpo
            // da solicitação
            List<No> createdNodes = nodeService.createNodes(nodes);

            // Retorna uma resposta de sucesso com os novos nós criados e o status HTTP 201
            // (CREATED)
            return new ResponseEntity<>(createdNodes, HttpStatus.CREATED);
        } catch (Exception e) {
            // Em caso de qualquer exceção durante o processo, retorna um erro interno do
            // servidor (HTTP 500)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint para obter um nó pelo ID
    @GetMapping("/node/{id}")
    public ResponseEntity<No> getNoById(@PathVariable Long id) {
        try {
            // Tentativa de obter um nó pelo ID fornecido
            No response = nodeService.getNoById(id);

            // Retorna uma resposta com o nó encontrado e o status HTTP 200 (OK)
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            // Se o nó não for encontrado, retorna um status HTTP 404 (NOT FOUND)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Em caso de qualquer outra exceção, retorna um erro interno do servidor (HTTP
            // 500)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/node/project/{id}")
    public ResponseEntity<List<No>> getNoByIdProject(@PathVariable Long id) {
        try {
            List<No> response = nodeService.getNoByIdProject(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint para obter todos os nós
    @GetMapping("/node/all-nodes")
    public List<No> getAllNodes() {
        try {
            // Tentativa de obter todos os nós
            List<No> response = nodeService.getAllNodes();

            // Retorna a lista de nós encontrados
            return response;
        } catch (Exception e) {
            // Em caso de qualquer exceção durante o processo, lança uma exceção de tempo de
            // execução
            throw new RuntimeException("Não foi possível criar o nó, erro: " + e.getMessage() + "");
        }
    }

    @PostMapping("/node/algorithm")
    public List<List<String>> findAllPaths(@RequestBody GraphAlgorithmDTO algoDto) {
        try {
            // Tentativa de obter todos os nós
            List<List<String>> response = graphAlgorithmService.findAllPaths(algoDto);

            // Retorna a lista de nós encontrados
            return response;
        } catch (Exception e) {
            // Em caso de qualquer exceção durante o processo, lança uma exceção de tempo de
            // execução
            throw new RuntimeException("Não foi possível realizar o algoritmo. Erro: " + e.getMessage() + "");
        }
    }

    // Endpoint para atualizar um nó pelo ID
    @PutMapping("/node/{id}")
    public ResponseEntity<NodeEntity> updateNode(@PathVariable("id") Long id, @RequestBody NodeEntity nodeDTO) {
        try {
            // Tentativa de atualizar um nó com base no ID e no NodeEntity fornecido
            NodeEntity response = nodeService.updateNode(id, nodeDTO);

            // Retorna uma resposta com o nó atualizado e o status HTTP 200 (OK)
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            // Se o nó não for encontrado, retorna um status HTTP 404 (NOT FOUND)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Em caso de qualquer exceção durante o processo, retorna um erro interno do
            // servidor (HTTP 500)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint para deletar um nó pelo ID
    @DeleteMapping("/node/{id}")
    public ResponseEntity<HttpStatus> deleteNode(@PathVariable("id") Long id) {
        try {
            // Tentativa de deletar um nó pelo ID fornecido
            nodeService.deleteNode(id);

            // Retorna uma resposta vazia com o status HTTP 204 (NO CONTENT)
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            // Se o nó não for encontrado, retorna um status HTTP 404 (NOT FOUND)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Em caso de qualquer outra exceção, retorna um erro interno do servidor (HTTP
            // 500)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/node/project/{idProject}")
    public ResponseEntity<HttpStatus> deleteNoByIdProject(@PathVariable Long idProject) {
        try {
            // Tentativa de deletar um nó pelo ID fornecido
            nodeService.deleteByIdProject(idProject);

            // Retorna uma resposta vazia com o status HTTP 204 (NO CONTENT)
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            // Se o nó não for encontrado, retorna um status HTTP 404 (NOT FOUND)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Em caso de qualquer outra exceção, retorna um erro interno do servidor (HTTP
            // 500)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint para criar uma nova aresta
    @PostMapping("/edge")
    public ResponseEntity<List<BidirectionalDTO>> createEdges(@RequestBody List<EdgeEntity> edgeList) {
        try {
            List<BidirectionalDTO> response = new ArrayList<>();

            for (EdgeEntity edge : edgeList) {
                BidirectionalDTO bidirectionalDTO = edgeService.createEdge(edge);
                response.add(bidirectionalDTO);
            }

            System.out.print(response);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível criar a aresta, erro: " + e.getMessage() + "");
        }
    }

    // Endpoint para obter uma aresta
    @GetMapping("/edge")
    public ResultDTO getEdge(@RequestBody EdgeQuery edgeQuery) {
        try {
            // Tentativa de obter uma aresta com base na consulta EdgeQuery fornecida
            return edgeService.getEdge(edgeQuery);
        } catch (Exception e) {
            // Em caso de qualquer exceção durante o processo, lança uma exceção de tempo de
            // execução
            throw new RuntimeException("Não foi possível obter as arestas, erro:" + e.getMessage() + "");
        }
    }

    // Endpoint para deletar uma aresta
    @DeleteMapping("/edge")
    public ResultDTO deleteEdge(@RequestBody EdgeQuery edgeQuery) {
        try {
            // Tentativa de deletar uma aresta com base na consulta EdgeQuery fornecida
            return edgeService.deleteEdge(edgeQuery);
        } catch (Exception e) {
            // Em caso de qualquer exceção durante o processo, lança uma exceção de tempo de
            // execução
            throw new RuntimeException("Não foi possível obter as arestas, erro:" + e.getMessage() + "");
        }
    }

    @PostMapping("/export")
    public ResponseEntity<Resource> exportPathToExcel(@RequestBody ExportExcelDTO exportDTO) {
        byte[] excelBytes = graphAlgorithmService.exportPathToExcel(exportDTO);

        // Configure os cabeçalhos de resposta
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "caminho.xls");

        // Crie um objeto Resource a partir dos bytes do arquivo Excel
        ByteArrayResource resource = new ByteArrayResource(excelBytes);

        // Retorne a resposta com o arquivo Excel como conteúdo
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(excelBytes.length)
                .body(resource);
    }

}
