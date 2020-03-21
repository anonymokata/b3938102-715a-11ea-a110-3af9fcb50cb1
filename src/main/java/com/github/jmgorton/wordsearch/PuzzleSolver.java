package com.github.jmgorton.wordsearch;

import java.io.*;

// import java.util.Scanner;

public class PuzzleSolver extends PuzzleReader {

  public PuzzleSolver() {
    super();
  }

  public PuzzleSolver(String filePath) {
    super(filePath);
  }

  public PuzzleSolver(File f) {
    super(f);
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
