package oy.tol.tra;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.fail;

//import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;

//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Timeout;

import oy.tol.tra.Dictionary.Type;

@DisplayName("Testing that the implementations handle the contents (data) correctly.")
public class ContentsTests {

	// Implementations to test:
	static int index = 0;
	static Integer testValue;
	static boolean testResult;
	static PhoneNumber foundValue;
	static final Person [] persons = {
		new Person("A", "A"),
		new Person("Baba", "Betty"),
		new Person("Carl Erik", "Carlsson-Möttönen"),
		new Person("Antti", "Juustila"),
		new Person("Kimba", "Kimberlay"),
		new Person("Pat", "Patterson-Bradley"),
		new Person("Ziba", "Zum")
	};
	static Pair<Person,PhoneNumber> [] array;

	@Test
	//@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
	@DisplayName("Tests that does not add two identical keys")
	void testAddingIdenticalKeysBST() {
		final Dictionary<Person,PhoneNumber> dict = new KeyValueBSearchTree<>();
		if (dict.getType() == Type.BST) {
			assertDoesNotThrow(() -> dict.add(new Person("Antti", "Juustila"), new PhoneNumber("999", "888", "7777 7777")),
					"Adding to BST must not throw");
			assertDoesNotThrow(() -> dict.add(new Person("Antti", "Juustila"), new PhoneNumber("358", "020", "1234 5678")),
					"Adding to BST must not throw");
			assertEquals(1, dict.size(), "After adding two identical keys, BST size must be one (1)");
			assertDoesNotThrow(() -> foundValue = dict.find(new Person("Antti", "Juustila")));
			assertNotNull(foundValue, "Pair must be in the dictionary in this test");
			assertEquals("358-020-1234 5678", foundValue.toString(),
					"Last added value should be in BST when adding Pair twice with duplicate keys");
		}
	}

	@Test
	//@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
	@DisplayName("Tests that does not add two identical keys")
	void testAddingIdenticalKeysHashTable() {
		final Dictionary<Person,PhoneNumber> dict = new KeyValueHashTable<>();
		if (dict.getType() == Type.HASHTABLE) {
			assertDoesNotThrow(() -> dict.add(new Person("Antti", "Juustila"), new PhoneNumber("999", "888", "7777 7777")),
					"Adding to hashtable must not throw");
			assertDoesNotThrow(() -> dict.add(new Person("Antti", "Juustila"), new PhoneNumber("358", "020", "1234 5678")),
					"Adding to hashtable must not throw");
			assertEquals(1, dict.size(), "After adding two identical keys, hashtable size must be one (1)");
			assertDoesNotThrow(() -> foundValue = dict.find(new Person("Antti", "Juustila")));
			assertNotNull(foundValue, "Pair must be in the dictionary in this test");
			assertEquals("358-020-1234 5678", foundValue.toString(),
					"Last added value should be in hashtable when adding Pair twice with duplicate keys");
		}
	}

	@Test
	//@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
	@DisplayName("Tests that BST toSortedArray provides correct data in correct order")
	void testSortOrderBST() {
		final Dictionary<Person,PhoneNumber> dict = new KeyValueBSearchTree<>();
		if (dict.getType() == Type.BST) {
			for (Person person : persons) {
				assertDoesNotThrow(() -> testResult = dict.add(person, new PhoneNumber("123", "123", "1234567")), "Failed to add a test person");
				assertTrue(testResult, "add must return true");
			}
			assertDoesNotThrow( () -> array = dict.toSortedArray(), "toSortedArray must not throw");
			assertNotNull(array, "Array returned from toSortedArray must not be null");
			assertEquals(persons.length, array.length, "Array from toSortedArray does not have correct number of elements");
			for (int index = 0; index < array.length; index++) {
				assertNotNull(array[index], "Array returned from toSortedArray must not contain any nulls");
			}
			String fromTestTarget = personsAsString(array);
			String fromTestData = Arrays.toString(persons);
			System.out.println("From test data:");
			System.out.println(fromTestData);
			System.out.println("From toSortedArray:");
			System.out.println(fromTestTarget);
			assertEquals(Arrays.toString(persons), fromTestTarget, "Sorted array from toSortedArray does not match the test data");
		}
	}

	@Test
	//@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
	@DisplayName("Tests that hashtable toSortedArray provides correct data in correct order")
	void testSortOrderHashtable() {
		final Dictionary<Person,PhoneNumber> dict = new KeyValueHashTable<>();
		if (dict.getType() == Type.HASHTABLE) {
			for (Person person : persons) {
				assertDoesNotThrow(() -> testResult = dict.add(person, new PhoneNumber("123", "123", "1234567")), "Failed to add a test person");
				assertTrue(testResult, "add must return true");
			}
			assertDoesNotThrow( () -> array = dict.toSortedArray(), "toSortedArray must not throw");
			assertNotNull(array, "Array returned from toSortedArray must not be null");
			assertEquals(persons.length, array.length, "Array from toSortedArray does not have correct number of elements");
			for (int index = 0; index < array.length; index++) {
				assertNotNull(array[index], "Array returned from toSortedArray must not contain any nulls");
			}
			String fromTestTarget = personsAsString(array);
			String fromTestData = Arrays.toString(persons);
			System.out.println("From test data:");
			System.out.println(fromTestData);
			System.out.println("From toSortedArray:");
			System.out.println(fromTestTarget);
			assertEquals(Arrays.toString(persons), fromTestTarget, "Sorted array from toSortedArray does not match the test data");
		}
	}

	private String personsAsString(Pair<Person,PhoneNumber> [] array) {
		Person fromArray[] = new Person[array.length];
		for (int index = 0; index < array.length; index++) {
			fromArray[index] = array[index].getKey();
		}
		return Arrays.toString(fromArray);
	}

}
