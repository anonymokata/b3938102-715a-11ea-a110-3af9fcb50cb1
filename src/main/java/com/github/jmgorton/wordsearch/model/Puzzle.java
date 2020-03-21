package com.github.jmgorton.wordsearch.model;

import java.util.*;

public class Puzzle {

    public List<String> hiddenWords;

    public PuzzleElement topLeftCorner = null;

    public Puzzle() {
        this.hiddenWords = new ArrayList<String>();
    }

}
