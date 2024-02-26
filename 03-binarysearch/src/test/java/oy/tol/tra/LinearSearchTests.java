package oy.tol.tra;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.ThreadLocalRandom;

@DisplayName("Basic tests for the linear search method.")
public class LinearSearchTests {

    private static final int NUMBERS_TO_SEARCH = 10;

    @Test
    @Timeout(value = 120, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    @DisplayName("Tests Integer linear search implementation")
    void findFromIntArrayTests() {
        try {
            System.out.println("-- Starting the linear integer search test --");
            Integer [] array = ArrayUtils.generateIntegerArray(9999999);
            ThreadLocalRandom tlr = ThreadLocalRandom.current();

            int counter = NUMBERS_TO_SEARCH;
            long start = 0;
            long linearDuration = 0;
            while (counter-- >= 0) {
                int toFind = tlr.nextInt(1, array.length);
                start = System.nanoTime();
                int linearIndex = SearchArray.slowLinearSearch(toFind, array, 0, array.length - 1);
                assertTrue(linearIndex >= 0, () -> "Binary search could not find the element to search");
                linearDuration += System.nanoTime() - start;
                System.out.println("Index of " + toFind + " is: " + linearIndex);    
            }
            linearDuration /= NUMBERS_TO_SEARCH;
            
            System.out.println("----------------------------------------------");
            System.out.format("Average linear search duration: %10d ns%n", linearDuration);
        } catch (Exception e) {
            fail("Something went wrong in the tests: " + e.getMessage());
        }
    }

    @Test
    @Timeout(value = 120, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    @DisplayName("Tests String linear search implementation")
    void findFromStringArrayTests() {
        try {
            System.out.println("-- Starting the linear string search  test --");
            String [] array = ArrayUtils.generateStringArray(9999999);
            ThreadLocalRandom tlr = ThreadLocalRandom.current();

            int counter = NUMBERS_TO_SEARCH;
            long start = 0;
            long linearDuration = 0;
            while (counter-- >= 0) {
                int toFind = tlr.nextInt(1, array.length);
                start = System.nanoTime();
                int linearIndex = SearchArray.slowLinearSearch(array[toFind], array, 0, array.length - 1);
                assertTrue(linearIndex >= 0, () -> "Binary search could not find the element to search");
                linearDuration += System.nanoTime() - start;
                System.out.println("Index of " + array[toFind] + " is: " + linearIndex);    
            }
            linearDuration /= NUMBERS_TO_SEARCH;
            
            System.out.println("----------------------------------------------");
            System.out.format("Average linear search duration: %10d ns%n", linearDuration);
        } catch (Exception e) {
            fail("Something went wrong in the tests: " + e.getMessage());
        }
    }
    
}
