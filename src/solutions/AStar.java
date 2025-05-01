package solutions;

import core_algorithms.BaseSearch;
import core_algorithms.MyPriorityQueue;
import core_algorithms.Node;
import core_algorithms.Problem;
import problems.Maze;

import java.awt.*;
import java.util.*;

public class AStar extends BaseSearch<Point, String> {

    public AStar(Problem<Point, String> problem) {
        super(problem, new AStarQueue(problem));
    }
    private static class AStarQueue implements MyPriorityQueue<Point, String> {
        private final PriorityQueue<Node<Point, String>> open;
        private final Map<Point, Integer> gScore = new HashMap<>();
        private final Problem<Point, String> problem;

        public AStarQueue(Problem<Point, String> problem) {
            this.problem = problem;
            this.open = new PriorityQueue<>(Comparator.comparingInt(this::f));
        }

        private int h(Point current) {
            Point goal = problem.goalState();
            return Math.abs(current.x - goal.x) + Math.abs(current.y - goal.y);
        }

        private int f(Node<Point, String> node) {
            return node.getPathCost() + h(node.getState());
        }

        @Override
        public void add(Node<Point, String> node) {
            Point state = node.getState();
            int cost = node.getPathCost();
            if (!gScore.containsKey(state) || cost < gScore.get(state)) {
                gScore.put(state, cost);
                open.add(node);
                problem.printState(state);
                System.out.println();
            }
        }

        @Override
        public Node<Point, String> pop() {
            return open.poll();
        }

        @Override
        public boolean isEmpty() {
            return open.isEmpty();
        }
    }
        public static void main(String[] args) {

            //7x5 maze
            /*char[][] grid = {
                    {'S', '.', '.', '#', '.', '.', '.'},
                    {'.', '#', '.', '#', '.', '#', '.'},
                    {'.', '#', '.', '.', '.', '#', '.'},
                    {'.', '.', '#', '#', '.', '.', '.'},
                    {'#', '.', '#', 'G', '.', '#', '.'}
            };

            Point start = new Point(0, 0);
            Point goal = new Point(4, 3);*/

            //10x10
            /*char[][] grid = {
                    {'S', '.', '.', '.', '#', '.', '.', '#', '.', '.'},
                    {'#', '#', '#', '.', '#', '.', '#', '#', '#', '.'},
                    {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '#', '#', '#', '#', '#', '#', '.', '#', '.'},
                    {'.', '.', '.', '.', '.', '.', '#', '.', '#', '.'},
                    {'#', '#', '.', '#', '#', '.', '#', '.', '.', '.'},
                    {'.', '.', '.', '.', '#', '.', '#', '#', '#', '.'},
                    {'#', '#', '#', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '.', '#', '#', '#', '#', '.'},
                    {'#', '#', '#', '#', '.', '.', '.', '#', 'G', '.'}
            };

            Point start = new Point(0, 0);
            Point goal = new Point(9, 8);*/

            //30x15
            char[][] grid = {
                    {'S', '.', '.', '#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.', '#', '.', '#', '.', '#', '#', '#', '.', '#', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '.', '.', '#', '#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.', '.', '.', '.', '#', '#', '.', '#', '.', '.', '.', '#', '.'},
                    {'.', '.', '#', '.', '.', '.', '#', '.', '.', '.', '.', '#', '#', '.', '#', '.', '#', '#', '.', '.', '#', '.', '.', '.', '.', '#', '#', '#', '#', '#'},
                    {'#', '#', '.', '#', '.', '.', '#', '#', '.', '#', '.', '#', '.', '#', '.', '.', '#', '.', '#', '.', '.', '.', '.', '#', '.', '#', '.', '.', '.', '#'},
                    {'.', '.', '#', '.', '.', '.', '.', '.', '.', '#', '.', '.', '.', '.', '#', '.', '#', '.', '.', '.', '#', '.', '.', '.', '.', '.', '.', '#', '.', '.'},
                    {'#', '.', '.', '#', '.', '#', '.', '.', '#', '.', '#', '.', '.', '.', '.', '.', '#', '.', '.', '#', '.', '#', '.', '.', '.', '.', '.', '.', '.', '.'},
                    {'.', '.', '#', '#', '.', '.', '.', '#', '.', '#', '#', '#', '#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '#', '#', '.', '.', '.', '#'},
                    {'#', '.', '.', '#', '#', '.', '#', '.', '#', '#', '.', '.', '.', '#', '.', '.', '.', '#', '.', '.', '#', '.', '.', '.', '.', '.', '#', '.', '.', '#'},
                    {'.', '#', '.', '.', '.', '#', '#', '.', '#', '.', '.', '.', '#', '#', '.', '.', '.', '.', '.', '#', '.', '.', '.', '.', '.', '.', '#', '.', '#', '#'},
                    {'.', '#', '.', '.', '.', '#', '.', '.', '#', '#', '.', '.', '#', '.', '#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '#', '.', '.', '#'},
                    {'#', '.', '.', '.', '.', '.', '#', '#', '#', '.', '#', '#', '.', '#', '.', '#', '#', '.', '#', '#', '.', '#', '.', '.', '#', '.', '.', '.', '#', '#'},
                    {'#', '#', '#', '.', '#', '.', '#', '#', '.', '.', '#', '#', '.', '.', '.', '#', '#', '.', '.', '.', '.', '#', '.', '.', '#', '.', '#', '.', '.', '#'},
                    {'.', '#', '.', '.', '#', '#', '.', '.', '.', '.', '#', '.', '.', '.', '#', '#', '.', '.', '.', '.', '#', '.', '.', '.', '.', '.', '.', '#', '.', '.'},
                    {'#', '#', '#', '.', '.', '#', '.', '.', '.', '#', '.', '.', '#', '.', '.', '.', '.', '.', '.', '#', '.', '#', '#', '.', '.', '#', '#', '.', '#', '.'},
                    {'#', '.', '.', '#', '.', '.', '.', '.', '#', '.', '#', '#', '#', '#', '#', '#', '.', '.', '.', '#', '#', '.', '.', '#', '.', '.', '.', '.', '#', 'G'}
            };

            Point start = new Point(0, 0);
            Point goal = new Point(14, 29);

            Maze maze = new Maze(grid, start, goal);

            BaseSearch<Point, String> search = new AStar(maze);
            search.search();
        }
}


