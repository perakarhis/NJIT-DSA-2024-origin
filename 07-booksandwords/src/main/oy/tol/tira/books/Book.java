package oy.tol.tira.books;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * TODO: This interface class MUST be implemented following the instructions in the README.md.

 * @see BookFactory#createBook()
 * @author Antti Juustila
 * @version 1.0
 */
public interface Book {
    /**
     * Sets the source files to use in counting unique words.
     * <p>The implementation MUST check if the files actually exist, and throw
     * <code>FileNotFoundException</code> if not.
     * <p>
     * The files MUST NOT be opened nor read from at this point. It should be done when starting
     * to count the words in {@link #countUniqueWords()}.
     * <p>
     * Note: many operating systems like Windows and macOS hide the file name extension from the user by default.
     * This means that even though the file name is, for example, "MyNotes.txt", the user only sees
     * a file "MyNotes". The "real" file in the file system still has that file name extension. When giving file names 
     * as parameters from the user interface, you MUST specify the whole file name, with the file name extension.
     * Also, if the file is somewhere else than where the app is executed from in the command line,
     * the full or relative path MUST be given. The Book implementation MUST NOT change the file name
     * given as a parameter in any way. User or code (and tests) must give valid file names as parameter.
     * @param fileName The book file name with path.
     * @param ignoreWordsFile The ignore file, containing the words to ignore as comma separated list.
     * @throws FileNotFoundException If the files do not exist, method throws FileNotFoundException.
     */
    void setSource(String fileName, String ignoreWordsFile) throws FileNotFoundException;

    /**
     * Opens the files, starts counting the word occurrences from the file, ignoring ones in the ignore file.
     * Files can be closed after all words have been read from the files.
     * <p>
     * If reading the file(s) fails, must throw <code>IOException</code>.
     * If there is not enough memory to handle the file, must throw <code>OutOfMemoryError</code>.
     * @throws IOException Reading a file failed.
     * @throws OutOfMemoryError Not enough memory to handle the words.
     */
    void countUniqueWords() throws IOException, OutOfMemoryError;

    /**
     * Prints out to the console the top 100 list of words by corrurrence, sorted by descending order.
     * <p>
     * Obviously, the list MUST be shorter if the file has less than 100 unique words.
     * <p>
     * Also the method must print out, after the top list:
     * <ul>
     *  <li>the total number of words in the file.
     *  <li>the count of unique words in the file.
     *  <li>the count of words to ignore.
     *  <li>the count of words ignored in the book file.
     * </ul>
     * Additionally, you SHOULD print out information specific to your
     * algorithm. For example, if you use hash tables, print the number of
     * collisions happening with your hash function. If you use binary search
     * trees, you could print the deepest level of search done while looking
     * for or placing a word in the tree.
     */
    void report();

    /**
     * Releases all the resources (e.g. memory) reserved by the counting.
     */
    void close();

    /**
     * For testing and evaluation. These MUST return valid values for a book
     * after calling {@link countUniqueWords()} and before calling {@link close()}.
     * <p>
     * Gets the count of unique words in the file just handled.
     * @return The count of unique words in the file.
     */
    int getUniqueWordCount();

    /**
     * For testing and evaluation. These MUST return valid values for a book
     * after calling {@link countUniqueWords()} and before calling {@link close()}.
     * <p>
     * Gets the count of words in the file just handled.
     * @return The count of words in the file.
     */
    int getTotalWordCount();

    /**
     * For testing and evaluation. These MUST return valid values for a book
     * after calling {@link countUniqueWords()} and before calling {@link close()}.
     * <p>
     * Gets a word in the top 100 list by index.
     * Must return null (no exceptions thrown!) if the request cannot be completed:
     * <ul>
     *  <li>if there are no words in the list, returns null.
     *  <li>if the list has less than 100 items and index is greater than the number of unique items, returns null.
     *  <li>if the index is less than zero, returns null
     * </ul>
     * @param position The position of the word requested in the results list.
     * @return The word in the list, or null if the request cannot be completed.
     */
    String getWordInListAt(int position);

    /**
     * For testing and evaluation. These MUST return valid values for a book
     * after calling {@link countUniqueWords()} and before calling {@link close()}.
     * <p>
     * Gets the word count in the top 100 list by index.
     * Must return -1 (no exceptions thrown!) if the request cannot be completed:
     * <ul>
     *  <li>if there are no words in the list, returns -1.
     *  <li>if the index is greater than or equal to count of items in the list, returns -1.
     *  <li>if the index is less than zero, returns -1.
     * </ul>
     * Basically, return -1 if the index is out of bounds of the list.
     * @param position The position of the word count requested in the results list.
     * @return The occurrence count of the word in the list, or -1 if the request cannot be completed.
     */
    int getWordCountInListAt(int position);
}
