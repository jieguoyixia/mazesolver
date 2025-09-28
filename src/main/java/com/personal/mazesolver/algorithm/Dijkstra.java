package com.personal.mazesolver.algorithm;

import com.personal.mazesolver.model.Maze;

import java.util.ArrayList;
import java.util.List;

public class Dijkstra implements MazeSolverAlgorithm{
    private List<List<int[]>> steps = new ArrayList<>();
    @Override
    public List<int[]> solve(Maze maze) {
        steps.clear();;
        return List.of();
    }

    @Override
    public List<List<int[]>> getSteps() {
        return List.of();
    }
}
