package oy.tol.tra;
import java.util.function.Predicate;
public class Algorithms {
    public static <T extends Comparable<T>> void sort(T[] array) {
        int i = array.length - 1;
        for (int j = i; j > 0; --j) {
            for (int k = 0; k <= j - 1; ++k) {
                if (array[k].compareTo(array[k + 1]) > 0) {
                    swap(array, k, k+1);
                }
            }
        }
    }
    public static <T > void swap(T[] array, int x, int y) {
        T tmp = array[x];
        array[x] = array[y];
        array[y] = tmp;
    }
    // ...
    public static <T> void reverse(T[] array) {
        int i = 0;
        while (i < array.length / 2) {
            swap(array, i, array.length - i - 1);
            i++;
        }

    }

    public static <T extends Comparable<T>> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex) {
        int l = fromIndex - 1;
        int r = toIndex + 1;
        while (l + 1 != r) {
            int mid = (l + r) / 2;
            if (fromArray[mid].compareTo(aValue) >= 0) {
                r = mid;
            } else {
                l = mid;
            }
        }
        if (r >= toIndex + 1) {
            return -1;
        }
        if (!fromArray[r].equals(aValue)) {
            return -1;
        }
        return r;
    }

    private static <E extends Comparable<E>> int partition(E[] array, int begin, int end) {
        // implement partition here...
        E x = array[end];
        int i = begin - 1;
        for (int j = begin; j <= end - 1; ++j) {
            if (array[j].compareTo(x) <= 0) {
                i = i + 1;
                swap(array, i, j);
            }
        }
        swap(array, i+1, end);
        return i + 1;
    }

    public static <E extends Comparable<E>> void quickSort(E[] array, int begin, int end) {
        if (begin < end) {
            int q = partition(array, begin, end);
            quickSort(array, begin, q - 1);
            quickSort(array, q + 1, end);
        }
    }

    public static <E extends Comparable<E>> void fastSort(E[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static <T> int partitionByRule(T[] array, int count, Predicate<T> rule) {
        // Find first element rules applies to.
        // Index of that element will be in variable index.
        int index = 0;
        for (; index < count; index++) {
            if (rule.test(array[index])) {
                break;
            }
        }
        // If went to the end, nothing was selected so quit here.
        if (index >= count) {
            return count;
        }
        // Then start finding not selected elements starting from next from index.
        // If the element is not selected, swap it with the selected one.
        int nextIndex = index + 1;
        // Until end of array reached.
        while (nextIndex != count) {
            if (!rule.test(array[nextIndex])) {
                swap(array, index, nextIndex);
                // If swapping was done, add to index since now it has non-selected element.
                index++;
            }
            nextIndex++;
        }
        return index;
    }
}
