package com.personal.mazesolver.algorithm;

import com.personal.mazesolver.model.Maze;

import java.util.*;

public class Dijkstra implements MazeSolverAlgorithm{
    private List<List<int[]>> steps = new ArrayList<>();

    @Override
    public List<int[]> solve(Maze maze) {
        int[][] grid = maze.getGrid();
        int rows = grid.length;
        int cols = grid[0].length;

        int[] start = maze.getStart();
        int[] end = maze.getEnd();

        int[][] dist = new int[rows][cols];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);

        Map<String, int[]> parent = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> dist[a[0]][a[1]]));

        dist[start[0]][start[1]] = 0;
        pq.offer(start);

        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int r = current[0], c = current[1];

            // Record step
            steps.add(List.of(new int[]{r, c}));

            if (r == end[0] && c == end[1]) {
                return buildPath(parent, end);
            }

            for (int[] d : directions) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 0) {
                    int newDist = dist[r][c] + 1; // cost = 1 per step
                    if (newDist < dist[nr][nc]) {
                        dist[nr][nc] = newDist;
                        parent.put(nr + "," + nc, new int[]{r, c});
                        pq.offer(new int[]{nr, nc});
                    }
                }
            }
        }

        return new ArrayList<>(); // no path found
    }

    private List<int[]> buildPath(Map<String, int[]> parent, int[] end) {
        LinkedList<int[]> path = new LinkedList<>();
        int[] curr = end;
        while (curr != null) {
            path.addFirst(curr);
            curr = parent.get(curr[0] + "," + curr[1]);
        }
        return path;
    }

    @Override
    public List<List<int[]>> getSteps() {
        return steps;
    }
}