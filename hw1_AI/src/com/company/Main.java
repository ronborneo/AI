package com.company;

import com.sun.xml.internal.bind.v2.runtime.Coordinator;

import java.util.*;

public class Main {

  private static class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public String toString() {
      return "x: " + x + " y: " + y;
    }

    public void setCoordinatesTo(Coordinate otherCoordinate) {
      x = otherCoordinate.x;
      y = otherCoordinate.y;
    }
  }

  private static final int MIN_SHUFFLE = 5;
  private static final int MAX_SHUFFLE = 15;
  private static Coordinate freeSpace = new Coordinate(0, 0);

  private static final Coordinate[] COORDINATE_TRANSFORMATIONS = new Coordinate[] {
          new Coordinate(-1, 0),
          new Coordinate(1, 0),
          new Coordinate(0, 1),
          new Coordinate(0, -1)
  };

  private static int[][] board = new int[][] {
          { 0, 1, 2 },
          { 3, 4, 5 },
          { 6, 7, 8 }
  };

  private static ArrayList<Coordinate> getPossibleTransformations() {
    ArrayList<Coordinate> coordinates = new ArrayList<>();
    Arrays.stream(COORDINATE_TRANSFORMATIONS).filter(transformation -> isValidTransformation(transformation)).
            forEach(item -> coordinates.add(item));
    return coordinates;
  }

  private static boolean isValidTransformation(Coordinate transformation) {
    return freeSpace.x + transformation.x >= 0 && freeSpace.x + transformation.x <= 2
            && freeSpace.y + transformation.y >= 0 && freeSpace.y + transformation.y <= 2;
  }

  private static void shuffle() {
    int numberOfShuffles = getRandomShuffleNumber();
    printBoard();
    while (numberOfShuffles > 0) {
      System.out.println("shuffle #" + numberOfShuffles);
      handleSingleShuffle();
      numberOfShuffles--;
    }
  }

  private static void handleSingleShuffle() {
    Coordinate randomCoordinate = getRandomCoordinatesToSwap();
    swapCoordinateValues(freeSpace, randomCoordinate);
    freeSpace.setCoordinatesTo(randomCoordinate);
    printBoard();
  }

  private static Coordinate getRandomCoordinatesToSwap() {
    ArrayList<Coordinate> possibleTransformations = getPossibleTransformations();
    int randomCoordinateIndex = getRandomCoordinateIndex(possibleTransformations.size());
    Coordinate slidableCoordinate = possibleTransformations.get(randomCoordinateIndex);
    return new Coordinate(freeSpace.x + slidableCoordinate.x, freeSpace.y + slidableCoordinate.y);
  }

  private static void swapCoordinateValues(Coordinate c1, Coordinate c2) {
    int temp = board[c1.x][c1.y];
    board[c1.x][c1.y] = board[c2.x][c2.y];
    board[c2.x][c2.y] = temp;
  }

  private static int getRandomShuffleNumber() {
    Random random = new Random();
    return (random.nextInt(MAX_SHUFFLE)) + MIN_SHUFFLE;
  }

  private static int getRandomCoordinateIndex(int listSize) {
    Random random = new Random();
    return random.nextInt(listSize);
  }

  private static void printBoard() {
    System.out.println("+board+");
    Arrays.stream(board).forEach(row -> printRow(row));
    System.out.println("+-----+");
  }

  private static void printRow(int[] row) {
    Arrays.stream(row).forEach(number -> System.out.print("|" + number));
    System.out.println("|");
  }

  public static void main(String[] args) {
    shuffle();
  }
}