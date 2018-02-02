package com.company;

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
