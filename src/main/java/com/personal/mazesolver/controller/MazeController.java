package com.personal.mazesolver.controller;

import com.personal.mazesolver.model.Maze;
import com.personal.mazesolver.service.MazeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/maze")
public class MazeController {

    private final MazeService mazeService;

    public MazeController(MazeService mazeService) {
        this.mazeService = mazeService;
    }

    @GetMapping("/generate")
    public Maze generateMaze() {
        return mazeService.generateMaze();
    }
}
