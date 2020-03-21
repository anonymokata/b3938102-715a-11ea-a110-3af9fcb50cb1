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

        assertNull(a.puzzle);
    }

}