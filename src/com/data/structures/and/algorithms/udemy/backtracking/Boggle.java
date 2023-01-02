package com.data.structures.and.algorithms.udemy.backtracking;

import java.util.List;

/**
 * Given a matrix with letters and a list of words
 * the goal is to find given words in the given matrix
 * formed by sequence of adjacent characters.
 *
 * complexity:
 *  we have maximum 8 choices to make in every cells
 *  and we have m*n characters so we have O(8^m*n)
 * Space complexity:
 *  O(m*n) -> #of recursive calls
 */
public class Boggle {

    private static final List<String> dictionary = List.of("RAY", "APPLE", "FAKE", "BOOKS");
    private static final int M = 4, N = 4;

    public static void main(String[] args) {
        char[][] boggle = {
                {'T','Y','R','S'},
                {'N','U','A','K'},
                {'Z','F','E','O'},
                {'A','C','B','O'}
        };
        findWords(boggle);
    }

    private static void findWords(char[][] boggle) {
        int[][] visited = new int[M][N];
        String word = "";
        // Cell Iteration
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                findWords(boggle, visited, i, j, word);
            }
            
        }
    }

    private static void findWords(char[][] boggle, int[][] visited, int i, int j, String word) {
        visited[i][j] = 1;
        word += boggle[i][j];

        if (dictionary.contains(word)) {
            System.out.println(word);
        }

        // Choices Iterations
        for (int row = i - 1; row <= i + 1 ; row++) {
            for (int column = j - 1; column <= j + 1; column++) {
                if (isValid(row, column, visited)) {
                    findWords(boggle, visited, row, column, word);
                }
            }
        }
        visited[i][j] = 0;
    }

    private static boolean isValid(int row, int column, int[][] visited) {
        return (row >= 0 && row < M &&
                column >= 0 && column < N &&
                visited[row][column] == 0);
    }
}
