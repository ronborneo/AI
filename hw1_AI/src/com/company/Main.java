package com.company;

public class Main {
  public static void main(String[] args) {
    Board board = new Board();
    Shuffler shuffler = new Shuffler(board);
    shuffler.shuffle();

    System.out.println("shuffle done, attempting to solve...");
    Solver solver = new Solver(board);
    Node correctNode = solver.aStarSearch();

    if (correctNode != null) {
      System.out.println("** Goal state achieved **");
      correctNode.boardState.printBoard();
    }  else {
      System.out.println("error: no solution.");
    }

    System.out.println("Solution length: " + solver.solutionLength
            + "\nShuffle length: " + shuffler.shuffleLength);
  }
}