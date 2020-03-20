package com.github.jmgorton.wordsearch;

import static org.junit.Assert.*;

import java.io.*;

import org.junit.Test;


public class AppTest {

  @Test
  public void validInputFilePathCheckFilePath() {
    App app = new App("/puzzles/Puzzle1a.txt");
    assertEquals("/puzzles/Puzzle1a.txt", app.filePath);
  }

  @Test
  public void validInputFilePathCheckFile() {
    App app = new App("/puzzles/Puzzle1a.txt");
    assertEquals(new File("/puzzles/Puzzle1a.txt"), app.file);
  }

  @Test
  public void validInputFilePathCheckInput() {
    App app = new App("/puzzles/Puzzle1a.txt");
    assertEquals(System.in, app.input);
  }

  @Test
  public void validInputFileCheckFilePath() {
    // System.out.println(System.getProperty("user.dir"));
    String path = System.getProperty("user.dir");
    File f = new File(path + "/puzzles/Puzzle1a.txt");
    App app = new App(f);
    assertEquals(path + "/puzzles/Puzzle1a.txt", app.filePath);
  }

  @Test
  public void validInputFileCheckFile() {
    String path = System.getProperty("user.dir");
    File f = new File(path + "/puzzles/Puzzle1a.txt");
    App app = new App(f);
    assertEquals(new File(path + "/puzzles/Puzzle1a.txt"), app.file);
  }

//   @Test
//   public void validInputFileCheckInput() {
//     String path = System.getProperty("user.dir");
//     File f = new File(path + "/puzzles/Puzzle1a.txt");
//     App app = new App(f);
//     try {
//       assertEquals(new FileInputStream(f), app.input);
//     } catch (Exception e) {
//       assertTrue(false);
//     }
//   }

  @Test
  public void nullFilePathCheckFilePath() {
    String s = null;
    App app = new App(s);
    assertNull(app.filePath);
  }

  @Test
  public void nullFilePathCheckFile() {
    String s = null;
    App app = new App(s);
    assertNull(app.file);
  }

  @Test
  public void nullFilePathCheckInput() {
    String s = null;
    App app = new App(s);
    assertEquals(app.input, System.in);
  }

  @Test
  public void invalidFilePathCheckFilePath() {
    App app = new App("/foo/bar/baz");
    assertEquals(app.filePath, "/foo/bar/baz");
  }

  @Test
  public void invalidFilePathCheckFile() {
    App app = new App("/foo/bar/baz");
    assertEquals(app.file, new File("/foo/bar/baz"));
  }

  @Test
  public void invalidFilePathCheckInput() {
    App app = new App("/foo/bar/baz");
    assertEquals(app.input, System.in);
  }

  @Test
  public void invalidFileCheckFilePath() {
    String s = "/foo/bar/baz";
    File f = new File(s);
    App app = new App(f);
    assertEquals(app.filePath, null);
  }

  @Test
  public void invalidFileCheckFile() {
    String s = "/foo/bar/baz";
    File f = new File(s);
    App app = new App(f);
    assertEquals(app.file, null);
  }

  @Test
  public void invalidFileCheckInput() {
    String s = "/foo/bar/baz";
    File f = new File(s);
    App app = new App(f);
    assertEquals(app.input, System.in);
  }

}
