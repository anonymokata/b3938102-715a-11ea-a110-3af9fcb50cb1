package com.github.jmgorton.wordsearch;

/**
 * Hello world!
 *
 */
public class App 
{
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
