package com.pegasus.pegasus.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class algoritmo_backstracking {
    private final int V; // número de vértices
    private final List<List<Integer>> adj;

    public algoritmo_backstracking(int V) {
        this.V = V;
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new LinkedList<>());
        }
    }

    // adiciona uma aresta ao grafo
    public void addEdge(int src, int dest) {
        adj.get(src).add(dest);
    }

    // função de backtracking para encontrar todos os caminhos
    private void findAllPathsUtil(Integer u, Integer d,
            boolean[] visited,
            List<Integer> localPathList) {
        // marca o nó atual como visitado e o adiciona ao caminho
        visited[u] = true;
        localPathList.add(u);
        // se o destino é encontrado
        if (u.equals(d)) {
            System.out.println(localPathList);
        }
        // continua a partir do nó atual e tenta todos os caminhos
        for (Integer i : adj.get(u)) {
            if (!visited[i]) {
                findAllPathsUtil(i, d, visited, localPathList);
            }
        }
        // remove o vértice atual do caminho e o marca como não visitado
        localPathList.remove(u);
        visited[u] = false;
    }

    // invoca a função utilitária para encontrar todos os caminhos
    public void findAllPaths(int s, int d) {
        boolean[] visited = new boolean[V];
        ArrayList<Integer> pathList = new ArrayList<>();
        // chama a função utilitária de backtracking
        findAllPathsUtil(s, d, visited, pathList);
    }

    public static void main(String[] args) {
        // Cria o grafo com 900 nós
        algoritmo_backstracking g = new algoritmo_backstracking(6);
        // exemplo de como adicionar arestas ao grafo
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 3);
        g.addEdge(4, 5);
        g.addEdge(1, 5);
        g.addEdge(0, 5);
        // ... Adicione as outras arestas aqui
        // Encontra todos os caminhos entre os nós 0 e 5
        g.findAllPaths(0, 5);
    }
}