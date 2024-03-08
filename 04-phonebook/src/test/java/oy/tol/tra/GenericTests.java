package oy.tol.tra;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Timeout;

import oy.tol.tra.Dictionary.Type;

@DisplayName("Testing that the implementations are really generic.")
public class GenericTests {

    // Implementations to test:
    static Dictionary<String, Integer> slowArray = null;
    static Dictionary<String, Integer> fastHashTable = null;
    static Dictionary<String, Integer> fastBST = null;
    static final int TEST_COUNT = 100;
    static Pair<String, Integer> [] array;
    static int index = 0;
    static Integer testValue;
    static boolean testResult;
    // One test method uses these:
    static Dictionary<Person,PhoneNumber> dict;
    static PhoneNumber foundValue = null;

    @BeforeAll
    static void allocateDataStructures() {
        try {
            slowArray = new KeyValueArray<>(25);
            fastHashTable = new KeyValueHashTable<>(25);
            fastBST = new KeyValueBSearchTree<>();    
        } catch (Exception e) {
            e.printStackTrace();
            fail("Could not instantiate some of the Dictionary implementations");
        }
    }

    @Test
    //@Timeout(value = 60, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    @DisplayName("Tests KeyValueArray, slow linear array")
    void testSlowArray() {
        try {
            List<String> randomList = new ArrayList<>();
            for (int index = 0; index < TEST_COUNT; index++) {
                randomList.add(String.valueOf(index));
            }
            Collections.shuffle(randomList);
            for (index = 0; index < TEST_COUNT; index++) {
                assertDoesNotThrow( () -> slowArray.add(randomList.get(index), Integer.parseInt(randomList.get(index))), () -> "add must not throw");
            }
            System.out.println(">> Testing key-value array with " + slowArray.size() + " entries");
            assertEquals(TEST_COUNT, slowArray.size(), () -> "Inserted count must match");
            for (index = 0; index < TEST_COUNT; index++) {
                assertDoesNotThrow( () -> testValue = slowArray.find(String.valueOf(index)), () -> "find must not throw.");
                assertEquals(testValue, index, () -> "Inserted and retrieved values must match");
            }
            assertNull(slowArray.find("1999"), () -> "Must return null when element not in table");
            assertDoesNotThrow(() -> array = slowArray.toSortedArray(), "toSortedArray must not throw");
            assertTrue(isSorted(array), () -> "slowArray.toSortedArray() does not sort correctly");
            assertEquals(randomList.size(), array.length, () -> "Test array and toSortedArray lengths do not match");
            Object [] originalArray = randomList.toArray();
            Arrays.sort(originalArray);
            for (int index = 0; index < TEST_COUNT; index++) {
                assertTrue(originalArray[index].equals(array[index].getKey()), () -> "Array elements do not match");
            }    
            int elements = slowArray.size();
            assertDoesNotThrow(() -> slowArray.compress(), () -> "Compressing the storage should not throw");
            assertEquals(elements, slowArray.size(), () -> "After compression must have same number of elements than before");
            assertDoesNotThrow(() -> array = slowArray.toSortedArray(), "toSortedArray must not throw");
            for (int index = 0; index < TEST_COUNT; index++) {
                assertTrue(originalArray[index].equals(array[index].getKey()), () -> "Array elements do not match");
            }    
        } catch (Exception e) {
            e.printStackTrace();
            fail("Something went wrong in the test." + e.getMessage());
        }
    }

    @Test
    //@Timeout(value = 30, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    @DisplayName("Tests hash table")
    void testHashTable() {
        try {
            if (fastHashTable.getType() == Type.NONE) {
                System.out.println("=============================================================");
                System.out.println("Not testing hash table yet since it has not been implemented.");
                System.out.println("=============================================================");
                return;
            }
            List<String> randomList = new ArrayList<>();
            for (int index = 0; index < TEST_COUNT; index++) {
                randomList.add(String.valueOf(index));
            }
            Collections.shuffle(randomList);
            for (index = 0; index < TEST_COUNT; index++) {
                assertDoesNotThrow( () -> testResult = fastHashTable.add(randomList.get(index), Integer.parseInt(randomList.get(index))), () -> "add must not throw");
                assertTrue(testResult, "Hashtable add should return true when adding elements to it.");
            }
            System.out.println(">> Testing hash table with " + fastHashTable.size() + " entries");
            assertEquals(TEST_COUNT, fastHashTable.size(), () -> "Inserted count must match");
            for (int index = 0; index < TEST_COUNT; index++) {
                final int toFind = index;
                assertDoesNotThrow( () -> testValue = fastHashTable.find(String.valueOf(toFind)), () -> "Hash table find must not throw.");
                assertEquals(testValue, index, () -> "Inserted and retrieved values must match");
            }
            assertNull(fastHashTable.find("1999"), () -> "Must return null when element not in table");
            assertDoesNotThrow(() -> array = fastHashTable.toSortedArray(), "toSortedArray must not throw");
            assertNotNull(array, () -> "Returned array from toSortedArray must not be null");
            assertTrue(isSorted(array), () -> "KeyValueHashTable.toSortedArray() does not sort correctly");
            assertEquals(randomList.size(), array.length, () -> "Test array and toSortedArray lengths do not match");
            Object [] originalArray = randomList.toArray();
            Arrays.sort(originalArray);
            for (int index = 0; index < TEST_COUNT; index++) {
                assertNotNull(array[index], () -> "array from toSortedArray must not contain null elements");
                assertTrue(originalArray[index].equals(array[index].getKey()), () -> "Array elements do not match");
            }    
            int elements = fastHashTable.size();
            assertDoesNotThrow(() -> fastHashTable.compress(), () -> "Compressing the storate should not throw");
            assertEquals(elements, fastHashTable.size(), () -> "After compression must have same number of elements than before");
            assertDoesNotThrow(() -> array = fastHashTable.toSortedArray(), "toSortedArray must not throw");
            assertNotNull(array, () -> "Returned array from toSortedArray must not be null");
            assertEquals(randomList.size(), array.length, () -> "Test array and toSortedArray lengths do not match");
            for (int index = 0; index < TEST_COUNT; index++) {
                assertNotNull(array[index], () -> "array from toSortedArray must not contain null elements");
                assertTrue(originalArray[index].equals(array[index].getKey()), () -> "Array elements do not match");
            }    
        } catch (Exception e) {
            e.printStackTrace();
            fail("Something went wrong in the test." + e.getMessage());
        }
    }

    @Test
    //@Timeout(value = 30, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    @DisplayName("Tests BST")
    void testBST() {
        try {
            if (fastBST.getType() == Type.NONE) {
                System.out.println("======================================================");
                System.out.println("Not testing BST yet since it has not been implemented.");
                System.out.println("======================================================");
                return;
            }
            List<String> randomList = new ArrayList<>();
            for (int index = 0; index < TEST_COUNT; index++) {
                randomList.add(String.valueOf(index));
            }
            Collections.shuffle(randomList);
            for (int index = 0; index < TEST_COUNT; index++) {
                final int findValue = index;
                assertDoesNotThrow(() -> testResult = fastBST.add(randomList.get(findValue), Integer.parseInt(randomList.get(findValue))), "BST add must not throw");
                assertTrue(testResult, "BST add must return true when add is called and element added to the tree");
            }
            System.out.println(">> Testing BST with " + fastBST.size() + " entries");
            assertEquals(TEST_COUNT, fastBST.size(), () -> "Inserted count must match");
            for (int index = 0; index < TEST_COUNT; index++) {
                final int findValue = index;
                assertDoesNotThrow( () -> testValue = fastBST.find(String.valueOf(findValue)), "BST find must not throw");
                assertEquals(index, testValue, () -> "Inserted and retrieved values must match");
            }
            assertNull(fastBST.find("1999"), () -> "Must return null when element not in tree");
            assertDoesNotThrow( () -> array = fastBST.toSortedArray(), "BST toSortedArray must not throw");
            assertNotNull(array, () -> "Returned array from toSortedArray must not be null");
            assertTrue(isSorted(array), () -> "KeyValueBSearchTree.toSortedArray() does not sort correctly");
            assertEquals(randomList.size(), array.length, () -> "Test array and toSortedArray lengths do not match");
            Object [] originalArray = randomList.toArray();
            Arrays.sort(originalArray);
            for (int index = 0; index < TEST_COUNT; index++) {
                assertNotNull(array[index], "Array from toSortedArray must not contain null elements");
                assertTrue(originalArray[index].equals(array[index].getKey()), () -> "Array elements do not match");
            }
            int elements = fastBST.size();
            assertDoesNotThrow(() -> fastBST.compress(), () -> "Compressing the storage should not throw");
            assertEquals(elements, fastBST.size(), () -> "After compression must have same number of elements than before");

        } catch (Exception e) {
            e.printStackTrace();
            fail("Something went wrong in the test." + e.getMessage());
        }
    }

    @Test
    //@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    @DisplayName("Tests that does not add two identical keys")
    void testAddingIdenticalKeys() {
        dict = new KeyValueBSearchTree<>();
        if (dict.getType() == Type.BST) {
            assertDoesNotThrow(() -> dict.add(new Person("Antti", "Juustila") , new PhoneNumber("999", "888", "7777 7777")), "Adding to BST must not throw");
            assertDoesNotThrow(() -> dict.add(new Person("Antti", "Juustila") , new PhoneNumber("358", "020", "1234 5678")), "Adding to BST must not throw");
            assertEquals(1, dict.size(), "After adding two identical keys, BST size must be one (1)");
            assertDoesNotThrow(() -> foundValue = dict.find(new Person("Antti", "Juustila")));
            assertNotNull(foundValue, "Pair must be in the dictionary in this test");
            assertEquals("358-020-1234 5678", foundValue.toString(), "Last added value should be in BST when adding Pair twice with duplicate keys");
        }
        dict = new KeyValueHashTable<>();
        if (dict.getType() == Type.HASHTABLE) {
            assertDoesNotThrow(() -> dict.add(new Person("Antti", "Juustila") , new PhoneNumber("999", "888", "7777 7777")), "Adding to BST must not throw");
            assertDoesNotThrow(() -> dict.add(new Person("Antti", "Juustila") , new PhoneNumber("358", "020", "1234 5678")), "Adding to BST must not throw");
            assertEquals(1, dict.size(), "After adding two identical keys, BST size must be one (1)");
            assertDoesNotThrow(() -> foundValue = dict.find(new Person("Antti", "Juustila")));
            assertNotNull(foundValue, "Pair must be in the dictionary in this test");
            assertEquals("358-020-1234 5678", foundValue.toString(), "Last added value should be in BST when adding Pair twice with duplicate keys");
        }
    }

    private <T extends Comparable<T>> boolean isSorted(Pair<String, Integer> [] array) {
        for (int i = 0; i < array.length - 1; ++i) {
            if (array[i].getKey().compareTo(array[i + 1].getKey()) >= 0)
                return false;
        }
        return true;
    }
}
