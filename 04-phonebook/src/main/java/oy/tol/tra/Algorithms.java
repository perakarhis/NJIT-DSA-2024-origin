package oy.tol.tra;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;
public class Algorithms {
    public static <T extends Comparable<T>> void sort(T [] array) {
        int i = 0;
        int j = 0;
        for(i = 0; i < array.length - 1; i++){
            for(j = 0; j < array.length - 1 - i; j++){
                if (array[j].compareTo(array[j+1])>0) {
                    T tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                    }
                 } 
              }
            }
    public static <T> void reverse(T [] array) {
        int m = 0;
        while (m < array.length/2) {
            T temp = array[m];
            array[m] = array[array.length-m-1];
            array[array.length-m-1] = temp;
            m++;
        }
    }
    public static <T extends Comparable<T>> int binarySearch(T aValue, T [] fromArray, int fromIndex, int toIndex) {
        if (fromIndex > toIndex){
            return -1;
        }
        int mid = (fromIndex + toIndex)/2;
        if (aValue.compareTo(fromArray[mid]) == 0){
            return mid;
        }
        if (aValue.compareTo(fromArray[mid]) < 0){
            return binarySearch(aValue, fromArray, fromIndex, mid - 1);
        }else{
            return binarySearch(aValue, fromArray, mid + 1, toIndex);
        }   
    }

    public static <E extends Comparable<E>> void fastSort(E [] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static <E extends Comparable<E>> void quickSort(E [] array, int begin, int end) {
        if (begin < end){
            int pivot = partition(array,begin,end);
            quickSort(array, begin, pivot-1);
            quickSort(array, pivot+1, end);
        }
    }

     private static <E extends Comparable<E>> int partition(E [] array, int start, int end) {
        int i = start - 1;
        while (start < end){
            if (array[start].compareTo(array[end]) < 0){
                i++;
                E temp = array[i];
                array[i] = array[start];
                array[start] = temp;
            }
            start++;
        }
        E temp = array[i + 1];
        array[i + 1] = array[end];
        array[end] = temp;
        return i + 1;
    }
    public static <E extends Comparable<E>> int partitionByRule(E [] array, int count, Predicate<E> rule) {
        int index = 0;
        for(int i = 0; i < array.length; i++){
            if(array[i] != null){
                if(i != index){
                    E temp = array[i];
                    array[i] = array[index];
                    array[index] = temp;
                }
                index++;
            }
        }
        return index;
    }

    public static <T> void sortWithComparator(T[] array, Comparator<? super T> comparator) {  
        Arrays.sort(array, comparator);  
    } 
}
