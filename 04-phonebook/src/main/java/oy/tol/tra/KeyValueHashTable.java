package oy.tol.tra;

import java.util.Arrays;

public class KeyValueHashTable<K extends Comparable<K>, V> implements Dictionary<K, V> {

    // This should implement a hash table.

    private Pair<K, V>[] values = null;
    private int count = 0;
    private int collisionCount = 0;
    private int maxProbingSteps = 0;
    private int reallocationCount = 0;
    private static final double LOAD_FACTOR = 0.45;
    private static final int DEFAULT_CAPACITY = 1000;
    boolean er=true;

    public KeyValueHashTable(int capacity) throws OutOfMemoryError {
        ensureCapacity(capacity);
    }

    public KeyValueHashTable() throws OutOfMemoryError {
        ensureCapacity(DEFAULT_CAPACITY);
    }

    @Override
    public Type getType() {
        return Type.HASHTABLE;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void ensureCapacity(int capacity) throws OutOfMemoryError {
        if (capacity < DEFAULT_CAPACITY) {
            capacity = DEFAULT_CAPACITY;
        }
        // Assuming capacity means the count of elements to add, so multiplying by fill factor.
        values = (Pair<K, V>[]) new Pair[(int) ((double) capacity * (1.0 + LOAD_FACTOR))];
        reallocationCount = 0;
        count = 0;
        collisionCount = 0;
        maxProbingSteps = 0;
    }

    @Override
    public int size() {
        // TODO: Implement this.
        return count;
    }
    @Override
    public String getStatus() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Hash table load factor is %.2f%n", LOAD_FACTOR));
        builder.append(String.format("Hash table capacity is %d%n", values.length));
        builder.append(String.format("Current fill rate is %.2f%%%n", (count / (double)values.length) * 100.0));
        builder.append(String.format("Hash table had %d collisions when filling the hash table.%n", collisionCount));
        builder.append(String.format("Hash table had to probe %d times in the worst case.%n", maxProbingSteps));
        builder.append(String.format("Hash table had to reallocate %d times.%n", reallocationCount));
        return builder.toString();
    }

    @Override
    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {
        // TODO: Implement this.
        // Remeber to check for null values.
        
        if(key==null||value==null)
        {
            throw new IllegalArgumentException();
        }
        if (((double)count * (1.0 + LOAD_FACTOR)) >= values.length) {
            reallocate((int)((double)(values.length) * (1.0 / LOAD_FACTOR)));
        }
        
        if(values[Math.abs(key.hashCode())%values.length]==null)
        {
             values[Math.abs(key.hashCode())%values.length]=new Pair<K,V>(key, value);
             count++;
        }
        else if(values[Math.abs(key.hashCode())%values.length]!=null&&values[Math.abs(key.hashCode())%values.length].getKey().equals(key))
        {
            values[Math.abs(key.hashCode())%values.length].setValue(value);
        }
        
        else if(values[Math.abs(key.hashCode())%values.length]!=null&&!values[Math.abs(key.hashCode())%values.length].getKey().equals(key))
        {
            for(int i=Math.abs(key.hashCode())%values.length+1;i<values.length;i++)
            {
               if(values[i]==null)
               {
                values[i]=new Pair<K,V>(key, value);
                count++;
                er=false;
                break;
               }
            }
            if (er) {
                reallocate((int)((double)(values.length) * (1.0 / LOAD_FACTOR)));
                add(key,value);
            }
            er=true;
        }
        return true;
    }


    @Override
    public V find(K key) throws IllegalArgumentException {
        // Remember to check for null.
        if(key==null)
        {
            throw new IllegalArgumentException();
        }
        if(values[Math.abs(key.hashCode())%values.length]==null)
        {
            return null;
        }
         else
          {
             if (key.equals(values[Math.abs(key.hashCode())%values.length].getKey())) {
        return values[Math.abs(key.hashCode())%values.length].getValue();
    }
    else{
        for(int i=Math.abs(key.hashCode())%values.length+1;i<values.length;i++)
        {
            if(values[i]==null){
                 return null;
            }
            else if(key.equals(values[i].getKey()))
            {
                return values[i].getValue();
            }
        }
    }
          }
          
        // Must use same method for computing index as add method
        return null;
        
        
    }

    @Override
    @java.lang.SuppressWarnings({"unchecked"})
    public Pair<K,V> [] toSortedArray() {
        Pair<K, V> [] sorted = (Pair<K,V>[])new Pair[count];
        int newIndex = 0;
        for (int index = 0; index < values.length; index++) {
           if (values[index] != null) {
              sorted[newIndex++] = new Pair<>(values[index].getKey(), values[index].getValue());
           }
        }
       Algorithms.mergeSort(sorted);
        
        return sorted;
      }

    @SuppressWarnings("unchecked")
    private void reallocate(int newSize) throws OutOfMemoryError {
        if (newSize < DEFAULT_CAPACITY) {
            newSize = DEFAULT_CAPACITY;
        }
        reallocationCount++;
        Pair<K, V>[] oldPairs = values;
        this.values = (Pair<K, V>[]) new Pair[(int)((double)newSize * (1.0 + LOAD_FACTOR))];
        count = 0;
        collisionCount = 0;
        maxProbingSteps = 0;
        for (int index = 0; index < oldPairs.length; index++) {
            if (oldPairs[index] != null) {
                add(oldPairs[index].getKey(), oldPairs[index].getValue());
            }
        }
    }

    @Override
    public void compress() throws OutOfMemoryError {
        int newCapacity = (int)(count * (1.0 / LOAD_FACTOR));
		    if (newCapacity < values.length) {
			      reallocate(newCapacity);
		    } 
    }
 
}