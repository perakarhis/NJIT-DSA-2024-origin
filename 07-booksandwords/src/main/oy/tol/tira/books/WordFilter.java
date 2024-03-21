package oy.tol.tira.books;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

class WordFilter {

    private String[] wordsToFilter = null;
    private int ignoreWordCount = 0;
    private static final int ARRAY_SIZE = 100;

    void readFile(String fileName) throws IOException {
        wordsToFilter = new String[ARRAY_SIZE];
        File file = new File(fileName);
        FileReader fileReader = new FileReader(file, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        ignoreWordCount = 0;
        while ((line = bufferedReader.readLine()) != null) {
            line = line.toLowerCase(Locale.ROOT);
            String[] items = line.split(",");
            if (ignoreWordCount + items.length > wordsToFilter.length) {
                reallocateArray();
            }
            for (int count = 0; count < items.length; count++) {                
                wordsToFilter[ignoreWordCount++] = items[count];
            }
        }
        fileReader.close();
    }

    int ignoreWordCount() {
        return ignoreWordCount;
    }
    
    boolean shouldFilter(String word) {
        for (int count = 0; count < wordsToFilter.length; count++) {
            if (wordsToFilter[count] == null) {
                return false;
            }
            if (wordsToFilter[count].equals(word)) {
                return true;
            }
        }
        return false;
    }

    void close() {
        wordsToFilter = null;
        ignoreWordCount = 0;
    }

    private void reallocateArray() {
        int newSize = wordsToFilter.length * 2;
        String[] newArray = new String[newSize];
        for (int oldArrayIndex = 0; oldArrayIndex < ignoreWordCount; oldArrayIndex++) {
            newArray[oldArrayIndex] = wordsToFilter[oldArrayIndex];
        }
        wordsToFilter = newArray;
    }

}
