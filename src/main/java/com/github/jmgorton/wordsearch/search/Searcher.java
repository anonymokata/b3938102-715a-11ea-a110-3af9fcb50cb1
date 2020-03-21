package com.github.jmgorton.wordsearch.search;

import com.github.jmgorton.wordsearch.model.Coord;

public interface Searcher {

  public Coord findWord(String word);
  
}
