package com.github.jmgorton.wordsearch.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class PuzzleElementTest {

    @Test
    public void basicConstructor() {
        PuzzleElement pe = new PuzzleElement(Integer.valueOf(3));
        // assertEquals(3, pe.value);
        assertTrue(pe.value == 3);
    }

    @Test
    public void singleNeighborConstructor() {
        PuzzleElement a = new PuzzleElement(Integer.valueOf(45));
        PuzzleElement b = new PuzzleElement(Integer.valueOf(13), a);
        assertTrue(b.toLeft.value == 45);
    }

}