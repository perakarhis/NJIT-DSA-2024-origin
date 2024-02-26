package oy.tol.tra;

/**
 * A generic interface to queue class. Queues work following 
 * the first-in-first-out principle.
 * Students: Implement this interface in a separate <code>QueueImplementation.java</code> file.
 *           No implementation in this file!!
 */
public interface QueueInterface<E> {

   /**
    * For querying the current capacity of the queue.
    @return The number of elements the queue can currently hold.
    */
   public int capacity();
   
   /**
    * Add an element to the queue.
    * @param element The element to add, must not be null.
    * @throws QueueAllocationException If the reallocation for the queue failed in case queue needs reallocation.
    * @throws NullPointerException If the element is null.
    */
   public void enqueue(E element) throws QueueAllocationException, NullPointerException;

   /**
    * Removes an element from the queue.
    * @return The element from the head of the queue.
    * @throws QueueIsEmptyException If the queue is empty.
    */
   public E dequeue() throws QueueIsEmptyException;

   /**
    * Returns the element at the head of the queue, not removing it from the queue.
    * @return The element in the head of the queue.
    * @throws QueueIsEmptyException If the queue is empty.
    */
   public E element() throws QueueIsEmptyException;

   /**
    * Returns the count of elements currently in the queue.
    * @return Count of elements in the queue.
    */
   public int size();

   /**
    * Can be used to check if the queue is empty.
    * @return True if the queue is empty, false otherwise.
    */
   public boolean isEmpty();

   /**
    * Resets the queue to empty state, removing the objects.
    */
   public void clear();

}
