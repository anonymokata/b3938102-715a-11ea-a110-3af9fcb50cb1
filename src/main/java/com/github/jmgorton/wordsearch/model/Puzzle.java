package com.github.jmgorton.wordsearch.model;

import java.util.*;

public class Puzzle {

    // TODO turn into map
    public List<String> hiddenWords;
    public List<Coord[]> locs;
    public Map<String, Coord[]> locks;

    public PuzzleElement topLeftCorner = null;
    public PuzzleElement topRightCorner = null;
    public PuzzleElement bottomLeftCorner = null;

    // the project spec says the puzzle will always be a square
    public Integer size = null;

    public Puzzle() {
        this.hiddenWords = new ArrayList<String>();
        this.locs = new ArrayList<Coord[]>();
        this.locks = new HashMap<String, Coord[]>();
    }

}
