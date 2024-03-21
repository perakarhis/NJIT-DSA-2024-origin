package oy.tol.tra;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;

/*

/**
 * Unit tests for testing the queue implementation.
 * 
 * DO NOT change anything here, just implement the QueueInterface, instantiate it in
 * QueueBuilder.createIntegerQueue and perform the tests.
 */
@DisplayName("Basic tests for the QueueImplementation class.")
@TestMethodOrder(OrderAnnotation.class)
 public class QueueTests 
{
    static Random randomizer = null;
    static final int MIN_QUEUE_SIZE = 10;
    static final int MAX_QUEUE_SIZE = 100;
    static int toAdd = 0;
    
    /**
     * Initialize the test.
     */
    @Test
    @BeforeAll
    @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    @DisplayName("Initializing the test data and queue object to test.")
    static void initializeQueueImplementation() {
        System.out.println("Testing creating queue.");
        randomizer = new Random();
        int queueSize = Math.max(2, randomizer.nextInt(100));
        QueueInterface<Integer> queueToTest = QueueFactory.createIntegerQueue(queueSize);
        assertNotNull(queueToTest, () -> "Could not create queue object to test. Implement QueueBuilder.createIntegerQueue().");
        assertEquals(queueSize, queueToTest.capacity(), () -> "Queue capacity must be what was requested in creating it.");
    }

    @Test
    @Order(1)
    @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    @DisplayName("Test the empty queue behaviour.")
    void emptyQueueTest() {
        // Test that count of just initialized queue is zero and remove returns null.
        System.out.println("Testing empty queue.");
        QueueInterface<Integer> queueToTest = QueueFactory.createIntegerQueue(10);
        assertTrue(queueToTest.isEmpty(), () -> "The queue should be empty.");
        queueToTest.clear();
        assertTrue(queueToTest.isEmpty(), () -> "The queue should be empty.");
        assertEquals(0, queueToTest.size(), () -> "Expected queue to be empty, count() returning 0.");
        assertThrows(QueueIsEmptyException.class, () -> queueToTest.dequeue(), "Expecting to throw QueueIsEmptyException when removing from empty queue.");
        assertThrows(QueueIsEmptyException.class, () -> queueToTest.element(), "Expecting to throw QueueIsEmptyException when accessing element from empty queue");
        assertThrows(NullPointerException.class, () -> queueToTest.enqueue(null), "Must not be able to add null to queue.");
        assertEquals("[]", queueToTest.toString(), () -> "Empty queue as string failed.");
    }

    @Test
    @Order(2)
    @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    @DisplayName("Very basic queue tests")
    void basicQueueTests() {
        System.out.println("Testing basic queue functionality.");
        QueueInterface<Integer> queueToTest = QueueFactory.createIntegerQueue(5);
        assertTrue(queueToTest.isEmpty(), () -> "The queue should be empty.");
        assertDoesNotThrow(() -> queueToTest.enqueue(1), "Should successfully add elements to queue.");
        assertDoesNotThrow(() -> queueToTest.enqueue(2), "Should successfully add elements to queue.");
        assertDoesNotThrow(() -> queueToTest.enqueue(3), "Should successfully add elements to queue.");
        assertDoesNotThrow(() -> queueToTest.enqueue(4), "Should successfully add elements to queue.");
        assertDoesNotThrow(() -> queueToTest.enqueue(5), "Should successfully add elements to queue.");
        assertFalse(queueToTest.isEmpty(), () -> "The queue should not be empty.");
        assertEquals(5, queueToTest.size(), () -> "Should have five elements in the queue.");
        assertEquals(1, queueToTest.element(), () -> "First-in-first-out so 1 should be the element()");
        assertEquals("[1, 2, 3, 4, 5]", queueToTest.toString(), () -> "Queue as string failed.");
        Integer element = queueToTest.dequeue();
        assertEquals("[2, 3, 4, 5]", queueToTest.toString(), () -> "Queue as string failed.");
        assertNotNull(element, () -> "Should be able to remove element from queue.");
        assertEquals(1, element, () -> "First-in-first-out so 1 should come out");
        assertEquals(4, queueToTest.size(), () -> "Should have four elements in the queue.");
        assertDoesNotThrow(() -> queueToTest.enqueue(6), "Should successfully add elements to queue.");
        assertEquals(5, queueToTest.size(), () -> "Should have five elements in the queue.");
        for (int counter = 0; counter < 4; counter++) {
            assertNotNull(queueToTest.dequeue(), () -> "Should be able to take four elements out.");
        }
        assertEquals(1, queueToTest.size(), () -> "Should have one element in the queue.");
        assertEquals(6, queueToTest.element(), () -> "Only six should be left int the queue.");
        assertNotNull(queueToTest.dequeue(), () -> "Should be able to take final element out.");
        assertEquals(0, queueToTest.size(), () -> "Expected queue to be empty, count() returning 0.");
        assertTrue(queueToTest.isEmpty(), () -> "The queue should be empty.");
        assertThrows(QueueIsEmptyException.class, () -> queueToTest.dequeue(), "Expecting to get null when removing from empty queue.");
        assertThrows(QueueIsEmptyException.class, () -> queueToTest.element(), "Expecting to get null when removing from empty queue");
    }

    @Test
    @Order(3)
    @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    @DisplayName("Test filling the queue and emptying it using add and remove.")
    void addRemoveQueueTest() {
        System.out.println("Testing filling and emptying a queue.");
        int queueSize = randomQueueSize();
        QueueInterface<Integer> queueToTest = QueueFactory.createIntegerQueue(queueSize);
        assertTrue(queueToTest.isEmpty(), () -> "The queue should be empty.");
        // Create a random count to fill the queue to.
        int elementCount = randomizer.nextInt(queueSize) + 5;
        // Fill the list with test data.
        List<Integer> testData = fillWithTestData(elementCount);
        // Push the test data to the queue, asserting that add succeeded.
        for (Integer value : testData) {
            assertDoesNotThrow(() -> queueToTest.enqueue(value), "In this test add must succeed, add failed.");
        }
        // Check that the queue has the correct element of items.
        assertEquals(elementCount, queueToTest.size(), () -> "Queue must have the number of elements added into it.");
        // Take elements from the queue and compare that the values are in the same order than in the test data list, reversed.
        while (queueToTest.size() > 0) {
            Integer numberFromQueue = queueToTest.dequeue();
            assertNotNull(numberFromQueue, () -> "Item taken from queue should not be null.");
            assertEquals(testData.get(0), numberFromQueue, () -> "Items removed must be in the order they were added into queue.");
            testData.remove(0);
        }
        // And since removing all, test now that the queue is really empty.
        assertTrue(queueToTest.isEmpty(), () -> "The queue should be empty.");
        assertEquals(0, queueToTest.size(), () -> "After removing all items, queue must be empty.");
        assertThrows(QueueIsEmptyException.class, () -> queueToTest.dequeue(), "Remove must return null after all items have been removed.");
    }

    @Test
    @Order(4)
    @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    @DisplayName("Test queue reallocation.")
    void overFillQueueTest() {
        System.out.println("Testing trying to overfill a queue.");
        int queueSize = randomQueueSize();
        QueueInterface<Integer> queueToTest = QueueFactory.createIntegerQueue(queueSize);
        // Fill the queue to contain max number of items.
        List<Integer> testData = fillWithTestData(queueSize);
        for (Integer value : testData) {
            assertDoesNotThrow(() -> queueToTest.enqueue(value), "In this test add must succeed, add failed.");
        }
        int oldCapacity = queueToTest.capacity();
        // Queue should be now full so the next add should reallocate, grow capacity.
        assertDoesNotThrow(() -> queueToTest.enqueue(42), "Pushing to a full queue must reallocate and success.");
        int newCapacity = queueToTest.capacity();
        assertTrue(newCapacity > oldCapacity, () -> "The capacity did not grow when it should have.");
        assertEquals(testData.get(0), queueToTest.dequeue(), () -> "First thing queued was not dequeued from queue.");
        assertEquals(testData.get(1), queueToTest.dequeue(), () -> "Reallocated queue has no second to last element in place.");
        assertFalse(queueToTest.isEmpty(), () -> "Queue should not be empty at this point.");
    }

    @Test
    @Order(5)
    @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    @DisplayName("Test clearing the queue so state is correct after clear.")
    void resetQueueTest() {
        System.out.println("Testing clearing a queue.");
        // Put something in the queue, then reset it and check if it is empty.
        QueueInterface<Integer> queueToTest = QueueFactory.createIntegerQueue(randomQueueSize());
        queueToTest.enqueue(109);
        queueToTest.enqueue(111);
        queueToTest.clear();
        assertTrue(queueToTest.isEmpty(), () -> "The queue should be empty.");
        assertThrows(QueueIsEmptyException.class, () -> queueToTest.dequeue(), "After resetting a queue, dequeue must throw QueueIsEmptyException.");
        assertEquals(0, queueToTest.size(), () -> "After resetting a queue, count must return zero.");        
    }

    @Test
    @Order(6)
    @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    @DisplayName("Testing the dequeue and reset methods")
    void removeTest() {
        System.out.println("Testing remove and reset methods with count and element values.");
        int queueSize = randomQueueSize();
        QueueInterface<Integer> queueToTest = QueueFactory.createIntegerQueue(queueSize);
        queueToTest.enqueue(109);
        queueToTest.enqueue(111);
        assertEquals(109, queueToTest.dequeue(), () -> "Remove must return the first value put in");
        assertEquals(1, queueToTest.size(), () -> "After removing, count must one less.");
        assertEquals(111, queueToTest.dequeue(), () -> "Remove must return the second value put in");
        assertEquals(0, queueToTest.size(), () -> "After removing, count must one less.");
        assertTrue(queueToTest.isEmpty(), () -> "The queue should be empty.");
        assertThrows(QueueIsEmptyException.class, () -> queueToTest.dequeue(), "After removing last item a queue, dequeue must throw QueueIsEmptyException.");
        queueToTest.clear();
        assertTrue(queueToTest.isEmpty(), () -> "The queue should be empty.");
        assertThrows(QueueIsEmptyException.class, () -> queueToTest.dequeue(), "After resetting a queue, dequeue must throw QueueIsEmptyException.");
    }

    @Test
    @Order(7)
    @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    @DisplayName("Testing the print method")
    void printTest() {
        System.out.println("Testing printing queue values in correct order (head -> tail).");
        int queueSize = randomQueueSize();
        QueueInterface<Integer> queueToTest = QueueFactory.createIntegerQueue(queueSize);
        assertEquals("[]", queueToTest.toString(), () -> "Empty queue must print out as \"[]\"");
        
        assertDoesNotThrow(() -> queueToTest.enqueue(42), "In this test enqueue must succeed, but failed");
        assertEquals("[42]", queueToTest.toString(), () -> "Queue with one value to string fails");
        // Fill the queue to contain max number of items.
        List<Integer> testData = fillWithTestData(5);
        queueToTest.clear();
        for (Integer value : testData) {
            assertDoesNotThrow(() -> queueToTest.enqueue(value), "In this test enqueue must succeed, but failed.");
        }
        System.out.println("Step 1: Test data " + testData.toString() + "\nQueue:            " + queueToTest.toString());
        assertEquals(testData.toString(), queueToTest.toString(), () -> "Queue as string must match test data as string.");

        testData.remove(0);
        assertNotNull(queueToTest.dequeue(), () -> "Should have elements in the queue.");
        System.out.println("Step 2: Test data " + testData.toString() + "\nQueue:            " + queueToTest.toString());
        assertEquals(testData.toString(), queueToTest.toString(), () -> "Queue as string must match test data as string.");

        toAdd = randomizer.nextInt(1000);
        testData.add(toAdd);
        assertDoesNotThrow(() -> queueToTest.enqueue(toAdd), "In this test enqueue must succeed, but failed");
        System.out.println("Step 3: Test data " + testData.toString() + "\nQueue:            " + queueToTest.toString());
        assertEquals(testData.toString(), queueToTest.toString(), () -> "Queue as string must match test data as string.");

        testData.remove(0);
        assertDoesNotThrow( () -> queueToTest.dequeue(), "Should have elements in the queue.");
        testData.remove(0);
        assertDoesNotThrow( () -> queueToTest.dequeue(), () -> "Should have elements in the queue.");
        System.out.println("Step 4: Test data " + testData.toString() + "\nQueue:            " + queueToTest.toString());
        assertEquals(testData.toString(), queueToTest.toString(), () -> "Queue as string must match test data as string.");

        toAdd = randomizer.nextInt(1000);
        testData.add(toAdd);
        assertDoesNotThrow(() -> queueToTest.enqueue(toAdd), "Should be able to enqueue at this point");
        toAdd = randomizer.nextInt(1000);
        testData.add(toAdd);
        assertDoesNotThrow(() -> queueToTest.enqueue(toAdd), () -> "Should be able to enqueue at this point");
        System.out.println("Step 5: Test data " + testData.toString() + "\nQueue:            " + queueToTest.toString());
        assertEquals(testData.toString(), queueToTest.toString(), () -> "Queue as string must match test data as string.");
    }

    @Test
    @Order(8)
    @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    @DisplayName("Testing reallocation of the queue.")
    void reallyGrowTheQueue() {
        QueueInterface<Integer> queueToTest = QueueFactory.createIntegerQueue(30);
        // Fill the stack to contain max number of items.
        List<Integer> testData = fillWithTestData(20);
        int firstNumberInQueue = testData.get(0);
        int firstFillCount = testData.size();
        for (Integer value : testData) {
            assertDoesNotThrow(() -> queueToTest.enqueue(value), "In this test enqueue must succeed, but failed.");
        }
        // Stack should be now full so the next push must reallocate internal array and capacity should be increased.
        int newCapacity = queueToTest.capacity();
        testData = fillWithTestData(newCapacity * 10);
        for (Integer value : testData) {
            assertDoesNotThrow(() -> queueToTest.enqueue(value), "In this test enqueue must succeed, but failed.");
        }
        assertEquals(firstFillCount + testData.size(), queueToTest.size(), () -> "Queue does not hold enought elements after growing it ten times the original.");
        assertEquals(firstNumberInQueue, queueToTest.dequeue(), () -> "First thing enqueued was not dequeued from the queue.");
    }

    @Test
    @Order(9)
    @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    @DisplayName("Test with strings and reallocation")
    void testStringQueueWithRealloc() {
        // Note the internal array size of four.
        QueueInterface<String> queue = new QueueImplementation<>(4);
        // Strings with capital first letter are in the first added "batch".
        queue.enqueue("First");
        queue.enqueue("Second");
        queue.enqueue("Third");
        queue.enqueue("Fourth");
        assertEquals("[First, Second, Third, Fourth]", queue.toString(), () -> "Queue as string must match test data.");
        // Queue now has "[First, Second, Third, Fourth]"
        // Dequeued values not needed, so no variables here to avoid warnings about unused objects.
        queue.dequeue(); 
        queue.dequeue();
        // And now it should have "[Third, Fourth]"
        queue.enqueue("fifth");
        queue.enqueue("sixth");
        queue.enqueue("seventh");
        queue.enqueue("eight");
        // And now it should have "[Third, Fourth, fifth, sixth, seventh, eight]"
        queue.dequeue();
        // And now it should have "[Fourth, fifth, sixth, seventh, eight]"
        assertEquals("[Fourth, fifth, sixth, seventh, eight]", queue.toString(), () -> "Queue as string must match test data.");
    }

    @Test
    @Order(10)
    @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    @DisplayName("Test toString with adding and removing")
    void testAddRemoveToString() {
        QueueInterface<Integer> queue = new QueueImplementation<>(5);
        assertEquals("[]", queue.toString(), "Not expected output from toString");
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        assertEquals("[1, 2, 3, 4]", queue.toString(), "Not expected output from toString");
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        assertEquals("[4]", queue.toString(), "Not expected output from toString");
        queue.enqueue(5);
        queue.enqueue(6);
        assertEquals("[4, 5, 6]", queue.toString(), "Not expected output from toString");
    }
    
    /**
     * Utility method to create a random size queue.
     * @param itemCount
     * @return
     */
    private int randomQueueSize() {
        return Math.max(MIN_QUEUE_SIZE, randomizer.nextInt(MAX_QUEUE_SIZE));
    }

    /**
     * Utility method to create a list with random test data.
     * @param itemCount Number of items to put into the testa data list.
     * @return A list of test data to use with the test queue.
     */
    private List<Integer> fillWithTestData(int itemCount) {
        List<Integer> testData = new ArrayList<Integer>();
        for (int count = 0; count < itemCount; count++) {
            testData.add(randomizer.nextInt(1000));
        }
        return testData;
    }
}
