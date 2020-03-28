package com.github.jmgorton.wordsearch;

import java.io.File;

public class App extends PuzzleSolver {


  // test ... instance of class A which extends class B calls method defined in B, but which uses
  // a method that has been overridden in A -- which version of the method is used?

  public App() {
	  super();
  }

  public App(String filePath) {
    super(filePath);
  }

  public App(File f) {
	  super(f);
  }

  public static void main(String[] args) {
    // System.out.println("App.java ...");

    // for (int i = 0; i < args.length; i++) {
    //   System.out.println(args[0]);
    // }


    PuzzleSolver ps = null;
    if (args != null && args.length > 0) {
      if (args.length > 1) {
        System.err.println("Invalid Usage: ... Too many arguments supplied");
        System.exit(1);
        // return;
      } else {
        File input = new File(args[0]);
        if (input.exists()) {
          ps = new PuzzleSolver(input);
        } else {
          System.err.println("No such file. ...");
          System.exit(2);
          // return;
        }
      }
    } else {
      System.out.println("No argument supplied ... Reading from System.in");
      ps = new PuzzleSolver();
    }

    // String path = System.getProperty("user.dir");
    // path += "/puzzles/Puzzle1a.txt";
    // path += args[0];
    // PuzzleSolver ps = new PuzzleSolver(path);
    try {
        ps.readPuzzle();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    ps.search();
    ps.printWordLocations();
  }
}
