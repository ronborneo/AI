package com.company;

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
}
