package com.personal.mazesolver.model;

import lombok.Data;

@Data
public class SolveRequest {
    private Maze maze;
    private String algorithm;
    private boolean stepByStep = false;
}
