package oy.tol.tira.books;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;
public class Algorithms {

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
    public static <T extends Comparable<T>> int binarySearch(T aValue, T [] fromArray, int fromIndex, int toIndex) {
        int mid;
        while(fromIndex<=toIndex){
            mid=fromIndex+(toIndex-fromIndex)/2;
            if(aValue.compareTo(fromArray[mid])>0){
                fromIndex=mid+1;
            }else if(aValue.compareTo(fromArray[mid])<0){
                toIndex=mid-1;
            }else{
                return mid;
            }
        }
        return -1;
    }
    public static <E extends Comparable<E>> void fastSort(E [] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static <E extends Comparable<E>> void quickSort(E [] array, int begin, int end) {
        if(begin>=end){
            return;
        }
        int pivot=partition(array, begin, end);
        quickSort(array, begin, pivot-1);
        quickSort(array, pivot+1, end);
    }
    private static <E extends Comparable<E>> int partition(E [] array, int begin, int end) {
        E p=array[begin];
        int left=begin;
        int right=end;
        while(left!=right){
            while ((left<right)&&array[right].compareTo(p)>0) {
                right--;
            }
            while ((left<right)&&array[left].compareTo(p)<=0) {
                left++;
            }
            if(left<right){
                swap(array, left, right);
            }
        }
        array[begin]=array[left];
        array[left]=p;
        return left;
    }


    public static <T> int partitionByRule(T [] pairs,int count,Predicate<T> judgeNullPredicate){
        int left = 0;
        int right = count - 1;

        while (left <= right) {
            while (left <= right && !judgeNullPredicate.test(pairs[left])) {
                left++;
            }

            while (left <= right && judgeNullPredicate.test(pairs[right])) {
                right--;
            }

            if (left < right) {
                swap(pairs, left, right);
                left++;
                right--;
            }
        }
        return left;

    }
    public static <T> void sortWithComparator( T[] array, Comparator<? super T> comparator) {
        Arrays.sort(array, comparator);
    }
    public static <T>void swap(T[] array,int index1,int index2){
        T tmp=array[index1];
        array[index1]=array[index2];
        array[index2]=tmp;
    }
}
