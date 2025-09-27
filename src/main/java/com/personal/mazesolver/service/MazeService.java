package com.personal.mazesolver.service;

import com.personal.mazesolver.model.Maze;
import org.springframework.stereotype.Service;

import java.util.Random;

// verb logic/actions perform on maze model
@Service
public class MazeService {
    private final Random random = new Random();
    private static final int ROWS = 11;
    private static final int COLS = 11;

    // Generate a solvable maze
    public Maze generateMaze() {
        int[][] grid = new int[ROWS * 2 + 1][COLS * 2 + 1];
        boolean[][] visited = new boolean[ROWS][COLS];

        // Fill walls
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = 1;
            }
        }

        // Start and end positions
        int[] start = {1, 1};
        int[] end = {ROWS * 2 - 1, COLS * 2 - 1};

        // Carve maze using recursive backtracking
        carve(0, 0, ROWS, COLS, grid, visited);

        return new Maze(ROWS, COLS, grid, start, end);
    }

    // Recursive backtracking
    private void carve(int r, int c, int rows, int cols, int[][] grid, boolean[][] visited) {
        int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
        shuffle(directions);
        visited[r][c] = true;
        grid[r*2+1][c*2+1] = 0;

        for (int[] d : directions) {
            int nr = r + d[0], nc = c + d[1];
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && !visited[nr][nc]) {
                grid[r*2+1+d[0]][c*2+1+d[1]] = 0;
                carve(nr, nc, rows, cols, grid, visited);
            }
        }
    }

    // Shuffle directions
    private void shuffle(int[][] dirs) {
        for (int i = dirs.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int[] tmp = dirs[i];
            dirs[i] = dirs[j];
            dirs[j] = tmp;
        }
    }
}