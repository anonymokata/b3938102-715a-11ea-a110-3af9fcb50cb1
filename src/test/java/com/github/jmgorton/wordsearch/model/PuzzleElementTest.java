package com.github.jmgorton.wordsearch.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class PuzzleElementTest {

    @Test
    public void basicConstructor() {
        PuzzleElement pe = new PuzzleElement(3);
        // assertEquals(3, pe.value);
        assertTrue(pe.value == 3);
    }

    @Test
    public void singleNeighborConstructor() {
        PuzzleElement a = new PuzzleElement(45);
        PuzzleElement b = new PuzzleElement(13, a);
        assertTrue(b.toLeft.value == 3);
    }

}