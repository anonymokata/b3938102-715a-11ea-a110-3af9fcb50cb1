package com.github.jmgorton.wordsearch;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.github.jmgorton.wordsearch.model.Coord;
import com.github.jmgorton.wordsearch.model.PuzzleElement;

// import java.util.Scanner;

public class PuzzleSolver extends PuzzleReader {

  // instance variables

  Method[] finderMethods;  
  List<String> yetToFind;

  // constructors

  public PuzzleSolver() {
    super();
    collectFinderMethods();
  }

  public PuzzleSolver(String filePath) {
    super(filePath);
    collectFinderMethods();
  }

  public PuzzleSolver(File f) {
    super(f);
    collectFinderMethods();
  }

  // instance methods

  public Boolean search() {

    // this copies values
    this.yetToFind = new ArrayList<String>(this.puzzle.hiddenWords);
    // this copies references
    // this.yetToFind = this.puzzle.hiddenWords;

    for (PuzzleElement lineReset = this.puzzle.topLeftCorner; lineReset != null; lineReset = lineReset.below) {

      // loops no longer use assumption that puzzle is square, or even rectangular
      // but do assume that all rows are left-aligned
      // note: other places in code still assume puzzle is square, e.g. singular puzzle.size value
      for (PuzzleElement cursor = lineReset; cursor != null; cursor = cursor.toRight) {

        checkElement(cursor);
        if (yetToFind.size() == 0) return true;
      }

    }

    return false;
  }

  // finder-caller

  private void checkElement(PuzzleElement start) {

    Iterator<String> it = this.yetToFind.iterator();
    String word;
    WORDCYCLE:
    while (it.hasNext()) {
      word = it.next();

      // invoke the methods we collected earlier
      for (Method m : this.finderMethods) {
        if (m != null) {

          try {
            Object result = m.invoke(this, start, word);

            // if result is null this doesn't enter so we're ok
            if (result instanceof Boolean) {
              Boolean boolRes = (Boolean) result;
              if (boolRes.booleanValue()) {
                it.remove();
                continue WORDCYCLE;
              }
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

    }

  }

  // finders

  // better to reuse more of this finders code ..? between methods, only changing
  // starting point check, coordinate creation, and puzzle element iteration assignments
  @SuppressWarnings("unused")
  private Boolean findWordVert(PuzzleElement start, String word) {

    // if our starting point does not provide us enough room to even find the word, return false
    if (start.location.y > this.puzzle.size - word.length()) return false;

    String wordRev = reverseWord(word);

    Boolean found, foundRev;
    found = foundRev = true;

    // leave cursor as our placeholder, then iterate in the vertical descending 
    // direction checking both word and wordRev to see if this is a match
    for (int k = 0; k < word.length(); k++) {

      if (found.booleanValue() && !start.value.equals(word.charAt(k))) {
        found = false;
      }
      if (foundRev.booleanValue() && !start.value.equals(wordRev.charAt(k))) {
        foundRev = false;
      }

      if (!found && !foundRev) {
        break;
      } else if (k == word.length() - 1) {
        // we've found a word here -- record it
        Coord[] locs = new Coord[word.length()];

        // with this method, start has been reassigned and now points to the end of
        // the word we just checked, not the beginning
        Integer i = start.location.y;
        Integer j = start.location.x;

        if (found) {
          for (int s = 0; s < word.length(); s++) {
            Coord c = new Coord(j, i - s);
            locs[word.length() - 1 - s] = c;
          }
        } else {
          for (int s = 0; s < word.length(); s++) {
            Coord c = new Coord(j, i - s);
            locs[s] = c;
          }
        }

        this.puzzle.wordLocs.put(word, locs);
        return true;

      } else if (start.below == null) {
        // this should not ever happen
        throw new RuntimeException("The element which is supposed to be checked next is null...");
      } else {
        start = start.below;
      }

    }

    return false;
  }

  @SuppressWarnings("unused")
  private Boolean findWordHoriz(PuzzleElement start, String word) {
    
    // if our starting point does not provide us enough room to even find the word, return false
    if (start.location.x > this.puzzle.size - word.length()) return false;

    String wordRev = reverseWord(word);

    Boolean found, foundRev;
    found = foundRev = true;

    // leave cursor as our placeholder, then iterate to the right horizontally
    // checking both word and wordRev to see if this is a match
    for (int k = 0; k < word.length(); k++) {

      if (found.booleanValue() && !start.value.equals(word.charAt(k))) {
        found = false;
      }
      if (foundRev.booleanValue() && !start.value.equals(wordRev.charAt(k))) {
        foundRev = false;
      }

      if (!found && !foundRev) {
        break;
      } else if (k == word.length() - 1) {
        // we've found a word here -- record it
        Coord[] locs = new Coord[word.length()];

        // with this method, start has been reassigned and now points to the end of
        // the word we just checked, not the beginning
        Integer i = start.location.y;
        Integer j = start.location.x;

        if (found) {
          for (int s = 0; s < word.length(); s++) {
            Coord c = new Coord(j - s, i);
            locs[word.length() - 1 - s] = c;
          }
        } else {
          for (int s = 0; s < word.length(); s++) {
            Coord c = new Coord(j - s, i);
            locs[s] = c;
          }
        }

        this.puzzle.wordLocs.put(word, locs);
        return true;

      } else if (start.toRight == null) {
        // this should not ever happen
        throw new RuntimeException("The element which is supposed to be checked next is null...");
      } else {
        start = start.toRight;
      }

    }

    return false;
  }

  @SuppressWarnings("unused")
  private Boolean findWordDiagDesc(PuzzleElement start, String word) {

    // if our starting point does not provide us enough room to even find the word, return false
    if (start.location.x > this.puzzle.size - word.length() 
      || start.location.y > this.puzzle.size - word.length()) return false;

    String wordRev = reverseWord(word);

    Boolean found, foundRev;
    found = foundRev = true;

    // leave cursor as our placeholder, then iterate in the diagonal descending 
    // direction checking both word and wordRev to see if this is a match
    for (int k = 0; k < word.length(); k++) {

      if (found.booleanValue() && !start.value.equals(word.charAt(k))) {
        found = false;
      }
      if (foundRev.booleanValue() && !start.value.equals(wordRev.charAt(k))) {
        foundRev = false;
      }

      if (!found && !foundRev) {
        break;
      } else if (k == word.length() - 1) {
        // we've found a word here -- record it
        Coord[] locs = new Coord[word.length()];

        // with this method, start has been reassigned and now points to the end of
        // the word we just checked, not the beginning
        Integer i = start.location.y;
        Integer j = start.location.x;

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

        this.puzzle.wordLocs.put(word, locs);
        return true;

      } else if (start.belowToRight == null) {
        // this should not ever happen
        throw new RuntimeException("The element which is supposed to be checked next is null...");
      } else {
        start = start.belowToRight;
      }

    }

    return false;
  }

  @SuppressWarnings("unused")
  private Boolean findWordDiagAsc(PuzzleElement start, String word) {

    // if our starting point does not provide us enough room to even find the word, return false
    if (start.location.x > this.puzzle.size - word.length() 
      || start.location.y < word.length() - 1) return false;

    String wordRev = reverseWord(word);

    Boolean found, foundRev;
    found = foundRev = true;

    // leave cursor as our placeholder, then iterate in the diagonal descending 
    // direction checking both word and wordRev to see if this is a match
    for (int k = 0; k < word.length(); k++) {

      if (found.booleanValue() && !start.value.equals(word.charAt(k))) {
        found = false;
      }
      if (foundRev.booleanValue() && !start.value.equals(wordRev.charAt(k))) {
        foundRev = false;
      }

      if (!found && !foundRev) {
        break;
      } else if (k == word.length() - 1) {
        // we've found a word here -- record it
        Coord[] locs = new Coord[word.length()];

        // with this method, start has been reassigned and now points to the end of
        // the word we just checked, not the beginning
        Integer i = start.location.y;
        Integer j = start.location.x;

        if (found) {
          for (int s = 0; s < word.length(); s++) {
            Coord c = new Coord(j - s, i + s);
            locs[word.length() - 1 - s] = c;
          }
        } else {
          for (int s = 0; s < word.length(); s++) {
            Coord c = new Coord(j - s, i + s);
            locs[s] = c;
          }
        }

        this.puzzle.wordLocs.put(word, locs);
        return true;

      } else if (start.aboveToRight == null) {
        // this should not ever happen
        throw new RuntimeException("The element which is supposed to be checked next is null...");
      } else {
        start = start.aboveToRight;
      }

    }

    return false;
  }

  public void printWordLocations() {
    for (String s : this.puzzle.wordLocs.keySet()) {
      Coord[] locs = this.puzzle.wordLocs.get(s);
      System.out.print(s + ": ");
      StringBuilder toPrint = new StringBuilder("");
      for (Coord c : locs) {
        toPrint.append(c.toString()).append(",");
      }
      toPrint.deleteCharAt(toPrint.length() - 1);
      System.out.println(toPrint);
    }
  }

  // private helper methods

  private void collectFinderMethods() {
    this.finderMethods = this.getClass().getDeclaredMethods();

    Integer notNull = 0;
    // we're going to execute all the methods that start with "findWord"
    // and get rid of the null elements ... just cause it's nicer
    for (int i = 0; i < this.finderMethods.length; i++) {
      Method m = this.finderMethods[i];
      if (!m.getName().startsWith("findWord")/* || m.getName().length() < 9*/) {
        this.finderMethods[i] = null;
      } else {
        notNull++;
      }
    }

    // make the array with no null elements
    Method[] copy = new Method[notNull];
    Integer cursor = 0;
    for (Method m : this.finderMethods) {
      if (m != null) {
        copy[cursor++] = m;
      }
    }

    // reassign to our new array
    this.finderMethods = copy;
  }

  private String reverseWord(String word) {
    if (word.length() < 2) return word;
    return word.charAt(word.length() - 1) 
    + reverseWord(word.substring(1, word.length() - 1)) 
    + word.charAt(0);
  }

  public static void main(String[] args) {

    String path = System.getProperty("user.dir");
    path += "/puzzles/Puzzle1b.txt";
    PuzzleSolver ps = new PuzzleSolver(path);

    // if (args != null && args.length > 0) {
    //   if (args.length > 1) {
    //     System.err.println("Invalid Usage: ...");
    //     System.exit(1);
    //     return;
    //   } else {
    //     File input = new File(args[0]);
    //     if (input.exists()) {
    //       ps = new PuzzleSolver(input);
    //     } else {
    //       System.err.println("No such file. ...");
    //       System.exit(2);
    //       return;
    //     }
    //   }
    // } else {
    //   System.out.println("no argument supplied -- using System.in");
    //   ps = new PuzzleSolver();
    // }

    try {
      ps.readPuzzle();
    } catch (Exception e) {
      e.printStackTrace();
    }

    ps.search();

  }
}
