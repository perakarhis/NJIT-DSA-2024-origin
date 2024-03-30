package oy.tol.tra;

/**
 * An implementation of the StackInterface.
 * <p>
 * TODO: Students, implement this so that the tests pass.
 * 
 * Note that you need to implement construtor(s) for your concrete StackImplementation, which
 * allocates the internal Object array for the Stack:
 * - a default constructor, calling the StackImplementation(int size) with value of 10.
 * - StackImplementation(int size), which allocates an array of Object's with size.
 *  -- remember to maintain the capacity and/or currentIndex when the stack is manipulated.
 */
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
      // TODO: call the constructor with size parameter with default size of 10.
      try 
      {
         itemArray = new Object[DEFAULT_STACK_SIZE] ;
         this.capacity = DEFAULT_STACK_SIZE;
      }
      catch (Exception e)
      {
         throw new StackAllocationException("The programme failed to allocate space for the stack");
      }
      
   }

   /** TODO: Implement so that
    * - if the size is less than 2, throw StackAllocationException
    * - if the allocation of the array throws with Java exception,
    *   throw StackAllocationException.
    * @param capacity The capacity of the stack.
    * @throws StackAllocationException If cannot allocate room for the internal array.
    */
   public StackImplementation(int capacity) throws StackAllocationException {
      if(capacity < 2)
      {
         throw new StackAllocationException("The capacity is less than 2");
      }
      try 
      {
         itemArray = new Object[capacity] ;
         this.capacity = capacity;
      }
      catch (Exception e)
      {
         throw new StackAllocationException("The programme failed to allocate space for the stack");
      }
   }

   @Override
   public int capacity() {
      // TODO: Implement this
      return this.capacity;
   }

   @Override
   public void push(E element) throws StackAllocationException, NullPointerException {
      // TODO: Implement this
      if (element == null) {
         throw new NullPointerException("The element is null.");
     }
     if (currentIndex == capacity - 1) {
         Object [] newOne = new Object[capacity+10];
         for(int i = 0; i < capacity; ++i)
         {
            newOne[i] = itemArray[i];
         }
         this.capacity =  capacity+10;
         itemArray = newOne;

     }
     itemArray[++currentIndex] = element;
   }

   @SuppressWarnings("unchecked")
   @Override
   public E pop() throws StackIsEmptyException {
      if(currentIndex==-1)
      {
         throw new StackIsEmptyException("The stack is empty, you cannot pop any element");
      }
      return (E)itemArray[currentIndex--];
   }

   @SuppressWarnings("unchecked")
   @Override
   public E peek() throws StackIsEmptyException {
      if(currentIndex==-1)
      {
         throw new StackIsEmptyException("The stack is empty, you cannot get the peek element");
      }
      return (E)itemArray[currentIndex];
   }

   @Override
   public int size() {
      // TODO: Implement this
      return currentIndex+1;
   }

   @Override
   public void clear() {
      // TODO: Implement this
      currentIndex = -1;
   }

   @Override
   public boolean isEmpty() {
      // TODO: Implement this
      return currentIndex==-1;
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
