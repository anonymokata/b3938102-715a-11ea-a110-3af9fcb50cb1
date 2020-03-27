package com.github.jmgorton.wordsearch;

import java.io.*;
import java.util.Scanner;

import com.github.jmgorton.wordsearch.model.Coord;
import com.github.jmgorton.wordsearch.model.Puzzle;
import com.github.jmgorton.wordsearch.model.PuzzleElement;

public class PuzzleReader {

  // instance variables

  public String filePath;
  public File file;
  public InputStream input;

  public Puzzle puzzle;

  // constructors

  public PuzzleReader() {
    this.filePath = null;
    this.file = null;
    this.input = System.in;

    this.puzzle = new Puzzle();
  }

  public PuzzleReader(String filePath) {
    this.filePath = filePath;
    this.puzzle = new Puzzle();
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

  // public instance methods

  public void readPuzzle() throws Exception {

    Scanner s = new Scanner(this.input);

    if (s.hasNextLine()) {
      String toParse = s.nextLine();
      String[] wordsToFind = toParse.split(",");
      for (String str : wordsToFind) {
        // System.out.println(str);
        this.puzzle.hiddenWords.add(str);
      }
    }

    // for maintaining coordinates
    Integer row = 0;

    PuzzleElement firstColRowAboveElement = null;
    PuzzleElement firstColThisRowElement = null;
    PuzzleElement toLeftElement = null;
    // if efficiency was a main goal, we would probably handle the first line separately
    // so we could eliminate if-blocks if there are many rows to iterate over
    // readability and maintainability also take an impact ... we prob should
    while (s.hasNextLine()) {
      // validate next string
      String toParse = s.nextLine();
      toParse = toParse.trim();
      if (toParse.isEmpty()) continue;

      // get a String array of the letters to compose the elements of this puzzle
      String[] thisRowStrings = toParse.split(",");
      Character[] thisRowLetters = convertStoC(thisRowStrings);

      // set size of puzzle
      this.puzzle.size = thisRowLetters.length;

      // handle the first element to be inserted in this row ...
      Character firstColCharacter = thisRowLetters[0];
      // update "special" elements upon moving to new row
      toLeftElement = null;
      firstColRowAboveElement = firstColThisRowElement;
      firstColThisRowElement = new PuzzleElement(firstColCharacter, toLeftElement, firstColRowAboveElement);

      // if this is the first row, set the first element as this puzzle's top left corner
      if (firstColRowAboveElement == null && toLeftElement == null) {
        this.puzzle.topLeftCorner = firstColThisRowElement;
        Coord origin = new Coord(0, 0);
        this.puzzle.topLeftCorner.setCoords(origin);
      } else {
        Coord firstInRow = new Coord(0, row);
        firstColThisRowElement.setCoords(firstInRow);
      }
      
      // prepare to begin appending elements to this row, via first element
      toLeftElement = firstColThisRowElement;

      for (int i = 1; i < thisRowLetters.length; i++) {
        // append each new element, set it's coordinates, and update the "cursor"
        PuzzleElement thisElement = new PuzzleElement(thisRowLetters[i], toLeftElement);
        Coord loc = new Coord(i, row);
        thisElement.setCoords(loc);
        toLeftElement = thisElement;

        // set topRightCorner and bottomLeftCorner elements
        if (row == 0 && i == this.puzzle.size - 1) {
          this.puzzle.topRightCorner = thisElement;
        } else if (row == this.puzzle.size - 1 && i == 0) {
          this.puzzle.bottomLeftCorner = thisElement;
        }
      }

      row++;
    }

    s.close();
  }

  // private helper methods

  private static Character[] convertStoC(String[] in) {
    Character[] out = new Character[in.length];

    for (int i = 0; i < in.length; i++) {
      String temp;
      if (in[i] == null) temp = "";
      else temp = in[i].trim();

      if (temp.length() == 0) {
        out[i] = ' ';
        continue;
      }

      if (temp.length() > 1) {
        // return null or don't worry about it ??? consider and address later if it comes up
        out[i] = ' ';
        continue;
      }

      out[i] = temp.charAt(0);
    }

    return out;
  }

  public static void main(String[] args) {

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
