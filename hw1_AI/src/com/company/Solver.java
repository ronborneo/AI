package com.company;

import java.util.*;

public class Solver {
  private Board board;

  public Solver(Board board) {
    this.board = board;
    Heuristic heuristic = new Heuristic(board, goalState);
    System.out.println("heuristic value: " + heuristic.getHeuristicValue());
  }

  private final int[][] goalState = new int[][] {
    { 0, 1, 2 },
    { 3, 4, 5 },
    { 6, 7, 8 }
  };

  private void aStarSearch() {
    Node node = new Node(board, 0);
    Comparator<Node> byPathCost = (Node node1, Node node2)-> node1.compareTo(node2);
    PriorityQueue<Node> frontier = new PriorityQueue<>(byPathCost);
    frontier.add(node);
    HashSet<Node> explored = new HashSet<>();
    //while (!frontier.isEmpty()) {
    //  Node currentNode = frontier.poll();
      // if
    //}

  }


}
