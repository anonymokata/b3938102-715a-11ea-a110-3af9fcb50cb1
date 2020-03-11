package com.github.jmgorton.wordsearch;

import java.io.*;
import java.util.Scanner;

public class PuzzleReader {

    public File file;
    public InputStream is;

    public PuzzleReader() {
	// TODO Auto-generated constructor stub

	//	String filePath = "src/main/java/com/github/jmgorton/wordsearch/puzzles/Puzzle1a.txt";
	//	this.file = new File(filePath);

	//	this.is = new FileInputStream(this.file);

	this.is = System.in;
	this.file = null;
    }

    public PuzzleReader(File f) {
	this.is = System.in;
	this.file = f;
    }

    public void readPuzzle() {
	Scanner s = new Scanner(this.is);
	String[] wordsToFind;

	if (s.hasNextLine()) {
	    String toParse = s.nextLine();
	    wordsToFind = toParse.split(",");
	}
	
	while (s.hasNextLine()) {
	    String toParse = s.nextLine();
	    
	}

	s.close();
    }

    public static void main(String[] args) {
	System.out.println("Inside the puzzle solver!");

	PuzzleSolver ps = new PuzzleSolver();

	Scanner s = new Scanner(ps.is);

	while (s.hasNextLine()) {
	    System.out.println(s.nextLine());
	}

	//	ps.is = System.in;

	//	while (ps.is.hasNextLine()) {
	//	    System.out.println(ps.is.nextLine());
	//	}

	
    }

}
