package oy.tol.tira.books;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
public class BSearchTreeBookImplementation implements Book {
    private static final int MAX_WORD_LEN = 100;

    public static WordCount[] words = null;
    public static int indexOfWordCount=0;
    private WordCount root=null;
    private String bookFile = null;
    private String wordsToIgnoreFile = null;
    private WordFilter filter = null;

    public static int uniqueWordCount = 0;
    private int totalWordCount = 0;
    private int ignoredWordsTotal = 0;

    public static int maxProbingSteps = 0;

    @Override
    public void setSource(String fileName, String ignoreWordsFile) throws FileNotFoundException {
        boolean success = false;
        if (checkFile(fileName)) {
            bookFile = fileName;
            if (checkFile(ignoreWordsFile)) {
                wordsToIgnoreFile = ignoreWordsFile;
                success = true;
            }
        }
        if (!success) {
            throw new FileNotFoundException("Cannot find the specified files");
        }
    }
    private boolean checkFile(String fileName) {
        if (fileName != null) {
            File file = new File(fileName);
            if (file.exists() && !file.isDirectory()) {
                return true;
            }
        }
        return false;
    }
    @Override
    public void countUniqueWords() throws IOException, OutOfMemoryError {
        if (bookFile == null || wordsToIgnoreFile == null) {
            throw new IOException("No file(s) specified");
        }
        // Reset the counters
        uniqueWordCount = 0;
        totalWordCount = 0;
        ignoredWordsTotal = 0;
        // Create the filter class to handle filtering.
        filter = new WordFilter();
        // Read the words to filter.
        filter.readFile(wordsToIgnoreFile);

        // Start reading from the book file using UTF-8.
        FileReader reader = new FileReader(bookFile, StandardCharsets.UTF_8);
        int c;
        // Array holds the code points of the UTF-8 encoded chars.
        int[] array = new int[MAX_WORD_LEN];
        int currentIndex = 0;
        while ((c = reader.read()) != -1) {
            // If the char is a letter, then add it to the array...
            if (Character.isLetter(c)) {
                array[currentIndex] = c;
                currentIndex++;
            } else {
                // ...otherwise a word break was met, so handle the word just read.
                if (currentIndex > 0) {
                    // If a word was actually read, then create a string out of the codepoints,
                    // normalizing the word to lowercase.
                    String word = new String(array, 0, currentIndex).toLowerCase(Locale.ROOT);
                    // Reset the counter for the next word read.
                    currentIndex = 0;
                    addToWords(new WordCount(word, 1));
                }
            }
        }
        // Must check the last word in the file too. There may be chars in the array 
        // not yet handled, when read() returns -1 to indicate EOF.
        if (currentIndex > 1) {
            String word = new String(array, 0, currentIndex).toLowerCase(Locale.ROOT);
            addToWords(new WordCount(word, 1));
        }
        
        // Close the file reader.
        reader.close();
    }


    private void addToWords(WordCount wordcount) throws OutOfMemoryError {
        // Filter out too short words or words in filter list.
        if (!filter.shouldFilter(wordcount.word) && wordcount.word.length() >= 2) {
            if(root==null){
                root=wordcount;
                uniqueWordCount++;
            }else{
                root.insert(wordcount, wordcount.hash);
            }
            totalWordCount++;
        } else {
            ignoredWordsTotal++;
        }
    }

    private void Arrayreallocate(int newSize) throws OutOfMemoryError {
        WordCount[] newWords = new WordCount[newSize];
        for (int index = 0; index < newSize; index++) {
            newWords[index] = words[index];
        }
        words = newWords;
    }



    @Override
    public void report() {
        words=new WordCount[uniqueWordCount];
        if (words == null) {
            System.out.println("*** No words to report! ***");
            return;
        }
        System.out.println("Listing words from a file: " + bookFile);
        System.out.println("Ignoring words from a file: " + wordsToIgnoreFile);
        System.out.println("Sorting the results...");
        indexOfWordCount=0;
        WordCount.binaryTreeToListArray(root);;
        int length=Algorithms.partitionByRule(words, words.length, element->element==null);
        Arrayreallocate(length);
        Algorithms.fastSort(words);
        System.out.println("...sorted.");
        for (int index = 0; index < 100; index++) {
            if (index>=words.length) {
                break;
            }
            String word = String.format("%-20s", words[index].word).replace(' ', '.');
            System.out.format("%4d. %s %6d%n", index + 1, word, words[index].count);
        }
        System.out.println("Count of words in total: " + totalWordCount);
        System.out.println("Count of unique words:    " + uniqueWordCount);
        System.out.println("Count of words to ignore:    " + filter.ignoreWordCount());
        System.out.println("Ignored words count:      " + ignoredWordsTotal);
        System.out.println("Data of the BSearchTree: ");
        System.out.println("Tree has max depth of: " + calculateDepth(root));
        System.out.println("Max ProbingSteps: " + maxProbingSteps);
        
    }
    
    private int calculateDepth(WordCount node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = calculateDepth(node.left);
        int rightDepth = calculateDepth(node.right);
        return 1 + Math.max(leftDepth, rightDepth);
    }

    @Override
    public void close() {
        bookFile = null;
        wordsToIgnoreFile = null;
        words = null;
        root=null;
        if (filter != null) {
            filter.close();
        }
        filter = null;
    }
    @Override
    public int getUniqueWordCount() {
        return uniqueWordCount;
    }
    @Override
    public int getTotalWordCount() {
        return totalWordCount;
    }
    @Override
    public String getWordInListAt(int position) {
        if (words != null && position >= 0 && position < uniqueWordCount) {
            return words[position].word;
        }
        return null;
    }
    @Override
    public int getWordCountInListAt(int position) {
        if (words != null && position >= 0 && position < uniqueWordCount) {
            return words[position].count;
        }
        return -1;
    }
}