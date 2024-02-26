# Queue exercise

Data structures and algorithms 2024.

## The goal

* The goal of this exercise is to implement a Queue data structure.
* The implementation must pass all the tests included in the exercise.
* After your implementation of queue passes the tests, implement the missing code in `DailyTasks.java` to see an applied usage of the queue.
* When done, deliver your project as instructed in the course.
* Time complexity requirements:
  * `capacity()`: O(1).
  * `enqueue()`: O(1) except when reallocation of the internal array happens: O(n).
  * `dequeue()`: O(1).
  * `size():` O(1).
  * `isEmpty():` O(1).
  * `element():`O(1).
  * `toString()`: O(n).

## Prerequisites

You have all the tools installed and working. This was tested in the `00-init` exercise of the course. If you haven't done that yet, do it now.

## Instructions

The UML class diagram below shows an overview of the classes in this exercise. Do not mind the DailyTasks class. You will implement that later in the course.

![Classes in this exercise](classes.png)

You should **implement** the interface `QueueInterface` in a new `QueueImplementation.java` file, which you need to **create** inside the `oy.tol.tra` Java package. 

> Note: the implementation of methods `dequeue()`, `element()`, `size()` and `isEmpty()` must have time complexity of O(1).

Note that Java packages must be located in the corresponding directory. So `QueueImplementation.java` must be located in this project's `src/main/java/oy/tol/tra/` directory!

Remember to **add** the new java file **to git** version control!:

```console
git add src/main/java/oy/tol/tra/QueueImplementation.java 
```
Otherwise it will be missing from your remote repository and thus also from the teachers when they inspect your work!

Implement the queue so that you use `E` template parameter for the `QueueInterface`:

```Java
public class QueueImplementation<E> implements QueueInterface<E> {
```

Make sure to **read** the `QueueInterface` documentation **carefully** so that your implementation follows the interface documentation. Obviously you need to know how queues work in general, so **check out** the course lectures and other material on queue data structures.

If you have trouble getting started writing the code for the `QueueImplementation.java`, check out the previous Stack exercise and do this one in a similar fashion. Use a plain Java Object array as the internal data structure. Implement constructors for the queue, following the example from the Stack exercise.

Make sure you allocate a larger array if there is an attempt to add more elements to the array than it currently fits. 

**Make sure** all overridden methods are tagged with `@Override`:

```Java
@Override
   public int capacity() {
```
As mentioned, you should use the Java array as the internal data structure for holding the elements:

```Java
private Object[] itemArray;
```

And in the `QueueImplementation`, constructor(s) should allocate the array of elements, in addition to other things you need to implement:

```Java
   itemArray = new Object[capacity];
   ...
```
The initial state of the queue should look, for example, like this:

```console
values: 
index:  0 1 2 3 4 5 6 7 8 9
        ^
        head tail -- head and tail are both at index 0
size: 0
capacity: 10
```

**Override** also the `toString()` method inherited from the Java `Object` class.

It returns the queue as a string in the format "[1, 2, 3, 4, 5]", where 1 is the next element to take out from the queue (head), and 5 was the element most recenty entered into the queue (tail). If the queue is empty, returns "[]". 

Note that the internal array may at some point have the elements there like this (an example with integers, array size is 10):

```text
Example with Integer queue, array size is 10:
index:  0   1  2 3 4 5 6 7 8  9
items: [11,12,13,_,_,_,_,8,9,10]
                ^       ^
              tail     head
```
App has added numbers from 1 onwards and removed some numbers (1...7) after which numbers from 8...13 were added. In the example above, this method should return "[8, 9, 10, 11, 12, 13]".

Make sure also to implement **reallocating** a bigger array when enqueueing an element and the array is already full!

This is done in a similar way than in the Stack exercise. Now just take a look at the lecture material and take care to notice that values in the array are not necessarily in indexes 0...tail, but e.g. from 14....7!:

```console
values: 6 1 0 9 9 1 8 2 . .  .  .  .  .  9  2  6 
index:  0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
                        ^                ^      
                        tail             head
```

So you *cannot* just copy the elements from the old table to the new table, leave a gap of empty elements in between, and then some more empty space at the end of the new array. 

So make sure that the new array has the queue elements a) in the same order as before and b) without any empty gaps in between. For example, if the queue array was (capacity initially 10):

```console
values: 6 1 0 9 9 1 8 2 9 2
index:  0 1 2 3 4 5 6 7 8 9
                    ^      
                    tail head (both in index 6; array is full!)
```

The new increased capacity (20) array should be:

```console
values: 8 2 9 2 6 1 0 9 9 1 
index:  0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19
        ^                    ^      
        head                 tail 
```
And then a new element can be added to the array in index `tail`.

After you have implemented your queue class, **instantiate** it in the `QueueFactory.createIntegerQueue()`. After this, you are ready to test your implementation.

## Testing 

**Run the QueueTests tests** to make sure your queue implementation passes the unit tests. From command line, you can execute the tests (in the directory that contains the exercise `pom.xml` file):

```
mvn test
```

If the tests do not pass, you will see errors. Otherwise you will see that the tests succeed. If you have issues, fix your implementation and try again.

When working with this exercise, **do not**:

* Change the `QueueInterface` class in any way.
* Change the unit tests in any way.

Your queue implementation java file is the only file you need to edit in this exercise.

## Delivery

Deliver your updated repository as instructed in the course.

## Questions or problems?

Participate in the course lectures, exercises and online support group.

If you have issues building and running the tests, make sure you have the correct JDK installed, environment variables are as they should and Maven is installed.

## About

* Course material for Tietorakenteet ja algoritmit | Data structures and algorithms 2021.
* Study Program for Information Processing Science, University of Oulu.
* Antti Juustila, INTERACT Research Group.
* Modified for NJIT implementation:
* Pertti Karhapää, M3S Research Unit
