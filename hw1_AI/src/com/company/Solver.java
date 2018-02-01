package com.company;

import java.util.*;

public class Solver {
  private Board board;
  private HashMap<Integer, Coordinate> correctTileLocations;

  public Solver(Board board) {
    this.board = board;
    this.correctTileLocations = getCorrectTileLocations();

    Heuristic heuristic = new Heuristic(correctTileLocations, board, goalState);
    System.out.println(heuristic.getHeuristicValue());
  }

  private final int[][] goalState = new int[][] {
    { 0, 1, 2 },
    { 3, 4, 5 },
    { 6, 7, 8 }
  };

  private HashMap<Integer, Coordinate> getCorrectTileLocations() {
    HashMap<Integer, Coordinate> tileLocations = new HashMap<>();
    tileLocations.put(0, new Coordinate(0, 0));
    tileLocations.put(1, new Coordinate(0, 1));
    tileLocations.put(2, new Coordinate(0, 2));
    tileLocations.put(3, new Coordinate(1, 0));
    tileLocations.put(4, new Coordinate(1, 1));
    tileLocations.put(5, new Coordinate(1, 2));
    tileLocations.put(6, new Coordinate(2, 0));
    tileLocations.put(7, new Coordinate(2, 1));
    tileLocations.put(8, new Coordinate(2, 2));
    return tileLocations;
  }

  private void aStarSearch() {
    Node node = new Node(board, 0);
    Comparator<Node> byPathCost = (Node node1, Node node2)-> node1.compareTo(node2);
    PriorityQueue<Node> frontier = new PriorityQueue<>(byPathCost);
    frontier.add(node);
    HashSet<Node> explored = new HashSet<>();
    while (!frontier.isEmpty()) {
      Node currentNode = frontier.poll();
      // if
    }

  }


}
