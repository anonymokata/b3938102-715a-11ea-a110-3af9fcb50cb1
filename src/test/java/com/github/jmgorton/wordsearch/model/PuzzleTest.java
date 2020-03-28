package com.github.jmgorton.wordsearch.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class PuzzleTest {

    @Test
    public void defaultConstructorTest() {
        Puzzle p = new Puzzle();

        assertNotNull(p);

        assertNull(p.topLeftCorner);
        assertNull(p.topRightCorner);
        assertNull(p.bottomLeftCorner);

        assertNotNull(p.hiddenWords);
        assertNotNull(p.wordLocs);

        assertEquals(0, p.hiddenWords.size());
        assertEquals(0, p.wordLocs.size());
    }

}
