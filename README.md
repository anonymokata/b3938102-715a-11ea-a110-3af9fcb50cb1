# WordSearch
## Overview
A Maven/Java project for finding and returning the locations of words in a classic word search problem.
## Packaging the App
To package the application into a jar file for execution, run:
`mvn package`
## Running the App
To run the application with no arguments supplied, you can run either:

`mvn exec:java` or 
`java -cp target/<file-name-SNAPSHOT>.jar com.github.jmgorton.wordsearch.App`\n

To run the application with an file location as an argument, you can run:
`java -cp target/<file-name-SNAPSHOT>.jar com.github.jmgorton.wordsearch.App <relative/path/to/some/puzzle.txt>`
## Running Tests
To run all tests, navigate to the root directory of the project and run:
`mvn test`

## Other Commands
For an overview of what maven is, see https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html \n

For a list of other maven goals to target, visit https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#Lifecycle_Reference
