package com.company;

import java.util.*;

/**
 * Created by ronborneo on 1/31/18.
 */
public class Heuristic {
  private final HashMap<Integer, Coordinate> correctTileLocations;
  private final int[][] goalState;
  private final Board boardState;
  private final int BOARD_LENGTH = 3;

  public Heuristic(HashMap<Integer, Coordinate> correctTileLocations, Board boardState, int[][] goalState) {
    this.correctTileLocations = correctTileLocations;
    this.goalState = goalState;
    this.boardState = boardState;
  }

  public int getHeuristicValue() {
    int misplacedTiles = getMisplacedTiles();
    int totalManhattanDistance = getTotalManhattanDistance();
    System.out.println("misplaced: " + misplacedTiles + " manhattan: " + totalManhattanDistance);
    return misplacedTiles + totalManhattanDistance;
  }

  private int getMisplacedTiles() {
    int misplacedTiles = 0;
    for (int i = 0; i < BOARD_LENGTH; i++)
      for (int j = 0; j < BOARD_LENGTH; j++)
        if (boardState.board[i][j] != goalState[i][j] && !boardState.freeSpace.equals(new Coordinate(i, j)))
          misplacedTiles++;
    return misplacedTiles;
  }

  private int getTotalManhattanDistance() {
    int manhattanDistanceSum = 0;
    for (int i = 0; i < BOARD_LENGTH; i++)
      for (int j = 0; j < BOARD_LENGTH; j++) {
        Coordinate currentCoordinate = new Coordinate(i, j);
        if (!currentCoordinate.equals(boardState.freeSpace))
          manhattanDistanceSum += getManhattanDistance(currentCoordinate);
      }
    return manhattanDistanceSum;
  }

  private int getManhattanDistance(Coordinate location) {
    int currentNum = boardState.board[location.x][location.y];
    Coordinate goalCoordinate = correctTileLocations.get(currentNum);
    return Math.abs(location.x - goalCoordinate.x) + Math.abs(location.y - goalCoordinate.y);
  }

}
