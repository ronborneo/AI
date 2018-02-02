package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Solver {
  private final Board board;
  private final Transformations transformations;
  public Solver(Board board) {
    this.board = board;
    this.transformations = new Transformations(board);
    Heuristic heuristic = new Heuristic(board, goalState);
    System.out.println("heuristic value: " + heuristic.getHeuristicValue());
  }

  private final int[][] goalState = new int[][] {
    { 0, 1, 2 },
    { 3, 4, 5 },
    { 6, 7, 8 }
  };

  public Node aStarSearch() {
    Node node = new Node(board, 0);
    Comparator<Node> byPathCost = (Node node1, Node node2) -> node1.compareTo(node2);
    PriorityQueue<Node> frontier = new PriorityQueue<>(byPathCost);
    frontier.add(node);
    HashSet<Node> explored = new HashSet<>();
    Heuristic heuristic = new Heuristic(null);

    while (!frontier.isEmpty()) {
      Node currentNode = frontier.poll();
      System.out.println("** current state **");
      currentNode.boardState.printBoard();

      if (currentNode.isGoalState(goalState)) {
        System.out.println("** goal state **");
        return currentNode;
      }

      explored.add(currentNode);
      transformations.board = currentNode.boardState;
      ArrayList<Coordinate> possibleTransformations = transformations.getPossibleTransformations();
      for (Coordinate transformation : possibleTransformations) {
        Board newBoardState = currentNode.boardState.getCopyOfAppliedTransformation(transformation);
        heuristic.setBoardState(newBoardState);
        Node child = new Node(currentNode, newBoardState, heuristic.getHeuristicValue());
        if (!inSet(child, explored) && !frontier.contains(child)) {
          frontier.add(child);
        }
      }
    }

    return null;
  }

  private boolean inSet(Node node, HashSet<Node> set) {
    HashSet<Node> newSet = set.stream().filter(item -> item.boardState == node.boardState).collect(Collectors.toCollection(HashSet::new));
    return !newSet.isEmpty();
  }
}
