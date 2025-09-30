package com.personal.mazesolver.controller;

import com.personal.mazesolver.model.Maze;
import com.personal.mazesolver.model.SolveRequest;
import com.personal.mazesolver.model.SolveResponse;
import com.personal.mazesolver.service.MazeService;
import com.personal.mazesolver.service.MazeSolverService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/maze")
public class MazeController {

    private final MazeService mazeService;
    private final MazeSolverService solverService;

    public MazeController(MazeService mazeService, MazeSolverService solverService) {
        this.mazeService = mazeService;
        this.solverService = solverService;
    }

    @GetMapping("/generate")
    public Maze generateMaze() {
        return mazeService.generateMaze();
    }

    @PostMapping("/solve")
    public SolveResponse solveMaze(@RequestBody SolveRequest request) {
        return solverService.solveMaze(request);
    }
}
