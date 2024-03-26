package oy.tol.tra;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@DisplayName("Testing the Generic algorithms.")
public class GenericTests {
   
   @Test
   @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
   @DisplayName("Testing the Generic reverse() with integers") 
   void reverseTestInteger() {
      Integer [] testArray = getArrayWithNumbers();

      List<Integer> list = new ArrayList<Integer>(Arrays.asList(testArray));
      Collections.reverse(list);
      Integer [] expectedReversedArray = new Integer [list.size()];
      list.toArray(expectedReversedArray);

      System.out.format("==> Reverse test array has %d elements%n", testArray.length);
      System.out.println("testArray: " + Arrays.toString(testArray));
      Algorithms.reverse(testArray);
      System.out.println("Reversed:  " + Arrays.toString(testArray));
      assertTrue(Arrays.equals(expectedReversedArray, testArray), () -> "Reversed array is not correct!");
      System.out.println("-- Reverse test finished");
   }

   @Test
   @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
   @DisplayName("Testing the Generic reverse() with strings") 
   void reverseTestString() {
      String [] testArray = getArrayWithStrings();

      List<String> list = new ArrayList<String>(Arrays.asList(testArray));
      Collections.reverse(list);
      String [] expectedReversedArray = new String [list.size()];
      list.toArray(expectedReversedArray);
      
      System.out.format("==> Reverse test array has %d elements%n", testArray.length);
      System.out.println("testArray: " + Arrays.toString(testArray));
      Algorithms.reverse(testArray);
      System.out.println("Reversed:  " + Arrays.toString(testArray));
      assertTrue(Arrays.equals(expectedReversedArray, testArray), () -> "Reversed array is not correct!");
      System.out.println("-- Reverse test finished");
   }


   @Test
   @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
   @DisplayName("Testing the Generic sort() with integers")
   void sortTestInteger() {
      Integer [] testArray = getArrayWithNumbers();
      
      List<Integer> list = new ArrayList<Integer>(Arrays.asList(testArray));
      Collections.sort(list);
      Integer [] correctlySorteddArray = new Integer [list.size()];
      list.toArray(correctlySorteddArray);

      System.out.format("==> Sort test array has %d elements%n", testArray.length);
      System.out.println("testArray: " + Arrays.toString(testArray));
      Algorithms.sort(testArray);
      System.out.println("Sorted:  " + Arrays.toString(testArray));
      assertTrue(Arrays.equals(correctlySorteddArray, testArray), () -> "Sorted array is not correct!");
      System.out.println("-- Sort test finished");
   }

   @Test
   @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
   @DisplayName("Testing the Generic sort() with strings")
   void sortTestString() {
      String [] testArray = getArrayWithStrings();
      
      List<String> list = new ArrayList<String>(Arrays.asList(testArray));
      Collections.sort(list);
      String [] correctlySorteddArray = new String [list.size()];
      list.toArray(correctlySorteddArray);

      System.out.format("==> Sort test array has %d elements%n", testArray.length);
      System.out.println("testArray: " + Arrays.toString(testArray));
      Algorithms.sort(testArray);
      System.out.println("Sorted:  " + Arrays.toString(testArray));
      assertTrue(Arrays.equals(correctlySorteddArray, testArray), () -> "Sorted array is not correct!");
      System.out.println("-- Sort test finished");
   }

   private Integer [] getArrayWithNumbers() {
      int size = ThreadLocalRandom.current().nextInt(5) + 5;
      Integer [] array = new Integer[size];
      for (int i = 0; i < size; i++) {
         array[i] = ThreadLocalRandom.current().nextInt(10);
      }
      return array;
   }

   private String [] getArrayWithStrings() {
      int leftLimit = 48; // numeral '0'
      int rightLimit = 122; // letter 'z'
      int targetStringLength = 5;
      final Random random = new Random();
      
      int size = ThreadLocalRandom.current().nextInt(5) + 5;
      String [] array = new String[size];

      for (int i = 0; i < size; i++) {
         String generatedString = random.ints(leftLimit, rightLimit + 1)
         .filter(number -> (number <= 57 || number >= 65) && (number <= 90 || number >= 97))
         .limit(targetStringLength)
         .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
         .toString();
         array[i] = generatedString;
       }

      return array;
  }

}
