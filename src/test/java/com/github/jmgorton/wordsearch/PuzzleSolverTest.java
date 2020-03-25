package com.github.jmgorton.wordsearch;

import static org.junit.Assert.*;

import org.junit.Test;

public class PuzzleSolverTest {

    @Test
    public void defaultConstructor() {
        PuzzleReader a = new PuzzleSolver();

        assertNull(a.file);
        assertNull(a.filePath);
        assertEquals(a.input, System.in);

        assertNotNull(a.puzzle);
        assertNotNull(a.puzzle.hiddenWords);
        assertNull(a.puzzle.topLeftCorner);
    }

    @Test
    public void testSolveOneWord() {
        String path = System.getProperty("user.dir");
        path += "/puzzles/Puzzle1b.txt";
        PuzzleSolver p = new PuzzleSolver(path);
        try {
            p.readPuzzle();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        p.solve();

        assertEquals(Integer.valueOf(7), p.puzzle.locs.get(0)[0].x);
        assertEquals(Integer.valueOf(3), p.puzzle.locs.get(0)[4].x);
        assertEquals(Integer.valueOf(4), p.puzzle.locs.get(0)[0].y);
        assertEquals(Integer.valueOf(0), p.puzzle.locs.get(0)[4].y);
    }

}