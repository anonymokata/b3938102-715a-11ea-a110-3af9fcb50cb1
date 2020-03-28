# WordSearch

## Overview
A Maven/Java project for finding and returning the locations of words in a classic word search problem. For a complete description of the problem statement, look at this [repo][Problem Statement]. To generate puzzles to use for testing, use a site similar to [this one][Puzzle Generator] (the formatting of the output puzzle file may need to be slightly modified).

## Quickstart
To get up and running quickly, follow these steps:
1. From the local directory that you want this project to reside in on your computer, clone this repository. (`git init`; `git clone <repo-url>`)
2. Enter the root directory of the project. (Likely, this means running `cd wordsearch`)
3. To create the executable, run `mvn package`. This command will also compile and test the code before packaging it -- to learn more about Maven build lifecycle stages, go [here][Lifecycle targets].
4. To run the application on one of the default puzzles, run `java -cp target/wordsearch-1.0-SNAPSHOT.jar com.github.jmgorton.wordsearch.App puzzles/Puzzle1a.txt`.

## Running Tests
To run all tests, navigate to the root directory of the project and run:

`mvn test`

To run a single test, or multiple specific tests, run:

`mvn -Dtest=App <testName>`

## Packaging the App
To package the application into a jar file for execution, run:

`mvn package`

## Running the App
To run the application with no arguments supplied, you can execute one of the following commands:

* `mvn exec:java`
* `java -cp target/<file-name-SNAPSHOT>.jar com.github.jmgorton.wordsearch.App`

Running the app with no arguments prompts the app to attempt to read the puzzle from System.in. To run the application with a file location as an argument, you can run:

`java -cp target/<file-name-SNAPSHOT>.jar com.github.jmgorton.wordsearch.App <relative/path/to/some/puzzle.txt>`

Be sure the puzzle is in the right format!

## Other Commands
For an overview of what maven is, see [Maven's 5 Minute Guide]. 

For a list of other maven lifecycle phases to target, check [this][Lifecycle targets] out.



[Problem Statement]: https://github.com/PillarTechnology/kata-word-search
[Puzzle Generator]: http://puzzlemaker.discoveryeducation.com/WordSearchSetupForm.asp
[Maven's 5 Minute Guide]: https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html 
[Lifecycle targets]: https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#Lifecycle_Reference

