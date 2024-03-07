package oy.tol.tra;

/**
 * A generic and slow Key-Value linear array.
 */
public class KeyValueArray<K extends Comparable<K>, V> implements Dictionary<K,V> {

   private Pair<K, V> [] pairs = null;
   private int count = 0;
   private int reallocationCount = 0;

   public KeyValueArray(int capacity) {
      ensureCapacity(capacity);
   }

   public KeyValueArray() {
      ensureCapacity(20);
   }

   @Override
   public Type getType() {
      return Type.SLOW;
   }

   @SuppressWarnings("unchecked")
   @Override
   public void ensureCapacity(int size) throws OutOfMemoryError {
      if (size < 20) {
         size = 20;
      }
      pairs = (Pair<K,V>[])new Pair[size];
      reallocationCount = 0;
   }

   @Override
   public int size() {
      return count;
   }

   @Override
   public String getStatus() {
      String toReturn = "KeyValueArray reallocated " + reallocationCount + " times, each time doubles the size\n";
      toReturn += String.format("KeyValueArray fill rate is %.2f%%%n", (count / (double)pairs.length) * 100.0);
      return toReturn;
   }

   @Override
   public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {
      if (null == key || value == null) throw new IllegalArgumentException("Person or phone number cannot be null");
      for (Pair<K, V> pair : pairs) {
         // Must not have duplicate keys, so check if key is already in the array.
         if (pair != null && pair.getKey().equals(key)) {
            pair.setValue(value);
            return true;
         }
      }
      if (count >= pairs.length) {
         reallocate(pairs.length * 2);
      }
      if (count < pairs.length) {
         pairs[count++] = new Pair<>(key, value);
         return true;
      }
      return false;
   }

   @Override
   public V find(K key) throws IllegalArgumentException {
      if (null == key) throw new IllegalArgumentException("Person to find cannot be null");
      for (int counter = 0; counter < count; counter++) {
         if (pairs[counter] != null && key.equals(pairs[counter].getKey())) {
            return pairs[counter].getValue();
         }
      }
      return null;
   }

   @Override
   @java.lang.SuppressWarnings({"unchecked"})
   public Pair<K,V> [] toSortedArray() {
      Pair<K, V> [] sorted = (Pair<K,V>[])new Pair[count];
      int newIndex = 0;
      for (int index = 0; index < count; index++) {
         if (pairs[index] != null) {
            sorted[newIndex++] = new Pair<>(pairs[index].getKey(), pairs[index].getValue());
         }
      }
      Algorithms.fastSort(sorted);
      return sorted;
   }

   @Override
   public void compress() throws OutOfMemoryError {
      // First partition the null's to the end of the array.
      int indexOfFirstNull = Algorithms.partitionByRule(pairs, count, element -> element == null);
      // Then reallocate using the index from partitioning, pointing the first null in the array.
      reallocate(indexOfFirstNull);
   }

   @java.lang.SuppressWarnings("unchecked")
   private void reallocate(int newSize) throws OutOfMemoryError {
      reallocationCount++;
      Pair<K, V> [] newPairs = (Pair<K,V>[])new Pair[newSize];
      for (int index = 0; index < count; index++) {
         newPairs[index] = pairs[index];
      }
      pairs = newPairs;
   }

}
