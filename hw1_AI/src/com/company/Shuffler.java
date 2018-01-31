package com.company;

import java.util.*;

public class Shuffler {
  private final int MIN_SHUFFLE = 5;
  private final int MAX_SHUFFLE = 15;
  private Coordinate freeSpace;
  private int[][] board;

  public Shuffler() {
    freeSpace = new Coordinate(0, 0);
    board = new int[][] {
            { 0, 1, 2 },
            { 3, 4, 5 },
            { 6, 7, 8 }
    };
  }

  private final Coordinate[] COORDINATE_TRANSFORMATIONS = new Coordinate[] {
          new Coordinate(-1, 0),
          new Coordinate(1, 0),
          new Coordinate(0, 1),
          new Coordinate(0, -1)
  };

  public int[][] getBoard() { return board; }

  public void shuffle() {
    int numberOfShuffles = getRandomShuffleNumber();
    System.out.println("number of shuffles: " + numberOfShuffles);
    printBoard();
    while (numberOfShuffles > 0) {
      System.out.println("shuffle #" + numberOfShuffles);
      handleSingleShuffle();
      numberOfShuffles--;
    }
  }

  private int getRandomShuffleNumber() {
    Random random = new Random();
    return (random.nextInt(MAX_SHUFFLE)) + MIN_SHUFFLE;
  }

  private void handleSingleShuffle() {
    Coordinate randomCoordinate = getRandomCoordinatesToSwap();
    swapCoordinateValues(freeSpace, randomCoordinate);
    freeSpace.setCoordinatesTo(randomCoordinate);
    printBoard();
  }

  private Coordinate getRandomCoordinatesToSwap() {
    ArrayList<Coordinate> possibleTransformations = getPossibleTransformations();
    int randomIndex = getRandomIndex(possibleTransformations.size());
    Coordinate transformation = possibleTransformations.get(randomIndex);
    return new Coordinate(freeSpace.x + transformation.x, freeSpace.y + transformation.y);
  }

  private void swapCoordinateValues(Coordinate c1, Coordinate c2) {
    int temp = board[c1.x][c1.y];
    board[c1.x][c1.y] = board[c2.x][c2.y];
    board[c2.x][c2.y] = temp;
  }

  private ArrayList<Coordinate> getPossibleTransformations() {
    ArrayList<Coordinate> coordinates = new ArrayList<>();
    Arrays.stream(COORDINATE_TRANSFORMATIONS).filter(transformation -> isValidTransformation(transformation)).
            forEach(item -> coordinates.add(item));
    return coordinates;
  }

  private boolean isValidTransformation(Coordinate transformation) {
    return freeSpace.x + transformation.x >= 0 && freeSpace.x + transformation.x <= 2
            && freeSpace.y + transformation.y >= 0 && freeSpace.y + transformation.y <= 2;
  }

  private int getRandomIndex(int listSize) {
    Random random = new Random();
    return random.nextInt(listSize);
  }

  private void printBoard() {
    System.out.println("+board+");
    Arrays.stream(board).forEach(row -> printRow(row));
    System.out.println("+-----+");
  }

  private void printRow(int[] row) {
    Arrays.stream(row).forEach(number -> System.out.print("|" + number));
    System.out.println("|");
  }
}
