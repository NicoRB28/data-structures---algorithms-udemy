package com.data.structures.and.algorithms.udemy.backtracking;

public class RatInAMaze {
    public static void main(String[] args) {
        int[][] maze = {{1, 1, 0, 0},
                        {1, 1, 0, 1},
                        {1, 1, 0, 0},
                        {0, 1, 1, 1}};
        solveTheMaze(maze);
    }

    private static void solveTheMaze(int[][] maze) {
        int n = maze.length;
        int[][] solution  = new int[n][n];
        if (solveTheMaze(maze, 0, 0, solution, n)) {
            for (int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze.length; j++) {
                    System.out.print(" " + solution[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("No path available");
        }
    }

    private static boolean solveTheMaze(int[][] maze, int x, int y, int[][] sol, int mazeLength) {
        if (x == mazeLength - 1 && y == mazeLength - 1 && maze[x][y] == 1) {
            sol[x][y] = 1;
            return true;
        }
        if (isSafe(maze, x, y, mazeLength)) {
            sol[x][y] = 1;

            // go in x direction
            if (solveTheMaze(maze, x + 1, y, sol, mazeLength)) return true;
            // go in y direction
            if (solveTheMaze(maze, x, y + 1, sol, mazeLength)) return true;

            // si no se pudo mover backtrack
            sol[x][y] = 0;
            return false;
        }
        return false;
    }

    private static boolean isSafe(int[][] maze, int x, int y, int mazeLength) {
        return (x >= 0 && x < mazeLength
                &&
                y >= 0 && y < mazeLength
                &&
                maze[x][y] == 1);
    }
}
