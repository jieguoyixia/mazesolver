package com.personal.mazesolver.algorithm;

import com.personal.mazesolver.model.Maze;

import java.util.*;

public class BFS implements MazeSolverAlgorithm {
    private List<List<int[]>> steps = new ArrayList<>();
    @Override
    public List<int[]> solve(Maze maze) {
        steps.clear();

        int[][] grid = maze.getGrid();
        int rows = grid.length;
        int cols = grid[0].length;
        int[] start = maze.getStart();
        int[] end = maze.getEnd();

        boolean[][] visited = new boolean[rows][cols];
        Map<String, int[]> parent = new HashMap<>();

        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        visited[start[0]][start[1]] = true;

        // add initial step
        steps.add(List.of(new int[]{start[0], start[1]}));

        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!queue.isEmpty()) {
            List<int[]> currentLayer = new ArrayList<>();
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();

                if (Arrays.equals(current, end)) {
                    return buildPath(parent, start, end);
                }

                for (int[] dir : directions) {
                    int nr = current[0] + dir[0];
                    int nc = current[1] + dir[1];

                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols &&
                            grid[nr][nc] == 0 && !visited[nr][nc]) {

                        visited[nr][nc] = true;
                        queue.add(new int[]{nr, nc});
                        parent.put(nr + "," + nc, current);
                        currentLayer.add(new int[]{nr, nc});
                    }
                }
            }

            if (!currentLayer.isEmpty()) {
                steps.add(currentLayer);
            }
        }

        return new ArrayList<>();
    }

    private List<int[]> buildPath(Map<String, int[]> parent, int[] start, int[] end) {
        List<int[]> path = new ArrayList<>();
        int[] current = end;

        while (current != null) {
            path.add(current);
            if (Arrays.equals(current, start)) break;
            current = parent.get(current[0] + "," + current[1]);
        }

        Collections.reverse(path);
        return path;
    }

    @Override
    public List<List<int[]>> getSteps() {
        return steps;
    }
}