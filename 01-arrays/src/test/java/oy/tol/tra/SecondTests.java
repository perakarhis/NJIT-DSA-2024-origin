package oy.tol.tra;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@DisplayName("Testing the IntArray, second tests.")
public class SecondTests {
   
   @Test
   @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
   @DisplayName("Testing the IntArray.reverse()") 
   void reverseTest() {
      Integer [] testArray = getArrayWithNumbers();

      List<Integer> list = new ArrayList<Integer>(Arrays.asList(testArray));
      Collections.reverse(list);
      Integer [] expectedReversedArray = new Integer [list.size()];
      list.toArray(expectedReversedArray);
      
      Grades toTest = new Grades(testArray);
      toTest.reverse();
      System.out.format("==> Reverse test array has %d elements%n", testArray.length);
      System.out.println("testArray: " + Arrays.toString(testArray));
      System.out.println("Reversed:  " + Arrays.toString(toTest.getArray()));
      assertTrue(Arrays.equals(expectedReversedArray, toTest.getArray()), () -> "Reversed array is not correct!");
      System.out.println("-- Reverse test finished");
   }

   @Test
   @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
   @DisplayName("Testing the IntArray.sort()")
   void sortTest() {
      Integer [] testArray = getArrayWithNumbers();
      
      List<Integer> list = new ArrayList<Integer>(Arrays.asList(testArray));
      Collections.sort(list);
      Integer [] correctlySorteddArray = new Integer [list.size()];
      list.toArray(correctlySorteddArray);

      Grades toTest = new Grades(testArray);
      toTest.sort();
      System.out.format("==> Sort test array has %d elements%n", testArray.length);
      System.out.println("testArray: " + Arrays.toString(testArray));
      System.out.println("Sorted:  " + Arrays.toString(toTest.getArray()));
      assertTrue(Arrays.equals(correctlySorteddArray, toTest.getArray()), () -> "Sorted array is not correct!");
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

}
