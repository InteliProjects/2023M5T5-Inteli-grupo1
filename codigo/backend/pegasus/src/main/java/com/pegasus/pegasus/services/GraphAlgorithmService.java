package com.pegasus.pegasus.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pegasus.pegasus.DTOs.ExportExcelDTO;
import com.pegasus.pegasus.DTOs.GraphAlgorithmDTO;
import com.pegasus.pegasus.entities.NodeEntity;
import com.pegasus.pegasus.entities.No;
import com.pegasus.pegasus.repositories.NodeRepository;
import com.pegasus.pegasus.repositories.NoRepository;
import com.pegasus.pegasus.services.NodeService;

@Service
public class GraphAlgorithmService {

    @Autowired
    private NodeService nodeService;

    @Autowired
    private NoRepository nodeRepository;

    // Método para encontrar todos os caminhos entre dois nós em um grafo
    public List<List<String>> findAllPaths(GraphAlgorithmDTO algoDto) {
        long originId = algoDto.getOriginId();
        long destinyId = algoDto.getDestinyId();

        No originNode = nodeRepository.findById(originId)
                .orElseThrow(() -> new RuntimeException("Nó de origem não encontrado com o ID: " + originId));
        No destinyNode = nodeRepository.findById(destinyId)
                .orElseThrow(() -> new RuntimeException("Nó de destino não encontrado com o ID: " + destinyId));

        List<No> vizinhos = nodeService.getNeighbor(originNode.getNome());
        System.out.println(vizinhos);
        for (No i : vizinhos) {
            System.out.println("Vizinhos do nó " + originNode.getNome() + ": " + i.getNome());
        }

        int numNodes = (int) nodeRepository.count();
        boolean[] visited = new boolean[numNodes];
        Map<Long, Integer> nodeIdToIndex = new HashMap<>();

        // Mapeia os IDs dos nós para os índices no array visited
        Iterable<No> allNodes = nodeRepository.findAll();
        int index = 0;
        for (No node : allNodes) {
            nodeIdToIndex.put(node.getId(), index);
            index++;
        }

        List<List<String>> allPaths = new ArrayList<>();
        List<String> currentPath = new ArrayList<>();

        findAllPathsUtil(originNode, destinyNode, visited, currentPath, allPaths, nodeIdToIndex);
        System.out.println("Algoritmo encerrou. AllPaths: " + allPaths);
        return allPaths;
    }

    // Método utilitário recursivo para encontrar todos os caminhos
    private void findAllPathsUtil(No currentNode, No destinyNode,
            boolean[] visited, List<String> currentPath, List<List<String>> allPaths,
            Map<Long, Integer> nodeIdToIndex) {

        int currentIndex = nodeIdToIndex.get(currentNode.getId());
        visited[currentIndex] = true;
        currentPath.add(currentNode.getIdReactFlow());

        // Aqui você pode fazer algo com os vizinhos, dependendo da lógica do seu
        // algoritmo
        System.out.println("Visiting node with Name: " + currentNode.getNome() + ", ID: " + currentNode.getId());
        System.out.println("Vizinhos: " + nodeService.getNeighbor(currentNode.getNome()));

        if (currentNode.getId().equals(destinyNode.getId())) {
            allPaths.add(new ArrayList<>(currentPath));
            System.out.println("Found a path: " + currentPath);

        }

        for (No neighbor : nodeService.getNeighbor(currentNode.getNome())) {
            int neighborIndex = nodeIdToIndex.get(neighbor.getId());
            if (!visited[neighborIndex]) {
                findAllPathsUtil(neighbor, destinyNode, visited, currentPath, allPaths, nodeIdToIndex);
            }
        }
        visited[currentIndex] = false;
        currentPath.remove(currentNode.getIdReactFlow());
    }

    private List<String> getAllUniqueNeighbors(List<String> path) {
        List<String> allNeighbors = new ArrayList<>();
        for (String nodeId : path) {
            No node = nodeRepository.findByIdReactFlow(nodeId);
            if (node != null) {
                for (No neighbor : nodeService.getNeighbor(node.getNome())) {
                    if (!allNeighbors.contains(neighbor.getIdReactFlow())
                            && !path.contains(neighbor.getIdReactFlow())) {
                        allNeighbors.add(neighbor.getIdReactFlow());
                    }
                }
            }
        }
        return allNeighbors;
    }

    public byte[] exportPathToExcel(ExportExcelDTO exportDTO) {
        if (exportDTO.getAllPaths() == null || exportDTO.getAllPaths().isEmpty()) {
            System.out.println("Lista de caminhos vazia ou nula.");
            return null;
        }
        List<String> path = exportDTO.getAllPaths();
        List<String> allNodes = new ArrayList<>(path);
        List<String> neighbors = getAllUniqueNeighbors(path);
        allNodes.addAll(neighbors);
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Caminhos");
        Row headerRow = sheet.createRow(0);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Transferência");
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.CENTER); // Centraliza o conteúdo
        CellStyle greenStyle = workbook.createCellStyle();
        greenStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        greenStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        greenStyle.setAlignment(HorizontalAlignment.CENTER); // Centraliza o conteúdo
        CellStyle redStyle = workbook.createCellStyle();
        redStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        redStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        redStyle.setAlignment(HorizontalAlignment.CENTER);
        for (int i = 0; i < allNodes.size(); i++) {
            String nodeId = allNodes.get(i);
            No nodeEntity = nodeRepository.findByIdReactFlow(nodeId);
            String nodeName = nodeEntity != null ? nodeEntity.getNome() + " (" + nodeId +
                    ")"
                    : "Desconhecido (ID desconhecido)";
            headerCell = headerRow.createCell(i + 1);
            headerCell.setCellValue("Nó " + nodeName);
            headerCell.setCellStyle(style);
        }
        int rowCount = 1;
        Row row = sheet.createRow(rowCount);
        Cell cell = row.createCell(0);
        No startNode = nodeRepository.findByIdReactFlow(path.get(0));
        No endNode = nodeRepository.findByIdReactFlow(path.get(path.size() - 1));
        cell.setCellValue("Transferência (" + (startNode != null ? startNode.getNome() : "Desconhecido") +
                ") para (" + (endNode != null ? endNode.getNome() : "Desconhecido") + ")");
        for (int i = 0; i < allNodes.size(); i++) {
            cell = row.createCell(i + 1);
            if (path.contains(allNodes.get(i))) {
                cell.setCellValue("O"); // Marca com "O" branco (círculo vazio) se o nó faz
                // parte do caminho
                cell.setCellStyle(greenStyle);
            } else {
                cell.setCellValue("0"); // Marca com "0" se o nó é um vizinho, mas não faz
                // parte do caminho
            }
        }
        // Auto size columns to fit content
        for (int i = 0; i <= allNodes.size(); i++) {
            sheet.autoSizeColumn(i);
        }
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            workbook.write(outputStream);
            workbook.close();
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
