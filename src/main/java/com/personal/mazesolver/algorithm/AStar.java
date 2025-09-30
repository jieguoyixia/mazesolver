package com.personal.mazesolver.algorithm;

import com.personal.mazesolver.model.Maze;
import java.util.*;

public class AStar implements MazeSolverAlgorithm{
    private List<List<int[]>> steps = new ArrayList<>();

    @Override
    public List<int[]> solve(Maze maze) {
        steps.clear();
        int[][] grid = maze.getGrid();
        int rows = grid.length;
        int cols = grid[0].length;

        int[] start = maze.getStart();
        int[] end = maze.getEnd();

        int[][] gScore = new int[rows][cols];
        for (int[] row : gScore) Arrays.fill(row, Integer.MAX_VALUE);

        Map<String, int[]> parent = new HashMap<>();
        PriorityQueue<int[]> openSet = new PriorityQueue<>(Comparator.comparingInt(a -> gScore[a[0]][a[1]] + heuristic(a, end)));

        gScore[start[0]][start[1]] = 0;
        openSet.offer(start);

        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!openSet.isEmpty()) {
            int[] current = openSet.poll();
            int r = current[0], c = current[1];

            // Record step for visualization
            steps.add(List.of(new int[]{r, c}));

            if (r == end[0] && c == end[1]) {
                return buildPath(parent, end);
            }

            for (int[] d : directions) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 0) {
                    int tentativeG = gScore[r][c] + 1;
                    if (tentativeG < gScore[nr][nc]) {
                        gScore[nr][nc] = tentativeG;
                        parent.put(nr + "," + nc, new int[]{r, c});
                        openSet.offer(new int[]{nr, nc});
                    }
                }
            }
        }

        return new ArrayList<>(); // no path found
    }

    private int heuristic(int[] node, int[] goal) {
        return Math.abs(node[0] - goal[0]) + Math.abs(node[1] - goal[1]);
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