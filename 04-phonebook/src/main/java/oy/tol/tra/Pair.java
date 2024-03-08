package oy.tol.tra;

/**
 * Class Pair defines a key-value -pair of objects where each key has an
 * associated value.
 * 
 * Keys must implement the Comparable interface. Equality is determined by
 * comparing keys.
 */
public class Pair<K extends Comparable<K>, V> implements Comparable<Pair<K, V>> {
   private K key;
   private V value;

   public Pair(K key, V value) {
      this.key = key;
      this.value = value;
   }

   public K getKey() {
      return key;
   }

   public void setKey(K key) {
      this.key = key;
   }

   public V getValue() {
      return value;
   }

   public void setValue(V value) {
      this.value = value;
   }

   @Override
   public int compareTo(Pair<K, V> o) {
      return key.compareTo(o.key);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (getClass() != obj.getClass()) {
         return false;
      }
      Pair<?,?> other = (Pair<?,?>) obj;
      if (key == null) {
         if (other.key != null) {
            return false;
         }
      } else if (!key.equals(other.key)) {
         return false;
      }
      return true;
   }

}
