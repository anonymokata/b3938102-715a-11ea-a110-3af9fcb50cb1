package com.github.jmgorton.wordsearch.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class PuzzleElementTest {

    @Test
    public void basicConstructor() {
        PuzzleElement pe = new PuzzleElement('A');
        // assertEquals(3, pe.value);
        assertTrue(pe.value == 'A');
    }

    @Test
    public void singleNeighborConstructor() throws Exception {
        PuzzleElement a = new PuzzleElement('A');
        PuzzleElement b = new PuzzleElement('B', a);
        assertTrue(b.toLeft.value == 'A');
        assertFalse(b.toLeft.value == 'B');
    }

}