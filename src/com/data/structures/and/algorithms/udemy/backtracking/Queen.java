package com.data.structures.and.algorithms.udemy.backtracking;

/**
 * Queen can move horizontally, vertically and diagonally
 * we need to place the 4 queens in a way they don't attack
 * each other.
 *
 * choices:
 *  select the row    (4 choices)
 *  select the column (4 choices)
 * constraints:
 *  is safe to place the queen in the selected place?
 *
 * branching factor n, and recursive call n => n^n
 * T(n) = O(n²) + n * T(n-1)
 * substitution:
 * T(n) = O(n²)(O((n-2)!))
 * O(n!)
 *
 * space complexity:
 * O(n) because the board is given so we do not considerate in the analysis
 *
 */
public class Queen {
    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 0, 0},
                         {0, 0, 0, 0, 0},
                         {0, 0, 0, 0, 0},
                         {0, 0, 0, 0, 0},
                         {0, 0, 0, 0, 0}};
        saveTheQueens(board);
    }

    private static void saveTheQueens(int[][] board) {
        if (saveTheQueens(board, 0, board.length)) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    System.out.print(" "+board[i][j]+" ");
                }
                System.out.println();
            }
        } else {
            System.out.println("There is no solution for this board.");
        }
    }

    private static boolean saveTheQueens(int[][] board, int column, int length) {
        // Base case
        if (column >= length) return true;

        // Choices
        for (int row = 0; row < length; row++) {
            //constraints
            if (isSafe(board, row, column, length)) {
                board[row][column] = 1;
                // check if the next position is safe
                if (saveTheQueens(board, column + 1, length)) return true;
                // if the next position is not safe we have to backtracking
                board[row][column] = 0;
            }
        }
        return false;
    }

    private static boolean isSafe(int[][] board, int row, int column, int length) {
        int i, j;
        // is another queen present horizontally?
        for (i = 0; i < column; i++) {
            if (board[row][i] == 1 ) return false;
        }
        // is another queen present diagonally (upper)?
        for (i = row, j = column; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) return false;
        }
        // is another queen present diagonally (lower)?
        for (i = row, j = column; i < length && j >= 0; i++, j--) {
            if (board[i][j] == 1) return false;
        }
        return true;
    }


}
