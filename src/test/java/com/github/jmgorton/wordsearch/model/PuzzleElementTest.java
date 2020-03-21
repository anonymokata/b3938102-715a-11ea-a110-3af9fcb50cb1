package com.github.jmgorton.wordsearch.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class PuzzleElementTest {

    @Test
    public void basicConstructor() {
        PuzzleElement pe = new PuzzleElement('A');

        assertEquals(pe.value, Character.valueOf('A'));
    }

    @Test
    public void singleNeighborConstructorTwoElements() throws Exception {
        PuzzleElement a = new PuzzleElement('A');
        PuzzleElement b = new PuzzleElement('B', a);

        assertEquals(b.toLeft.value, Character.valueOf('A'));
        assertEquals(a.toRight.value, Character.valueOf('B'));
        assertNotEquals(b.toLeft.value.charValue(), 'B');
    }

    @Test
    public void singleNeighborConstructorMultipleElements() throws Exception {
        PuzzleElement a = new PuzzleElement('A');
        PuzzleElement b = new PuzzleElement('B', a);
        PuzzleElement c = new PuzzleElement('C', b);

        assertEquals(c.toLeft.toLeft.value, Character.valueOf('A'));
        assertEquals(c.toLeft.toRight.value, Character.valueOf('C'));
        assertEquals(b.toRight.value, Character.valueOf('C'));
        assertEquals(a.toRight.value, Character.valueOf('B'));

        assertNull(c.above);
        assertNull(a.above);
        assertNull(b.above);

        assertNull(c.below);
        assertNull(a.below);
        assertNull(b.below);
    }

    @Test
    public void dualNeighborConstructorThreeElements() throws Exception {

        PuzzleElement d = new PuzzleElement('D');
        PuzzleElement b = new PuzzleElement('B', d);
        PuzzleElement a = new PuzzleElement('A', null, d);
        PuzzleElement c = new PuzzleElement('C', a, b);

        

        assertEquals(c.toLeft.value, Character.valueOf('A'));
        assertEquals(b.below.value, Character.valueOf('C'));
        assertEquals(b.toLeft.value, Character.valueOf('D'));
        assertEquals(d.toRight.value, Character.valueOf('B'));
        assertEquals(d.below.value, Character.valueOf('A'));

        assertNull(c.below);
        assertNull(c.toRight);
        assertNull(a.toLeft);
        assertNull(a.below);
        assertNull(b.above);
        assertNull(b.toRight);
        assertNull(d.toLeft);
        assertNull(d.above);
    }

}