package com.company;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by ronborneo on 1/31/18.
 */
public class Board {
  public int[][] board;
  public Coordinate freeSpace;

  public Board() {
    board = new int[][] {
            {0,1,2},
            {3,4,5},
            {6,7,8}
    };
    freeSpace = new Coordinate(0, 0);
  }

  public Board(Board boardState) {
    this.board = boardState.cloneBoard();
    this.freeSpace = new Coordinate(boardState.freeSpace);
  }

  public int[][] cloneBoard() {
    int[][] newBoard = new int[board.length][board.length];
    for (int i = 0; i < board.length; i++)
      newBoard[i] = board[i].clone();
    return newBoard;
  }

  public Board getCopyOfAppliedTransformation(Coordinate transformation) {
    Board newBoard = new Board(this);
    Coordinate transformedCoordinates = new Coordinate(freeSpace.x + transformation.x, freeSpace.y + transformation.y);
    newBoard.swapCoordinateValues(newBoard.freeSpace, transformedCoordinates);
    newBoard.freeSpace.setCoordinatesTo(transformedCoordinates);
    return newBoard;
  }

  public void swapCoordinateValues(Coordinate c1, Coordinate c2) {
    int temp = board[c1.x][c1.y];
    board[c1.x][c1.y] = board[c2.x][c2.y];
    board[c2.x][c2.y] = temp;
  }

  @Override
  public String toString() {
    StringBuilder stringToReturn = new StringBuilder();
    Arrays.stream(board).forEach(row -> toStringHelper(stringToReturn, row));
    return stringToReturn.toString();
  }

  private void toStringHelper(StringBuilder string, int[] rowToAppend) {
    Arrays.stream(rowToAppend).forEach(item -> string.append("[" + item + "]"));
    string.append("\n");
  }

  public void printBoard() {
    System.out.println("+board+");
    Arrays.stream(board).forEach(row -> printRow(row));
    System.out.println("+-----+");
  }

  private void printRow(int[] row) {
    Arrays.stream(row).forEach(number -> System.out.print("|" + number));
    System.out.println("|");
  }
}
