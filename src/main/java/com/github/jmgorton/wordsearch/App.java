package com.github.jmgorton.wordsearch;

import java.io.File;

public class App extends PuzzleSolver {

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
    System.out.println("Hello World!");

    for (int i = 0; i < args.length; i++) {
      System.out.println(args[0]);
    }

    //	PuzzleSolver ps = new PuzzleSolver();
    //	ps.main(args);

    PuzzleSolver.main(args);
  }
}
