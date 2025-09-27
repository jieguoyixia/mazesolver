package com.personal.mazesolver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//noun represent maze
//Store the state of the maze
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Maze {
    private int rows;
    private int columns;
    private int[][] grid;
}