package com.github.jmgorton.wordsearch;

import static org.junit.Assert.*;

import org.junit.Test;

public class PuzzleReaderTest {

  @Test
  public void readNullString() {
    String s = null;
    PuzzleReader pr = new PuzzleReader(s);
    assertTrue(pr.input == System.in);
  }

  @Test
  public void readValidPuzzleCheckPuzzle() {
    
  }
}
