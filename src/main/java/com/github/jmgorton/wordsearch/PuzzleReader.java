package com.github.jmgorton.wordsearch;

import java.io.*;
import java.util.Scanner;

public class PuzzleReader {
  public String filePath;
  public File file;
  public InputStream input;

  public PuzzleReader() {
    this.filePath = null;
    this.file = null;
    this.input = System.in;
  }

  public PuzzleReader(String filePath) {
    this.filePath = filePath;
    try {
      if (this.filePath != null) {
        this.file = new File(filePath);
      }

      if (this.file != null && this.file.exists() && this.file.canRead()) {
        this.input = new FileInputStream(this.file);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (this.input == null) {
        this.input = System.in;
      }
    }
  }

  public PuzzleReader(File f) {
    this();
    if (f != null) {
      if (f.exists() && f.canRead()) {
        try {
          this.input = new FileInputStream(f);
        } catch (Exception e) {
          // or here?
          this.input = System.in;
          e.printStackTrace();
        } finally {
          // do something here?
          //		    if (this.input == null) this.input = System.in;
        }

        this.file = f;
        this.filePath = this.file.getAbsolutePath();
      }
    }
  }

  public void readPuzzle() {
    System.out.println("readPuzzle");
    Scanner s = new Scanner(this.input);
    String[] wordsToFind;

    if (s.hasNextLine()) {
      String toParse = s.nextLine();
      wordsToFind = toParse.split(",");
      for (String str : wordsToFind) {
        System.out.println(str);
      }
    }

    while (s.hasNextLine()) {
      String toParse = s.nextLine();
      System.out.println(toParse);
    }

    System.out.println("end of func readPuzzle()");

    s.close();
  }

  public static void main(String[] args) {
    System.out.println("Inside the puzzle solver!");

    PuzzleSolver ps = new PuzzleSolver();

    Scanner s = new Scanner(ps.input);

    while (s.hasNextLine()) {
      System.out.println(s.nextLine());
    }

    //	ps.is = System.in;

    //	while (ps.is.hasNextLine()) {
    //	    System.out.println(ps.is.nextLine());
    //	}

    s.close();
  }
}
