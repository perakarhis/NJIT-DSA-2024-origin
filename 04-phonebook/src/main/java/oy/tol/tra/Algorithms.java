
package oy.tol.tra;

import java.util.function.Predicate;

public class Algorithms {
    public static <T extends Comparable<T>> void sort(T[] array) {
        int n = array.length;
        boolean swapped;
        
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (array[i - 1].compareTo(array[i]) > 0) {
                    // Swap elements if they are in the wrong order
                    T temp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
    }

    public static <T> void reverse(T[] array) {
        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            T temp = array[left];
            array[left] = array[right];
            array[right] = temp;

            left++;
            right--;
        }
    }
    public static <T extends Comparable<T>> int binarySearch(T value, T[] array, int fromIndex, int toIndex) {
        if (fromIndex <= toIndex) {
            int mid = fromIndex + (toIndex - fromIndex) / 2;
            int comparisonResult = value.compareTo(array[mid]);
    
            if (comparisonResult == 0) {
                return mid; // Finding a match
            } else if (comparisonResult < 0) {
                return binarySearch(value, array, fromIndex, mid - 1); // continue to search on the left 
            } else {
                return binarySearch(value, array, mid + 1, toIndex); // continue to search on the right
            }
        }
    
        return -1; // no match
    }
    
    public static <E extends Comparable<E>> void fastSort(E[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static <E extends Comparable<E>> void quickSort(E[] array, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(array, begin, end);
            quickSort(array, begin, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, end);
        }
    }

    private static <E extends Comparable<E>> int partition(E[] array, int begin, int end) {
        E pivot = array[end];
        int i = begin - 1;

        for (int j = begin; j < end; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                E temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        E temp = array[i + 1];
        array[i + 1] = array[end];
        array[end] = temp;

        return i + 1;
    }
    public static <K extends Comparable<K>, V> int partitionByRule(Pair<K, V>[] pairs, int count, Predicate judgeNullPredicate) {
        if (pairs == null || count <= 0) {
            return 0;
        }

        int left = 0;
        int right = count - 1;

        while (left <= right) {
            if (pairs[left] == null) {
                swap(pairs, left, right);
                right--;
            } else {
                left++;
            }
        }

        return left;
    }

    private static <K extends Comparable<K>, V> void swap(Pair<K, V>[] pairs, int i, int j) {
        Pair<K, V> temp = pairs[i];
        pairs[i] = pairs[j];
        pairs[j] = temp;
    }

    public static void sortWithComparator(Person[] array, AscendingPersonComparator ascendingPersonComparator) {
    }

    public static void sortWithComparator(Person[] array, DescendingPersonComparator descendingPersonComparator) {
    }
}
