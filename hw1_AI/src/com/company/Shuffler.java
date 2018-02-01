package com.company;

import java.util.*;

public class Shuffler {
  private final int MIN_SHUFFLE = 5;
  private final int MAX_SHUFFLE = 15;
  private Board board;

  public Shuffler(Board board) {
    this.board = board;
  }

  private final Coordinate[] COORDINATE_TRANSFORMATIONS = new Coordinate[]{
          new Coordinate(-1, 0),
          new Coordinate(1, 0),
          new Coordinate(0, 1),
          new Coordinate(0, -1)
  };

  public Board getBoard() {
    return board;
  }

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
    swapCoordinateValues(board.freeSpace, randomCoordinate);
    board.freeSpace.setCoordinatesTo(randomCoordinate);
    printBoard();
  }

  private Coordinate getRandomCoordinatesToSwap() {
    ArrayList<Coordinate> possibleTransformations = getPossibleTransformations();
    int randomIndex = getRandomIndex(possibleTransformations.size());
    Coordinate transformation = possibleTransformations.get(randomIndex);
    return new Coordinate(board.freeSpace.x + transformation.x, board.freeSpace.y + transformation.y);
  }

  private void swapCoordinateValues(Coordinate c1, Coordinate c2) {
    int temp = board.board[c1.x][c1.y];
    board.board[c1.x][c1.y] = board.board[c2.x][c2.y];
    board.board[c2.x][c2.y] = temp;
  }

  private ArrayList<Coordinate> getPossibleTransformations() {
    ArrayList<Coordinate> coordinates = new ArrayList<>();
    Arrays.stream(COORDINATE_TRANSFORMATIONS).filter(transformation -> isValidTransformation(transformation)).
            forEach(item -> coordinates.add(item));
    return coordinates;
  }

  private boolean isValidTransformation(Coordinate transformation) {
    return board.freeSpace.x + transformation.x >= 0 && board.freeSpace.x + transformation.x <= 2
            && board.freeSpace.y + transformation.y >= 0 && board.freeSpace.y + transformation.y <= 2;
  }

  private int getRandomIndex(int listSize) {
    Random random = new Random();
    return random.nextInt(listSize);
  }

  private void printBoard() {
    System.out.println("+board+");
    Arrays.stream(board.board).forEach(row -> printRow(row));
    System.out.println("+-----+");
  }

  private void printRow(int[] row) {
    Arrays.stream(row).forEach(number -> System.out.print("|" + number));
    System.out.println("|");
  }
}