package problems;

import core_algorithms.Problem;
import core_algorithms.Tuple;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Maze implements Problem<Point, String> {
    private final char[][] grid;
    private final Point start;
    private final Point goal;

    public Maze(char[][] grid, Point start, Point goal) {
        this.grid = grid;
        this.start = start;
        this.goal = goal;
    }

    @Override
    public Point initialState() {
        return start;
    }

    @Override
    public Point goalState() {
        return goal;
    }

    @Override
    public List<Tuple<Point, String>> execution(Point state) {
        List<Tuple<Point, String>> successors = new ArrayList<>();
        int[][] directions = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}
        };
        String[] dirNames = {"UP", "DOWN", "LEFT", "RIGHT"};

        for (int i = 0; i < directions.length; i++) {
            int newX = state.x + directions[i][0];
            int newY = state.y + directions[i][1];
            if (isValid(newX, newY)) {
                Point newState = new Point(newX, newY);
                successors.add(new Tuple<>(newState, dirNames[i], 1));
            }
        }

        return successors;
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < grid.length &&
                y >= 0 && y < grid[0].length &&
                grid[x][y] != '#';
    }

    @Override
    public void printState(Point state) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                Point p = new Point(i, j);
                if (p.equals(state)) {
                    System.out.print("* ");
                } else {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

}
