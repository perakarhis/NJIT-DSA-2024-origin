package oy.tol.tra;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ArrayUtils {
   /**
     * Generates an array with specified size and fills it with randomly permutated numbers.
     * 
     * @param size            The size of the array.
     * @return                Array filled with permutated numbers.
     */
    public static Integer [] generateIntegerArray(int size) {
      // DO NOT touch this method!
      Integer [] array = new Integer[size];
      ThreadLocalRandom tlr = ThreadLocalRandom.current();

      for (int counter = 0; counter < size; counter++) {
         array[counter] = counter + 1;
      }
      for (int counter = 0; counter < size - 1; counter++) {
         int indexToSwitch = tlr.nextInt(size);
         if (indexToSwitch != counter) {
            int value = array[indexToSwitch];
            array[indexToSwitch] = array[counter];
            array[counter] = value;
         }
      }
      return array;
  }

  /**
   * Generates an array of random strings.
   * 
   * @param size The size of the string array.
   * @return The array of random strings.
   */
  public static String [] generateStringArray(int size) {
      String [] array = new String[size];

      final int leftLimit = 97; // letter 'a'
      final int rightLimit = 122; // letter 'z'
      int targetStringLength = 10;
      Random random = new Random();
  
      for (int counter = 0; counter < size; counter++) {
          // From https://www.baeldung.com/java-random-string
          array[counter] = random.ints(leftLimit, rightLimit + 1)
              .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
              .limit(targetStringLength)
              .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
              .toString();      
      }
      return array;
  }
}
