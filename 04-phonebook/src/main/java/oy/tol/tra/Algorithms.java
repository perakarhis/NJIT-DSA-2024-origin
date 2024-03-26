package oy.tol.tra;
import java.util.function.Predicate;
import java.util.Comparator;

public  class Algorithms{
        public static <T> void sortWithComparator(T[] arr, Comparator<? super T> comparator) {
            int n = arr.length;
            boolean swapped=false;;
            while (swapped) {
                //swapped = false;
                for (int i = 0; i < n - 1; i++) {
                    if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                        T temp = arr[i];
                        arr[i] = arr[i + 1];
                        arr[i + 1] = temp;
                        swapped = true;
                    }
                }
                n--;
            }
        }
        private static <K extends Comparable<K>, V>  void swap(Pair<K, V>[] arr, int i, int j) {
            Pair<K, V> temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    public static <K extends Comparable<K>, V>  void fastSort(Pair<K, V>[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }
    private static <K extends Comparable<K>, V>  void quickSort(Pair<K, V>[] arr, int left, int right) {
        if (left < right) {
            int Index = partition(arr, left, right);
            quickSort(arr, left, Index - 1);
            quickSort(arr, Index + 1, right);
        }
    }
    private static <K extends Comparable<K>, V>  int partition(Pair<K, V>[] arr, int left, int right) {
        Pair<K, V> pivot = arr[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (((Comparable<K>) arr[j].getKey()).compareTo(pivot.getKey()) <= 0) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, right);
        return i + 1;
    }
    public static <K extends Comparable<K>, V> int partitionByRule(Pair<K, V>[]arr,int count,Predicate<Pair<K, V>> rule)
    {
        for(int i=0;i<count;i++)
        {
            if(rule.test(arr[i]))
            {
                for(int j=i;j<count;j++)
                {
                    if(!rule.test(arr[j]))
                    {
                        arr[j]=arr[i];
                        break;
                    }
                }
            }
        }
        for(int i=0;i<count;i++)
        {
            if(rule.test(arr[i]))
            {
                return i;
            }
        }
        return 0;
    }
    public static <K extends Comparable<K>, V>void mergeSort(Pair<K, V>[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        Pair<K, V>[] sorted = new Pair[arr.length];
        mergeSort(arr, sorted, 0, arr.length - 1);
    }
    private static<K extends Comparable<K>, V> void mergeSort(Pair<K, V>[] arr, Pair<K, V>[] sorted, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(arr, sorted, start, mid);
            mergeSort(arr, sorted, mid + 1, end);
            merge(arr, sorted, start, mid, end);
        }
    }
    private static  <K extends Comparable<K>, V> void merge(Pair<K, V>[] arr, Pair<K, V>[] sorted, int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        int k = start;
        while (i <= mid && j <= end) {
            if (arr[i].getKey().compareTo(arr[j].getKey()) <= 0) {
                sorted[k++] = arr[i++];
            } else {
                sorted[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            sorted[k++] = arr[i++];
        }
        while (j <= end) {
            sorted[k++] = arr[j++];
        }
        for (k = start; k <= end; k++) {
            arr[k] = sorted[k];
        }
    }
    public static <K extends Comparable<K>, V> void bubbleSort(Pair<K, V>[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    Pair<K, V> temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    public static <K extends Comparable<K>, V> void insertionSort (Pair<K, V>[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            Pair<K, V> key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].getKey().compareTo(key.getKey()) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
  }