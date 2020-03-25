package com.github.jmgorton.wordsearch;

import java.io.*;
import java.util.List;

import com.github.jmgorton.wordsearch.model.Coord;

// import java.util.Scanner;

public class PuzzleSolver extends PuzzleReader {

  // constructors

  public PuzzleSolver() {
    super();
  }

  public PuzzleSolver(String filePath) {
    super(filePath);
  }

  public PuzzleSolver(File f) {
    super(f);
  }

  // instance methods

  public Boolean solve() {

    List<String> words = this.puzzle.hiddenWords;

    for (String word : words) {
      Coord[] loc = findWord(word);
      this.puzzle.locs.add(loc);
      // if (loc == null) return false;
      // else {
      //   // print the locations according to spec
      // }
    }

    return true;
  }

  private Coord[] findWord(String word) {
    Coord[] ret = null;

    ret = findWordHoriz(word);

    return ret;
  }

  private Coord[] findWordHoriz(String word) {
    Coord[] ret = null;
    return ret;
  }

  public static void main(String[] args) {
    System.out.println("Inside the puzzle solver!");

    PuzzleSolver ps = null;

    System.out.println("instantiating ps");

    if (args != null && args.length > 0) {
      if (args.length > 1) {
        System.err.println("Invalid Usage: ...");
        System.exit(1);
        return;
      } else {
        File input = new File(args[0]);
        if (input.exists()) {
          ps = new PuzzleSolver(input);
        } else {
          System.err.println("No such file. ...");
          System.exit(2);
          return;
        }
      }
    } else {
      System.out.println("no argument supplied -- using System.in");
      ps = new PuzzleSolver();
    }

    System.out.println("reading puzzle");

    try {
      ps.readPuzzle();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("end of func");
    ///////////////////////////////////////

    //	Scanner s = new Scanner(ps.is);

    //	while (s.hasNextLine()) {
    //	    System.out.println(s.nextLine());
    //	}

    /////////////////////////////////////////

    //	ps.is = System.in;

    //	while (ps.is.hasNextLine()) {
    //	    System.out.println(ps.is.nextLine());
    //	}

  }
}
