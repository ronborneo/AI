package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Solver {
  private final int[][] goalState = new int[][] {
    { 0, 1, 2 },
    { 3, 4, 5 },
    { 6, 7, 8 }
  };
  private final Board board;
  private final Transformations transformations;
  private final PriorityQueue<Node> frontier = new PriorityQueue<>((node1, node2) -> node1.compareTo(node2));
  private final HashSet<Node> explored = new HashSet<>();
  private final Heuristic heuristic = new Heuristic(null);
  public int solutionLength = -1; /* -1 to not include original state */
  public Solver(Board board) {
    this.board = board;
    this.transformations = new Transformations(board);
  }

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
    frontier.add(node);

    while (!frontier.isEmpty()) {
      Node currentNode = frontier.poll();
      if (currentNode.isGoalState(goalState))
        return currentNode;
      explored.add(currentNode);
      transformations.board = currentNode.boardState;
      ArrayList<Coordinate> possibleTransformations = transformations.getPossibleTransformations();
      possibleTransformations.forEach(transformation -> handleSingleTransformation(transformation, currentNode));
    }
    return null;
  }

  private void handleSingleTransformation(Coordinate transformation, Node currentNode) {
    Board newBoardState = currentNode.boardState.getCopyOfAppliedTransformation(transformation);
    heuristic.setBoardState(newBoardState);
    Node child = new Node(currentNode, newBoardState, heuristic.getHeuristicValue());
    if (notInExploredOrFrontier(child))
      frontier.add(child);
  }

  private boolean notInExploredOrFrontier(Node node) {
    return (!inSet(node, explored) && !frontier.contains(node));
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

  public void printSequence(Node node) {
    if (node == null) return;
    printSequence(node.parentNode);
    node.boardState.printBoard();
    solutionLength++;
  }
}
