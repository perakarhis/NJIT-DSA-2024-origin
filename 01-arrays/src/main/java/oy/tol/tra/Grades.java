package oy.tol.tra;

public class Grades<T extends Comparable<T>> {

   private T[] grades = null;
   public Grades(T[] grades) {
      this.grades = grades.clone();
   }
   /**
<<<<<<< HEAD
    * 
=======
    *
>>>>>>> 47434adae5c33ba265ca4c812421097df94ed50b
    */
   public void reverse() {
      int start = 0;
      int end = grades.length - 1;

      while (start < end) {
         T temp = grades[start];
         grades[start] = grades[end];
         grades[end] = temp;
         start++;
         end--;
      }
   }

   /**
<<<<<<< HEAD
    * 
=======
    *
>>>>>>> 47434adae5c33ba265ca4c812421097df94ed50b
    */
   public void sort() {
      boolean swapped=true;
      while (swapped==true){
         swapped = false;
         for (int i = 1; i < grades.length; i++) {
            if (grades[i - 1].compareTo(grades[i]) > 0) {
               T temp = grades[i - 1];
               grades[i - 1] = grades[i];
               grades[i] = temp;
               swapped = true;
            }
         }
      }
   }
   public T[] getArray() {
      return grades.clone();
   }
}