package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Solver {
  private final Board board;
  private final Transformations transformations;
  public int solutionLength = 0;
  public Solver(Board board) {
    this.board = board;
    this.transformations = new Transformations(board);
  }

  private final int[][] goalState = new int[][] {
    { 0, 1, 2 },
    { 3, 4, 5 },
    { 6, 7, 8 }
  };

  public Node aStarSearch() {
    /* ghetto test cases */
//    board.board = new int[][] {
//            {8,0,6},
//            {5,4,7},
//            {2,3,1}
//    };
//    board.freeSpace.x = 0;
//    board.freeSpace.y = 1;

    Node node = new Node(board, 0);
    Comparator<Node> byPathCost = (Node node1, Node node2) -> node1.compareTo(node2);
    PriorityQueue<Node> frontier = new PriorityQueue<>(byPathCost);
    frontier.add(node);
    HashSet<Node> explored = new HashSet<>();
    Heuristic heuristic = new Heuristic(null);

    while (!frontier.isEmpty()) {
      Node currentNode = frontier.poll();
      currentNode.boardState.printBoard();
      if (currentNode.isGoalState(goalState)) {
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
      solutionLength++;
    }

    return null;
  }

  private boolean inSet(Node node, HashSet<Node> set) {
    HashSet<Node> newSet = set.stream()
            .filter(item -> is2dArrayEquals(item.boardState.board, node.boardState.board))
            .collect(Collectors.toCollection(HashSet::new));
    return !newSet.isEmpty();
  }

  private boolean is2dArrayEquals(int[][] array1, int[][] array2) {
    for (int i = 0; i < array1.length; i++) {
      if (!(Arrays.equals(array1[i], array2[i])))
        return false;
    }
    return true;
  }
}
