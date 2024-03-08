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
