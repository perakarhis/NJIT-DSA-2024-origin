
package oy.tol.tra;

public class Algorithms {
    public static <T>void swap(T[] array,int index1,int index2){
        T tmp=array[index1];
        array[index1]=array[index2];
        array[index2]=tmp;
    }
    public static <T extends Comparable<T>> void sort(T [] array){
        for (int i = 0; i < array.length-1; i++) {
            for (int j = 0; j < array.length-i-1; j++) {
                if(array[j].compareTo(array[j+1])>0){
                    swap(array, j, j+1);
                }
            }
        }
    }
    public static <T> void reverse(T [] array){
        int left=0;
        int right=array.length-1;
        while(left<right){
            swap(array,left,right);
            left++;
            right--;
        }
    }
    public static <T extends Comparable<T>> int binarySearch(T value, T[] array, int fromIndex, int toIndex) {
        if (fromIndex <= toIndex) {
            int mid = fromIndex + (toIndex - fromIndex) / 2;
            int result = value.compareTo(array[mid]);
    
            if (result == 0) {
                return mid; 
            } else if (result < 0) {
                return binarySearch(value, array, fromIndex, mid - 1); 
            } else {
                return binarySearch(value, array, mid + 1, toIndex); 
            }
        }
        return -1; 
    }

    public static <E extends Comparable<E>> void fastSort(E [] array) {
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
}