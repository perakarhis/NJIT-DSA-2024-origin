package oy.tol.tra;

public class LinkedListImplementation<E> implements LinkedListInterface<E> {

   private class Node<T> {
      Node(T data) {
         element = data;
         next = null;
      }
      T element;
      Node<T> next;
      @Override
      public String toString() {
         return element.toString();
      }
   }

   private Node<E> head = null;
   private int size = 0;

   @Override
   public void add(E element) throws NullPointerException, LinkedListAllocationException {
      if (null == element) {
         throw new NullPointerException("Element to add must not be null");
      }
      try {
         if (null == head) {
            head = new Node<E>(element);
         } else {
            Node<E> current = head;
            while (null != current.next) {
               current = current.next;
            }
            current.next = new Node<>(element);
         }
         size++;
      } catch (Exception e) {
         throw new LinkedListAllocationException(e.getMessage());
      }
   }

   @Override
   public void add(int index, E element) throws NullPointerException, LinkedListAllocationException, IndexOutOfBoundsException {
      if (null == element) {
         throw new NullPointerException("Element to add must not be null");
      }
      if (index < 0 || index > size) {
         throw new IndexOutOfBoundsException("Invalid index to the list");
      }
      try {
         if (index == 0) {
            Node<E> newHead = new Node<>(element);
            newHead.next = head;
            head = newHead;
            size++;
         } else {
            int counter = 1;
            Node<E> current = head.next;
            Node<E> previous = head;
            while (null != current) {
               if (counter == index) {
                  previous.next = new Node<>(element);
                  previous.next.next = current;
                  size++;
                  break;
               }
               counter++;
               previous = current;
               current = current.next;
            }
         }
      } catch (Exception e) {
         throw new LinkedListAllocationException(e.getMessage());
      }
   }

   @Override
   public boolean remove(E element) throws NullPointerException {
      if (null == element) {
         throw new NullPointerException("Element parameter must not be null");
      }
      if (null == head) {
         return false;
      }
      boolean result = false;
      // First check if the head is the one, easier this way.
      if (head.element.equals(element)) {
         Node<E> oldHead = head;
         head = oldHead.next;
         oldHead = null;
         result = true;
      } else {
         Node<E> current = head.next;
         Node<E> previous = head;
         while (null != current) {
            if (current.element.equals(element)) {
               previous.next = current.next;
               current = null;
               result = true;
               break;
            }
            previous = current;
            current = current.next;
         }
      }
      if (result) {
         size--;
      }
      return result;
   }

   @Override
   public E remove(int index) throws IndexOutOfBoundsException {
      if (index < 0 || index >= size) {
         throw new IndexOutOfBoundsException("Invalid index to the list");
      }
      E removed = null;
      if (index == 0) {
         removed = head.element;
         head = head.next;
      } else {
         int counter = 1;
         Node<E> current = head.next;
         Node<E> previous = head;
         while (null != current) {
            if (counter == index) {
               removed = current.element;
               previous.next = current.next;
               current = null;
               break;
            }
            counter++;
            previous = current;
            current = current.next;
         }
      }
      if (null != removed) {
         size--;
      }
      return removed;
   }

   @Override
   public E get(int index) throws IndexOutOfBoundsException {
      if (index < 0 || index >= size) {
         throw new IndexOutOfBoundsException("Invalid index to the list");
      }
      if (null == head) {
         return null;
      }
      int counter = 0;
      Node<E> current = head;
      while (null != current) {
         if (counter == index) {
            return current.element;
         }
         counter++;
         current = current.next;
      }
      return null;
   }

   @Override
   public int indexOf(E element) throws NullPointerException {
      if (null == element) {
         throw new NullPointerException("Element parameter must not be null");
      }
      if (null == head) {
         return -1;
      }
      int index = -1;
      if (head.element.equals(element)) {
         index = 0;
      } else {
         boolean found = false;
         Node<E> current = head.next;
         index = 1;
         while (null != current) {
            if (current.element.equals(element)) {
               found = true;
               break;
            }
            index++;
            current = current.next;
         }
         if (!found) {
            index = -1;
         }
      }
      return index;
   }

   @Override
   public int size() {
      return size;
   }

   @Override
   public void clear() {
      head = null;
      size = 0;
   }

  // NOTE! You do not need to pay attention to this method.
  // You do not need to do anything here, but do not delete it.
  @Override
   public void reverse() {
      if (null == head || null == head.next) {
         return;
      }
      // We come here only if head is not null and next neither,
      // so list has at least two elements. Therefore safe to assume head.next
      // is a valid reference.
      Node<E> current = head.next;
      Node<E> previous = head;
      Node<E> oldNext = null;
      head.next = null;
      while (null != current) {
         oldNext = current.next;
         current.next = previous;
         if (null == oldNext) {
            head = current;
            break;
         } else {
            previous = current;
            current = oldNext;
         }
      }
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder("[");
      if (null != head) {
         Node<E> current = head;
         while (null != current) {
            builder.append(current.toString());
            if (null != current.next) {
               builder. append(", ");
            }
            current = current.next;
         }
      }
      builder.append("]");
      return builder.toString();
   }
   
}
