package com.github.jmgorton.wordsearch.search;

import com.github.jmgorton.wordsearch.Coord;

public interface Searcher {
	
	public Coord findWord(String word);

}
