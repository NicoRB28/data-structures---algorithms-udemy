package com.data.structures.and.algorithms.udemy.backtracking;

/**
 * we have a board of 8*8 and we must make the knight visit
 * every square once.
 *
 * Choices:
 *  there are 8 choices that we can make to achieve solution
 * Constraints:
 *  the knight can move to a cell only once.
 *  the knight can not move outside the matrix.
 *
 * Complexity:
 *  O(8^n²)
 * space:
 *  O(n^2)
 */
public class KnightTour {
    // Choices
    /**
     * row = 2 , col = 1  goes two up and 1 right for example.
     * row = -2, col = -1 goes two down and 1 to the left.
     */
    private static int[] pathRow = {2, 1, -1, -2, -2, -1,  1,  2};
    private static int[] pathCol = {1, 2,  2,  1, -1, -2, -2, -1};

    public static void main(String[] args) {
        int[][] board = {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
        };

        knightTour(board);
    }

    private static void knightTour(int[][]board) {
        if(knightTour(board, 0, 0, 0)){
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    System.out.print(" "+board[i][j]+" ");
                }
                System.out.println();
            }
        } else {
            System.out.println("error");
        }
    }

    private static boolean knightTour(int[][] board, int row, int col, int step) {
        if (step == 63) return true;

        for (int i = 0; i < pathRow.length; i++) {
            int newRow = row + pathRow[i];
            int newCol = col + pathCol[i];
            
            if (isValidMove(board, newRow, newCol)) {
                step++;
                board[newRow][newCol] = step;

                if (knightTour(board, newRow, newCol, step)) return true;

                step--;
                board[newRow][newCol] = 0;
            }
        }
        return false;
    }

    private static boolean isValidMove(int[][] board, int newRow, int newCol) {
        return  (newRow >= 0 && newRow < 8
        && newCol >= 0 && newCol < 8
        && board[newRow][newCol] == 0);
    }
}
