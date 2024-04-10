
package oy.tol.tra;


public class Algorithms {
    public static <T extends Comparable<T>> void sort(T[] array) {
        int n = array.length;
        boolean swapped = true;

        while(swapped) {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (array[i - 1].compareTo(array[i]) > 0) {
                    swap(array, i - 1, i);
                    swapped = true;
                }
            }
        }
    }

    public static <T> void reverse(T[] array) {
        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            swap(array, left, right);
            left++;
            right--;
        }
    }

    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
