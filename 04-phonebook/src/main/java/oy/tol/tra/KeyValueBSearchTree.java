package oy.tol.tra;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class KeyValueBSearchTree<K extends Comparable<K>, V> implements Dictionary<K, V> {

    // This is the BST implementation, KeyValueHashTable has the hash table
    // implementation

    private TreeNode<K, V> root=null;
    private int count = 0;
    private int maxTreeDepth = 0;

    @Override
    public Type getType() {
        return Type.BST;
    }

    @Override
    public int size() {
        return count;
    }

   @Override
    public String getStatus() {
        StringBuilder toReturn1=new StringBuilder("Tree has max depth of " + maxTreeDepth + ".\n");
        toReturn1.append("Longest collision chain in a tree node is " + TreeNode.longestCollisionChain + "\n");
        TreeAnalyzerVisitor<K, V> visitor = new TreeAnalyzerVisitor<>();
        root.accept(visitor);
        toReturn1.append("Min path height to bottom: " + visitor.minHeight + "\n");
        toReturn1.append("Max path height to bottom: " + visitor.maxHeight + "\n");
        toReturn1.append("Ideal height if balanced: " + Math.ceil(Math.log(count)) + "\n");
        String toReturn = toReturn1.toString();
        return toReturn;
    }

    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {
        if(key==null||value==null)
        {
            throw new IllegalArgumentException("This is a custom NullPointerException message");
        }
        if(root==null)
        {
            root=new TreeNode<K,V>(key, value);
            count++;
            return true;
        }
        else {
           count+= root.insert(key,value,key.hashCode());
           return true;
        }
        }
   
    @Override
    public void ensureCapacity(int size) throws OutOfMemoryError {
        // Nothing to do here. Trees need no capacity.
    }
  
public Pair<K, V>[] toSortedArray() {
    TreeToArrayVisitor<K, V> visitor = new TreeToArrayVisitor<>(count);
    root.accept(visitor);
    Pair<K, V>[] sorted = visitor.getArray();
    Algorithms.mergeSort(sorted);
    return sorted;
}

public V find(K key) {
    return root.find(key, key.hashCode());
}



    @Override
    public void compress() throws OutOfMemoryError {
    }
    
}