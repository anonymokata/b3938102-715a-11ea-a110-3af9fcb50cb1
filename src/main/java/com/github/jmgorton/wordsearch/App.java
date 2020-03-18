package com.github.jmgorton.wordsearch;

import java.io.*;

/**
 * Hello world!
 *
 */
public class App extends PuzzleSolver
{
    //    public String filePath;
    //    public File file;
    //    public InputStream input;

    public App(String filePath) {
	//	this.filePath = filePath;
	//	try {
	//	    if (filePath != null) {
	//		this.file = new File(filePath);
	//	    }
	//	    if (this.file != null && this.file.exists() && this.file.canRead()) {
	//		this.input = new FileInputStream(this.file);
	//	    }
	//	} catch (Exception e) {
	//	    e.printStackTrace();
	//	} finally {
	//	    if (this.input == null) {
	//		this.input = System.in;
	//	    }
	//	}

	///////////////////////////////////////

	super(filePath);
	System.out.println("app filePath constructor");

    }

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

	for (int i = 0; i < args.length; i++) {
	    System.out.println(args[0]);
	}

	//	PuzzleSolver ps = new PuzzleSolver();
	//	ps.main(args);

	PuzzleSolver.main(args);
    }
}
