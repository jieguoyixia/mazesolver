package com.personal.mazesolver.algorithm;

import com.personal.mazesolver.model.Maze;

import java.util.List;

public interface MazeSolverAlgorithm {
    List<int[]> solve(Maze maze);
    List<List<int[]>> getSteps();
}
