package com.github.jmgorton.wordsearch;

//import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void inputFilePath()
    {
	System.out.println("inputFilePath test");
	App app = new App("/puzzles/Puzzle1a.txt");
	//	assertEquals(app.filePath, "/puzzles/Puzzle1a.txt");
	System.out.println(app == null ? "null app" : "not null app");
	if (app != null) {
	    System.out.println(app.input);
	    System.out.println(app.file);
	    System.out.println(app.filePath);
	}
	System.out.println(System.in);
	assertEquals("/puzzles/Puzzle1a.txt", app.filePath);
	//	assertNotEquals(app.input, System.in);
    }

    @Test
    public void nullFilePath()
    {
	App app = new App(null);
	assertNull(app.filePath);
    }

    @Test
    public void invalidFilePath()
    {
	App app = new App("/foo/bar/baz");
	assertEquals(app.input, System.in);
    }
}
