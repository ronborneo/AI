package com.company;

import java.util.*;

public class Shuffler {
  private final int OFFSET = 5;
  private final int MAX_SHUFFLES = 16;
  private final Board board;
  private final Transformations transformations;
  public int shuffleLength = 0;
  public Shuffler(Board board) {
    this.board = board;
    this.transformations = new Transformations(board);
  }

  public void shuffle() {
    int numberOfShuffles = getRandomShuffleNumber();
    shuffleLength = numberOfShuffles;
    System.out.println("number of shuffles: " + numberOfShuffles);
    board.printBoard();
    while (numberOfShuffles > 0) {
      System.out.println("shuffle #" + numberOfShuffles);
      handleSingleShuffle();
      numberOfShuffles--;
    }
  }

  /* Number between 0..<MAX_SHUFFLES + OFFSET. In this case, 0..<16 + 5 => [5, 20] */
  private int getRandomShuffleNumber() {
    Random random = new Random();
    return (random.nextInt(MAX_SHUFFLES)) + OFFSET;
  }

  private void handleSingleShuffle() {
    Coordinate randomCoordinate = getRandomCoordinatesToSwap();
    board.swapCoordinateValues(board.freeSpace, randomCoordinate);
    board.freeSpace.setCoordinatesTo(randomCoordinate);
    board.printBoard();
  }

  private Coordinate getRandomCoordinatesToSwap() {
    ArrayList<Coordinate> possibleTransformations = transformations.getPossibleTransformations();
    int randomIndex = getRandomIndex(possibleTransformations.size());
    Coordinate transformation = possibleTransformations.get(randomIndex);
    return new Coordinate(board.freeSpace.x + transformation.x, board.freeSpace.y + transformation.y);
  }

  private int getRandomIndex(int listSize) {
    Random random = new Random();
    return random.nextInt(listSize);
  }
}