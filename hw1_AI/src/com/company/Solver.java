package com.company;

import java.util.*;

public class Solver {
  private int [][] board;
  private Coordinate freeSpace;
  private HashMap<Integer, Coordinate> correctTileLocations;

  public Solver(Coordinate freeSpace, int[][] board) {
    this.freeSpace = freeSpace;
    this.board = board;
    this.correctTileLocations = getCorrectTileLocations();

    /* TESTING PURPOSES BELOW */
    this.freeSpace = new Coordinate(0, 1);
    System.out.println(getHeuristicValue(new int[][] {
            {1,0,2},
            {3,4,5},
            {6,7,8}
    }));
  }

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

  private void aStarSearch() {
    int[][] currentState = board;

  }

  private int getHeuristicValue(int[][] boardState) {
    int misplacedTiles = getMisplacedTiles(boardState);
    int totalManhattanDistance = getTotalManhattanDistance(boardState);
    return misplacedTiles + totalManhattanDistance;
  }

  private int getMisplacedTiles(int[][] boardState) {
    int misplacedTiles = 0;
    for (int i = 0; i < board.length; i++)
      for (int j = 0; j < boardState.length; j++)
        if (boardState[i][j] != goalState[i][j] && !freeSpace.equals(new Coordinate(i, j)))
          misplacedTiles++;
    return misplacedTiles;
  }

  private int getTotalManhattanDistance(int[][] boardState) {
    int manhattanDistanceSum = 0;
    for (int i = 0; i < boardState.length; i++)
      for (int j = 0; j < boardState.length; j++) {
        Coordinate currentCoordinate = new Coordinate(i, j);
        if (!currentCoordinate.equals(freeSpace))
          manhattanDistanceSum += getManhattanDistance(boardState, currentCoordinate);
      }
    return manhattanDistanceSum;
  }

  private int getManhattanDistance(int[][] boardState, Coordinate location) {
    int currentNum = boardState[location.x][location.y];
    Coordinate goalCoordinate = correctTileLocations.get(currentNum);
    return Math.abs(location.x - goalCoordinate.x) + Math.abs(location.y - goalCoordinate.y);
  }
}
