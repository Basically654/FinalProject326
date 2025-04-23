package core_algorithms;



import java.util.*;

/**
 *  WARNING:
 *     1. This class will NOT work if S (i.e., the data type of states) is an array type (e.g., int[], String[], etc.)
 *     2. The data type of states must provide the correct equals method
 *
 *  An implementation of the general best-first search algorithm
 *
 *  To implement a specific search algorithm (such as BFS, DFS, etc.),
 *  extend this class and provide an implementation of the PriorityQueue
 *
 *  Type parameters:
 *      S:  the data type of states
 *      A: the data type of actions
 *  */

public class BaseSearch<S,A> {
    private final Problem<S,A> p;
    private final MyPriorityQueue<S,A> frontier;
    private final Map<S, Node<S,A>> reached = new HashMap<>();

    public BaseSearch(Problem<S, A> p, MyPriorityQueue<S, A> frontier) {
        this.p = p;
        this.frontier = frontier;
    }

    public boolean search(){
        Node <S,A> start = new Node<>(p.initialState(),null,0,null);
        frontier.add(start);
        reached.put(p.initialState(),start);
        while(!frontier.isEmpty()){
            Node<S,A> node = frontier.pop();
            if(node.getState().equals(p.goalState())){
                printPath(node);
                return true;
            }
            for(Tuple<S,A> t : p.execution(node.getState())){
                Node<S,A> child = new Node<>( t.getState(), t.getAction(),node.getPathCost()+t.getCost(),node);
                if (!reached.containsKey(child.getState()) || child.getPathCost()<reached.get(child.getState()).getPathCost()){
                    reached.put(child.getState(),child);
                    frontier.add(child);
                }
            }

        }
        return false;
    }

    public void printPath(Node<S,A> node){
        int pathCost = node.getPathCost();
        Stack<S> path = new Stack<>();
        do {
            path.add(node.getState());
            node = node.getParent();
        } while(node!=null);

        System.out.println("Path (from initial state to goal state): ");
        while(!path.isEmpty()){
            p.printState(path.pop());
            if(!path.isEmpty()) {
                System.out.println("↓");
            }
        }
        System.out.println("\nPath cost: "+pathCost);
    }
}
