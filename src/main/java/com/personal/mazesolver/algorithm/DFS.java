package com.personal.mazesolver.algorithm;

import com.personal.mazesolver.model.Maze;
import java.util.ArrayList;
import java.util.List;

public class DFS implements MazeSolverAlgorithm{
    private List<List<int[]>> steps = new ArrayList<>();
    private List<int[]> path = new ArrayList<>();
    private boolean found = false;

    @Override
    public List<int[]> solve(Maze maze) {
        steps.clear();
        int[][] grid = maze.getGrid();
        int rows = grid.length;
        int cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];
        int[] start = maze.getStart();
        int[] end = maze.getEnd();

        dfs(grid, start[0], start[1], end, visited, new ArrayList<>());

        return path;
    }

    private void dfs(int[][] grid, int r, int c, int[] end,
                     boolean[][] visited, List<int[]> currentPath) {

        // Boundary & wall checks
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length) return;
        if (grid[r][c] == 1 || visited[r][c]) return;
        if (found) return; // stop if path already found

        visited[r][c] = true;
        currentPath.add(new int[]{r, c});

        // Record step for visualization
        steps.add(new ArrayList<>(currentPath));

        // If it reached the end
        if (r == end[0] && c == end[1]) {
            path = new ArrayList<>(currentPath);
            found = true;
            return;
        }

        // Explore neighbors (up, down, left, right)
        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        for (int[] dir : directions) {
            dfs(grid, r + dir[0], c + dir[1], end, visited, currentPath);
        }

        // Backtrack
        currentPath.remove(currentPath.size() - 1);
    }

    @Override
    public List<List<int[]>> getSteps() {
        return steps;
    }
}