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
    assertNull(pr.puzzle);
  }

  @Test
  public void readValidPuzzleCheckPuzzle() {
    StringBuilder s = new StringBuilder(System.getProperty("user.dir"));
    s.append("/puzzles/Puzzle1a.txt");

    PuzzleReader pr = new PuzzleReader(s.toString());

    pr.readPuzzle();

    assertNotNull(pr);
    assertNotNull(pr.puzzle);
    assertNotNull(pr.puzzle.topLeftCorner);
    assertEquals(pr.puzzle.topLeftCorner.value, Character.valueOf('Y'));
  }
}
