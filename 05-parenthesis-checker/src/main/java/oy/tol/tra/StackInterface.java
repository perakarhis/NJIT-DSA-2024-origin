package oy.tol.tra;
public interface StackInterface<E> {

   public int capacity();
   
   public void push(E element) throws StackAllocationException, NullPointerException;

   public E pop() throws StackIsEmptyException;

   public E peek() throws StackIsEmptyException;

   public int size();

    public boolean isEmpty();

   public void clear();
}
