package oy.tol.tra;


public class QueueImplementation<E> implements QueueInterface<E> {
    private Object[] itemArray;
    private int capacity;
    private int head = 0;
    private int tail = 0;
    private int size = 0;
    private static final int DEFAULT_QUEUE_SIZE = 10;

    public QueueImplementation() throws QueueAllocationException {
        try {
            itemArray = new Object[DEFAULT_QUEUE_SIZE];
            this.capacity = DEFAULT_QUEUE_SIZE;
        } catch (Exception e) {
            throw new QueueAllocationException("The programme failed to allocate space for the queue");
        }

    }

    public QueueImplementation(int capacity) {
        if(capacity <= 2)
        {
           
            throw new QueueAllocationException("The programme failed to allocate space for the queue");
            
        }
        else
        {
            this.capacity = capacity;
        }
            
        this.itemArray = new Object[capacity];
        this.size = 0;
        this.head = 0;
        this.tail = 0;
    }


    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public E dequeue() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Queue is empty");
        }
        size--;
        E tmp = (E) itemArray[head];
        itemArray[head] = null;
        head = (head + 1) % capacity;
        return tmp;
    }

    @Override
    public void enqueue(E element) throws QueueAllocationException, NullPointerException {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }
        if (tail == capacity && head != 0) {
            itemArray[0] = element;
            tail = 1;
            size++;
            return;
        }
        if (size == capacity) {
            Object[] newOne = new Object[capacity + 10];
            for (int i = 0; i < size; ++i) {
                newOne[i] = itemArray[(head + i) % capacity];
            }
            tail = size;
            head = 0;
            this.capacity = capacity + 10;
            itemArray = newOne;
            itemArray[tail] = element;
            tail++;
            size++;
            return;
        }
        itemArray[tail] = element;
        tail++;
        size++; 
    }

    @Override
    public E element() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Queue is empty");
        }
        return (E)itemArray[head];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public void clear() {
        head = 0;
        tail = 0;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        if (tail >= head)
        {
            for (var index = head; index < tail; index++) {
                builder.append(itemArray[index].toString());
                if (index < tail - 1) {
                    builder.append(", ");
                }
            }
        }
        else {
            for (var index = head; index < (tail+capacity); index++) {
                builder.append(itemArray[index%capacity].toString());
                if (index < (tail + capacity - 1)) {
                    builder.append(", ");
                }
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
