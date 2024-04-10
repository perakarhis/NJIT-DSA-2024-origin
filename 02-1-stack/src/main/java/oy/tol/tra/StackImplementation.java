package oy.tol.tra;

public class StackImplementation<E> implements StackInterface<E> {

   private Object [] itemArray;
   private int capacity;
   private int currentIndex = -1;
   private static final int DEFAULT_STACK_SIZE = 10;

   /**
    * Allocates a stack with a default capacity.
    * @throws StackAllocationException
    */
   public StackImplementation() throws StackAllocationException {
      this(DEFAULT_STACK_SIZE);
   }

   public StackImplementation(int capacity) throws StackAllocationException {
      if (capacity < 2) {
         throw new StackAllocationException("Capacity must be at least 2.");
      }
      this.capacity = capacity;
      this.itemArray = new Object[capacity];
   }

   @Override
   public int capacity() {
      return capacity;
   }

   @Override
   public void push(E element) throws StackAllocationException, NullPointerException {
      if (element == null) {
         throw new NullPointerException("Element to push cannot be null.");
      }
      if (currentIndex + 1 >= capacity) {
         try {
         capacity += 10;
         Object[] newItemArray = new Object[capacity];
         for (int i = 0; i <= currentIndex; i++) {
            newItemArray[i] = itemArray[i];
         }
         itemArray = newItemArray;
      }catch (StackAllocationException e) {
         throw new StackAllocationException("The stack is full.");
      }
      }
      currentIndex++;
      itemArray[currentIndex] = element;
   }

   @SuppressWarnings("unchecked")
   @Override
   public E pop() throws StackIsEmptyException {
      if (isEmpty()) {
         throw new StackIsEmptyException("The stack is empty.");
      }
      E poppedElement = (E) itemArray[currentIndex];
      itemArray[currentIndex--] = null;
      return poppedElement;
   }

   @SuppressWarnings("unchecked")
   @Override
   public E peek() throws StackIsEmptyException {
      if (isEmpty()) {
         throw new StackIsEmptyException("The stack is empty.");
      }
      return (E)itemArray[currentIndex];
   }

   @Override
   public int size() {
      return currentIndex + 1;
   }

   @Override
   public void clear() {
      for (int i = 0; i <= currentIndex; i++) {
         itemArray[i] = null;
      }
      currentIndex = -1;
   }

   @Override
   public boolean isEmpty() {
      return currentIndex == -1;
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder("[");
      for (var index = 0; index <= currentIndex; index++) {
         builder.append(itemArray[index].toString());
         if (index < currentIndex) {
            builder.append(", ");
         }
      }
      builder.append("]");
      return builder.toString();
   }
}
