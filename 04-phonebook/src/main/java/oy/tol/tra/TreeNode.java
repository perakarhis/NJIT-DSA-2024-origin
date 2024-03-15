package oy.tol.tra;

class TreeNode<K extends Comparable<K>, V> {

   private int hash = -1;
   Pair<K,V> keyValue;
   TreeNode<K, V> left = null;
   TreeNode<K, V> right = null;
   static int currentAddTreeDepth = 0;
   static int longestCollisionChain = 0;
   // OPTIONAL Handling collisions with a linked list in the tree node.
   LinkedListImplementation<Pair<K,V>> list = null;
   
   // Needed for searching by key; value is not then needed.
   TreeNode(K key) throws NullPointerException {
      if (null == key)
         throw new NullPointerException("K cannot be null");
      keyValue = new Pair<>(key, null);
      this.hash = key.hashCode();
      left = null;
      right = null;
      list = null;
   }

   TreeNode(K toAdd, V value) throws NullPointerException {
      if (null == toAdd)
         throw new NullPointerException("K cannot be null");
      keyValue = new Pair<>(toAdd, value);
      this.hash = toAdd.hashCode();
      left = null;
      right = null;
      list = null;
   }

   V find(K toFind, int toFindHash) {
      if (this.hash == toFindHash) {
         if (this.keyValue.getKey().equals(toFind)) {
            // same key, so we found them
            return this.keyValue.getValue();
         } else {
            // OPTIONAL different key have the same hash, keep looking from the linked list.
            if (null != list) {
               int index = list.indexOf(new Pair<K,V>(toFind, null));
               if (index >= 0) {
                  return list.get(index).getValue();
               }
            }
            // END OPTIONAL 
         }
      } else if (toFindHash < this.hash) {
         if (null != left) {
            return left.find(toFind, toFindHash);
         }
      } else if (toFindHash > this.hash) {
         if (null != right) {
            return right.find(toFind, toFindHash);
         }
      }
      return null;
   }

   int insert(K key, V value, int toInsertHash) throws RuntimeException {
      if (null == key || null == value) {
         throw new NullPointerException("Keys or values cannot be null");
      }
      int added = 0;
      TreeNode.currentAddTreeDepth++;
      if (toInsertHash < this.hash) {
         if (null == left) {
            left = new TreeNode<>(key, value);
            added = 1;
         } else {
            added = left.insert(key, value, toInsertHash);
         }
      } else if (toInsertHash > this.hash) {
         if (null == right) {
            right = new TreeNode<>(key, value);
            added = 1;
         } else {
            added = right.insert(key, value, toInsertHash);
         }
      } else { // equal hashes
         if (this.keyValue.getKey().equals(key)) {
            // Key-value pair already in tree, update the value for the key.
            this.keyValue = new Pair<>(key, value);
         } else {
            // OPTIONAL different key, same hash, put in the linked list.
            if (null == list) {
               list = new LinkedListImplementation<>();
               list.add(new Pair<>(key, value));
            } else {
               Pair<K,V> newItem = new Pair<>(key, value);
               int index = list.indexOf(newItem);
               if (index < 0) {
                  list.add(newItem);
               } else {
                  list.remove(index);
                  list.add(newItem);
               }
            }
            added = 1;
            if (list.size() > TreeNode.longestCollisionChain) {
               TreeNode.longestCollisionChain = list.size();
            }
            // END OPTIONAL
         }
      }
      return added;
   }

   public void accept(Visitor<K, V> visitor) {
      visitor.visit(this);
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
      TreeNode<?,?> other = (TreeNode<?,?>) obj;
      if (keyValue == null) {
         if (other.keyValue != null) {
            return false;
         }
      } else if (!keyValue.equals(other.keyValue)) {
         return false;
      }
      return true;
   }
}
