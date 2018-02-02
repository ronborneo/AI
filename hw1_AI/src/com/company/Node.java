package com.company;

/**
 * Created by ronborneo on 1/31/18.
 */
public class Node {
  public Board boardState;
  public int pathCost;
  public Node parentNode = null;

  public Node(Board boardState, int pathCost) {
    this.boardState = new Board(boardState);
    this.pathCost = pathCost;
  }

  public Node(Node parentNode, Board boardState, int pathCost) {
    this(boardState, pathCost);
    this.parentNode = parentNode;
  }

  public int compareTo(Node otherNode) {
    if (pathCost < otherNode.pathCost)
      return -1;
    else if (pathCost == otherNode.pathCost)
      return 0;
    else
      return 1;
  }

  public boolean isGoalState(int[][] goalState) {
    for (int i = 0; i < boardState.board.length; i++)
      for (int j = 0; j < boardState.board.length; j++)
        if (boardState.board[i][j] != goalState[i][j])
          return false;
    return true;
  }
}
