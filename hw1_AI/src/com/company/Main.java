package com.company;

public class Main {
  public static void main(String[] args) {
    Shuffler shuffler = new Shuffler();
    shuffler.shuffle();

    /*
     Pass board to AI to solve
     ai.solve(shuffler.getBoard())
     */

    Solver solver = new Solver(new Coordinate(0, 0), new int[][] { {0,1,2}, {3,4,5}, {6,7,8}});
  }
}