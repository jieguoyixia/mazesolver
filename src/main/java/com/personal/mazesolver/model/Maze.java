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
    private int[] start;
    private int[] end;

    // Print maze
    public void printMaze() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i == start[0] && j == start[1]) {
                    System.out.print("S ");
                } else if (i == end[0] && j == end[1]) {
                    System.out.print("E ");
                } else {
                    System.out.print(grid[i][j] == 0 ? "  " : "█ ");
                }
            }
            System.out.println();
        }
    }
}
