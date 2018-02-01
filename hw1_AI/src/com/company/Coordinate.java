package com.company;

/**
 * Created by ronborneo on 1/31/18.
 */
public class Coordinate {
  public int x;
  public int y;

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

  @Override
  public boolean equals(Object obj) {
    if (obj == this) { return true; }
    if (!(obj instanceof Coordinate)) { return false; }
    Coordinate otherCoordinate = (Coordinate) obj;
    return y == otherCoordinate.y && x == otherCoordinate.x;
  }
}
