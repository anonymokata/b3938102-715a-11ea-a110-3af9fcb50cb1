package com.github.jmgorton.wordsearch.model;

public class Coord {
  public Integer x, y;

  public Coord(Integer x, Integer y) {
    if (x < 0 || y < 0) throw new RuntimeException("Invalid coordinate value(s) -- can't be negative.");
    this.x = x;
    this.y = y;
  }

  @Override
  public String toString() {
    return "(" + x + "," + y + ")";
  }

  public static void main(String[] args) {
    
  }
}
