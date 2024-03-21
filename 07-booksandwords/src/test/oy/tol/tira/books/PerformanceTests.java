package oy.tol.tira.books;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

//@Timeout(1440)
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Tests using different Book files growing in size")
public class PerformanceTests {
    
    private static final String outputFileName = "compare.csv";
    private static final String separator = ",";
    private static int currentIndex = 0;
    private static BufferedWriter writer = null;
    private static final String[] testFiles = {"word.txt","tiny.txt","small.txt","MetaMorph.txt","Species.txt","Ulysses.txt","WarPeace.txt","Bulk.txt", "sapmirussian.txt", "japanese.txt"};
    
    @BeforeAll
    static void openOutputFile() {
        try {
            writer = new BufferedWriter(new FileWriter(outputFileName));
            writer.append("testfile,bytes,words,unique words,time (ms),nˆ2,n*m,lg(n*m), (n*m)*lg(n*m)");
            writer.newLine();
        } catch (IOException e) {
            fail("Could not open test output file for writing");
        }
    }

    @Test 
    void handleTestFiles() {
        if (null != writer) {
            try {
                while (currentIndex < testFiles.length) {
                    System.out.println("==> Starting to analyse book " + testFiles[currentIndex]);
                    String path = getFullPathToTestFile(testFiles[currentIndex]);
                    File file = new File(path);
                    long fileSize = file.length();
                    file = null;
                    Book testBook = BookFactory.createBook();
                    assertNotNull(testBook, () -> "BookFactory.createBook() returned null.");
                    String ignoreFile = getFullPathToTestFile("ignore-words.txt");
                    long start = System.currentTimeMillis();
                                
                    testBook.setSource(path, ignoreFile);
                    testBook.countUniqueWords();
                    testBook.report();
                    long end = System.currentTimeMillis();
                    long duration = end - start;
                    
                    /*
                    testfile,bytes,words,unique words,time,nˆ2,n*m,log(n*m), n*log(n*m)
                    */
                    long totalWords = testBook.getTotalWordCount();
                    long uniqueWords = testBook.getUniqueWordCount();
                    long totalTimesUnique = Math.multiplyExact(totalWords, uniqueWords);
                    long base2LogTotalTimesUnique = (long)(Math.log10(totalTimesUnique) / Math.log10(2));
                    writer.append(testFiles[currentIndex]);
                    writer.append(separator);
                    writer.append(Long.toString(fileSize));
                    writer.append(separator);
                    writer.append(Long.toString(totalWords));
                    writer.append(separator);
                    writer.append(Long.toString(uniqueWords));
                    writer.append(separator);
                    writer.append(Long.toString(duration));
                    writer.append(separator);
                    writer.append(Long.toString((long)Math.pow((double)totalWords,2.0)));
                    writer.append(separator);
                    writer.append(Long.toString(totalTimesUnique));
                    writer.append(separator);
                    writer.append(Long.toString(base2LogTotalTimesUnique));
                    writer.append(separator);
                    writer.append(Long.toString(Math.multiplyExact(totalTimesUnique, base2LogTotalTimesUnique)));
                    writer.newLine();
                    writer.flush();
                    testBook.close();
                    testBook = null;
                    currentIndex++;
                }
            } catch (Exception e) {
                fail("Could not write test output file: " + e.getMessage());
            }    
        } else {
            fail("Cannot run tests since opening output file writer failed.");
        }
    }

    @AfterAll
    static void closeOutputFile() {
        try {
            writer.close();
        } catch (IOException e) {
            fail("Could not close test output file");
        }
    }

    private String getFullPathToTestFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return file.getAbsolutePath();
    }
}
