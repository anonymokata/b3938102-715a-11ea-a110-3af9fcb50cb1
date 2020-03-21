package com.github.jmgorton.wordsearch.search;

import com.github.jmgorton.wordsearch.model.Coord;

public interface Searcher {

  // TODO this might make more sense as an abstract class -- non-static, non-final fields; constructor

  public Coord findWord(String word);
  
}
