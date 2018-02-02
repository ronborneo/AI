package com.company;

import java.util.*;

/**
 * Created by ronborneo on 1/31/18.
 */
public class Heuristic {
  private final HashMap<Integer, Coordinate> correctTileLocations;
  private Board boardState;
  private final int BOARD_LENGTH = 3;

  private int[][] goalState = new int[][] {
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

  public Heuristic(Board boardState, int[][] goalState) {
    this(boardState);
    this.goalState = goalState;
  }

  public Heuristic(Board boardState) {
    this.correctTileLocations = getCorrectTileLocations();
    this.boardState = boardState;
  }

  public void setBoardState(Board boardState) {
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
        if (isMisplacedTile(new Coordinate(i, j)))
          misplacedTiles++;
    return misplacedTiles;
  }

  private int getTotalManhattanDistance() {
    int manhattanDistanceSum = 0;
    for (int i = 0; i < BOARD_LENGTH; i++)
      for (int j = 0; j < BOARD_LENGTH; j++) {
        Coordinate currentCoordinate = new Coordinate(i, j);
        if (!isFreeSpace(currentCoordinate))
          manhattanDistanceSum += getManhattanDistance(currentCoordinate);
      }
    return manhattanDistanceSum;
  }

  private int getManhattanDistance(Coordinate location) {
    int currentNum = boardState.board[location.x][location.y];
    Coordinate goalCoordinate = correctTileLocations.get(currentNum);
    return Math.abs(location.x - goalCoordinate.x) + Math.abs(location.y - goalCoordinate.y);
  }

  private boolean isMisplacedTile(Coordinate coordinate) {
    return boardState.board[coordinate.x][coordinate.y] != goalState[coordinate.x][coordinate.y]
            && !isFreeSpace(coordinate);
  }

  private boolean isFreeSpace(Coordinate coordinate) {
    return coordinate.equals(boardState.freeSpace);
  }

}
