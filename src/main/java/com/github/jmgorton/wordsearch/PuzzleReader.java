package com.github.jmgorton.wordsearch;

import java.io.*;
import java.util.Scanner;

public class PuzzleReader {

    public String filePath;
    public File file;
    public InputStream input;

    public PuzzleReader() {
	// TODO Auto-generated constructor stub

	//	String filePath = "src/main/java/com/github/jmgorton/wordsearch/puzzles/Puzzle1a.txt";
	//	this.file = new File(filePath);

	//	this.is = new FileInputStream(this.file);

	/////////////////////////////////////////////////

	System.out.println("puzzle reader default constructor");
	this.filePath = null;
	this.file = null;
	this.input = System.in;
    }

    public PuzzleReader(String filePath) {
	this.filePath = filePath;
	try {
	    if (this.filePath != null) {
		this.file = new File(filePath);
	    }

	    if (this.file != null && this.file.exists() && this.file.canRead()) {
		this.input = new FileInputStream(this.file);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (this.input == null) {
		this.input = System.in;
	    }
	}

	///////////////////////////////////////////////

	//	System.out.println("puzzle reader filePath constructor");
	//	if (filePath == null) {
	//	    this();
	//	    System.out.println("input is System.in? " + this.input == System.in ? "true" : "false");
	//	} else {
	//	    File f = new File(filePath);
	//	    this(f);
	//	}

	//////////////////////////////////////////////

	//	this(new File(filePath != null ? filePath : ""));
	System.out.println("puzzle reader filePath constructor");
    }

    public PuzzleReader(File f) {

	this();
	System.out.println("puzzle reader file constructor");
	//	if (f == null) {
	//	    this();
	//	    System.out.println("input is System.in? " + this.input == System.in ? "true" : "false");
	//	} else {
	if (f != null) {
	    if (f.exists() && f.canRead()) {
		try {
		    this.input = new FileInputStream(f);
		} catch (Exception e) {
		    // or here?
		    this.input = System.in;
		    e.printStackTrace();
		} finally {
		    // do something here?
		    //		    if (this.input == null) this.input = System.in;
		}

		this.file = f;
		this.filePath = this.file.getAbsolutePath();
	    }
	}
    }

    public void readPuzzle() {
	System.out.println("readPuzzle");
	Scanner s = new Scanner(this.input);
	String[] wordsToFind;

	if (s.hasNextLine()) {
	    String toParse = s.nextLine();
	    wordsToFind = toParse.split(",");
	    for (String str : wordsToFind) {
		System.out.println(str);
	    }
	}
	
	while (s.hasNextLine()) {
	    String toParse = s.nextLine();
	    System.out.println(toParse);
	}

	System.out.println("end of func readPuzzle()");

	s.close();
    }

    public static void main(String[] args) {
	System.out.println("Inside the puzzle solver!");

	PuzzleSolver ps = new PuzzleSolver();

	Scanner s = new Scanner(ps.input);

	while (s.hasNextLine()) {
	    System.out.println(s.nextLine());
	}

	//	ps.is = System.in;

	//	while (ps.is.hasNextLine()) {
	//	    System.out.println(ps.is.nextLine());
	//	}

	
    }

}
