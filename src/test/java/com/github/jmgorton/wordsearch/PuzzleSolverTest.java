package com.github.jmgorton.wordsearch;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

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
        assertNull(a.puzzle.topRightCorner);
        assertNull(a.puzzle.bottomLeftCorner);
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
        p.search();

        assertEquals(1, p.puzzle.wordLocs.size());
        assertNotNull(p.puzzle.wordLocs.get("DWARF"));
        assertEquals(5, p.puzzle.wordLocs.get("DWARF").length);

        // for (Coord c : p.puzzle.locs.get(0)) {
        //     System.out.println(c.toString());
        // }

        assertEquals(Integer.valueOf(7), p.puzzle.wordLocs.get("DWARF")[0].x);
        assertEquals(Integer.valueOf(3), p.puzzle.wordLocs.get("DWARF")[4].x);
        assertEquals(Integer.valueOf(4), p.puzzle.wordLocs.get("DWARF")[0].y);
        assertEquals(Integer.valueOf(0), p.puzzle.wordLocs.get("DWARF")[4].y);
    }

    @Test
    public void testSolveTwoWords() {
        String path = System.getProperty("user.dir");
        path += "/puzzles/Puzzle1c.txt";
        PuzzleSolver p = new PuzzleSolver(path);
        try {
            p.readPuzzle();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        p.search();

        assertEquals(2, p.puzzle.wordLocs.size());
        assertNotNull(p.puzzle.wordLocs.get("DWARF"));
        assertNotNull(p.puzzle.wordLocs.get("SILMARILLION"));

        // for (Coord c : p.puzzle.wordLocs.get("SILMARILLION")) {
        //     System.out.println(c.toString());
        // }

        assertEquals(Integer.valueOf(7), p.puzzle.wordLocs.get("DWARF")[0].x);
        assertEquals(Integer.valueOf(3), p.puzzle.wordLocs.get("DWARF")[4].x);
        assertEquals(Integer.valueOf(4), p.puzzle.wordLocs.get("DWARF")[0].y);
        assertEquals(Integer.valueOf(0), p.puzzle.wordLocs.get("DWARF")[4].y);

        assertEquals(Integer.valueOf(2), p.puzzle.wordLocs.get("SILMARILLION")[0].x);
        assertEquals(Integer.valueOf(2), p.puzzle.wordLocs.get("SILMARILLION")[11].x);
        assertEquals(Integer.valueOf(0), p.puzzle.wordLocs.get("SILMARILLION")[0].y);
        assertEquals(Integer.valueOf(11), p.puzzle.wordLocs.get("SILMARILLION")[11].y);
    }

    @Test
    public void testSolvePuzzle1() {
        String path = System.getProperty("user.dir");
        path += "/puzzles/Puzzle1a.txt";
        PuzzleSolver p = new PuzzleSolver(path);
        try {
            p.readPuzzle();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        p.search();

        assertEquals(8, p.puzzle.wordLocs.size());
        assertNotNull(p.puzzle.wordLocs.get("DWARF"));
        assertNotNull(p.puzzle.wordLocs.get("SILMARILLION"));
        assertNotNull(p.puzzle.wordLocs.get("WORD"));
        assertNotNull(p.puzzle.wordLocs.get("KING"));
        assertNotNull(p.puzzle.wordLocs.get("FELLOW"));
        assertNotNull(p.puzzle.wordLocs.get("RING"));
        assertNotNull(p.puzzle.wordLocs.get("BONUS"));
        assertNotNull(p.puzzle.wordLocs.get("AXE"));

        // Set<String> keys = p.puzzle.wordLocs.keySet();
        // for (String s : keys) {
        //     System.out.println(s);
        //     Coord[] locs = p.puzzle.wordLocs.get(s);
        //     for (Coord c : locs) {
        //         System.out.println("\t" + c.toString());
        //     }
        // }

        assertEquals(Integer.valueOf(7), p.puzzle.wordLocs.get("DWARF")[0].x);
        assertEquals(Integer.valueOf(3), p.puzzle.wordLocs.get("DWARF")[4].x);
        assertEquals(Integer.valueOf(4), p.puzzle.wordLocs.get("DWARF")[0].y);
        assertEquals(Integer.valueOf(0), p.puzzle.wordLocs.get("DWARF")[4].y);

        assertEquals(Integer.valueOf(2), p.puzzle.wordLocs.get("SILMARILLION")[0].x);
        assertEquals(Integer.valueOf(2), p.puzzle.wordLocs.get("SILMARILLION")[11].x);
        assertEquals(Integer.valueOf(0), p.puzzle.wordLocs.get("SILMARILLION")[0].y);
        assertEquals(Integer.valueOf(11), p.puzzle.wordLocs.get("SILMARILLION")[11].y);

        assertEquals(Integer.valueOf(19), p.puzzle.wordLocs.get("WORD")[0].x);
        assertEquals(Integer.valueOf(19), p.puzzle.wordLocs.get("WORD")[3].x);
        assertEquals(Integer.valueOf(18), p.puzzle.wordLocs.get("WORD")[0].y);
        assertEquals(Integer.valueOf(15), p.puzzle.wordLocs.get("WORD")[3].y);

        assertEquals(Integer.valueOf(16), p.puzzle.wordLocs.get("KING")[0].x);
        assertEquals(Integer.valueOf(19), p.puzzle.wordLocs.get("KING")[3].x);
        assertEquals(Integer.valueOf(4), p.puzzle.wordLocs.get("KING")[0].y);
        assertEquals(Integer.valueOf(4), p.puzzle.wordLocs.get("KING")[3].y);

        assertEquals(Integer.valueOf(10), p.puzzle.wordLocs.get("FELLOW")[0].x);
        assertEquals(Integer.valueOf(5), p.puzzle.wordLocs.get("FELLOW")[5].x);
        assertEquals(Integer.valueOf(10), p.puzzle.wordLocs.get("FELLOW")[0].y);
        assertEquals(Integer.valueOf(15), p.puzzle.wordLocs.get("FELLOW")[5].y);

        assertEquals(Integer.valueOf(11), p.puzzle.wordLocs.get("RING")[0].x);
        assertEquals(Integer.valueOf(14), p.puzzle.wordLocs.get("RING")[3].x);
        assertEquals(Integer.valueOf(8), p.puzzle.wordLocs.get("RING")[0].y);
        assertEquals(Integer.valueOf(5), p.puzzle.wordLocs.get("RING")[3].y);

        assertEquals(Integer.valueOf(15), p.puzzle.wordLocs.get("BONUS")[0].x);
        assertEquals(Integer.valueOf(11), p.puzzle.wordLocs.get("BONUS")[4].x);
        assertEquals(Integer.valueOf(4), p.puzzle.wordLocs.get("BONUS")[0].y);
        assertEquals(Integer.valueOf(4), p.puzzle.wordLocs.get("BONUS")[4].y);

        assertEquals(Integer.valueOf(3), p.puzzle.wordLocs.get("AXE")[0].x);
        assertEquals(Integer.valueOf(5), p.puzzle.wordLocs.get("AXE")[2].x);
        assertEquals(Integer.valueOf(3), p.puzzle.wordLocs.get("AXE")[0].y);
        assertEquals(Integer.valueOf(5), p.puzzle.wordLocs.get("AXE")[2].y);
    }

    @Test
    public void testReverseWord() throws Exception {
        // object to use to invoke the method
        // method does not depend on the type of constructor we use
        PuzzleSolver ps = new PuzzleSolver();

        // argument types 
        Class<?>[] argTypes = new Class<?>[1];
        argTypes[0] = String.class;

        // retrieve the method in question
        Method reverseWord = PuzzleSolver.class.getDeclaredMethod("reverseWord", argTypes);
        // without the next line, we'd get an IllegalAccessException
        reverseWord.setAccessible(true);

        // invoke the method in question
        String output = (String) reverseWord.invoke(ps, "absolute");

        // check the output
        assertEquals("etulosba", output);
    }

    @Test
    public void testReverseTwoWords() throws Exception {
        PuzzleSolver ps = new PuzzleSolver();

        Class<?>[] argTypes = new Class<?>[1];
        argTypes[0] = String.class;

        Method reverseWord = PuzzleSolver.class.getDeclaredMethod("reverseWord", argTypes);
        reverseWord.setAccessible(true);

        String output = (String) reverseWord.invoke(ps, "absolute anoddnumber");

        assertEquals("rebmunddona etulosba", output);
        assertNotEquals("someotherstring", output);
    }

}