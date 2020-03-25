package com.github.jmgorton.wordsearch.model;

import java.util.*;

public class Puzzle {

    public List<String> hiddenWords;

    public List<Coord[]> locs = new ArrayList<Coord[]>();

    public PuzzleElement topLeftCorner = null;
    public PuzzleElement topRightCorner = null;
    public PuzzleElement bottomLeftCorner = null;

    public Puzzle() {
        this.hiddenWords = new ArrayList<String>();
    }

}
