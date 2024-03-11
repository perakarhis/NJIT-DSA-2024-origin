package oy.tol.tra;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Timeout;

import oy.tol.tra.Dictionary.Type;

@DisplayName("Testing Dictionary implementations with test hashable")
public class HashFuncTests {

	@Test
	//@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
	@DisplayName("Tests that BST can handle equal hashes for not equal objects correctly")
	void testBSTWithIdenticalHashDifferentObject() {
		KeyValueBSearchTree<Owner, PhoneNumber> tree = new KeyValueBSearchTree<>();
		if (tree.getType() == Type.BST) {
			assertDoesNotThrow(() -> tree.add(new Owner("antin"), new PhoneNumber("358", "040", "212 4124")), "BST.add must not throw");
			assertEquals(1, tree.size(), "After adding one element to tree, size must be one");
			assertDoesNotThrow(() -> tree.add(new Owner("tinan"), new PhoneNumber("358", "040", "097 2352")), "BST.add must not throw");
			assertEquals(2, tree.size(), "After adding one element to tree, size must be one");
			Pair<Owner,PhoneNumber> array[] = tree.toSortedArray();
			assertEquals(2, array.length, "Array must have two elements");
			assertNotEquals(array[0].getKey(), array[1].getKey(), "Must have different Owners in array");
			assertEquals(array[0].getKey().hashCode(), array[1].getKey().hashCode(), "Owners have the same hash by design");
		}
	}

	@Test
	//@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
	@DisplayName("Tests that BST can handle equal hashes for not equal objects correctly")
	void testHashtableWithIdenticalHashDifferentObject() {
		KeyValueHashTable<Owner, PhoneNumber> tree = new KeyValueHashTable<>();
		if (tree.getType() == Type.HASHTABLE) {
			assertDoesNotThrow(() -> tree.add(new Owner("antin"), new PhoneNumber("358", "040", "212 4124")), "hashtable.add must not throw");
			assertEquals(1, tree.size(), "After adding one element to hashtable, size must be one");
			assertDoesNotThrow(() -> tree.add(new Owner("tinan"), new PhoneNumber("358", "040", "097 2352")), "hashtable.add must not throw");
			assertEquals(2, tree.size(), "After adding one element to hashtable, size must be one");
			Pair<Owner,PhoneNumber> array[] = tree.toSortedArray();
			assertEquals(2, array.length, "Array must have two elements");
			assertNotEquals(array[0].getKey(), array[1].getKey(), "Must have different Owners in array");
			assertEquals(array[0].getKey().hashCode(), array[1].getKey().hashCode(), "Owners have the same hash by design");
		}
	}

}
