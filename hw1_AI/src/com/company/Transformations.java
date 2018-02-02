package com.company;

import java.util.*;

/**
 * Created by ronborneo on 2/1/18.
 */
public class Transformations {
  public Board board;
  private final Coordinate[] COORDINATE_TRANSFORMATIONS = new Coordinate[]{
          new Coordinate(-1, 0),
          new Coordinate(1, 0),
          new Coordinate(0, 1),
          new Coordinate(0, -1)
  };

  public Transformations(Board board) {
    this.board = board;
  }

  public ArrayList<Coordinate> getPossibleTransformations() {
    ArrayList<Coordinate> transformations = new ArrayList<>();
    Arrays.stream(COORDINATE_TRANSFORMATIONS)
            .filter(transformation -> isValidTransformation(transformation))
            .forEach(item -> transformations.add(item));
    return transformations;
  }

  private boolean isValidTransformation(Coordinate transformation) {
    return board.freeSpace.x + transformation.x >= 0 && board.freeSpace.x + transformation.x <= 2
            && board.freeSpace.y + transformation.y >= 0 && board.freeSpace.y + transformation.y <= 2;
  }
}
