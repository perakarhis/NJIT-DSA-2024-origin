package oy.tol.tra;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import org.junit.jupiter.api.Order;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

/*

/**
 * Unit tests for testing the stack implementation.
 * 
 * DO NOT change anything here, just implement the StackInterface, instantiate it in
 * StackBuilder.createIntegerStack and perform the tests.
 */
@DisplayName("Basic tests for the StackImplementation class.")
@TestMethodOrder(OrderAnnotation.class)
public class StackTests {
    static int stackSize = 10;
    static Random randomizer = new Random();
    static final int MAX_STACK_SIZE = 10;
    static Integer numberFromStack = null;

    /**
     * Initialize the test.
     */
    @Test
    @BeforeAll
    @DisplayName("Initializing the test data and stack object to test.")
    static void initializeStackImplementation() {
        StackInterface<Integer> stackToTest = StackFactory.createIntegerStack(10);
        assertNotNull(stackToTest,
                () -> "Could not create stack object to test. Implement StackBuilder.createIntegerStack().");
        assertEquals(stackSize, stackToTest.capacity(),
                () -> "Stack capacity must be the same as provided in construction");
        assertTrue(stackToTest.isEmpty(), () -> "Stack should be empty after creating it.");
        assertEquals(0, stackToTest.size(), "Size should be 0 for empty stack.");
        assertEquals("[]", stackToTest.toString(), () -> "Test data and stack as string must match");
    }

    @Test
    @Order(1)
    @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    @DisplayName("Test the empty stack behaviour.")
    void emptyStackTest() {
        // Test that count of just initialized stack is zero and pop returns null.
        StackInterface<Integer> stackToTest = StackFactory.createIntegerStack(10);
        assertEquals(0, stackToTest.size(), () -> "Expected stack to be empty, count() returning 0.");
        assertTrue(stackToTest.isEmpty(), () -> "Stack should be empty after creating it.");
        assertThrows(StackIsEmptyException.class, () -> stackToTest.pop(),
                "Expecting to get StackIsEmptyException when popping from empty stack.");
        assertThrows(StackIsEmptyException.class, () -> stackToTest.peek(),
                "Expecting to get StackIsEmptyException when peeking from empty stack");
        assertThrows(NullPointerException.class, () -> stackToTest.push(null),
                () -> "Must get NullPointerException when trying to push null to stack.");
        assertDoesNotThrow(() -> stackToTest.push(1), "Must not throw when pushing to stack.");
        assertDoesNotThrow(() -> numberFromStack = stackToTest.peek(), "Peeking from stack with one element should not throw");
        assertEquals(1, numberFromStack, "Number 1 was pushed so we should get that from peek().");
        assertDoesNotThrow(() -> numberFromStack = stackToTest.pop(), "Popping from stack with one element should not throw");
        assertEquals(1, numberFromStack, "Number 1 was pushed so we should get that from pop().");
        assertEquals(0, stackToTest.size(), () -> "Expected stack to be empty, count() returning 0.");
        assertTrue(stackToTest.isEmpty(), () -> "Stack should be empty after popping only element from it.");
    }

    @Test
    @Order(2)
    @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    @DisplayName("Test filling the stack and emptying it using push and pop.")
    void pushPopStackTest() {
        // Create a random count to fill the stack to.
        int elementCount = randomizer.nextInt(stackSize) + 10;
        // Fill the list with test data.
        List<Integer> testData = fillWithTestData(elementCount);
        // Push the test data to the stack, asserting that push succeeded.
        StackInterface<Integer> stackToTest = StackFactory.createIntegerStack(10);
        for (Integer value : testData) {
            assertDoesNotThrow(() -> stackToTest.push(value), "In this test push must succeed, but push failed.");
        }
        assertFalse(stackToTest.isEmpty(), () -> "Stack should not be empty here.");
        // Check that the stack has the correct element of items.
        assertEquals(elementCount, stackToTest.size(), () -> "Stack must have the number of elements pushed into it.");
        // Pop elements from the stack and compare that the values are in the same order
        // than in the test data list, reversed.
        int counter = testData.size() - 1;

        while (stackToTest.size() > 0) {
            assertDoesNotThrow(() -> numberFromStack = stackToTest.pop(),
                    "Pop should not throw in this test, but it did.");
            assertNotNull(numberFromStack, () -> "Item popped from stack should not be null.");
            assertEquals(testData.get(counter), numberFromStack,
                    () -> "Items popped must be in the order they were pushed into stack.");
            counter--;
        }
        // And since popping all out, test now that the stack is really empty.
        assertEquals(0, stackToTest.size(), () -> "After popping all items, stack must be empty.");
        assertTrue(stackToTest.isEmpty(), () -> "Stack should be empty after popping all out.");
        assertThrows(StackIsEmptyException.class, () -> stackToTest.pop(),
                "Pop must throw StackIsEmptyException if stack is empty.");
        assertEquals("[]", stackToTest.toString(), () -> "Test data and stack as string must match");
    }

    @Test
    @Order(3)
    @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    @DisplayName("Try to put more items in the stack it should be able to hold.")
    void overFillStackTest() {
        // Reset the stack to be empty.
        StackInterface<Integer> stackToTest = StackFactory.createIntegerStack(10);
        assertTrue(stackToTest.isEmpty(), () -> "Stack should be empty after creating it.");
        // Fill the stack to contain max number of items.
        int oldCapacity = stackToTest.capacity();
        List<Integer> testData = fillWithTestData(oldCapacity);
        for (Integer value : testData) {
            assertDoesNotThrow(() -> stackToTest.push(value), "In this test push must succeed, but push failed.");
        }
        assertFalse(stackToTest.isEmpty(), () -> "Stack should not be empty here.");
        // Stack should be now full so the next push must reallocate internal array and
        // capacity should be increased.
        assertDoesNotThrow(() -> stackToTest.push(42), "Pushing to a full stack must not fail.");
        int newCapacity = stackToTest.capacity();
        assertTrue(newCapacity > oldCapacity, () -> "The capacity did not grow when it should have.");
        assertEquals(42, stackToTest.pop(), () -> "Last thing pushed was not popped from stack.");
        assertEquals(testData.toString(), stackToTest.toString(), () -> "Test data and stack as string must match");
        assertEquals(testData.get(testData.size() - 1), stackToTest.pop(),
                () -> "Reallocated stack has no second to last element in place.");
    }

    @Test
    @Order(4)
    @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    @DisplayName("Test resetting the stack so state is correct after reset.")
    void resetStackTest() {
        // Put something in the stack, then reset it and check if it is empty.
        StackInterface<Integer> stackToTest = StackFactory.createIntegerStack(10);
        assertTrue(stackToTest.isEmpty(), () -> "Stack should be empty after creating it.");
        stackToTest.push(109);
        assertFalse(stackToTest.isEmpty(), () -> "Stack should not be empty here.");
        stackToTest.push(111);
        assertFalse(stackToTest.isEmpty(), () -> "Stack should not be empty here.");
        stackToTest.clear();
        assertTrue(stackToTest.isEmpty(), () -> "Stack should be empty after creating it.");
        assertThrows(StackIsEmptyException.class, () -> stackToTest.pop(),
                "Pop must throw StackIsEmptyException if stack is empty.");
        assertEquals(0, stackToTest.size(), () -> "After resetting a stack, count must return zero.");
        assertEquals("[]", stackToTest.toString(), () -> "Test data and stack as string must match");
    }

    @Test
    @Order(5)
    @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    @DisplayName("Testing the peek method")
    void peekTest() {
        StackInterface<Integer> stackToTest = StackFactory.createIntegerStack(10);
        stackToTest.push(109);
        assertFalse(stackToTest.isEmpty(), () -> "Stack should not be empty here.");
        stackToTest.push(111);
        assertEquals(111, stackToTest.peek(), () -> "Peek must return the last value put in");
        assertEquals(2, stackToTest.size(), () -> "After peeking, count must be the same as before.");
        assertEquals("[109, 111]", stackToTest.toString(), () -> "Test data and stack as string must match");
        stackToTest.clear();
        assertTrue(stackToTest.isEmpty(), () -> "Stack should be empty after creating it.");
        assertThrows(StackIsEmptyException.class, () -> stackToTest.peek(),
                "Peek must throw StackIsEmptyException if stack is empty.");
    }

    @Test
    @Order(6)
    @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    @DisplayName("Try to put more items in the stack it should be able to hold.")
    void reallyGrowTheStack() {
        StackInterface<Integer> stackToTest = StackFactory.createIntegerStack(10);
        // Fill the stack to contain max number of items.
        List<Integer> testData = fillWithTestData(stackSize);
        int firstFillCount = testData.size();
        for (Integer value : testData) {
            assertDoesNotThrow(() -> stackToTest.push(value), "In this test push must succeed, but push failed.");
        }
        // Stack should be now full so the next push must reallocate internal array and
        // capacity should be increased.
        int newCapacity = stackToTest.capacity();
        testData = fillWithTestData(newCapacity * 10);
        for (Integer value : testData) {
            assertDoesNotThrow(() -> stackToTest.push(value), "In this test push must succeed, but push failed.");
        }
        assertFalse(stackToTest.isEmpty(), () -> "Stack should not be empty here.");
        assertEquals(firstFillCount + testData.size(), stackToTest.size(),
                () -> "Stack does not hold enought elements after growing it ten times the original.");
        assertEquals(testData.get(testData.size() - 1), stackToTest.pop(),
                () -> "Last thing pushed was not popped from stack.");
    }

    @Test
    @Order(7)
    @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    @DisplayName("Check that capacity() is not actually the same as size(), which is wrong.")
    void testCapacityIsNotTheSize() {
        StackInterface<Integer> stackToTest = StackFactory.createIntegerStack(10);
        // Check current capacity and size.
        int currentCapacity = stackToTest.capacity();
        int currentSize = stackToTest.size();
        // Now calculate how much to add so that capacity is not increased.
        final int testDataSize = currentCapacity - currentSize - 2;
        if (testDataSize > 0) {
            // Then add test data so that the capacity is not changed.
            List<Integer> testData = fillWithTestData(testDataSize);
            for (Integer value : testData) {
                assertDoesNotThrow(() -> stackToTest.push(value), "In this test push must succeed, but push failed.");
            }
            // Then check that size increased correctly...
            assertEquals(currentSize + testDataSize, stackToTest.size(),
                    () -> "Increased size must be the same as expected");
            // ...and that capacity didn't increase.
            assertEquals(currentCapacity, stackToTest.capacity(), () -> "Capacity should not increase here");
            assertNotEquals(stackToTest.size(), stackToTest.capacity(), () -> "Capacity must not be the same as size");
        }
    }

    /**
     * Utility method to create a list with random test data.
     * 
     * @param itemCount Number of items to put into the testa data list.
     * @return A list of test data to use with the test stack.
     */
    private List<Integer> fillWithTestData(int itemCount) {
        List<Integer> testData = new ArrayList<Integer>();
        for (int count = 0; count < itemCount; count++) {
            testData.add(randomizer.nextInt(Integer.MAX_VALUE - 1));
        }
        return testData;
    }
}
