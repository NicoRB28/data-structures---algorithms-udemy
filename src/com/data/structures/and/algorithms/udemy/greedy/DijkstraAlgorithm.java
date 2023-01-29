package com.data.structures.and.algorithms.udemy.greedy;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * shortest path in graph
 */
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        int[][] adjacencyMatrix = {
                    /*A  B  C  D  E  F  G/
                /*A*/{0, 3, 5, 6, 0, 8, 0},
                /*B*/{3, 0, 0, 4, 2, 0, 5},
                /*C*/{5, 0, 0, 0, 0, 4, 0},
                /*D*/{6, 4, 0, 0, 0, 1, 6},
                /*E*/{0, 2, 0, 0, 0, 0, 10},
                /*F*/{8, 0, 6, 1, 0, 0, 8},
                /*G*/{0, 8, 0, 6, 10, 8, 0}
        };

        dijkstraAlgorithm(adjacencyMatrix);
    }

    private static void dijkstraAlgorithm(int[][] adjacencyMatrix) {
        int numberOfVertices = adjacencyMatrix.length;
        boolean[] visited = new boolean[numberOfVertices];
        int[] distance = new int[numberOfVertices];
        //start
        distance[0] = 0;
        initDistancesWithInfinity(distance, numberOfVertices);
        for (int i = 0; i < numberOfVertices - 1; i++) {
            int vertexWithMinimumDistance = findMinVertex(distance, visited);
            visited[vertexWithMinimumDistance] = true;
            for (int j = 0; j < numberOfVertices; j++) {
                if (adjacencyMatrix[vertexWithMinimumDistance][j] != 0 && !visited[j]) {
                    int newDistance = distance[vertexWithMinimumDistance] + adjacencyMatrix[vertexWithMinimumDistance][j];

                    if (newDistance < distance[j]) {
                        distance[j] = newDistance;
                    }
                }
            }
        }
        printDistances(numberOfVertices, distance);
    }

    private static void printDistances(int numberOfVertices, int[] distance) {
        for (int i = 0; i < numberOfVertices; i++) {
            System.out.println(i + " " + distance[i]);
        }
    }

    private static int findMinVertex(int[] distance, boolean[] visited) {
        int minVertex = -1;
        for (int i = 0; i < distance.length; i++) {
            if (!visited[i] && (minVertex == -1 || distance[i] < distance[minVertex])) {
                minVertex = i;
            }
        }
        return minVertex;
    }

    private static void initDistancesWithInfinity(int[] distance, int numberOfVertices) {
        IntStream.range(1, numberOfVertices)
                .forEach(index -> distance[index] = Integer.MAX_VALUE);
    }
}
