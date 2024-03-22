# 04-phonebook Phonebook task


Data structures and algorithms 2024.

In this task you will implement two fast data structures, hash table and a binary search tree. Tests will read phone books to these data structures. Both of these will implement a `Dictionary` interface.

Several programming languages have a dictionary container (for example, [Swift](https://swiftdoc.org/v2.2/type/dictionary/)), other programing languages call it a "map" (for example [Java](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html)).

> Note that Binary Search Tree, BST, is a *data structure* and related algorithms. It *is not* the same thing as binary search. So do not implement a binary search in this exercise!

Phone book files have names (first name, last name) and phone numbers (country, area code, phone number):

```
Bony Danar Krish,Dürer,372,050,7020307
Hilja Yann Malachai,Magritte,212,045,7020315
Rholmark Richard Eira,Manet,39,040,7020329
Findlay-James Lisandro Callin,van Rijn,380,042,7020369
Cahlum Umair Jace,Thesleff,33,045,7020308
Zaaine Jazeb Stuart,Rivera,39,042,7020325
Loui Jaida Bowie,Dali,372,020,7020393
```

A phone book file can have persons with the same name. Here, you will implement the dictionaries so that the key value (Person) has to be unique in the dictionary. This is obviously not the fact in the Real World. Here we'll make this assumption to keep the task getting too complicated and challenging. So if the phone book test files contain the same person (name) twice, implement the dictionaries so that the **last one added** should be stored in the dictionary.

> Many languages have e.g. MultiMaps, that do allow having same key value twice in a container. We will stick to a simple implementation in this course, having only one unique key per dictionary.

Tests use different size phonebooks to test the correctness of your code. Additionally, `PerformanceTests` fills the phonebooks with different sizes of phonebooks, larger and larger. So then you'll be able to estimate the time complexity class of your implementation in a real computer; in practice, the speed of execution.

Since the phone book files can be large, **consider that** when designing and implementing your algorithms and data structures:

* It will be a good idea to use hash values in calculating indices to array and comparing objects.
* Think about and experiment with different **hash functions** -- which function gives you the best result in avoiding collicions.
* Even though the phone books do not have two persons with a same name, the hash generated from the name can be the same with two different persons. So you need to handle collisions in both implementations.
* With BST do not limit the hash value to a  positive integer with some artificial upper limit value. This is needed only with hash table, not with BST. Here the hash can be whichever possible integer value.
* Avoid doing things that are not necessary. For example, with BST it is not necessary to first search if the person is in the tree, and then add it if it cannot be found. Just add it there. Do not create objects until you know that it is actually necessary, to avoid allocating memory.


## The goal

* The goal of this exercise is to create two implementations of a faster phonebook using both a **hash table** *and* a **binary search *tree*** (BST).
* The implementations must pass all the tests included in the exercise.
* The data structure implementations must be generic.
* When done, deliver your project as instructed in the course.

> Note that this is again a task where you **are not allowed to use** Java container classes (anything that implements `Collection` or `Map` directly or indirectly) or algorithms from classes `Arrays` or `Collections`. All data structures and algoritms *will be implemented by yourself* using plain Java arrays and classes you implement in this course. You obviously may use the basic data types int, Integer, String, etc., and arrays [] of these -- and the code you have implemented previously in this course.

## Useful information about the tests

1. `ContentsTests`, `GenericTests`, `HashFuncTests`, and `PhoneBookTests` check if you have implemented the data structures through a `getType()` method. This method is implemented in KeyValueBSearchTree, KeyValueHash, and KeyValueArray classes. It is set to return `Type.NONE` by default in BST and HashTable and you change it to the correct type (BST or HASHTABLE) **only when you have implemented them**.
2. `PhoneBookTests` also checks if you have implemented the data structure and tests it only if you have done it (`getType()`...) but assumes that you always **execute the whole test class**, not individual test methods. So do not execute test methods separately with this test. This is because this test compares your implementations *always* to the slow `KeyValueArray` data structure. If you execute test methods separately, the test has nothing to compare and the tests fail. Note also that the slow `KeyValueArray` is filled with test data, and this may take time, especially with a slow computer.

It is advisable to execute tests in this order:

1. `GenericTests` since it uses small data sets and is thus fast. You will quickly see if there is something wrong in your implementations.
2. `ContentTests` tests the `Dictionary.add` and `Dictionary.toSortedArray` to see if the implementations have some common issues. The test makes sure that your implementation does not add two or more key-value pairs with *identical* keys to the data structure. Another thing tested is to see that the `toSortedArray` returns all the elements added to the data structure, in the correct sort order.
3. `PhoneBookTests` since it tests only one relatively small data set (phonebook with 12345 entries) and you can use it to compare the speed of the implementations against each other and the slow `KeyValueArray`.
4. `PerformanceTests` you can use to test how your data structures (BST and hash table) can cope with larger and larger data sets. Then you can compare a) which one of your data structures is more efficient and b) how the execution time grows with the size of the data sets. This will also give you data for analysing the time complexity.

## Prerequisites

You have all the tools installed and working. This was tested in the `00-init` exercise  of the course.

You will need the `Algorithms.fastSort` method you implemented in `03-binarysearch` in this exercise. So **copy that file** from the previous exercise (`03-binarysearch`) to the source code directory of this exercise.

## Step 1 - Analysing slow data structure performance

The structure of the exercise is shown below in an UML class diagram.

![UML class diagram](classes.png)

The project includes a class `KeyValueArray`. It implements the `Dictionary` interface and is used by the tests for storing phone numbers and names of persons. A test data file `Phonebook.txt` is included, and the tests read that file of 62 691 person entries into the `KeyValueArray`. The key in the array is the `Person` object and value in the array is the `PhoneNumber` object. Thus we can search for the key and find their phone number, using the dictionary.

**Note** that this implementation uses your algorithms implemented in the `Algorithms` class:

* `KeyValueArray.toSortedArray()` calls your fast sorting algorithm `Algorithms.fastSort()` - you implemented this in exercise task `03-binarysearch`.
* In addition, ther is a `KeyValueArray.compress()` that compress the nulls away from the array, and makes the array smaller to reduce memory consumption. This calls `Algorithms.partitionByRule()` - you will be given this implementatoin to add in the Algorithms.java file since you have not implemented this in any earlier task.

The KeyValueArray does not need the hash value of a Person, so it works even if it the Person is not fully implemented. You can run `ContentsTests`, `GenericTests`, `HashFuncTests`, and `PhoneBookTests` to see that it works. 

**Study** the code in `KeyValueArray` to see how data is added to the array and how linear search is done.

**Note** that the internal array needs to be reallocated if the data does not fit in as new data items are added to the `KeyValueArray`. **Study** how it is done.

**Study also closely**, in addition to the `Dictionary` interface and the implementation of it in the `KeyValueArray` class, also classes `Person`, `PhoneNumber` and `Pair`, since you will need them also in your own implementation!

## Step 2 - Prerequisites for implementing hash table and BST implementations

You need to finish implementation of the `Person` class before you can start working on the data structures. Currently, the `Person` class returns a constant value instead of a proper hash value.

The `hashCode` method need to be implemented. In implementing this methods you **must** use both member variables (last and first names) when comparing `Person` calculating the hash value. A basic rule in Java is that the methods `hashCode`, `compareTo` and `equals` should use the same member variables to determine equalness, identity and comparison.

**Why** are these methods needed? Because:

1. It must be possible to compare `Person` objects to each oter -- are two Person objects the same (`equals`) and how can you sort persons by comparing them (`compareTo`). You saw these methods used in the `KeyValueArray` class.
2. The hash table and BST need to be able to compare the keys in these data structures. Person acts as the key, and PhoneNumber as a value. Comparing integers is much faster than comparing strings, so `hashCode` can be used here: it always produces the same hash from the same name. On the other hand, using `hashCode` alone is not enough, since two persons with different names can have the same hashcode. So you can use the hash for quick comparisons, but when you find the same hashes, make sure the key values are actually identical using `equals`.
3. To be able to use hash tables and implement efficient BST:s, *hashing* is needed to identify objects and calculate indices for objects to be placed in the array.

In the next section, below, the `hashCode` you implement will be used in your hash table to determine an index to the hash table where the object is placed or can be found from.

You will also use the key's (`Person`'s) `hashCode` in the BST implementation to quickly navigate the BST tree.

For the next step, `hashCode` may just return a constant random value, since a good implementation of this method is not needed until in steps 4 and 5.

## Step 3 - Instructions for implementing hash table and BST 

> **NB**: binary search tree needs **new helper classes** . You will see classes that define the tree node, and the tree itself! Note that in BST, you will **not** use arrays. For traversing the tree, ther is the [Visitor](https://en.wikipedia.org/wiki/Visitor_pattern) design pattern. it is used to, for example, pick the elements from the tree into an array and then sort it. **These have been implemented for you**.

You will now implement two faster data structures as *generic classes*, following the example set by the `KeyValueArray` which is also generic.

1. The `KeyValueHashTable` class must implement a *hash table* to hold and search for items.
2. The `KeyValueBSearchTree` class must implement a *binary search tree* to hold and search for items.

> Note that the given skeleton code has the method `getType()` and as long as the method returns `Type.NONE`, the two implementations are not tested. So when you are ready to test these, change the return value to return the correct type (either `HASHTABLE` or `BST`).

So, you can **first** implement one of the two data structures, change the return value of `getType()` to return the enum value of that data structure, and then test it. The other data structure is not tested and you may focus only on the one you work on. Then when you have implemented the other too, change that one's `getType()` return value so the tests will then also test that implementation!

As you implement these as generic classes, they can hold *any* Java objects as long as:

* The *key*, a generic type `K` *implements* the `Comparable` *interface*.
* The *value* `V` to store associated with each key, any Java class with no interface requirements.

The `PhoneBookTests` will treat the already implemented `KeyValueArray` and the two classes you implement, as `Dictionary` types:

```Java 
    static Dictionary<Person, PhoneNumber> slowPhoneBook = null;
    static Dictionary<Person, PhoneNumber> fastHashTablePhoneBook = null;
    static Dictionary<Person, PhoneNumber> fastBSTPhoneBook = null;
...
    slowPhoneBook = new KeyValueArray<>();
    fastHashTablePhoneBook = new KeyValueHashTable<>();
    fastBSTPhoneBook = new KeyValueBSearchTree<>();
```
This is because (as you can see from the class declarations) they *all implement* the *generic* `Dictionary` *interface*. 

Make sure you **do not use** the classes `Person` and `PhoneNumber` in your code when implementing `KeyValueHashTable`and `KeyValueBSearchTree`-- otherwise your implementations are *not generic*, but tied to these two classes. Only use the `K` and `V` template parameters as data object types. 

Look at the `KeyValueArray` as an example -- it **does not mention** `Person` and `PhoneNumber` classes **at all**. It only uses the `compareTo` from `Comparable` interface and `hashCode` and `equals` inherited and overridden from `Object`. These must be implemented by the template parameters `K` and `V`.

Your implementation should meet the following **goals**:

* No crashing, exceptions, index out of bounds errors, lost or duplicate data.
* Searching and adding should not get stuck in a loop running forever.
* All entries from the test file are added to the phonebook.
* The faster data structures are really significantly faster than the slow `KeyValueArray` implementation.

For analysing your solutions, there is code to `getStatus()` for printing out any interesting data from your implementation. For example, when you implement hash table, a counter can calculate how many collisions happened when adding persons to the hash table. When implementing the BST, print out the maximum depth of the tree when elements were added to the tree. **You need to figure out where to use these `status` variables.

An example of what to print:

```console
KeyValueArray reallocated 12 times, each time doubles the size
KeyValueArray fill rate is 76,53%
Hash table fill factor is 0,80
Hash table had 39195 collisions when filling the hash table.
Hash table had to probe 53 times in the worst case.
Hash table had to reallocate 12 times.
Current fill rate is 34,01%
Tree has max depth of 38.
Longest collision chain in a tree node is 1.
```

Tests in `PhoneBookTests` call `getStatus()` so you can then see the output and can evaluate your implementation quality. For example, if you have quite a many collisions, you can adjust the fill factor of your hash table or try to change your hash function so that you would have less collisions when filling the table.

With the BST, you can see that the max dept of the tree branches here was 38. It tells you how many comparisons at worst needs to be done when inserting or searching from the tree. The implementation giving the printout uses linked lists in BST to  handle collisions, so that's why it prints out the length of the longest linked list. Luckily this is only one length list, so the hash code must be good in avoiding collisions. **Everything needed for the linked list is already given to you**. Note also that the **hash table should not use linked list** in this task.

## Testing 

**Run the PhoneBookTests** to make sure your implementation passes the unit tests. From command line, you can execute the tests (in the directory that contains the exercise `pom.xml` file). Or you can run the tests from VS Code. Commands below for running them from command line:

```
mvn -Dtest=PhoneBookTests test
```

If the tests do not pass, you will see errors. Otherwise you will see that the tests succeed. If you have issues, fix your implementation and try again.

To make sure your implementation is really generic, execute also the `GenericTests` test:

```
mvn -Dtest=GenericTests test
```

These tests use `String` as the key `K` to the `Dictionary`, and `Integer` as the value `V`. Your implementation should work with these too, since these classes implement the `Comparable` interface, `hashCode()` and `equals()`! If tests fail, you must revise your implementation.

**Finally**, the instructions for executing `PerformanceTests`.

The amount of test data is so large that those files are not included in this repository. **Download** The test data file `towns.zip` using this link:

[https://moodle.njit.edu.cn/mod/resource/view.php?id=7694](https://moodle.njit.edu.cn/mod/resource/view.php?id=7694)

 **Unzip** `towns.zip` contents and copy to the **root directory of this exercise**, so that tests can find the files. **DO NOT COPY THE PhoneBook.txt FILE!** It contains a different number of names, so some tests will fail if you copy it. **Never ever add these test files to git!!!**

This test creates a file `compare.csv` that has data on executing the test files, e.g. the number of elements (persons) in the dictionary per test file, and the time taken to add the elements to the dictionaries.

Here you can see the `PerformanceTests` names of the test files read by this test, and the number of persons in each test file:

| File           |  Population   |
|----------------|--------------:|
| village.txt    | 100           |
| small-town.txt | 1000          |
| town.txt       | 10 000        |
| large-town.txt | 50 000        |
| city.txt       | 100 000       |
| large-city.txt | 500 000       |
| metropolis.txt | 1 000 000     |
| capital.txt    | 2 000 000     |
| megalopolis.txt| 5 000 000     |


When working with this exercise, **do not**:

* Change the test classes in any way.
* Change the input data file `PhoneBook.txt` in any way.

You only need to change the **`Person.java`**, **`KeyValueHashTable`** and **`KeyValueBSearchTree`** files in this exercise.


## Delivery

**Remember** to add any new code files to git (instructions below).

Open the file created by the `PerformanceTests` -- `compare.csv` in MS Excel, Apple Numbers, Google Sheet, or similar. You may create a graph depicting (scatterplot works well) visually the relationship of execution time to the n (number of elements in the phonebook). **Add** this graph as a picture file to the report and git.

**Evaluate** the correctness and time complexity of your implementation:

* Which kind of **hash functions** did you try out for calculating the hashes? Did some functions produce better hashes than others? E.g. more collisions or less collisions in filling the hash table?
* What **fill factors** did you use when creating and reallocating the arrays? Was the performance better with some fill factors?
* What was the impact of reallocation on performance with different solutions? Was the table finally having very much empty (null) objects and was thus wasting memory, perhaps too much?
* **How deep** did the BST get when filling it with data? Any difference when using different hash functions to generate the hashes (keys for the tree nodes)?
* Which techniques for handling **collision** did you use in both data structures? Did you try out different techniques, and why did you select the one in the implementation?
* When trying out different solutions, did you see significant differences in what was printed out in the `getStatus()` method? What do you print out there, and does it somehow describe the goodness of these implementations?
* Which fast **sorting algorithm** did you implement in `03-binarysearch` and used here? Did you try out if some other sorting algorithm could have performed faster with this data set? Would quicksort be slower or faster than heapsort? Did you have to increase the stack or heap size to make this work?

You may now start working on the additional tasks for improving your grade.

Deliver the exercise as instructed in the course, after your tests pass and your implementation meets the goals. Remember to **double check** that all necessary files from all course tasks have been added to the git and changes have been committed, before pushing the final delivery to your remote repository.

## Questions or problems?

Participate in the course lectures and exercises.

## About

* Course material for Tietorakenteet ja algoritmit | Data structures and algorithms 2022.
* Study Program for Information Processing Science, University of Oulu.
* (c) Antti Juustila, INTERACT Research Group.
* Implementation for NJIT:
* Pertti Karhapää, M3S Research Unit
