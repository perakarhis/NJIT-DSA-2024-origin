package oy.tol.tra;

public class QueueImplementation<E> implements QueueInterface<E> {
    private Object [] itemArray;
    private int capacity;
    private int current = 0;
    private int head = 0;
    private int tail = -1;
    private static final int DEFAULT_STACK_SIZE = 10;

    /**
     * Allocates a queue with a default capacity.
     * @throws QueueAllocationException
     */
    public QueueImplementation() throws QueueAllocationException {
        capacity=DEFAULT_STACK_SIZE;
        itemArray=new Object[DEFAULT_STACK_SIZE];
    }

    /**
     * @param capacity The capacity of the queue.
     * @throws QueueAllocationException If cannot allocate room for the internal array.
     */
    public QueueImplementation(int capacity) throws QueueAllocationException {
        if(capacity<2){
            throw new QueueAllocationException("Capacity must be at least 2.");
        }
        this.capacity=capacity;
        itemArray=new Object[capacity];
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public void enqueue(E element) throws QueueAllocationException, NullPointerException {
        if(current==capacity){
            Object[] tmp=new Object[this.capacity*2+1];
            int indexOfItemArray=head;
            int index=0;
            int loop=current;
            while(loop-->0){
                tmp[index++]=itemArray[indexOfItemArray];
                indexOfItemArray=(indexOfItemArray+1)%capacity;
            }
            head=0;
            tail=index-1;
            itemArray=tmp;
            tmp=null;
            capacity=capacity*2+1;
        }
        if(element==null){
            throw new NullPointerException();
        }
        tail=(tail+1)%capacity;
        itemArray[tail]=element;
        current++;
    }


    @Override
    public E dequeue() throws QueueIsEmptyException {
        E returnE =element();
        head=(head+1)%capacity;
        current--;
        return returnE;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E element() throws QueueIsEmptyException {
        if(isEmpty()){
            throw new QueueIsEmptyException("Cannot dequeue from an empty queue.");
        }
        return (E)itemArray[head];
    }

    @Override
    public int size() {
        return current;
    }

    @Override
    public boolean isEmpty() {
        return current==0;
    }

    @Override
    public void clear() {
        head=0;
        tail=-1;
        current=0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        int index=head;
        int loopTime=current;
        while(loopTime-->0){
            builder.append(itemArray[index].toString());
            index=(index+1)%capacity;
            if(loopTime!=0){
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
