package com.personal.mazesolver.model;

import lombok.Data;

import java.util.List;

@Data
public class SolveResponse {
    private String algorithm;
    private List<int[]> path;        // final path
    private List<List<int[]>> steps; // step-by-step exploration
    private Statistics statistics;

    @Data
    public static class Statistics {
        private long executionTimeMs;
        private int pathLength;
        private int nodesExplored;
    }
}
