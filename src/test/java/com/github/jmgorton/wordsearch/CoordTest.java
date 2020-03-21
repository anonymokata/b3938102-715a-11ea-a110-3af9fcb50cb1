package com.github.jmgorton.wordsearch;

import static org.junit.Assert.*;

import org.junit.Test;

public class CoordTest {

    @Test
    public void validConstructor() {
        Coord c = new Coord(2, 3);

        assertEquals(c.x, Integer.valueOf(2));
        assertEquals(c.y, Integer.valueOf(3));
    }

}