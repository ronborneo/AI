package com.company;

public class Main {
  public static void main(String[] args) {
    Board board = new Board();
    Shuffler shuffler = new Shuffler(board);
    shuffler.shuffle();

    Solver solver = new Solver(board);

  }
}