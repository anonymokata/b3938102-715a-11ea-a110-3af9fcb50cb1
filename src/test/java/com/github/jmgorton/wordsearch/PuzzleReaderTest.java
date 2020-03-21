package com.github.jmgorton.wordsearch;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.jmgorton.wordsearch.model.*;

public class PuzzleReaderTest {

  @Test
  public void readNullString() {
    String s = null;
    PuzzleReader pr = new PuzzleReader(s);

    assertNull(pr.filePath);
    assertNull(pr.file);
    assertEquals(pr.input, System.in);
    assertNotNull(pr.puzzle);
    assertNotNull(pr.puzzle.hiddenWords);
    assertNull(pr.puzzle.topLeftCorner);
  }

  @Test
  public void readValidPuzzleCheckPuzzle() throws Exception {
    StringBuilder s = new StringBuilder(System.getProperty("user.dir"));
    s.append("/puzzles/Puzzle1a.txt");

    // String s2 = System.getProperty("user.dir");
    // s2 += "/puzzles/Puzzle1a.txt";

    PuzzleReader pr = new PuzzleReader(s.toString());

    try {
      pr.readPuzzle();
    } catch (Exception e) {
      e.printStackTrace();
    }
    // pr.readPuzzle();

    assertNotNull(pr);
    assertNotNull(pr.puzzle);
    assertNotNull(pr.puzzle.topLeftCorner);
    assertEquals(pr.puzzle.topLeftCorner.value, Character.valueOf('Y'));

    Coord zero = new Coord(0, 0);
    assertEquals(pr.puzzle.topLeftCorner.location.x, zero.x);
    assertEquals(pr.puzzle.topLeftCorner.location.y, zero.y);
  }
}
