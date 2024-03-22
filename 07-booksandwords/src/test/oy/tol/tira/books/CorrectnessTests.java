package oy.tol.tira.books;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * Unit test for BooksAndWords.
 */
//@Timeout(720)
@DisplayName("Correctness tests for algorithm")
public class CorrectnessTests 
{
    /**
     * Test using small.txt
     */
    private int totalWordCount = -1;
    private int uniqueWordCount = -1;
    private String expectedWord = "";
    private int expectedCount = -1;

    @Test 
    public void testFilesNotGiven() {
        Book testTarget = BookFactory.createBook();
        assertNotNull(testTarget, () -> "BookFactory.createBook() returned null.");
        assertThrows(Exception.class, () -> { testTarget.countUniqueWords(); }, "Should throw if no files given.");
        assertDoesNotThrow(() -> { testTarget.close(); }, "Should not throw even though files not set/wrong." );
    }

    @Test
    public void testEmptyBookFile() {
        System.out.println("Starting to test emptybook.txt...");
        Book testTarget = BookFactory.createBook();
        String testBook = getFullPathToTestFile("emptybook.txt");
        String ignoreFile = getFullPathToTestFile("ignore-words.txt");
        assertNotNull(testTarget, () -> "BookFactory.createBook() returned null.");
        assertDoesNotThrow( () -> { testTarget.setSource(testBook, ignoreFile); }, "Setting test files failed.");
        assertDoesNotThrow( () -> { testTarget.countUniqueWords(); }, "Failed to process the book/ignore files.");
        assertDoesNotThrow( () -> { testTarget.report(); }, "Failed to print out the report.");
        totalWordCount = -1; 
        assertDoesNotThrow( () -> { totalWordCount = testTarget.getTotalWordCount(); }, "Failed to get total word count.");
        assertEquals(0, totalWordCount, () -> "Total word count not expected.");
        assertDoesNotThrow( () -> { uniqueWordCount = testTarget.getUniqueWordCount(); }, "Failed to get unique word count.");
        assertEquals(0,uniqueWordCount, () -> "Unique word count not expected.");
        assertDoesNotThrow( () -> { expectedWord = testTarget.getWordInListAt(0); }, "Failed to get word.");
        assertNull(expectedWord, () -> "For empty book, getWordInListAt(0) must return null" );
        assertDoesNotThrow( () -> { expectedCount = testTarget.getWordCountInListAt(0); }, "Failed to get word count.");
        assertEquals(-1, expectedCount, () -> "Unique word count not expected, should be -1 for an empty book file.");
        assertDoesNotThrow(() -> { testTarget.close(); }, "Closing the book should not throw.");
    }

    @Test
    public void testOneWordBookFile() {
        System.out.println("Starting to test word.txt...");
        Book testTarget = BookFactory.createBook();
        String testBook = getFullPathToTestFile("word.txt");
        String ignoreFile = getFullPathToTestFile("ignore-words.txt");
        assertNotNull(testTarget, () -> "BookFactory.createBook() returned null.");
        assertDoesNotThrow( () -> { testTarget.setSource(testBook, ignoreFile); }, "Setting test files failed.");
        assertDoesNotThrow( () -> { testTarget.countUniqueWords(); }, "Failed to process the book/ignore files.");
        assertDoesNotThrow( () -> { testTarget.report(); }, "Failed to print out the report.");
        totalWordCount = -1; 
        assertDoesNotThrow( () -> { totalWordCount = testTarget.getTotalWordCount(); }, "Failed to get total word count.");
        assertEquals(1, totalWordCount, () -> "Total word count not expected.");
        assertDoesNotThrow( () -> { uniqueWordCount = testTarget.getUniqueWordCount(); }, "Failed to get unique word count.");
        assertEquals(1,uniqueWordCount, () -> "Unique word count not expected.");
        assertDoesNotThrow( () -> { expectedWord = testTarget.getWordInListAt(0); }, "Failed to get word.");
        assertEquals("heidän", expectedWord, () -> "For one word book, getWordInListAt(0) must return the one word in the book" );
        assertDoesNotThrow( () -> { expectedCount = testTarget.getWordCountInListAt(0); }, "Failed to get word count.");
        assertEquals(1, expectedCount, () -> "Should be 1 for a book file with one word.");
        assertDoesNotThrow(() -> { testTarget.close(); }, "Closing the book should not throw.");
    }

    @Test
    public void testNonExistentBookFile() {
        Book testTarget = BookFactory.createBook();
        assertNotNull(testTarget, () -> "BookFactory.createBook() returned null.");
        assertThrows(Exception.class, () -> { testTarget.setSource("dfgsehjstjsr43sh5sth.txt", "dfsy56ksthserg4fgsd.txt"); }, "Must throw if book file does not exist.");
        assertThrows(Exception.class, () -> { testTarget.countUniqueWords(); }, "Should throw if using invalid files.");
        totalWordCount = -1; 
        assertDoesNotThrow(() -> { testTarget.close(); }, "Should not throw even though files not set/wrong." );
    }

    @Test
    public void testTinyBookFile() {
        System.out.println("Starting to test tiny.txt...");
        Book testTarget = BookFactory.createBook();
        String testBook = getFullPathToTestFile("tiny.txt");
        String ignoreFile = getFullPathToTestFile("ignore-words.txt");
        long start = System.currentTimeMillis();
        assertNotNull(testTarget, () -> "BookFactory.createBook() returned null.");
        assertDoesNotThrow( () -> { testTarget.setSource(testBook, ignoreFile); }, "Setting test files failed.");
        assertDoesNotThrow( () -> { testTarget.countUniqueWords(); }, "Failed to process the book/ignore files.");
        assertDoesNotThrow( () -> { testTarget.report(); }, "Failed to print out the report.");
        totalWordCount = -1; 
        assertDoesNotThrow( () -> { totalWordCount = testTarget.getTotalWordCount(); }, "Failed to get total word count.");
        assertEquals(38, totalWordCount, () -> "Total word count not expected.");
        assertDoesNotThrow( () -> { uniqueWordCount = testTarget.getUniqueWordCount(); }, "Failed to get unique word count.");
        assertEquals(29,uniqueWordCount, () -> "Unique word count not expected.");
        assertDoesNotThrow( () -> { expectedWord = testTarget.getWordInListAt(0); }, "Failed to get word.");
        assertEquals("words", expectedWord, () -> "Expected \"words\" as the top-1 word but got " + expectedWord);
        assertDoesNotThrow( () -> { expectedCount = testTarget.getWordCountInListAt(0); }, "Failed to get word count.");
        assertEquals(4, expectedCount, () -> "Unique word count not expected.");
        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println("Test took " + duration + " ms");
        if (duration > 100) {
            System.out.println("****** WARNING your implementation may be too slow!!");
        }
        assertDoesNotThrow(() -> { testTarget.close(); }, "Closing the book should not throw.");
    }

    @Test
    public void testSmallFile()
    {
        try {
            System.out.println("Starting to test small.txt...");
            Book testTarget = BookFactory.createBook();
            String testBook = getFullPathToTestFile("small.txt");
            String ignoreFile = getFullPathToTestFile("ignore-words.txt");
            long start = System.currentTimeMillis();
            assertNotNull(testTarget, () -> "BookFactory.createBook() returned null.");
            assertDoesNotThrow( () -> { testTarget.setSource(testBook, ignoreFile); }, "Setting test files failed.");
            assertDoesNotThrow( () -> { testTarget.countUniqueWords(); }, "Failed to process the book/ignore files.");
            assertDoesNotThrow( () -> { testTarget.report(); }, "Failed to print out the report.");
            totalWordCount = -1; 
            assertDoesNotThrow( () -> { totalWordCount = testTarget.getTotalWordCount(); }, "Failed to get total word count.");
            assertTrue(Math.abs(totalWordCount - 2308) < 5, () -> "Total word count too different from expected.");
            assertDoesNotThrow( () -> { uniqueWordCount = testTarget.getUniqueWordCount(); }, "Failed to get unique word count.");
            assertTrue(Math.abs(uniqueWordCount - 233) < 2, () -> "Unique word count too different from expected.");
            assertDoesNotThrow( () -> { expectedWord = testTarget.getWordInListAt(0); }, "Failed to get word.");
            assertEquals("in", expectedWord, () -> "Expected \"in\" as the top-1 word but got " + expectedWord);
            assertDoesNotThrow( () -> { expectedCount = testTarget.getWordCountInListAt(0); }, "Failed to get word count.");
            assertTrue(Math.abs(expectedCount - 78) < 2, () -> "Unique word count too different from expected.");
            long end = System.currentTimeMillis();
            long duration = end - start;
            System.out.println("Test took " + duration + " ms");
            if (duration > 100) {
                System.out.println("****** WARNING your implementation may be too slow!!");
            }
            assertDoesNotThrow(() -> { testTarget.close(); }, "Closing the book should not throw.");
        } catch (Exception e) {
            fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @Test
    public void testSapmiRussianFile() {
        System.out.println("Starting to test sapmirussian.txt...");
        Book testTarget = BookFactory.createBook();
        String testBook = getFullPathToTestFile("sapmirussian.txt");
        String ignoreFile = getFullPathToTestFile("ignore-words.txt");
        long start = System.currentTimeMillis();
        assertNotNull(testTarget, () -> "BookFactory.createBook() returned null.");
        assertDoesNotThrow( () -> { testTarget.setSource(testBook, ignoreFile); }, "Setting test files failed.");
        assertDoesNotThrow( () -> { testTarget.countUniqueWords(); }, "Failed to process the book/ignore files.");
        assertDoesNotThrow( () -> { testTarget.report(); }, "Failed to print out the report.");
        totalWordCount = -1; 
        assertDoesNotThrow( () -> { totalWordCount = testTarget.getTotalWordCount(); }, "Failed to get total word count.");
        assertTrue(Math.abs(totalWordCount - 455) < 3, () -> "Total word count too different from expected.");
        assertDoesNotThrow( () -> { uniqueWordCount = testTarget.getUniqueWordCount(); }, "Failed to get unique word count.");
        assertTrue(Math.abs(uniqueWordCount - 347) < 3, () -> "Unique word count too different from expected.");
        assertDoesNotThrow( () -> { expectedWord = testTarget.getWordInListAt(3); }, "Failed to get word.");
        assertEquals( "maiddái", expectedWord, () -> "Expected \"maiddái\" as the top-4 word but got " + expectedWord);
        assertDoesNotThrow( () -> { expectedCount = testTarget.getWordCountInListAt(3); }, "Failed to get word count.");
        assertEquals(6, expectedCount, () -> "Unique word count not what expected.");

        assertDoesNotThrow( () -> { expectedWord = testTarget.getWordInListAt(0); }, "Failed to get word.");
        assertEquals("ja", expectedWord, () -> "Expected \"ja\" as the top-1 word but got " + expectedWord);
        assertDoesNotThrow( () -> { expectedCount = testTarget.getWordCountInListAt(0); }, "Failed to get word count.");
        assertEquals(17, expectedCount, () -> "Unique word count for top-11 too different from expected.");

        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println("Test took " + duration + " ms");
        if (duration > 500) {
            System.out.println("****** WARNING your implementation may be too slow!!");
            System.out.println("****** WARNING Check the execution time against a very slow implementation.");
        }
        assertDoesNotThrow(() -> { testTarget.close(); }, "Closing the book should not throw.");
    }

    @Test
    public void testWarPeaceFile() {
        System.out.println("Starting to test WarPeace.txt...");
        Book testTarget = BookFactory.createBook();
        String testBook = getFullPathToTestFile("WarPeace.txt");
        String ignoreFile = getFullPathToTestFile("ignore-words.txt");
        long start = System.currentTimeMillis();
        assertNotNull(testTarget, () -> "BookFactory.createBook() returned null.");
        assertDoesNotThrow( () -> { testTarget.setSource(testBook, ignoreFile); }, "Setting test files failed.");
        assertDoesNotThrow( () -> { testTarget.countUniqueWords(); }, "Failed to process the book/ignore files.");
        assertDoesNotThrow( () -> { testTarget.report(); }, "Failed to print out the report.");
        totalWordCount = -1; 
        assertDoesNotThrow( () -> { totalWordCount = testTarget.getTotalWordCount(); }, "Failed to get total word count.");
        assertTrue(Math.abs(totalWordCount - 480967) < 20, () -> "Total word count too different from expected.");
        assertDoesNotThrow( () -> { uniqueWordCount = testTarget.getUniqueWordCount(); }, "Failed to get unique word count.");
        assertTrue(Math.abs(uniqueWordCount - 17560) < 5, () -> "Unique word count too different from expected.");
        assertDoesNotThrow( () -> { expectedWord = testTarget.getWordInListAt(0); }, "Failed to get word.");
        assertEquals( "to", expectedWord, () -> "Expected \"to\" as the top-1 word but got " + expectedWord);
        assertDoesNotThrow( () -> { expectedCount = testTarget.getWordCountInListAt(0); }, "Failed to get word count.");
        assertTrue(Math.abs(expectedCount - 16755) < 3, () -> "Unique word count too different from expected.");

        assertDoesNotThrow( () -> { expectedWord = testTarget.getWordInListAt(9); }, "Failed to get word.");
        assertEquals("her", expectedWord, () -> "Expected \"her\" as the top-11 word but got " + expectedWord);
        assertDoesNotThrow( () -> { expectedCount = testTarget.getWordCountInListAt(9); }, "Failed to get word count.");
        assertTrue(Math.abs(expectedCount - 4725) < 2, () -> "Unique word count for top-11 too different from expected.");

        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println("Test took " + duration + " ms");
        if (duration > 500) {
            System.out.println("****** WARNING your implementation may be too slow!!");
            System.out.println("****** WARNING Check the execution time against a very slow implementation.");
        }
        assertDoesNotThrow(() -> { testTarget.close(); }, "Closing the book should not throw.");
    }

    @Test
    public void testBulkFile() {
        System.out.println("Starting to test Bulk.txt...");
        Book testTarget = BookFactory.createBook();
        String testBook = getFullPathToTestFile("Bulk.txt");
        String ignoreFile = getFullPathToTestFile("ignore-words.txt");
        long start = System.currentTimeMillis();
        assertNotNull(testTarget, () -> "BookFactory.createBook() returned null.");
        assertDoesNotThrow( () -> { testTarget.setSource(testBook, ignoreFile); }, "Setting test files failed.");
        assertDoesNotThrow( () -> { testTarget.countUniqueWords(); }, "Failed to process the book/ignore files.");
        assertDoesNotThrow( () -> { testTarget.report(); }, "Failed to print out the report.");
        totalWordCount = -1; 
        assertDoesNotThrow( () -> { totalWordCount = testTarget.getTotalWordCount(); }, "Failed to get total word count.");
        assertTrue(Math.abs(totalWordCount - 2378668) < 100, () -> "Total word count too different (over 100) from expected.");
        assertDoesNotThrow( () -> { uniqueWordCount = testTarget.getUniqueWordCount(); }, "Failed to get unique word count.");
        assertTrue(Math.abs(uniqueWordCount - 97115) < 50, () -> "Unique word count too different (over 50) from expected.");
        assertDoesNotThrow( () -> { expectedWord = testTarget.getWordInListAt(0); }, "Failed to get word.");
        assertEquals( "ja", expectedWord, () -> "Expected \"ja\" as the top-1 word but got " + expectedWord);
        assertDoesNotThrow( () -> { expectedCount = testTarget.getWordCountInListAt(0); }, "Failed to get word count.");
        assertTrue(Math.abs(expectedCount - 62796) < 5, () -> "Unique word count too different from expected.");

        assertDoesNotThrow( () -> { expectedWord = testTarget.getWordInListAt(41); }, "Failed to get word.");
        assertEquals("heidän", expectedWord, () -> "Expected \"heidän\" as the top-42 word but got " + expectedWord);
        assertDoesNotThrow( () -> { expectedCount = testTarget.getWordCountInListAt(41); }, "Failed to get word count.");
        assertTrue(Math.abs(expectedCount - 6987) < 4, () -> "Unique word count for top-42 too different from expected.");

        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println("Test took " + duration + " ms");
        if (duration > 1000) {
            System.out.println("****** WARNING your implementation may be too slow!!");
            System.out.println("****** WARNING Check the execution time against a very slow implementation.");
        }
        assertDoesNotThrow(() -> { testTarget.close(); }, "Closing the book should not throw.");
    }


    private String getFullPathToTestFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return file.getAbsolutePath();
    }
}
