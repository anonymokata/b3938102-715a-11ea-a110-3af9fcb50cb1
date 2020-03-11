package com.github.jmgorton.wordsearch;

import java.io.*;
import java.util.Scanner;

public class PuzzleSolver extends PuzzleReader {

    //    public File file;
    //    public InputStream is;

    public PuzzleSolver() {
	// TODO Auto-generated constructor stub

	//	String filePath = "src/main/java/com/github/jmgorton/wordsearch/puzzles/Puzzle1a.txt";
	//	this.file = new File(filePath);

	//	this.is = new FileInputStream(this.file);

	this.is = System.in;
	this.file = null;
    }

    public PuzzleSolver(File f) {
	this.file = f;
	try {
	    this.is = new FileInputStream(this.file);
	} catch (FileNotFoundException e) {
	    System.err.println("file does not exist... Using System.in");
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    this.is = System.in;
	}
    }

    public static void main(String[] args) {
	System.out.println("Inside the puzzle solver!");

	PuzzleSolver ps;

	if (args != null && args.length > 0) {
	    if (args.length > 1) {	
		System.err.println("Invalid Usage: ...");
		System.exit(1);
		return;	
	    } else {
		File input = new File(args[0]);
		if (input.exists()) {
		    ps = new PuzzleSolver(input);
		} else {
		    System.err.println("No such file. ...");
		    System.exit(2);
		    return;
		}
	    }
	} else {
	    System.out.println("no argument supplied -- using System.in");
	    ps = new PuzzleSolver();
	}

	ps.readPuzzle();

	///////////////////////////////////////

	//	Scanner s = new Scanner(ps.is);

	//	while (s.hasNextLine()) {
	//	    System.out.println(s.nextLine());
	//	}

	/////////////////////////////////////////

	//	ps.is = System.in;

	//	while (ps.is.hasNextLine()) {
	//	    System.out.println(ps.is.nextLine());
	//	}

	
    }

}
