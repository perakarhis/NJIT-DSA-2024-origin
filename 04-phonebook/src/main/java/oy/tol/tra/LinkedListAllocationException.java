// Remove whole file? Not if they like to use linked list for resolving collisions. But only for BST.
// Instruct them to use something else in hashtable.

package oy.tol.tra;

public class LinkedListAllocationException extends RuntimeException {
   public LinkedListAllocationException(String message) {
      super(message);
   }
}
