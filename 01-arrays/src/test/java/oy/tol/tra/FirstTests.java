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

@DisplayName("Testing the IntArray, First test.")
public class FirstTests {
   
   @Test
   @DisplayName("Testing the IntArray.reverse()") 
   void reverseTest() {
      Integer [] testArray = getArrayWithThreeNumbers();

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
   @DisplayName("Testing the IntArray.sort()")
   void sortTest() {
      Integer [] testArray = getArrayWithThreeNumbers();
      
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

   private Integer [] getArrayWithThreeNumbers() {
      int size = 3;
      Integer [] array = new Integer[size];
      array[0] = ThreadLocalRandom.current().nextInt(10) + 2;
      array[1] = array[0] + ThreadLocalRandom.current().nextInt(3) + 1;
      array[2] = array[0] + 1;
      return array;
   }

}
