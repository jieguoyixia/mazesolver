package com.personal.mazesolver.service;

import com.personal.mazesolver.algorithm.BFS;
import com.personal.mazesolver.algorithm.DFS;
import com.personal.mazesolver.algorithm.Dijkstra;
import com.personal.mazesolver.model.Maze;
import com.personal.mazesolver.model.SolveRequest;
import com.personal.mazesolver.model.SolveResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MazeSolverService {
    private BFS bfs = new BFS();
    private DFS dfs = new DFS();
    private Dijkstra dijkstra = new Dijkstra();

    public SolveResponse solveMaze(SolveRequest request) {
        Maze maze = request.getMaze();
        SolveResponse response = new SolveResponse();
        SolveResponse.Statistics stats = new SolveResponse.Statistics();
        String algorithm = request.getAlgorithm();
        List<int[]> path = new ArrayList<>();
        List<List<int[]>> steps = new ArrayList<>();

        long startTime = System.currentTimeMillis();

        switch (algorithm.toUpperCase()) {
            case "BFS":
                path = bfs.solve(maze);
                steps = bfs.getSteps();
                break;
            case "DFS":
                path = dfs.solve(maze);
                steps = dfs.getSteps();
                break;
            case "DIJKSTRA":
                path = dijkstra.solve(maze);
                steps = dijkstra.getSteps();
                break;
        }

        long endTime = System.currentTimeMillis();

        stats.setExecutionTimeMs(endTime - startTime);
        stats.setPathLength(path.size());
        stats.setNodesExplored(steps.size());

        response.setAlgorithm(algorithm);
        response.setPath(path);
        response.setSteps(steps);
        response.setStatistics(stats);

        return response;
    }
}