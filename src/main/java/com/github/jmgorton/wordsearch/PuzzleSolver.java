package com.github.jmgorton.wordsearch;

import java.io.*;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.github.jmgorton.wordsearch.model.Coord;
import com.github.jmgorton.wordsearch.model.PuzzleElement;

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
      // this.puzzle.locs.add(loc);

      // if (loc == null) return false;
      // else {
      //   // print the locations according to spec
      // }
    }

    return true;
  }

  private Coord[] findWord(String word) {
    System.out.println("Attempting to find \"" + word + "\"...");
    Coord[] ret = null;

    Method[] methods = this.getClass().getDeclaredMethods();
    List<Method> methodsList = Arrays.asList(methods);

    // throws UnsupportedOperationException: remove
    // Iterator<Method> it = methodsList.iterator();
    // while (it.hasNext()) {
    //   Method m = it.next();
    //   if (!m.getName().startsWith("findWord") || m.getName().length() < 9) {
    //     it.remove();
    //   }
    // }

    // alternate B
    // for (Method m : methods) {
    //   if (!m.getName().startsWith("findWord") || m.getName().length() < 9) {
    //     m = null;
    //   }
    // }

    // alternate C -- this is the one that we're going with
    for (int i = 0; i < methods.length; i++) {
      Method m = methods[i];
      if (!m.getName().startsWith("findWord") || m.getName().length() < 9) {
        methods[i] = null;
      }
    }


    // check output -- to invoke methods later
    for (Method m : methodsList) {
      if (m != null) {
        // System.out.println(m.getName());

        try {
          System.out.println("trying to invoke method " + m.getName() + "() on \'" + word + "\'");
          Object result = m.invoke(this, word);

          if (result instanceof Coord[] && result != null) {
            // System.out.println("Adding result object [" + result.toString() + "] to puzzle.locs");
            // this.puzzle.locs.add((Coord[]) result);
            System.out.println("Returning result object [" + result.toString() + "] to caller.");
            return (Coord[]) result;
          }
        } catch (Exception ex) {
          if (ex instanceof IllegalAccessException) {

          } else if (ex instanceof IllegalArgumentException) {

          } else {

          }
        } finally {

        }

      }

    }

    // alternate B -- either of these methods will work
    // for (Method m : methods) {
    //   if (m != null) {
    //     System.out.println(m.getName());
    //   }
    // }

    // ret = findWordHoriz(word);

    return ret;
  }

  @SuppressWarnings("unused")
  private Coord[] findWordHoriz(String word) {
    Coord[] ret = null;
    return ret;
  }

  @SuppressWarnings("unused")
  private Coord[] findWordDiagDesc(String word) {
    // Coord[] ret = null;
    String wordRev = reverseWord(word);

    // System.out.println(word);
    // System.out.println(wordRev);

    PuzzleElement cursor = this.puzzle.topLeftCorner;
    Integer calcIts = this.puzzle.size - word.length() + 1;
    for (int i = 0; i < calcIts; i++) {
      // again uses assumption that puzzle will be square
      for (int j = 0; j < calcIts; j++) {

        // leave cursor as our placeholder, then iterate in the diagonal descending 
        // direction checking both word and wordRev to see if this is a match
        PuzzleElement checkIfFound = cursor;
        Boolean found, foundRev;
        found = foundRev = true;
        for (int k = 0; k < word.length(); k++) {

          if (found.booleanValue() && !checkIfFound.value.equals(word.charAt(k))) {
            found = false;
          }
          if (foundRev.booleanValue() && !checkIfFound.value.equals(wordRev.charAt(k))) {
            foundRev = false;
          }

          if (!found && !foundRev) {
            break;
          } else if (k == word.length() - 1) {
            // we've found a word here -- record it
            Coord[] locs = new Coord[word.length()];

            if (found) {
              for (int s = 0; s < word.length(); s++) {
                Coord c = new Coord(j - s, i - s);
                locs[word.length() - 1 - s] = c;
              }
            } else {
              for (int s = 0; s < word.length(); s++) {
                Coord c = new Coord(j - s, i - s);
                locs[s] = c;
              }
            }

            // return locs;
            this.puzzle.locs.add(locs);

          } else if (checkIfFound.belowToRight == null) {
            // this should not happen
            throw new RuntimeException("The next element to be checked is null...");
          } else {
            checkIfFound = checkIfFound.belowToRight;
          }

        }

      }
    }

    // return ret;
    return null;
  }

  private String reverseWord(String word) {
    // System.out.println(word);
    if (word.length() < 2) return word;
    return word.charAt(word.length() - 1) 
    + reverseWord(word.substring(1, word.length() - 1)) 
    + word.charAt(0);
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
