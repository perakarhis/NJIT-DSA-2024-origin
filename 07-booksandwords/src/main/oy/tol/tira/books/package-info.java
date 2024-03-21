/**
 * <h2>BooksAndWords Project</h2>
 * This documentation describes the BooksAndWords project and the things that you need to be implemented in this course project.
 * <p>
 * Your project MUST implement the {@link oy.tol.tira.Book} interface as documented there and in README.md.
 * <p>
 * The {@link oy.tol.tira.BookFactory} class is needed to instantiate your Book implementation.
 * Change the <code>createBook</code> method to return your implementation of the Book interface.
 * <p>
 * The {@link oy.tol.tira.books.BooksAndWords} app shows how you can use your implementation. You
 * may also use the app to test your implementation with different book and ignore files.
 * <p>
 * You MUST use the various tests in the test package to check your implementation works.
 * <p>
 * Make sure to understand that it is not enough that the functionality is OK. Your implementation
 * MUST also satisfy the time efficiency requirements -- be fast enough with LARGE book files.
 * <p>
 * You MUST NOT change the tests in any way.
 * <p>
 * The documentation for the interfaces and classes mentioned above can be found below.
 * <h3>How to get started?</h3>
 * Build the app for running with Maven (without running the tests):
 * <pre>
 * mvn package -DskipTests
 * </pre>
 * And you should see "BUILD SUCCESS" among the output.
 * After this, you can run the app (Windows command line):
 * <pre>
 * java -jar target/booksandwords-1.0-SNAPSHOT-jar-with-dependencies.jar
 * </pre>
 * Alternatively, you can run the app from Visual Studio Code using the Run and Debug menu.
 * <p>
 * Obviously, the app <strong>will fail</strong> if you have not yet implemented the Book interface.
 * <p>
 * Tests can be run (when you start implementing the stuff) from the VS Code Testing menu.
 * Alternatively, run tests from the command line using Maven:
 * <pre>
 * mvn test
 * </pre>
 * Tests use test files from <code>src/test/resources/</code>. There is a zip file there you need to unzip
 * to get the test files from the zip file.
 */
package oy.tol.tira.books;
