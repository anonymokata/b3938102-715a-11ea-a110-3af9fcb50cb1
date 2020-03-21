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

        assertEquals(a.value, Character.valueOf('A'));
        assertEquals(b.value.charValue(), 'B');
        assertEquals(c.value.charValue(), 'C');
        assertEquals(d.value, Character.valueOf('D'));

        assertEquals(a.above.value, Character.valueOf('D'));
        assertEquals(a.toRight.value, Character.valueOf('C'));
        assertEquals(c.above.value, Character.valueOf('B'));
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

    @Test
    public void manualFieldConstructorTwoElements() {
        PuzzleElement a = new PuzzleElement('A');
        PuzzleElement b = new PuzzleElement('B', a, null, null, null, null, null, null, null);

        assertEquals(b.above, a);
        assertEquals(a.below, b);
    }

    @Test
    public void manualFieldConstructorThreeElements() {
        PuzzleElement a = new PuzzleElement('A');
        PuzzleElement b = new PuzzleElement('B', a, null, null, null, null, null, null, null);
        PuzzleElement c = new PuzzleElement('C', null, a, b, null, null, null, null, null);

        assertEquals(b.above, a);
        assertEquals(a.below, b);

        assertEquals(c.toRight, b);
        assertEquals(b.toLeft, c);

        assertEquals(c.aboveToRight, a);
        assertEquals(a.belowToLeft, c);
    }

    @Test
    public void manualFieldConstructorThreeElementsReplaceElement() {
        PuzzleElement a = new PuzzleElement('A');
        PuzzleElement b = new PuzzleElement('B', a, null, null, null, null, null, null, null);
        PuzzleElement c = new PuzzleElement('C', null, a, b, null, null, null, null, null);
        PuzzleElement d = new PuzzleElement('D', null, null, null, null, b, c, null, null);

        assertEquals(b.above, d);
        assertEquals(d.below, b);

        assertEquals(c.toRight, b);
        assertEquals(b.toLeft, c);

        assertEquals(c.aboveToRight, d);
        assertEquals(d.belowToLeft, c);
    }

    @Test
    public void manualFieldConstructorThreeElementsOverrideElements() {
        PuzzleElement a = new PuzzleElement('A');
        //   a
        PuzzleElement b = new PuzzleElement('B', a, null, null, null, null, null, null, null);
        //   a
        //   b
        PuzzleElement c = new PuzzleElement('C', null, a, b, null, null, null, null, null);
        //   a
        // c b
        PuzzleElement d = new PuzzleElement('D', a, null, null, null, null, null, null, c);
        // c a
        // c d
        // ????
        // unsure what behavior even should be here ... maybe leave it at undefined for now
        // a situation like this would very likely lead to a puzzle-parsing Exception down the line


        assertEquals(d.value, Character.valueOf('D'));

        // assertEquals(b.above, d);
        // assertEquals(d.below, b);

        // assertEquals(c.toRight, b);
        // assertEquals(b.toLeft, c);

        // assertEquals(c.aboveToRight, d);
        // assertEquals(d.belowToLeft, c);
    }

}