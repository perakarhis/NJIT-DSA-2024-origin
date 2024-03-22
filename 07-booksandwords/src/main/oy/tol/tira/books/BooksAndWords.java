package oy.tol.tira.books;

import java.lang.NullPointerException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Command line application using the Book interface to count unique words in a book file.
 * <p>
 *  
 * @author Antti Juustila
 * @version 1.0
 */
public class BooksAndWords 
{
    /**
     * The main function for the app.
     * <p>
     * The app:
     * <ul>
     *  <li>Starts measuring time.
     *  <li>Prints out a welcome message.
     *  <li>Asks for the book file name.
     *  <li>Asks for the ignore file name.
     *  <li>Creates an instance of the {@link Book} interface by calling {@link BookFactory#createBook()}.
     *  <li>Gives the file names to the book by calling {@link Book#setSource(String, String)}.
     *  <li>Calls the method to start processing the files: {@link Book#countUniqueWords()}.
     *  <li>When processing is finished, calls {@link Book#report()} to see the results.
     *  <li>Closes the book by calling {@link Book#close()}.
     *  <li>Stops measuring time and reports how much time it took to process the book.
     * </ul>
     * If any exceptions happen, reason for those are printed out on the console.
     * 
     * @param args Startup parameters are not used by this app.
     */
    public static void main( String[] args )
    {
        System.out.println( "========================================================================" );
        System.out.println( "Welcome to Books and Words app!" );
        System.out.println( "App counts the unique words in a book and how many times a word appears." );
        System.out.println( "in the book. Then the 100 most frequent words are printed out." );
        System.out.println( "App also ignores words in an ignore file." );
        System.out.println( "=============================src/test/resources/===========================================" );
        
        String answer = "n";
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("Give the name of the book file > ");
            String bookFile = in.nextLine();
            System.out.println("Give the name of the words to ignore file > ");
            String wordsToIgnoreFile = in.nextLine();
            try {
                long start = System.currentTimeMillis();
                System.out.println("Processing....");
                Book theBook = BookFactory.createBook();
                theBook.setSource(bookFile, wordsToIgnoreFile);
                theBook.countUniqueWords();  
                theBook.report();
                theBook.close();
                long end = System.currentTimeMillis();
                long duration = end - start;
                System.out.format("Processing took %d ms%n", duration);
            } catch (NullPointerException e) {
                System.out.println("*** ERROR: Did you forget to implement BookFactory.createBook?!");
                System.out.println("*** ERROR: If you have it, then there is a null pointer somewhere else to fix!: " + e.getMessage());
            } catch (FileNotFoundException e) {
                System.out.println("*** ERROR: File not found: " + e.getMessage());
            } catch (OutOfMemoryError e) {
                System.out.println("*** ERROR: Cannot fit the book in memory: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("*** ERROR: Something went wrong with IO: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("*** ERROR: Something went wrong: " + e.getMessage());
            }
            System.out.print("Try again? Y/n >");
            answer = in.nextLine();

        } while (answer.equalsIgnoreCase("Y") || answer.isEmpty());
        in.close();
    }
    
}
