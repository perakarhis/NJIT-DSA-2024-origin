Addition: Sometimes I use IDEA instead of vscode while testing, so it may be some other unexpected situations in vscode if you test my work. However, all the homework I submitted are running well in IDEA. If there are some problems I made in my codes, please send me an email so that I can improve them in time.

1.The hashCode() method plays an important role in the hash table, hashing as much as  possible by multiplying string elements with prime numbers.
  To resolve the conflict, I used the square probing method, each time increasing the step size by +1, +4, +9... Until the insertion position is found. When space is      insufficient, hashing is used to create larger hash tables.

  After reading all the elements, I compress the space and discard the empty elements, converting the words into an array filled with WordCount. Finally, I used           Quicksort to sort the array to get the desired result.

2.To obtain the top 100,I used the Quick sorting algorithm and Data compression algorithm, which time complexity are O(n*log(n)) and O(n^2) respectively. Moreover, the    time complexity of adding elements to the hash table may be a little more than O(1) since the hash conflicts are difficult to avoid.

3.The implementation is considered correct as it has passed all the tests and the output is rational. So far, there have been probably not any issues,bugs or problems .   The ideas of the solution are to read more material and do more experiments on it.

4.Time Complexity: The time complexity of insertion operations depends on the balance of the tree and is O(log n) in the worst case, where n is the number of nodes.

5. I found the hardest part of this task to be finding the suitable algorithms to prevent the program from timeout.

6.By writing this exercise, I learned a lot about utilizing algorithms and how to tune and improve algorithms in my codes.
