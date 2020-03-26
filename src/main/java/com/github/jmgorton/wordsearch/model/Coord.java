package com.github.jmgorton.wordsearch.model;

public class Coord {
  public Integer x, y;

  // TODO put protections on this to reject negative numbers?
  public Coord(Integer x, Integer y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public String toString() {
    return "[" + x + ", " + y + "]";
  }

  public static void main(String[] args) {
    
  }
}
